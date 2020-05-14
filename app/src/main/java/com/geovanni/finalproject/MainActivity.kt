package com.geovanni.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.Email
import kotlinx.android.synthetic.main.login.password

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

    }

    fun SignUp(){
        startActivity(Intent(this, signup::class.java))

    }



    fun Login() {

        if (Email.text.toString().isNullOrEmpty() or password.text.toString().isNullOrBlank()){
            Toast.makeText(this, "Please enter your correct credentials", Toast.LENGTH_SHORT)
                .show()
            return
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.signInWithEmailAndPassword(Email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, SelectionActivity::class.java))
                    val user = auth.currentUser


                } else {

                }
            }
        Email.text.clear()
        password.text.clear()

    }



}
