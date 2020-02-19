package com.example.analyzequranupdated

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_arabic_dictionary.*
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArabicDictionaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arabic_dictionary)










        val layoutManager = RtlGridLayoutManager(this,4, GridLayoutManager.VERTICAL,false)


        recycler_view_alphabets.layoutManager = layoutManager

        var categoryList: ArrayList<String>


        RetrofitInstance.instance.GetAllAlphabets()
            .enqueue(object: Callback<ArrayList<String>> {
                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    Toast.makeText(this@ArabicDictionaryActivity,t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<ArrayList<String>>,
                    response: Response<ArrayList<String>>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@ArabicDictionaryActivity,response.message(), Toast.LENGTH_LONG).show()
                        categoryList = response.body()!!
                        val adapter = CategoriesAdapter(this@ArabicDictionaryActivity,categoryList)
                        recycler_view_alphabets.adapter = adapter
                    }
                }

            }
        )
    }















    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }
}
