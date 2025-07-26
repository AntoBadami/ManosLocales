package com.tecmov2025.manoslocales.ActivityLogin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecmov2025.manoslocales.Utils.CustomButton
import com.tecmov2025.manoslocales.Utils.CustomTextField
import com.tecmov2025.manoslocales.Utils.LinkText
import com.tecmov2025.manoslocales.ActivityHome.MainActivity
import com.tecmov2025.manoslocales.Networking.ApiRepository
import com.tecmov2025.manoslocales.R
import com.tecmov2025.manoslocales.SharedPreferences.ConfigPreferences
import com.tecmov2025.manoslocales.Utils.ProductViewModel
import com.tecmov2025.manoslocales.Utils.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Login Screen
 * @param navController permite la navegacion entre pantallas compose
 */
@Composable
fun LoginScreen(navController: NavController, viewModel: ProductViewModel,requireAuth : ()-> Unit)
{
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //observa si la sesion ya estaba abierta
    val  sesionEstaAbierta = viewModel.sesionEstaAbierta
    viewModel.verificarSesion(context)
    LaunchedEffect(sesionEstaAbierta)
    {
        if(sesionEstaAbierta == true)
        {
            requireAuth()
        }
    }

    //observa estados de mensajes
    val message by viewModel.snackbarMessage.collectAsState(initial = "")
    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            snackbarHostState.showSnackbar(message)
            viewModel.clearSnackbarMessage()
        }
    }

    //observa estado de la solicitud login
    val loginStatus = viewModel.loginStatus
    LaunchedEffect(loginStatus)
    {
        if (loginStatus == true)
        {
            viewModel.establecerSesionIniciada(context)
            goToHome(context)
        }
        else if (loginStatus == false)
        {  viewModel.showMessage("Usuario o contraseña invalidos")}
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment
                .CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(20.dp, alignment = Alignment.CenterVertically)
        ){
            Image(
                modifier = Modifier
                    .size(250.dp),
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit
            )

            CustomTextField(username, { username = it }, "Usuario")

            CustomTextField(password, { password = it }, "Contraseña", true)

            CustomButton(
                onClick = {
                    LoginButtonAction(
                        username = username,
                        password = password,
                        viewModel
                    )
                },
                text = "Iniciar Sesión"
            )

            LinkText("No tenes cuenta? - Registrate", { navController.navigate(Screens.RegisterScreen.route) })
            LinkText("Recuperar contraseña", { navController.navigate(Screens.PasswordScreen.route) })

        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 66.dp)
        )
    }
}



fun LoginButtonAction(
    username: String,
    password: String,
    viewModel: ProductViewModel
){

    val trimmedUsername = username.trim()
    val trimmedPassword = password.trim()

    //valida el formato del email
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    when {
        trimmedUsername.isBlank() || trimmedPassword.isBlank() -> {
            viewModel.showMessage("Por favor, completá todos los campos sin espacios.")
        }

        !isValidEmail(trimmedUsername) -> {
            viewModel.showMessage("El usuario debe tener formato de correo electrónico.")
        }

        trimmedPassword.length < 8 -> {
            viewModel.showMessage("La contraseña debe tener al menos 8 caracteres.")
        }

        else -> { viewModel.login(email = username,pass=password) }
    }
}

fun goToHome(context: Context)
{
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
    if (context is Activity) {
        context.finish()
    }
}