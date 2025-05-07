package com.tecmov2025.manoslocales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterForm()
{
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment
            .CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(20.dp, alignment = Alignment.CenterVertically)
    )
    {
        var name by remember { mutableStateOf("") }
        var lastname by remember { mutableStateOf("") }
        var mail by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordControl by remember { mutableStateOf("") }

        CustomTextField(
            name,
            { name = it },
            "Nombre")

        CustomTextField(
            lastname,
            { lastname = it },
            "Apellido")

        CustomTextField(
            mail,
            { mail = it },
            "Correo electronico")

        CustomTextField(
            username,
            { username = it },
            "Usuario")

        CustomTextField(
            password,
            { password = it },
            "Contraseña",
            true)

        CustomTextField(
            passwordControl,
            { passwordControl = it },
            "Repita Contraseña",
            true)

       CustomButton({},"Registrarse")
    }
}
