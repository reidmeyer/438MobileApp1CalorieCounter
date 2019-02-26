package com.example.lab1project_meyer.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab1project_meyer.R
import kotlinx.android.synthetic.main.addfood.*


//This activity deals with adding food
class AddFoodActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //fade on enter
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.addfood)
    }

    override fun onPause() {
        super.onPause()
        //fade on leave
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)

    }

    //function called on submit button
    fun submit(v: View)
    {

        //checks if inputs are not empty and calorie length is in check to protect overflow
        if (AddFoodName.text.toString() != "" && AddCalorie.text.toString() !="" && AddCalorie.text.length<8 )
        {
            val intent = Intent()
            //send data back
            intent.putExtra("FoodName", AddFoodName.text.toString())
            intent.putExtra("Calorie", AddCalorie.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
        else
        {
            //debug stuff. could put alert here
            println("you didn't enter in proper values")
        }


    }

}
