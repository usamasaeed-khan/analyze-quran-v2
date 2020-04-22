package com.example.analyzequranupdated

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_meanings.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MeaningsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meanings)

        val progressBar:ProgressBar = findViewById(R.id.progress_bar_meanings)
        progressBar.visibility = View.VISIBLE


        val intent = intent
        val word = intent.getStringExtra("word")

        //Toast.makeText(this,word,Toast.LENGTH_LONG).show()


        word_heading.text = word

        RetrofitInstance.instance.GetMeaningFromWord(word)
            .enqueue(object: Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@MeaningsActivity,t.message, Toast.LENGTH_LONG).show()
                    meaning_text.text = "Can't connect to network, try again!"
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    progressBar.visibility = View.GONE
                    if(response.isSuccessful){
                        Toast.makeText(this@MeaningsActivity,response.message(), Toast.LENGTH_LONG).show()

                        val meaning = response.body()!!.replace("<[^>]+>".toRegex(), "").trim { it <= ' ' }
                            .replace(" +".toRegex(), " ").replace("\n".toRegex(), "")

                        meaning_text.text = "Meaning: $meaning"
                    }
                    else meaning_text.text = "Can't connect to network,try again!"
                }

            })

    }
}
