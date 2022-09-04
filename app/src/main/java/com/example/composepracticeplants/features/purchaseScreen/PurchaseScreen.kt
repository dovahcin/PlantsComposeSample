package com.example.composepracticeplants.features.purchaseScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepracticeplants.R
import com.example.composepracticeplants.domain.Properties
import com.example.composepracticeplants.ui.theme.*

private val desc =
    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."

private val items = mutableListOf(
    Properties("Drained", R.drawable.droplet),
    Properties("Full Sun", R.drawable.sun),
    Properties("20cm x 40cm", R.drawable.size),
    Properties("38 ⁰C", R.drawable.temprature)
)

@Composable
fun PurchaseScreen(
    modifier: Modifier,
    price: Long,
    name: String,
    resourceId: Int,
    onBackClick: () -> Unit,
) {
    ComposePracticePlantsTheme {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Banner(modifier = modifier, image = resourceId, onBackClick)
            PurchaseCard(modifier = modifier.align(Alignment.BottomCenter), price, name)
        }
    }
}

@Composable
private fun Banner(
    modifier: Modifier,
    image: Int,
    onBackClick: () -> Unit,
) {
    var isFavorable by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier
        .background(NavColor)
        .fillMaxSize()) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = modifier
                .align(Alignment.TopCenter)
                .size(300.dp)
                .padding(16.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(
                id = if (!isFavorable) {
                    R.drawable.ic_baseline_favorite_border_white
                } else {
                    R.drawable.ic_baseline_favorite_filled
                }),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(30.dp)
                .padding(end = 8.dp, top = 8.dp)
                .clickable { isFavorable = !isFavorable }
        )
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(30.dp)
                .padding(start = 8.dp, top = 8.dp)
                .clickable { onBackClick.invoke() },
            tint = Color.White
        )
    }
}

@Composable
private fun PurchaseCard(modifier: Modifier, price: Long, name: String) {
    var number by remember {
        mutableStateOf(1)
    }
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            PlantDescription(name = name, description = desc, modifier = modifier)
            PlantProperties(
                properties = items,
                modifier = modifier.padding(vertical = 16.dp)
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Numerator(
                    modifier = modifier,
                    number,
                    onDecrementClick = {
                        if (number > 1)
                            number -= 1

                    },
                    onIncrementClick = {
                        number += 1
                    }
                )
                PurchaseButton(modifier = modifier, ultimatePrice(price, number))
            }
        }
    }
}

@Composable
private fun PlantDescription(name: String, description: String, modifier: Modifier) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = modifier.size(height = 8.dp, width = 0.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
private fun PlantProperties(properties: List<Properties>, modifier: Modifier) {
    LazyRow {
        items(properties) { properties ->
            PropertyCard(properties = properties, modifier = modifier)
        }
    }
}

@Composable
private fun PropertyCard(properties: Properties, modifier: Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(end = 16.dp, top = 16.dp)
            .size(110.dp),
        border = BorderStroke(2.dp, Gray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = properties.icon),
                contentDescription = null,
                modifier = modifier.size(30.dp)
            )
            Text(text = properties.name)
        }
    }
}

@Composable
private fun PurchaseButton(modifier: Modifier, price: Long) {
    Button(
        onClick = {},
        shape = MaterialTheme.shapes.small,
        modifier = modifier.size(150.dp, 40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ButtonColor, contentColor = Color.White
        )
    ) {
        Text(
            text = "Checkout $$price",
            style = MaterialTheme.typography.button,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun Numerator(
    modifier: Modifier,
    number: Int,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Adjuster(symbol = "-", modifier = modifier) {
            onDecrementClick.invoke()
        }
        Text("$number", modifier.padding(horizontal = 16.dp))
        Adjuster(symbol = "+", modifier = modifier) {
            onIncrementClick.invoke()
        }
    }
}

@Composable
private fun Adjuster(
    symbol: String,
    modifier: Modifier,
    onclick: () -> Unit,
) {
    Button(
        onClick = { onclick.invoke() },
        modifier = modifier.size(30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AdjusterColor,
            contentColor = DarkGray
        ),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            modifier = modifier.padding(1.dp),
            text = symbol,
        )
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    PurchaseScreen(Modifier, 60, "Aloe Vera", R.drawable.aloevera) { }
}

private fun ultimatePrice(price: Long, number: Int) = (price * number)
