package com.tecmov2025.manoslocales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilForm()
{
    var editable by remember { mutableStateOf(false) }
    //datos de ejemplo
    var name by remember { mutableStateOf("Juan") }
    var lastname by remember { mutableStateOf("Pérez") }
    var mail by remember { mutableStateOf("juanperez@mail.com") }
    var username by remember { mutableStateOf("juan123") }
    var password by remember { mutableStateOf("123456") }
    var passwordControl by remember { mutableStateOf("123456") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Perfil de Usuario", color = Color.DarkGray) },
                navigationIcon = {},
                actions = {
                    IconButton(onClick = { editable = !editable }) {
                        Icon(
                            imageVector = if (editable) Icons.Default.Close else Icons.Default.Edit,
                            contentDescription = if (editable) "Cancelar edición" else "Editar perfil"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val fieldModifier = Modifier
                .widthIn(max = 400.dp)
                .align(Alignment.CenterHorizontally)

            PerfilField("Nombre", name, editable, fieldModifier) { name = it }
            PerfilField("Apellido", lastname, editable, fieldModifier) { lastname = it }
            PerfilField("Correo electrónico", mail, editable, fieldModifier) { mail = it }
            PerfilField("Usuario", username, editable, fieldModifier) { username = it }

            PerfilField("Contraseña", password, editable, fieldModifier, isPassword = true) {
                password = it
            }
            if (editable) {
                PerfilField(
                    "Repetir Contraseña",
                    passwordControl,
                    editable,
                    fieldModifier,
                    isPassword = true
                ) {
                    passwordControl = it
                }
            }

            if (editable) {
                CustomButton(onClick = {
                    if (name.isEmpty() || lastname.isEmpty() || mail.isEmpty() || username.isEmpty() || password.isEmpty() || passwordControl.isEmpty()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Por favor, complete todos los campos.")
                        }
                    }else if (password != passwordControl) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Las contraseñas no coinciden")
                        }
                    } else {
                        editable = false
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Cambios guardados correctamente")
                        }
                    }
                }, text = "Guardar cambios")
            }
        }
    }
}

@Composable
fun PerfilField(
    label: String,
    value: String,
    editable: Boolean,
    modifier: Modifier,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    Column(modifier) {
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp),
            color = Color.DarkGray
        )
        if (editable) {
            CustomTextField(
                value = value,
                onValueChange = onValueChange,
                label = label,
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
            )
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Corrigiendo elevación
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Text(
                    text = if (isPassword) "•".repeat(value.length) else value,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }
        }
    }
}
