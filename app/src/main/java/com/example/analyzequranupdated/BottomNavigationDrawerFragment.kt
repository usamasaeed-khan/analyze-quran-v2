package com.example.analyzequranupdated

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_bottomsheet.*

class BottomNavigationDrawerFragment:BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.home -> Toast.makeText(context,"Home", Toast.LENGTH_LONG).show()


                R.id.donate -> startActivity(Intent(context,DonationsActivity::class.java))


                R.id.about -> Toast.makeText(context,"About", Toast.LENGTH_LONG).show()


                R.id.dictionary_arabic -> startActivity(Intent(context,ArabicDictionaryActivity::class.java))


            }
            dismiss()
            true


        }
    }


}