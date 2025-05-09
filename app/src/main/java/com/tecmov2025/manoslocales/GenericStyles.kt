package com.tecmov2025.manoslocales
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Campo de texto personalizado que puede ocultar la entrada (para contraseñas).
 *
 * @param value El valor del campo de texto.
 * @param onValueChange Función para manejar el cambio de valor.
 * @param label El texto de la etiqueta.
 * @param isPassword Indica si el campo debe ocultar la entrada como una contraseña. Por defecto es `false`.
 */
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

/**
 * Muestra un texto que actúa como enlace clickeable.
 *
 * @param text El texto que se mostrará.
 * @param onClick Acción a ejecutar cuando se hace clic en el texto.
 */
@Composable
fun LinkText(text: String,
             onClick: ()-> Unit)
{
    Text(
        modifier = Modifier
            .clickable{onClick()},
        text = text,)
}

/**
 * Botón personalizado reutilizable con tamaño fijo.
 *
 * @param onClick Acción a ejecutar al hacer clic.
 * @param text Texto que se mostrará dentro del botón.
 */
@Composable
fun CustomButton(onClick: () -> Unit, text: String)
{
    Button(
        modifier = Modifier
            .height(48.dp)
            .width(324.dp),
        onClick = onClick
    ) {
        Text(text)
    }
}

