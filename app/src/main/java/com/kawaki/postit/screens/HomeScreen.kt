package com.kawaki.postit.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kawaki.postit.R
import com.kawaki.postit.components.TextBox
import com.kawaki.postit.model.NotificationData
import com.kawaki.postit.model.PushNotification
import com.kawaki.postit.viewmodel.NotificationViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NotificationViewModel) {

    val key1 = remember { mutableStateOf("") }
    val key2 = remember { mutableStateOf("") }
    val key3 = remember { mutableStateOf("") }
    val key4 = remember { mutableStateOf("") }
    val key5 = remember { mutableStateOf("") }
    val value1 = remember { mutableStateOf("") }
    val value2 = remember { mutableStateOf("") }
    val value3 = remember { mutableStateOf("") }
    val value4 = remember { mutableStateOf("") }
    val value5 = remember { mutableStateOf("") }

    val serverKey = remember { mutableStateOf("") }
    val tokenOrTopic = remember { mutableStateOf("") }

    val fieldCount = remember { mutableStateOf(2) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "POST IT", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))
            Image(painter = painterResource(id = R.drawable.firebase), contentDescription = "Logo", modifier = Modifier.scale(0.50f))

            Spacer(modifier = Modifier.height(20.dp))

            TextBox(value = serverKey.value, onValueChange = { serverKey.value = it }, placeHolder = "Enter Your Server Key")
            TextBox(modifier = Modifier.padding(top = 10.dp), value = tokenOrTopic.value, onValueChange = { tokenOrTopic.value = it }, placeHolder = "Optional: Enter Token or Topic")

            when (fieldCount.value) {
                /** 2 keys and values */
            2 ->{
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp), value = key1.value,
                        onValueChange = { key1.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value1.value, onValueChange = { value1.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp), value = key2.value,
                        onValueChange = { key2.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value2.value, onValueChange = { value2.value = it }, placeHolder = "Value")

                    if (fieldCount.value == 2) {
                        IconButton(onClick = { fieldCount.value += 1 }) {
                            Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = "Add Field")
                        }
                    } else {
                        IconButton(onClick = { fieldCount.value -= 1 }) {
                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                        }
                    }
                }
            }

                /** 3 keys and values */
            3 -> {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp), value = key1.value,
                        onValueChange = { key1.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value1.value, onValueChange = { value1.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp), value = key2.value,
                        onValueChange = { key2.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value2.value, onValueChange = { value2.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key3.value,
                        onValueChange = { key3.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value3.value, onValueChange = { value3.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value += 1 }) {
                        Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = "Add Field")
                    }
                }
            }
                /** 4 keys and values */
            4 -> {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key1.value,
                        onValueChange = { key1.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value1.value, onValueChange = { value1.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key2.value,
                        onValueChange = { key2.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value2.value, onValueChange = { value2.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key3.value,
                        onValueChange = { key3.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value3.value, onValueChange = { value3.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key4.value,
                        onValueChange = { key4.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value4.value, onValueChange = { value4.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value += 1 }) {
                        Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = "Add Field")
                    }
                }
            }
                /** 5 keys and values */
            else -> {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key1.value,
                        onValueChange = { key1.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value1.value, onValueChange = { value1.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key2.value,
                        onValueChange = { key2.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value2.value, onValueChange = { value2.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key3.value,
                        onValueChange = { key3.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value3.value, onValueChange = { value3.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key4.value,
                        onValueChange = { key4.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value4.value, onValueChange = { value4.value = it }, placeHolder = "Value")

                    IconButton(onClick = { fieldCount.value -= 1 }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Remove Field")
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {
                    TextBox(
                        modifier = Modifier
                            .fillMaxWidth(0.30f)
                            .padding(end = 10.dp),
                        value = key5.value,
                        onValueChange = { key5.value = it },
                        placeHolder = "Key",
                        capitalization = KeyboardCapitalization.None
                    )
                    TextBox(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(end = 10.dp), value = value5.value, onValueChange = { value5.value = it }, placeHolder = "Value")
                    Spacer(modifier = Modifier.width(40.dp))
                }
            }
            }

            Button(onClick = {

                val headers = HashMap<String, String>()
                headers["Authorization"] = "key=${serverKey.value}"
                headers["Content-Type"] = "application/json"

                if (key1.value.isNotEmpty() && value1.value.isNotEmpty()) {
                    if (serverKey.value.trim().isEmpty()) {
                        Toast.makeText(context, "Enter your server key", Toast.LENGTH_LONG).show()
                    } else {
                        PushNotification(
                            data = NotificationData(
                                key1 = key1.value.trim(),
                                value1 = value1.value.trim(),
                                key2 = key2.value.trim(),
                                value2 = value2.value.trim(),
                                key3 = key3.value.trim(),
                                value3 = value3.value.trim(),
                                key4 = key4.value.trim(),
                                value4 = value4.value.trim(),
                                key5 = key5.value.trim(),
                                value5 = value5.value.trim()
                            ).convertToMap(),
                            to = tokenOrTopic.value.trim()
                        ).also { push ->
                            viewModel.sendNotification(
                                headers = headers,
                                notification = push,
                                onError = { error -> if (error.isNotEmpty()) scope.launch { Toast.makeText(context, error, Toast.LENGTH_LONG).show() } },
                                onSuccess = { scope.launch { Toast.makeText(context, "Success", Toast.LENGTH_LONG).show() } })
                        }
                    }
                }
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                Text(text = "POST", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold))
            }
        }
    }
}