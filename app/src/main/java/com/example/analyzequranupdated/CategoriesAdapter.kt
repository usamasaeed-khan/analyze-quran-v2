package com.example.analyzequranupdated

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view_item.view.*

class CategoriesAdapter(private val context:Context, private val categories:ArrayList<String>) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.card_view_item,parent,false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.setData(category)

        when(context){
            is WordsActivity -> {
                if(position % 2 == 1){
                    holder.itemView.setBackgroundColor(Color.GRAY)
                }
                holder.itemView.setOnClickListener {
                    val intent = Intent(context,EntryActivity::class.java)
                    intent.putExtra("root",category)
                    context.startActivity(intent)
                }
            }
            is ArabicDictionaryActivity -> {
                holder.itemView.setOnClickListener {
                    val intent = Intent(context,WordsActivity::class.java)
                    intent.putExtra("root",category)
                    context.startActivity(intent)
                }
            }
            is EntryActivity -> {
                if(position % 2 == 1){
                    holder.itemView.setBackgroundColor(Color.GRAY)
                }
                holder.itemView.setOnClickListener {
                    val intent = Intent(context,MeaningsActivity::class.java)
                    intent.putExtra("root",category)
                    context.startActivity(intent)
                }
            }
        }
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun setData(category:String){
            itemView.category_text.text = category
        }
    }

}