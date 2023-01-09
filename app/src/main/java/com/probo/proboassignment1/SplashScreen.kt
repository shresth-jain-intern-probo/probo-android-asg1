package com.probo.proboassignment1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    companion object{
        const val SHARED_PREF_NAME = "myPreferences"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        //Normal Handler is deprecated , so we have to change the code little bit

        // Handler().postDelayed({
        Handler().postDelayed({
            val mySharedPreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
            val email = mySharedPreference.getString("Email", null)
            val dob = mySharedPreference.getString("DOB", null)
            val password = mySharedPreference.getString("Password", null)
            val imageURI = mySharedPreference.getString("imageURI", null)

            if(email==null || dob==null || password==null)
                startActivity(Intent(this, SignUpActivity::class.java))
            else {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)

                val bundle = Bundle()
                bundle.putString("email", email)
                bundle.putString("dob", dob)
                bundle.putString("password", password)
                bundle.putString("imageURI", imageURI)

                intent.putExtras(bundle)
                startActivity(intent)
            }
            finish()
        }, 2000)
    }
}