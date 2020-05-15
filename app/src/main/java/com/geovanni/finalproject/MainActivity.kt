package com.geovanni.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.tv_login_Email
import kotlinx.android.synthetic.main.login.tv_login_password

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance()

        Login.setOnClickListener {
            Login()
            //finish()
        }

        SignUp.setOnClickListener {
            startActivity(Intent(this, signup::class.java))
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

    }

    fun launchTwitterActivity(view: View){
        val myIntent = Intent(this, TwitterActivity::class.java)
        startActivity(myIntent)
    }

    fun SignUp(){
        startActivity(Intent(this, signup::class.java))

    }



    fun Login() {

        if (tv_login_Email.text.toString().isNullOrEmpty() or tv_login_password.text.toString().isNullOrBlank()){
            Toast.makeText(this, "Please enter your correct credentials", Toast.LENGTH_SHORT)
                .show()
            return
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(tv_login_Email.text.toString()).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.signInWithEmailAndPassword(tv_login_Email.text.toString(), tv_login_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, SelectionActivity::class.java))
                    val user = auth.currentUser


                } else {

                }
            }
        tv_login_Email.text.clear()
        tv_login_password.text.clear()

    }



}
