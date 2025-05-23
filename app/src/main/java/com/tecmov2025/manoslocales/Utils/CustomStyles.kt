package com.tecmov2025.manoslocales.Utils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
)
{
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier
            .width(324.dp)
            .height(64.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.DarkGray,
            unfocusedTextColor = Color.DarkGray,
            focusedLabelColor = Color.DarkGray,
            unfocusedLabelColor = Color.DarkGray,
            cursorColor = Color.DarkGray,
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.Gray
        ),
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
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
        Text(
            text,
            color = MaterialTheme.colorScheme.background
        )
    }
}

/**
 * Un campo de entrada personalizado que muestra un título (etiqueta) encima del campo de texto.
 *
 * @param value El texto actual del campo de entrada.
 * @param onValueChange Función que se llama cuando cambia el texto ingresado.
 * @param label La etiqueta que se muestra como título del campo.
 */
@Composable
fun CustomTitledInput(value: String, onValueChange: (String)-> Unit, label: String)
{
    Column(modifier = Modifier
        .widthIn(max = 400.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        CustomTextField(
            value = value,
            onValueChange =onValueChange,
            label = label
        )
    }
}


/**
 * Composable que representa una tarjeta de opción clickeable.
 *
 * @param opcion Objeto de tipo `Opcion` que contiene el texto a mostrar y la acción a ejecutar al hacer clic.
 *
 * Este componente muestra una fila (`Row`) que ocupa todo el ancho disponible y
 * ejecuta la función `onclicick` de `opcion` al hacer clic.
 */
@Composable
fun OptionCard(opcion :Opcion)
{
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(top = 5.dp)
        .height(60.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp))
    {
        Row (modifier = Modifier
            .fillMaxSize()
            .clickable{opcion.onclicick() }
            .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {

            opcion.icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = opcion.text,
                    tint = Color.DarkGray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(fontSize = 20.sp, text = opcion.text, color = Color.DarkGray)
        }
    }

}
/**
 * Muestra una opción de configuración con un interruptor (Switch) que puede ser activado o desactivado.
 *
 * @param opcion La opción a mostrar, contiene el texto y el tipo.
 * @param switchesMutableListOf Lista mutable de estados booleanos para los switches.
 * @param index Índice de esta opción en la lista, usado para acceder al estado correspondiente.
 */
@Composable
fun OptionSwitchCard(opcion :Opcion,switchesMutableListOf : MutableList<Boolean>,
                     index : Int)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp)
        .height(60.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp))
    {
        Row (modifier = Modifier
            .fillMaxSize()
            .clickable{}
            .padding(start = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                modifier = Modifier
                    .wrapContentHeight(Alignment.Bottom)
                    .weight(3f),
                fontSize = 20.sp, text = opcion.text, color = Color.DarkGray)

            Switch(
                modifier = Modifier.weight(1f)
                .fillMaxWidth()
                .padding(3.dp),
                checked = switchesMutableListOf[index],
                onCheckedChange = { switchesMutableListOf[index] = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White
                )
            )
        }
    }
}
/**
 * opcion con lista desplegable
 */
@Composable
fun OptionDropdownCard(
    opcion: Opcion,
    opcionesLista: List<String>,
    seleccion: String,
    onSeleccion: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .height(60.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { expanded.value = true }
                    .padding(start = 30.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    text = "${opcion.text}: $seleccion",
                    color = Color.DarkGray,
                    softWrap = true
                )
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Desplegar",
                    tint = Color.Gray
                )
            }

            DropdownMenu(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(Color.White),
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                offset = DpOffset(x = 270.dp, y = -10.dp)
            ) {
                opcionesLista.forEach { texto ->
                    DropdownMenuItem(
                        text = { Text(texto) },
                        onClick = {
                            onSeleccion(texto)
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}

/**
 * Scaffold basico para pestañas
 * @param body funcion composable cuerpo de la pantalla en cuestion
 * @param title titulo para la barra
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(title : String,body: @Composable (padding: PaddingValues) -> Unit)
{
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(top = 15.dp)) },
                navigationIcon = {},
                actions = {}
            )
        }
    ){ padding -> body(padding)}

}