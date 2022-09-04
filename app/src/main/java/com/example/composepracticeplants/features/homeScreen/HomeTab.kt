package com.example.composepracticeplants.features.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepracticeplants.R
import com.example.composepracticeplants.domain.Properties
import com.example.composepracticeplants.domain.PurchaseInfo
import com.example.composepracticeplants.ui.theme.ComposePracticePlantsTheme

@Composable
fun HomeTab(
    modifier: Modifier,
    categories: List<Properties>,
    purchaseInfos: List<PurchaseInfo>,
    onPlantClick: (Long, String, Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Discover",
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier = modifier.weight(1f))
                RoundedPicture(painter = painterResource(id = R.drawable.profile_pic), modifier)
            }
            SearchBar(modifier)
            PlantCategories(categories = categories, modifier)
            SalesCard(modifier)
            PurchaseGrid(purchaseInfo = purchaseInfos, modifier, onPlantClick)
        }
    }
}

@Composable
private fun SalesCard(modifier: Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .size(120.dp)
            .padding(top = 16.dp)
            .clickable { },
        backgroundColor = MaterialTheme.colors.primaryVariant,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(24.dp, 0.dp)
        ) {
            Text(
                text = "Get",
                color = MaterialTheme.colors.onSecondary
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$20%",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 34.sp,
                        letterSpacing = 0.25.sp
                    ),
                    color = MaterialTheme.colors.onSecondary
                )
                Spacer(modifier = modifier.size(width = 8.dp, 0.dp))
                Text(
                    text = "Off",
                    color = MaterialTheme.colors.onSecondary,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "For Today",
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}


@Composable
private fun SearchBar(modifier: Modifier) {
    var text by remember { mutableStateOf("") }
    Card(shape = MaterialTheme.shapes.small,
        elevation = 0.dp,
        modifier = modifier.padding(top = 16.dp)
    ) {
        OutlinedTextField(
            value = text, onValueChange = { text = it },
            placeholder = { Text("search") },
            textStyle = TextStyle(color = MaterialTheme.colors.onSecondary),
            modifier = modifier
                .background(color = MaterialTheme.colors.surface)
                .fillMaxWidth(),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "SearchBar"
                )
            }
        )
    }
}


@Composable
fun PlantCategories(categories: List<Properties>, modifier: Modifier) {
    LazyRow {
        items(categories) { category ->
            PlantCategoryCard(category, modifier)
        }
    }
}

@Composable
private fun PlantCategoryCard(category: Properties, modifier: Modifier) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .padding(end = 16.dp, top = 16.dp)
            .clickable {

            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(all = 16.dp)
                .size(100.dp)
        ) {
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = "Green plant",
                modifier.size(50.dp)
            )
            Spacer(modifier = modifier.size(16.dp))
            Text(
                text = category.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun PurchaseGrid(
    purchaseInfo: List<PurchaseInfo>,
    modifier: Modifier,
    onPlantClick: (Long, String, Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(purchaseInfo) { priceCard ->
            PurchaseCard(
                plantName = priceCard.name,
                price = priceCard.price,
                priceCard.resourceId,
                modifier,
                onPlantClick
            )
        }
    }
}

@Composable
private fun PurchaseCard(
    plantName: String,
    price: Long,
    imageId: Int,
    modifier: Modifier,
    onPlantClick: (Long, String, Int) -> Unit,
) {
    var isFavorable by remember {
        mutableStateOf(false)
    }
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .size(100.dp, 230.dp)
            .clickable {
                onPlantClick.invoke(price, plantName, imageId)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = modifier.size(180.dp)
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Plant Pic",
                    modifier = modifier
                        .align(Alignment.Center)
                        .padding(top = 16.dp)
                )
                Image(
                    painter = painterResource(
                        id = if (!isFavorable)
                            R.drawable.ic_baseline_favorite_border_black
                        else
                            R.drawable.ic_baseline_favorite_filled
                    ),
                    contentDescription = "Icon Favorite",
                    modifier = modifier
                        .align(Alignment.TopEnd)
                        .size(30.dp)
                        .padding(end = 6.dp)
                        .clickable { isFavorable = !isFavorable }
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier.padding(8.dp, 0.dp)
            ) {
                Text(text = plantName, style = MaterialTheme.typography.body2)
                Text(text = "$price $", fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
private fun RoundedPicture(painter: Painter, modifier: Modifier) {
    Image(
        painter = painter,
        contentDescription = "Profile Picture",
        modifier = modifier
            .size(30.dp)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
    )
}

@Preview
@Composable
fun DefaultHomeTabPreview() {
    val categories = mutableListOf(
        Properties("Green Plants", R.drawable.green_plant),
        Properties("Desert Plants", R.drawable.desert_plant),
        Properties("Watering Plants", R.drawable.watering_plants),
        Properties("Vegetables", R.drawable.tomato),
    )
    val purchaseInfo = mutableListOf(
        PurchaseInfo("Yucca", 80L, R.drawable.yuccaplant),
        PurchaseInfo("Shamdani", 75L, R.drawable.shamdani),
        PurchaseInfo("Anjiri", 120L, R.drawable.anjiri),
        PurchaseInfo("Barg Pahn", 40L, R.drawable.bargpahn)
    )
    ComposePracticePlantsTheme {
        HomeTab(Modifier, categories, purchaseInfo) { _, _, _ ->
        }
    }
}
