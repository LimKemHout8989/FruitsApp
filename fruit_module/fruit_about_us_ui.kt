package com.example.androiddev.fruit_module

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitAboutUsScaffold(nc: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { nc.navigate("main") }) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = { Text("About Us") }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            FruitAboutUs()
        }
    }
}

@Composable
fun FruitAboutUs() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
            .verticalScroll(scrollState) // Enable vertical scrolling
    ) {

        Text(
            text = "      At [ FruitApp ], we are passionate about bringing you the freshest and most nutritious fruits from around the world. Our goal is to educate and inspire healthy living by offering detailed insights into a wide variety of fruits, from well-known favorites to exotic varieties. We take pride in our commitment to quality and transparency, providing in-depth information about each fruit’s nutritional values, family, order, genus, and unique characteristics.",
            fontSize = 16.sp, // Custom body text size
            textAlign = TextAlign.Justify, // Justify the text alignment
            lineHeight = 24.sp, // Line height for line spacing
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 8.dp) // Add horizontal padding for better spacing
        )

        SectionTitle("Our Mission")
        Text(
            text = "   We aim to promote a healthier lifestyle by making fruit knowledge easily accessible. Whether you want to learn about a fruit's nutritional benefits, its place in the botanical world, or simply how best to enjoy it, our application is your go-to resource.",
            fontSize = 16.sp,
            textAlign = TextAlign.Justify, // Justify the text alignment
            lineHeight = 24.sp, // Line height for line spacing
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 8.dp) // Add horizontal padding for better spacing
        )

        SectionTitle("Popular Fruits")

        // Display a couple of featured fruits
        FeaturedFruit(
            name = "Banana",
            scientificName = "Musa spp.",
            description = "Creamy and rich in potassium, great for smoothies and energy boosts.",
            imageUrl = "https://hips.hearstapps.com/hmg-prod/images/bananas-royalty-free-image-1702061943.jpg"
        )

        Spacer(modifier = Modifier.height(16.dp))

        FeaturedFruit(
            name = "Orange",
            scientificName = "Citrus × sinensis",
            description = "Juicy and refreshing, packed with Vitamin C for a healthy immune system.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Ambersweet_oranges.jpg/1200px-Ambersweet_oranges.jpg"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle("Contact Us")
        Text(
            text = "   Have any questions or want to place an order? \n   Feel free to reach out to us by (+855 15603689) \n   Or at contact@fruitcompany.com.",
            fontSize = 16.sp,
            textAlign = TextAlign.Justify, // Justify the text alignment
            lineHeight = 24.sp, // Line height for line spacing
            modifier = Modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 8.dp) // Add horizontal padding for better spacing
        )

        Spacer(modifier = Modifier.height(8.dp))

        SocialMediaLinks()
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp, // Custom size for section titles
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp) // Add some spacing below section titles
    )
}

@Composable
fun FeaturedFruit(name: String, scientificName: String, description: String, imageUrl: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = name,
                    fontSize = 18.sp, // Custom size for fruit name
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Scientific Name: $scientificName",
                    fontSize = 14.sp // Smaller text for scientific name
                )
                Text(
                    text = description,
                    fontSize = 14.sp // Description text
                )
            }
        }
    }
}

@Composable
fun SocialMediaLinks() {
    val annotatedLinkString = buildAnnotatedString {
        append("   Follow us on social media: ")

        pushStringAnnotation(tag = "URL", annotation = "https://www.facebook.com/fruitcompany")
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append("Facebook")
        }
        pop()

        append(" | ")

        pushStringAnnotation(tag = "URL", annotation = "https://twitter.com/fruitcompany")
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append("Twitter")
        }
        pop()

        append(" | ")

        pushStringAnnotation(tag = "URL", annotation = "https://www.instagram.com/fruitcompany")
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append("Instagram")
        }
        pop()
    }

    ClickableText(
        text = annotatedLinkString,
        onClick = { offset ->
            annotatedLinkString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let {
                    // Handle link click here
                }
        }
    )
}
