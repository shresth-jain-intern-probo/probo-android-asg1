package com.probo.proboassignment1

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnSignUp = findViewById<AppCompatButton>(R.id.sign_up_btn)
        btnSignUp.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View?) {
                // Code here executes on main thread after user presses button
            }
        })
        btnSignUp.setOnClickListener {
            validateInput()
        }
    }

    private fun validateInput() {
        val emailInp = findViewById<AppCompatEditText>(R.id.email_inp).text.toString().trim()
        val passwordInp = findViewById<AppCompatEditText>(R.id.pass_inp).text.toString()
        val confPasswordInp = findViewById<AppCompatEditText>(R.id.conf_pass_inp).text.toString()


        if(emailInp.isNotEmpty()) {
            if(emailInp.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())) {
                if(passwordInp.isNotEmpty() && confPasswordInp.isNotEmpty()) {
                    if(passwordInp == confPasswordInp) {
                        if(passwordInp.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$".toRegex())) {
                            val intent = Intent(this@SignUpActivity, MainActivity::class.java)

                            val bundle = Bundle()
                            bundle.putString("email", emailInp)
                            bundle.putString("dob", etSelectedDate?.text.toString())
                            bundle.putString("password", passwordInp)

                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                        else showSnackBar("Password must contain 8 letters(a capital and a small alphabet, a digit & one special symbol)")
                    }
                    else showSnackBar("Passwords Doesn't Match")
                }
                else showSnackBar("Passwords Field Can't Be Empty")
            }
            else showSnackBar("Invalid Email Format")
        }
        else showSnackBar("Email Field Can't Be Empty")
    }

    private fun showSnackBar(info: String) {
        val snackBar = Snackbar.make(findViewById(R.id.content_main),
            info,
            Snackbar.LENGTH_SHORT)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(
                this@SignUpActivity,
                R.color.snackbar_error
            )
        )
        snackBar.show()
    }}