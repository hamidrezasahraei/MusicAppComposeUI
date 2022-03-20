package me.hamsah.musiccompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.hamsah.musiccompose.ui.theme.DarkBackground
import me.hamsah.musiccompose.ui.theme.Silver
import me.hamsah.musiccompose.ui.theme.TextGrey
import me.hamsah.musiccompose.ui.theme.Typography

@Composable
fun BrowserScreen() {
    Box(
        modifier = Modifier
            .background(DarkBackground)
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Column {
            HeaderSection()
            ChipsSection(listOf("All", "Videos", "MP3s", "Albums"))
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Browser",
            style = Typography.h1,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_qr_code_scanner),
            contentDescription = "QR Code Scanner",
            modifier = Modifier
                .clip(CircleShape)
                .padding(end = 8.dp)
                .border(1.dp, Silver, CircleShape)
                .padding(4.dp)
                .size(24.dp),
            tint = Silver
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_account_circle),
            contentDescription = "Profile",
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Silver, CircleShape)
                .padding(4.dp)
                .size(24.dp),
            tint = Silver
        )
    }
}

@Composable
fun ChipsSection(chips: List<String>) {
    var selectedRowIndex by remember {
        mutableStateOf(0)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        chips.forEachIndexed { index, item ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(
                        start = 8.dp, top = 16.dp, bottom = 16.dp
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f)
                    .clickable { selectedRowIndex = index }
                    .background(if (selectedRowIndex == index) Silver else Color.Transparent)
                    .padding(12.dp)
            ) {
                Text(
                    text = item,
                    color = if (selectedRowIndex == index) DarkBackground else TextGrey
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BrowserScreen()
}