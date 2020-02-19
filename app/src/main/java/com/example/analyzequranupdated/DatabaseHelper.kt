package com.example.analyzequranupdated

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseHelper(context:Context): SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "offline_lexicon.db"
        const val TABLE_NAME = "lexicon_words"
        const val WORD_COLUMN = "word"
        const val ROOT_COLUMN = "root"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

    }

    fun getSuggestionWords(root:String):ArrayList<String>{

        val suggestionList = ArrayList<String>()

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.

        val projection = arrayOf(WORD_COLUMN)



        val db = this.readableDatabase



        if(db.isOpen){

            val cursor = db.query(
                true,
                TABLE_NAME,                 // The table name.
                projection,                 // The array of columns to return (pass null to get all)
                "$ROOT_COLUMN LIKE ?",              // The columns for the WHERE clause
                arrayOf(root),          // The values for the WHERE clause
                null,               // don't group the rows
                null,                 // don't filter by row groups
                null,
                null// The sort order
            )

            with(cursor){
                while(moveToNext()){

                    suggestionList.add(getString(0))

                }

                close()

            }


        }

        db.close()
        return suggestionList

    }
}