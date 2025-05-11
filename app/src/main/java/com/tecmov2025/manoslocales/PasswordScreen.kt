package com.tecmov2025.manoslocales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTargetMarker
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordForm()
{
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment
            .CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(20.dp, alignment = Alignment.CenterVertically)
    )
    {
        Text(
            text = "Recuperar Contraseña",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        var mail by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }

        CustomTitledInput(username, {username = it},"Usuario")
        CustomTitledInput(mail,{mail = it},"Correo electronico")

        CustomButton({},"Recuperar")
    }
}
