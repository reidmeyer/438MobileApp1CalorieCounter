package com.example.lab1project_meyer.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab1project_meyer.R
import kotlinx.android.synthetic.main.addworkout.*

//Similar to food activity. Deals with adding workouts
class AddWorkoutActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //fade in
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.addworkout)
    }

    override fun onPause() {
        super.onPause()
        //fade out
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)

    }

    //function alled on submit button
    fun submit1(v: View)
    {

        if (AddWorkoutName.text.toString() != "" && AddCalorie1.text.toString() !="" && AddCalorie1.text.length<8  )
        {
            val intent = Intent()
            //send data back
            intent.putExtra("FoodName", AddWorkoutName.text.toString())
            intent.putExtra("Calorie",  "-" + AddCalorie1.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
        else
        {
            println("you didn't enter in values")
        }


    }

}
