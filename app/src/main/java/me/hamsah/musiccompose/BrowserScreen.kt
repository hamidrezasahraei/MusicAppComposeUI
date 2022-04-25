package me.hamsah.musiccompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.hamsah.musiccompose.ui.Album
import me.hamsah.musiccompose.ui.HeaderSection
import me.hamsah.musiccompose.ui.LiveRadio
import me.hamsah.musiccompose.ui.standardQuadFromTo
import me.hamsah.musiccompose.ui.theme.Beige1
import me.hamsah.musiccompose.ui.theme.Beige2
import me.hamsah.musiccompose.ui.theme.Beige3
import me.hamsah.musiccompose.ui.theme.BlueViolet1
import me.hamsah.musiccompose.ui.theme.BlueViolet2
import me.hamsah.musiccompose.ui.theme.BlueViolet3
import me.hamsah.musiccompose.ui.theme.DarkBackground
import me.hamsah.musiccompose.ui.theme.DarkBackgroundOpacity
import me.hamsah.musiccompose.ui.theme.LightGreen1
import me.hamsah.musiccompose.ui.theme.LightGreen2
import me.hamsah.musiccompose.ui.theme.LightGreen3
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
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection("Browser")
            ChipsSection(listOf("All", "Videos", "MP3s", "Albums"))
            TrendingSection()
            LiveRadioSection(
                liveRadios = listOf(
                    LiveRadio(
                        radioTitle = "Ava",
                        darkColor = Beige3,
                        mediumColor = Beige2,
                        lightColor = Beige1
                    ),
                    LiveRadio(
                        radioTitle = "Javan",
                        darkColor = LightGreen3,
                        mediumColor = LightGreen2,
                        lightColor = LightGreen1
                    ),
                    LiveRadio(
                        radioTitle = "Javan",
                        darkColor = BlueViolet3,
                        mediumColor = BlueViolet2,
                        lightColor = BlueViolet1
                    )
                )
            )
            AlbumSection(
                albums = listOf(
                    Album(
                        title = "Eshghe Shirinam",
                        singer = "Ahllam",
                        image = R.drawable.ahllam
                    ),
                    Album(
                        title = "Party Life",
                        singer = "Deejay Al",
                        image = R.drawable.party
                    ),
                    Album(
                        title = "Zakhare Asli",
                        singer = "Sohrab MJ",
                        image = R.drawable.mj
                    ),
                    Album(
                        title = "Paeezi",
                        singer = "Satin",
                        image = R.drawable.satin
                    )
                )
            )
        }
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

@Composable
fun TrendingSection() {
    Column {
        SectionHeader(title = "Trending", subtitle = "Music", action = "See All")
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.shadmehr),
                contentDescription = "First trending music",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(2f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.zedbazi),
                    contentDescription = "Second trending music",
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.donya),
                    contentDescription = "Third trending music",
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

@Composable
fun LiveRadioSection(liveRadios: List<LiveRadio>) {
    Column {
        SectionHeader(title = "Live", subtitle = "Radio")
        LazyRow {
            items(liveRadios.size) {
                LiveRadioItem(liveRadio = liveRadios[it])
            }
        }
    }
}

@Composable
fun LiveRadioItem(liveRadio: LiveRadio) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(8.dp)
            .width(128.dp)
            .aspectRatio(1.1f)
            .clip(RoundedCornerShape(8.dp))
            .background(liveRadio.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = liveRadio.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = liveRadio.lightColor
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = liveRadio.radioTitle,
                style = Typography.h1,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String, action: String? = null) {
    Row(
        Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = title,
            style = Typography.h2,
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = subtitle,
            style = Typography.body2,
            modifier = Modifier
                .alignByBaseline()
                .weight(1f)
                .padding(start = 4.dp)
        )
        if (!action.isNullOrEmpty()) {
            Text(
                text = action,
                style = Typography.subtitle1,
                modifier = Modifier
                    .padding(4.dp)
                    .alignByBaseline()
            )
        }
    }
}

@Composable
fun AlbumSection(albums: List<Album>) {
    Column {
        SectionHeader(title = "Latest", subtitle = "Albums", action = "See All")
        LazyRow {
            items(albums.size) {
                AlbumItem(album = albums[it])
            }
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(modifier = Modifier.size(128.dp)) {
            Image(
                painter = painterResource(id = album.image),
                contentDescription = "Album",
                modifier = Modifier
                    .clip(CircleShape)
            )
            Canvas(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center)
            ) {
                drawCircle(
                    color = DarkBackgroundOpacity,
                    radius = 48f,
                    style = Fill
                )
                drawCircle(
                    color = DarkBackground,
                    radius = 32f,
                    style = Fill
                )
            }
        }
        Text(
            text = album.title,
            style = Typography.body1,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = album.singer,
            style = Typography.subtitle2,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BrowserScreen()
}