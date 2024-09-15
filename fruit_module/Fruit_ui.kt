package com.example.androiddev.fruit_module

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitScaffold(nc: NavController, vm: FruitViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fruits App", fontSize = 26.sp) },
                actions = {
                    Box(modifier = Modifier.padding(1.dp)) {
                        IconButton(onClick = {
                            nc.navigate("search")
                        }) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                            )
                        }
                    }
                    IconButton(onClick = {
                        vm.toggleSort()
                    }) {
                        Icon(
                            if (vm.isAscendingOrder.value)
                                Icons.Default.ArrowCircleDown
                            else Icons.Default.ArrowCircleUp,
                            contentDescription = "Down",
                        )
                    }

                    IconButton(onClick = {
                        vm.getResultList()
                    }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                        )
                    }
                    IconButton(onClick = {
                        nc.navigate("about_us")
                    }) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "About_us",
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            FruitBody(nc, vm)
        }
    }
}

@Composable
fun FruitBody(nc: NavController, vm: FruitViewModel) {
    LaunchedEffect(Unit) {
        if (vm.stateFruitList.value.isEmpty()) {
            vm.getResultList()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (vm.isLoading) {
            CircularProgressIndicator()
        } else if (vm.errorMessage.isNotEmpty()) {
            Text("Error: ${vm.errorMessage}")
        } else {
            LazyColumn() {
                items(vm.results.size) {

                    val element = vm.stateFruitList.value[it]

                    Row(
                        modifier = Modifier
                            .padding(0.dp)
                            .border(BorderStroke(1.dp, Color.LightGray))
                            .clickable {
                                vm.setFruit(element)
                                nc.navigate("fruit_detail")
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Surface(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .size(135.dp),
                        ) {
                            AsyncImage(modifier = Modifier.fillMaxWidth(),
                                model = element.image,
                                contentDescription = element.image
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Column() {
                                Text(element.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                                Spacer(modifier = Modifier.height(7.dp))
                                Text("Family :  ${element.family}")
                                Text("Order   :  ${element.order}")
                                Text("Genus  :  ${element.genus}")
                            }
                        }
                    }
                }
            }
        }
    }
}