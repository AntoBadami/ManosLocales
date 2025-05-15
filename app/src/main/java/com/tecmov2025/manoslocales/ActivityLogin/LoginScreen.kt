package com.tecmov2025.manoslocales.ActivityLogin

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
import com.tecmov2025.manoslocales.R

/**
 * Login Screen
 * @param navController permite la navegacion entre pantallas compose
 */
@Composable
fun LoginScreen(navController: NavController)
{
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                onClick = { LoginButtonAction(context) },
                text = "Iniciar Sesión"
            )

            LinkText("No tenes cuenta? - Registrate", { navController.navigate("RegisterScreen") })
            LinkText("Recuperar contraseña", { navController.navigate("PasswordScreen") })

        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 66.dp)
        )
    }
}


fun LoginButtonAction(context: Context)
{
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
}