package com.tecmov2025.manoslocales.ActivityLogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.tecmov2025.manoslocales.Utils.CustomTitleText
import com.tecmov2025.manoslocales.Utils.CustomTitledInput

@Composable
fun PasswordScreen()
{
    CustomScaffold("Recuperar Contraseña"){padding -> PasswordScreenBody(padding)}
}
@Composable
fun PasswordScreenBody(padding : PaddingValues)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment
                .CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(20.dp, alignment = Alignment.CenterVertically)
        )
        {
            var mail by remember { mutableStateOf("") }
            var username by remember { mutableStateOf("") }

            CustomTitledInput(username, { username = it }, "Usuario")
            CustomTitledInput(mail, { mail = it }, "Correo electronico")

            CustomButton({/*Accion*/}, "Recuperar")
        }
    }
}