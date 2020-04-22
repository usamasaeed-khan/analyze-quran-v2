package com.example.analyzequranupdated

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_arabic_dictionary.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArabicDictionaryActivity : AppCompatActivity() {

    private lateinit var searchView: MaterialSearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arabic_dictionary)


        val progressBar:ProgressBar = findViewById(R.id.progress_bar_dict)

        progressBar.visibility = View.VISIBLE





        val layoutManager = RtlGridLayoutManager(this,4, GridLayoutManager.VERTICAL,false)


        recycler_view_alphabets.layoutManager = layoutManager

        var categoryList: ArrayList<String>


        RetrofitInstance.instance.GetAllAlphabets()
            .enqueue(object: Callback<ArrayList<String>> {
                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@ArabicDictionaryActivity,t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ArrayList<String>>,
                    response: Response<ArrayList<String>>
                ) {
                    progressBar.visibility = View.GONE
                    if(response.isSuccessful){
                        Toast.makeText(this@ArabicDictionaryActivity,response.message(), Toast.LENGTH_LONG).show()
                        categoryList = response.body()!!
                        val adapter = CategoriesAdapter(this@ArabicDictionaryActivity,categoryList)
                        recycler_view_alphabets.adapter = adapter
                    }
                }

            }
        )


        startSuggestions()















        val toolbar: Toolbar = findViewById(R.id.actionbar_search)
        setSupportActionBar(toolbar)
        toolbar.title = "Arabic Dictionary"






    }

    private fun startSuggestions(){


        searchView = findViewById(R.id.search_view)
        val db = DatabaseHelper(this)
        searchView.setSuggestions(db.getSuggestions().toTypedArray())
        searchView.setEllipsize(true)
        searchView.layoutDirection = View.LAYOUT_DIRECTION_RTL


        searchView.setOnItemClickListener { adapter, _, position, _ ->

            val query = adapter.getItemAtPosition(position)
            val intent = Intent(this@ArabicDictionaryActivity,MeaningsActivity::class.java)
            intent.putExtra("word",query.toString())
            startActivity(intent)
            Toast.makeText(this,query.toString(),Toast.LENGTH_LONG).show()

        }



        searchView.setOnQueryTextListener(object: MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@ArabicDictionaryActivity,MeaningsActivity::class.java)
                intent.putExtra("word",query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


        searchView.setOnSearchViewListener(object: MaterialSearchView.SearchViewListener{
            override fun onSearchViewClosed() {

            }

            override fun onSearchViewShown() {

            }

        })
    }













    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.search_item -> {
                Toast.makeText(this,"Yo Bro!",Toast.LENGTH_LONG).show()
            }
        }
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val item:MenuItem = menu!!.findItem(R.id.search_item)
        searchView.setMenuItem(item)
        return true
    }

    override fun onBackPressed() {

        if(searchView.isSearchOpen) searchView.closeSearch()
        else super.onBackPressed()
    }

}
