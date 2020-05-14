package com.geovanni.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
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
    }

    fun signUp(){
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

        auth.createUserWithEmailAndPassword(Email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,MainActivity::class.java))

                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

}
