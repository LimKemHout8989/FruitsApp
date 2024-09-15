package com.example.androiddev.fruit_module

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitSearchResultScaffold(nc: NavController, vm: FruitViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        vm.resetStates()
                        nc.navigate("main")
                    }) {
                        Icon(Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null)
                    }
                },
                title = { Text("Search Result") },
                actions = {
//                    IconButton(onClick = {
//                        vm.toggleSort()
//                    }) {
//                        Icon(
//                            Icons.Default.ArrowDownward,
//                            contentDescription = "Down",
//                        )
//                    }
                    IconButton(onClick = {
                        vm.getSearchResultList()
                    }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            BookSearchResultBody(nc, vm)
        }
    }
}

@Composable
fun BookSearchResultBody(nc: NavController, vm: FruitViewModel) {
    LaunchedEffect(Unit) {
//        vm.getSearchResultList("Kiwi")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (vm.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (vm.errorMessage.isNotEmpty()) {
//            Text(vm.successMessage, color = Color.Red)
        } else {
            Column() {
                LazyColumn() {
                    items(vm.results.size) {

                        val element = vm.stateSearchFruitList.value[it]

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
                                AsyncImage(
                                    modifier = Modifier.fillMaxWidth(),
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
                                    Text(
                                        element.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    )
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
}