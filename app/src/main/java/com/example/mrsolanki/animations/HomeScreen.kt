package com.example.mrsolanki.animations

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.util.Pair
import android.view.View
import android.widget.Button
import android.util.Pair as UtilPair


import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        var myProfileButton = findViewById<Button>(R.id.myProfile)
        myProfileButton.setOnClickListener {  }
        myProfileButton.setOnClickListener{
            openProfileScreen()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home_screen, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openProfileScreen()
    {
        var profileScreen = Intent(this,ProfileScreen::class.java)

        var userImageView = findViewById<ImageView>(R.id.userImageView)
        var myProfileButton = findViewById<Button>(R.id.myProfile)

        var userImagePair = Pair<View,String>(userImageView,"userImageTrans")
        var myProfileButtonPair = Pair<View,String>(myProfileButton,"myProfileButtonTrans")


        val pairs = ActivityOptions.makeSceneTransitionAnimation(this,
                userImagePair,myProfileButtonPair
        )

        startActivity(profileScreen, pairs.toBundle())
    }
}
