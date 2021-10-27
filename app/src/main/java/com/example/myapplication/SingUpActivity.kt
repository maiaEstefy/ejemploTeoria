package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {


    lateinit var nuevoUsuario: TextView
    lateinit var bienvenidoLabel: TextView
    lateinit var continuarLabel: TextView
    lateinit var usuarioSingUpTextField: TextInputLayout
    lateinit var contrasenaTextField: TextInputLayout
    lateinit var inicioSesion: MaterialButton
    lateinit var singUpImageView: ImageView
    lateinit var emailEditText: TextInputEditText
    lateinit var passEditText: TextInputEditText
    lateinit var confirPassEditText: TextInputEditText

    //Firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        singUpImageView = findViewById(R.id.singUpAmageView)
        bienvenidoLabel = findViewById(R.id.bienvenidoLabel)
        continuarLabel = findViewById(R.id.continualLabel)
        usuarioSingUpTextField = findViewById(R.id.nameTextField)
        contrasenaTextField = findViewById(R.id.contrasenaTextField)
        inicioSesion = findViewById(R.id.inicioSesion)
        nuevoUsuario = findViewById(R.id.nuevoUsuarioTiene)
        emailEditText = findViewById(R.id.emailEditText)
        passEditText = findViewById(R.id.contrasenaEditText)
        confirPassEditText = findViewById(R.id.confirmarContrasenaEditText)

        inicioSesion.setOnClickListener({
            validate()
        })

        nuevoUsuario.setOnClickListener({
        })
        //inicializamos firebase auth
        auth = Firebase.auth
    }

    /*
    * Validamos los campos que sean correctos
    * */
    fun validate(){
        //trim quita espacios no deseados
        var email: String = emailEditText.text.toString().trim()
        var pass: String = passEditText.text.toString().trim()
        var confirmPass: String = confirPassEditText.text.toString().trim()

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Correo I<nvalido")
            return
        }
        else{
            emailEditText.setError(null)
        }
        if(pass.isEmpty() || pass.length <= 0){
            passEditText.setError("Se necesitan más de 8 caracteres")
            return
        }
        else if(!Pattern.compile("[0-9]]").matcher(pass).find()){
            passEditText.setError("Al menos debe tener un número")
        }else{
            passEditText.setError(null)
        }
        if(!confirmPass.equals(pass)){
            confirPassEditText.setError("Las contraseñas no coinciden")
            return
        }else{
            registrar(email, pass)
        }

    }

    /*
    *
    *Registramos al nuevo usuarios
    * definimos como parametros el email y la password
    * */
    fun registrar(email: String, pass:String){
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener({
                if(it.isSuccessful){
                    volverLogin(it.result?.user?.email ?: " ", ProviderType.BASIC)
                }
                else{
                    showAlert()
                }
            })
    }
    //351322756 Brian

    fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun volverLogin(email:String?, provider: ProviderType?){
        intent = Intent(this, LoginActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider?.name)
        }
        startActivity(intent)
    }
}