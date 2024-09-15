package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aibweekend1.localproduct_module.ProductInsertScaffold
import com.example.androiddev.fruit_module.FruitAboutUsScaffold
import com.example.androiddev.fruit_module.FruitDetailScaffold
import com.example.androiddev.fruit_module.FruitScaffold
import com.example.androiddev.fruit_module.FruitSearchResultScaffold
import com.example.androiddev.fruit_module.FruitSearchScaffold
import com.example.androiddev.fruit_module.FruitViewModel
import com.example.myapp.localproduct_module.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
			FruitsApp(),
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun FruitsApp(){
    val nc = rememberNavController()
    val vm = FruitViewModel()
    NavHost(navController = nc, startDestination = "main" ) {
        composable("main") {
            FruitScaffold(nc, vm)
        }
        composable("fruit_detail") {
            FruitDetailScaffold(nc, vm)
        }
        composable("search") {
            FruitSearchScaffold(nc, vm)
        }
        composable("search_result") {
            FruitSearchResultScaffold(nc, vm)
        }
        composable("about_us") {
            FruitAboutUsScaffold(nc)
        }
    }
}
