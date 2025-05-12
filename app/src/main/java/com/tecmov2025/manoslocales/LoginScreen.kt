package com.tecmov2025.manoslocales

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavHostController)
{
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment
                .CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(20.dp, alignment = Alignment.CenterVertically)
        ) {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

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
                        context,
                        username,
                        password,
                        navController,
                        onError = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Por favor complete todos los campos.")
                            }
                        }
                    )
                },
                text = "Iniciar Sesión"
            )
            LinkText("No tenes cuenta? - Registrate", { navController.navigate("RegisterScreen") })
            LinkText("Recuperar contraseña", { navController.navigate("PasswordScreen") })

        }
        androidx.compose.material3.SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 66.dp)
        )
    }
}

fun LoginButtonAction(
    context: Context,
    username: String,
    password: String,
    navController: NavHostController,
    onError: () -> Unit
)
{
    if (username.isNotBlank() && password.isNotBlank()) {
        // Navegar a MainActivity
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        // Cierra LoginActivity para no volver atras
        if (context is android.app.Activity) {
            context.finish()
        }
    } else {
        onError()
    }
}