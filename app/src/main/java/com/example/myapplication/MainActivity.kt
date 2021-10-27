package com.example.myapplication


import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair as UtilPair


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        //Agregar animaciones
        var animacion1: Animation = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba)

        var animacion2: Animation = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo)

        var deTextView = findViewById<TextView>(R.id.textDe)
        var logoTextView = findViewById<TextView>(R.id.textIts)

        var logo = findViewById<ImageView>(R.id.logoImg)

        deTextView.animation = animacion2
        logoTextView.animation = animacion2
        logo.animation = animacion1

        Handler(Looper.getMainLooper()).postDelayed({
            var intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        },3000)
    }
}

