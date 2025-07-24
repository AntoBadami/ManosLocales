package com.tecmov2025.manoslocales.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Muestra una opción de configuración con un interruptor (Switch) que puede ser activado o desactivado.*/
@Composable
fun ConfigSwitchCard(text: String,checked : Boolean, onCheckedChange: (Boolean) -> Unit)
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
                fontSize = 20.sp, text = text, color = Color.DarkGray)

            Switch(
                modifier = Modifier.weight(1f)
                    .fillMaxWidth()
                    .padding(3.dp),
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White
                )
            )
        }
    }
}

/**
 * Configuracion con lista desplegable
 */
@Composable
fun ConfigDropdownCard(
    text: String,
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
                    text = "${text}: $seleccion",
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
