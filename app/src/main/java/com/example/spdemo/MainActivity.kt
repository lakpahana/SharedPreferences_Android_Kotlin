package com.example.spdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var nameText:EditText
    private lateinit var  ageText:EditText
    //Because we need to access from other functions.
    //We must define object type when using lateint keyword to declare variables
    private lateinit var sf:SharedPreferences
    private  lateinit var editor:SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameText = findViewById(R.id.etName)
        ageText = findViewById(R.id.etAge)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()


    }

    override fun onPause() {
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        editor.apply{
            putString("sf_name",name)
            putInt("sf_age",age)
            commit() //remember to do this step
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name",null)
        val age = sf.getInt("sf_age",0)
        nameText.setText(name)

        if(age!=0) {
            ageText.setText(age.toString())
        }
    }

}