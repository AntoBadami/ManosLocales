package com.tecmov2025.manoslocales

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

class RegisterScreen
{
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

            Button(
                modifier = Modifier
                    .height(48.dp)
                    .width(324.dp),
                onClick = {}) {
                Text("Registrarse")
            }
        }
    }

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
}