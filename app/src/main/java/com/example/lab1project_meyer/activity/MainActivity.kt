package com.example.lab1project_meyer.activity

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import com.example.lab1project_meyer.R
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.ListView

//this class is an entry in our calorie counter arraylist
class Entry(fooood: String, cal: String)
{
    var Name: String = fooood;
    var Number: String = cal;
}


class MainActivity : AppCompatActivity() {

    //some globals to keep track of
    var FoodAndCalorieList: ArrayList<Entry> = arrayListOf()
    var DailyCaloriesAllowed = 0;
    var CaloriesRemaining = 0;
    var totalCaloriesConsumed = 0;

    private lateinit var listView: ListView

    //function to run to update values
    fun updateCaloriesRemaining(view: View) {
        totalCaloriesConsumed = 0;
        for (i in FoodAndCalorieList.indices)
        {
            //some quick easy math
            totalCaloriesConsumed = totalCaloriesConsumed + FoodAndCalorieList[i].Number.toInt()

        }
        //some quick easy math
        CaloriesRemaining = DailyCaloriesAllowed - totalCaloriesConsumed;

        //fixing label
        CaloriesRemainingNumberText.text = "${CaloriesRemaining}"
        CaloriesConsumed.text = "Calories Consumed: ${totalCaloriesConsumed}"



        //color fix as instructed
        if (CaloriesRemaining < 0)
        {
            CaloriesRemainingNumberText.setTextColor(Color.RED);
        }
        else
        {
            CaloriesRemainingNumberText.setTextColor(Color.parseColor("#808080"));
        }
    }

    //when data is received from activity
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //places entry into our arraylist
                val strEditText = data!!.getStringExtra("FoodName")
                val strEditText2 = data!!.getStringExtra("Calorie")

                FoodAndCalorieList.add(Entry(strEditText, strEditText2))

            }
            updateCaloriesRemaining(View(this));

        }



        //https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin

        listView = findViewById<ListView>(R.id.recipe_list_view)

        val recipeList = FoodAndCalorieList

        val adapter = CalorieAdapter(this, recipeList)
        listView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TotalCalories(View(this));

    }

//https://appsandbiscuits.com/moving-between-activities-with-intents-android-8-697b3b70fdfd
    //Sends to addfood
    fun onButtonClick(v: View) {
        val myIntent = Intent(this, AddFoodActivity::class.java)
        startActivityForResult(myIntent, 1)
    }

    //sends to addworkout
    fun addWorkout(v: View) {
        val myIntent = Intent(this, AddWorkoutActivity::class.java)
        startActivityForResult(myIntent, 1)
    }

    //resetseverything
    fun resetClick(v: View) {

        FoodAndCalorieList.clear();
        //clear list view if you can
        DailyCaloriesAllowed = 0;
        CaloriesRemaining = 0;
        updateCaloriesRemaining(View(this));
        TotalCalories(View(this));

    }

    //deletes the last entry added
    fun deleteLast(v: View) {

        if (FoodAndCalorieList.size>0)
        {
            FoodAndCalorieList.removeAt(FoodAndCalorieList.size-1);
            //clear list view if you can
            updateCaloriesRemaining(View(this));

            listView = findViewById<ListView>(R.id.recipe_list_view)

            val recipeList = FoodAndCalorieList

            val adapter = CalorieAdapter(this, recipeList)
            listView.adapter = adapter
        }

    }


//http://www.prandroid.com/2017/09/alert-dialog-box-in-android-using-kotlin.html
    //This is the function that runs in onCreate that does a dialogalert. It is mandatory and filters input
    //makes sure its a number, makes sure its a certain length. makes submit button disabled if failed
    //disables click outside of dialog
    fun TotalCalories(view: View)
    {
        val alert = AlertDialog.Builder(this)
        var totalCaloriesNumber: EditText?=null

        with (alert)
        {
            setTitle("Daily Calories: (2000 Recommended)")
            totalCaloriesNumber = EditText(context)
            totalCaloriesNumber!!.inputType = InputType.TYPE_CLASS_NUMBER

            setPositiveButton("Submit")
            {

                    dialog, whichButton ->

                dialog.dismiss()
                var calorienumber = totalCaloriesNumber!!.text.toString()
                CaloriesRemainingNumberText.text = "${calorienumber}"

                CaloriesConsumed.text = "Calories Consumed : ${totalCaloriesConsumed}"
                DailyCaloriesAllowed = calorienumber.toInt()
                CaloriesRemaining = DailyCaloriesAllowed

            }
        }

        val dialog = alert.create()
        dialog.setView(totalCaloriesNumber)
        dialog.show()
        dialog.setCanceledOnTouchOutside(false);

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false)

//https://stackoverflow.com/questions/40569436/kotlin-addtextchangelistener-lambda
        totalCaloriesNumber?.addTextChangedListener(object: TextWatcher
        {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString() != "" && p0.toString().length<9) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true)
                } else
                {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }
}
