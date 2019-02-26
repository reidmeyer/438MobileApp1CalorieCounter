package com.example.lab1project_meyer.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.lab1project_meyer.R


//This is an adapter class to be used with arraylist, Entry Object, and listView
//The base code is from https://stackoverflow.com/questions/40569436/kotlin-addtextchangelistener-lambda
class CalorieAdapter(private val context: Context,
                     private val dataSource: ArrayList<Entry>) : BaseAdapter()
{


    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_recipe, p2, false)


        // Get title element
        val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView

        // Get detail element
        val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView



        val recipe = getItem(p0) as Entry


        titleTextView.text = recipe.Name
        detailTextView.text = recipe.Number


        return rowView    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

}