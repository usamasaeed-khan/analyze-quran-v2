package com.example.analyzequranupdated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_entry.*
import kotlinx.android.synthetic.main.activity_words.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)





        val intent = intent

        val root:String? = intent.getStringExtra("root")



        val  layoutManagerWords = LinearLayoutManager(this)

        recycler_view_entries.layoutManager = layoutManagerWords

        val wordsList:ArrayList<String>

        val db = DatabaseHelper(this)


        wordsList = db.getSuggestionWords(root!!)


        val adapter = CategoriesAdapter(this,wordsList)
        recycler_view_entries.adapter = adapter



//        RetrofitInstance.instance.GetWordsFromEntry(root!!)
//            .enqueue(object: Callback<ArrayList<String>> {
//                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
//                    Toast.makeText(this@EntryActivity,t.message, Toast.LENGTH_LONG).show()
//                }
//                override fun onResponse(
//                    call: Call<ArrayList<String>>,
//                    response: Response<ArrayList<String>>
//                ) {
//                    if(response.isSuccessful){
//                        Toast.makeText(this@EntryActivity,response.message(), Toast.LENGTH_LONG).show()
//                        wordsList = response.body()!!
//                        val adapter = CategoriesAdapter(this@EntryActivity,wordsList)
//                        recycler_view_entries.adapter = adapter
//                    }
//                }
//            }
//        )
    }
}
