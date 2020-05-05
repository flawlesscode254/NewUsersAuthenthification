package com.example.foodsheriffsos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val regBtn = findViewById<View>(R.id.mBtnReg) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        regBtn.setOnClickListener(View.OnClickListener {
                view -> register ()
        })

    }

    private fun register(){
        val emailTxt = findViewById<View>(R.id.mEdtMailReg) as EditText
        val passwordTxt = findViewById<View>(R.id.mEdtPassReg) as EditText
        val nameTxt = findViewById<View>(R.id.mEdtNameReg) as EditText

        var email = mEdtMailReg.text.toString()
        var password = mEdtPassReg.text.toString()
        var name = mEdtNameReg.text.toString()

        if (!name.isEmpty() or !password.isEmpty() or !email.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Names").setValue(name)
                    Toast.makeText(this,"Successfully Signed In", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this,"Please enter all the credentials", Toast.LENGTH_LONG).show()
        }
    }
}
