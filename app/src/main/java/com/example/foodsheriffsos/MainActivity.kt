package com.example.foodsheriffsos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnGo.setOnClickListener {
            intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
        val loginBtn = findViewById<View>(R.id.mBtnSave) as Button

        loginBtn.setOnClickListener(View.OnClickListener {
                view -> login()
        })

        mBtnGo.setOnClickListener(View.OnClickListener {
                view -> register ()
        })
    }
    private fun login (){
        val emailTxt = findViewById<View>(R.id.mEdtMail) as EditText
        val passwordTxt = findViewById<View>(R.id.mEdtPass) as EditText

        var mail = mEdtMail.text.toString()
        var password = mEdtPass.text.toString()

        if (!mail.isEmpty() or !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"Successfully logged in", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this,"Please fill all the credentials", Toast.LENGTH_LONG).show()
        }
    }
    private fun register (){
        startActivity(Intent(this,Main2Activity::class.java))
    }
}