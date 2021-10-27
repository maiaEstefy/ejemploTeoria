package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var registrar: TextView
        registrar = findViewById(R.id.nuevoUsuario)

        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val pass: String? = bundle?.getString("pass")
        registrar.setOnClickListener({
            var intent= Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        })

        /*
        * FirebaseAuth.getInstance().
        *
        * */

    }
}