package com.example.androiddev.fruit_module

import android.content.Context
import android.graphics.ColorSpace.Rgb
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.size.Size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitDetailScaffold(nc: NavController, vm: FruitViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        nc.popBackStack()
                    }) {
                        Icon(Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null)
                    }
                },
                title = { Text(text = "Fruit Description") }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it),
        ) {
            Column(
                modifier = Modifier.border(BorderStroke(1.dp, Color.LightGray))
                    .verticalScroll(rememberScrollState())
                    .padding(10.dp),
            ) {
                Text( modifier = Modifier.padding(bottom = 10.dp, start = 25.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 2.sp,
                    text = "Fruit: ${vm.selectedFruit.name}"
                )
                Column(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .align(Alignment.CenterHorizontally),
                ){
                AsyncImage(modifier = Modifier.fillMaxWidth(),
                    model = vm.selectedFruit.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .background(color = Color.LightGray)
                    .clip(MaterialTheme.shapes.medium)) {
                    Surface(
                        modifier = Modifier
                            .padding(top= 16.dp, bottom = 0.dp, start=16.dp, end=16.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .align(Alignment.CenterHorizontally),
                    ){
                        Column (modifier = Modifier
                            .padding(36.dp)
                            .width(300.dp), ){
                            Text("Calories                =    ${vm.selectedFruit.calories}", fontSize = 16.sp, letterSpacing = 2.sp)
                            Spacer(modifier = Modifier.height(9.dp))
                            Text("Fat                        =    ${vm.selectedFruit.fat}", fontSize = 16.sp, letterSpacing = 2.sp)
                            Spacer(modifier = Modifier.height(9.dp))
                            Text("Sugar                    =    ${vm.selectedFruit.sugar}", fontSize = 16.sp, letterSpacing = 2.sp)
                            Spacer(modifier = Modifier.height(9.dp))
                            Text("Carbohydrates       =    ${vm.selectedFruit.carbohydrates}", fontSize = 16.sp, letterSpacing = 2.sp)
                            Spacer(modifier = Modifier.height(9.dp))
                            Text("Protein                 =     ${vm.selectedFruit.protein}", fontSize = 16.sp, letterSpacing = 2.sp)
                        }
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
//                    .padding(top = 6.dp)
                    .background(color = Color.LightGray)
                    .clip(MaterialTheme.shapes.medium)) {
                    Surface(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .align(Alignment.CenterHorizontally),
                    ){
                        Column (modifier = Modifier
                            .padding(36.dp)
                            .width(500.dp), ){
                            val selectedFruit = vm.stateFruitList.value.find { it.name == vm.selectedFruit.name }
                            Text(selectedFruit?.description ?: "Fruit not found", fontSize = 16.sp, textAlign = TextAlign.Center, letterSpacing = 1.sp, lineHeight = 24.sp)
                        }
                    }
                }
            }
        }
    }
}





//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SecondPage(nc: NavController, fs: FontSizeViewModel, dl: ThemeViewModel) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                navigationIcon = {
//                    IconButton(onClick = {
//                        nc.popBackStack()
//                    }) {
//                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
//                    }
//                },
//                title = { Text(text = "Second Page") },
//                actions = {
//                    IconButton(onClick = { fs.decrease() }) {
//                        Icon(Icons.Rounded.Remove, contentDescription = null)
//                    }
//                    IconButton(onClick = { fs.increase() }) {
//                        Icon(Icons.Rounded.Add, contentDescription = null)
//                    }
//                    IconButton(onClick = { dl.lighttheme() }) {
//                        Icon(Icons.Rounded.DarkMode, contentDescription = null)
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(it),
//        ) {
//            Column(
//                modifier = Modifier.verticalScroll(rememberScrollState()).padding(10.dp),
//            ) {
//                Text(fontSize = fs.size.sp, text = "Kotlin is a cross-platform, statically typed, general-purpose high-level programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference allows its syntax to be more concise.")
//            }
//        }
//    }
//}