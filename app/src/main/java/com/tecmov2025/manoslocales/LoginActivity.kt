package com.tecmov2025.manoslocales

import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Nav(navController)
        }
    }

    @Composable
    fun Nav(navController: NavHostController)
    {

        NavHost(
            navController = navController,
            startDestination = "SplashScreen")
        {
            val registerScreen = RegisterScreen()
            composable("Login") { Login(navController) }
            composable("SplashScreen") { SplashScreen(navController) }
            composable ("RegisterScreen"){ registerScreen.RegisterForm() }
        }
    }

    @Composable
    fun SplashScreen(navController: NavHostController)
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ){
          Text("Manos Locales")
        }
        LaunchedEffect(Unit)
        {
            delay(500)
            navController.navigate("Login")
        }
    }

    @Composable
    fun Login(navController: NavHostController)
    {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment
                .CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(20.dp, alignment = Alignment.CenterVertically)
        ) {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Image (
                modifier = Modifier
                    .size(150.dp),
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit
            )
            CustomTextField(
                username,
                { username = it },
                "Usuario")

            CustomTextField(
                password,
                { password = it },
                "Contraseña",
                true)

            Button(
                modifier = Modifier
                    .height(48.dp)
                    .width(324.dp),
                onClick =
                    {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }) {
                Text("Iniciar sesión")
            }

            LinkText("No tenes cuenta? - Registrate", { navController.navigate("RegisterScreen") })
            LinkText("Recuperar contraseña",{})

        }

    }

    /**
     * Campo de texto personalizado que puede ocultar la entrada (para contraseñas).
     *
     * @param value El valor del campo de texto.
     * @param onValueChange Función para manejar el cambio de valor.
     * @param label El texto de la etiqueta.
     * @param isPassword Indica si el campo debe ocultar la entrada como una contraseña. Por defecto es `false`.
     */
    @Composable
    fun CustomTextField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,
        isPassword: Boolean = false
    )
    {
        TextField (
            modifier = Modifier
                .width(324.dp)
                .height(48.dp),
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (isPassword)  KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
        )

    }

    @Composable
    fun LinkText(text: String,
                 onClick: ()-> Unit
    )
    {
        Text(
            modifier = Modifier
                .clickable{onClick()},
            text = text,
        )
    }

}
