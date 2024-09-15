package com.example.androiddev.fruit_module

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitSearchScaffold(nc: NavController, vm: FruitViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search Fruit") },
                actions = {
                    IconButton(onClick = {
                        nc.navigate("main")
                    }) {
                        Icon(Icons.Rounded.Close,
                            contentDescription = null)
                    }
                },
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            BookSearchBody(nc, vm)
        }
    }
}

@Composable
fun BookSearchBody(nc: NavController, vm: FruitViewModel) {

    fun String.titlecaseFirstChar() = replaceFirstChar(Char::titlecase)
    var name by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = name,
            onValueChange = { name = it },
            label = { Text("Fruit Name") },
            placeholder = { Text("Enter Fruit") },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )

        Button(modifier = Modifier.fillMaxWidth().padding(5.dp),
            onClick = {
                val item = FruitModel(name = name.titlecaseFirstChar())
//            val item = Records(
//                pid = "0",
//                title = title,
//                body = body,
//                price = price,
//                qty = qty,
//                image = image,
//            )
            vm.searchFruit(item.name)
                nc.navigate("search_result")
        }) {
            Text("Search")
        }

//        if (vm.isLoading) {
//            CircularProgressIndicator()
//        }
//
//        if (vm.successMessage.isNotEmpty()) {
//            Text(vm.successMessage, color = Color.Blue)
//        }
//
//        if (vm.errorMessage.isNotEmpty()) {
//            Text(vm.errorMessage, color = Color.Red)
//        }
    }
}