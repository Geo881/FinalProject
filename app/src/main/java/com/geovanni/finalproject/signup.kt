package com.geovanni.finalproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.sign_up.*


class signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        auth = FirebaseAuth.getInstance()

            signUp.setOnClickListener {
                signUp()
            }

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
    }

    fun signUp(){
        if (tv_login_Email.text.toString().isNullOrEmpty() or tv_login_password.text.toString().isNullOrBlank()){
            Toast.makeText(this, "Please enter your correct credentials", Toast.LENGTH_SHORT)
                .show()
            tv_login_Email.hideKeyboard()
            tv_login_password.hideKeyboard()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(tv_login_Email.text.toString()).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(tv_login_Email.text.toString(), tv_login_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,MainActivity::class.java))

                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun launchTwitterActivity(view: View){
        val myIntent = Intent(this, TwitterActivity::class.java)
        startActivity(myIntent)

    }

    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }



}
