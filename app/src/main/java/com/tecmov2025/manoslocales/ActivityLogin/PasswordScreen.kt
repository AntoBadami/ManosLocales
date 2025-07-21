package com.tecmov2025.manoslocales.ActivityLogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.tecmov2025.manoslocales.Utils.CustomButton
import com.tecmov2025.manoslocales.Utils.CustomScaffold
import com.tecmov2025.manoslocales.Utils.CustomTitledInput
import androidx.compose.material3.SnackbarHost
import kotlinx.coroutines.launch
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import com.tecmov2025.manoslocales.Utils.CustomTitledPasswordInput

@Composable
fun PasswordScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    CustomScaffold("Recuperar Contraseña") { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            PasswordScreenBody(padding, snackbarHostState, scope)

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) { snackbarData: SnackbarData ->
                Snackbar(snackbarData = snackbarData)
            }
        }
    }
}

@Composable
fun PasswordScreenBody(
    padding : PaddingValues,
    snackbarHostState: SnackbarHostState,
    scope: kotlinx.coroutines.CoroutineScope
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(padding)
    ) {
        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment
                .CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(20.dp, alignment = Alignment.CenterVertically)
        )
        {
            var mail by remember { mutableStateOf("") }
            var mailError by remember { mutableStateOf(false) }
            var showError by remember { mutableStateOf(false) }

            var showCodeInput by remember { mutableStateOf(false) }
            var code by remember { mutableStateOf("") }
            val expectedCode = "123456"
            var codeValid by remember { mutableStateOf(false) }
            var showPasswordInputs by remember { mutableStateOf(false) }

            var newPassword by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }

            CustomTitledInput(
                value = mail,
                onValueChange = {
                    mail = it
                },
                label = "Correo electrónico"
            )

            if (showError && mailError) {
                Text(
                    text = "Formato de correo inválido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            if (!showCodeInput && !showPasswordInputs) {
                CustomButton(
                    onClick = {
                        showError = true
                        mailError = !isValidEmail(mail)
                        if (!mailError) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Se envió un correo de recuperación")
                                kotlinx.coroutines.delay(2000)
                                showCodeInput = true
                            }
                        }
                    },
                    text = "Recuperar"
                )
            }

            if (showCodeInput && !codeValid) {
                CustomTitledInput(
                    value = code,
                    onValueChange = { code = it },
                    label = "Código recibido por correo"
                )
                CustomButton(
                    onClick = {
                        if (code == expectedCode) {
                            codeValid = true
                            showPasswordInputs = true
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Código incorrecto")
                            }
                        }
                    },
                    text = "Validar código"
                )
            }

            if (showPasswordInputs) {
                CustomTitledPasswordInput(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = "Nueva contraseña"
                )
                CustomTitledPasswordInput(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Confirmar contraseña"
                )
                CustomButton(
                    onClick = {
                        if (newPassword == confirmPassword && newPassword.length >= 8) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Contraseña restablecida correctamente")
                            }
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Las contraseñas no coinciden o son muy cortas")
                            }
                        }
                    },
                    text = "Cambiar contraseña"
                )
            }

        }
    }
}