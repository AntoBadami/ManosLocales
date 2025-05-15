package com.tecmov2025.manoslocales.ActivityLogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecmov2025.manoslocales.Utils.CustomButton
import com.tecmov2025.manoslocales.Utils.CustomScaffold
import com.tecmov2025.manoslocales.Utils.CustomTitledInput

/**
 * Pantalla de registro en app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen()
{ CustomScaffold("Registro"){ padding -> RegisterFormBody(padding)} }

@Composable
fun RegisterFormBody(padding: PaddingValues)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(scrollState),
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

            CustomTitledInput(value = name, onValueChange = { name = it }, label = "Nombre")
            CustomTitledInput(
                value = lastname,
                onValueChange = { lastname = it },
                label = "Apellido"
            )
            CustomTitledInput(
                value = mail,
                onValueChange = { mail = it },
                label = "Correo electrónico"
            )
            CustomTitledInput(
                value = username,
                onValueChange = { username = it },
                label = "Usuario"
            )
            CustomTitledInput(
                value = password,
                onValueChange = { password = it },
                label = "Contraseña"
            )
            CustomTitledInput(
                value = passwordControl,
                onValueChange = { passwordControl = it },
                label = "Repetir Contraseña"
            )
            CustomButton({}, "Registrarse")
        }
    }
}
