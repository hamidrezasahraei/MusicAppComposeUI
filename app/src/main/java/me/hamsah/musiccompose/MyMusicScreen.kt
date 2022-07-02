package me.hamsah.musiccompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import me.hamsah.musiccompose.ui.ActivityItemData
import me.hamsah.musiccompose.ui.HeaderSection
import me.hamsah.musiccompose.ui.theme.DarkBackground
import me.hamsah.musiccompose.ui.theme.Silver
import me.hamsah.musiccompose.ui.theme.Typography
import me.hamsah.musiccompose.ui.theme.albumItemDarkColor
import me.hamsah.musiccompose.ui.theme.albumItemLightColor
import me.hamsah.musiccompose.ui.theme.albumItemMediumColor

@Composable
fun MyMusicScreen() {
    Column(
        modifier = Modifier
            .background(DarkBackground)
            .fillMaxSize()
            .padding(vertical = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderSection("My Music", Modifier.padding(horizontal = 32.dp))
        SubtitleSection("Your Downloads")
        AlbumSection()
        SubtitleSection("Your Activities")
        ActivitySection()
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
private fun SubtitleSection(title: String) {
    Text(
        text = title,
        style = Typography.caption,
        color = Color.LightGray,
        fontWeight = FontWeight(FontWeight.Thin.weight),
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
    )
}

@Composable
private fun AlbumSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            AlbumItem(painterResource(id = R.drawable.ic_playlist), "Playlists", "23 Songs")
            AlbumItem(painterResource(id = R.drawable.ic_mp3), "Mp3s", "3 Songs")
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            AlbumItem(painterResource(id = R.drawable.ic_video), "Videos", "No Videos")
            AlbumItem(painterResource(id = R.drawable.ic_album), "Albums", "7 Songs")
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
private fun AlbumItem(
    icon: Painter,
    title: String,
    subtitle: String
) {
    ConstraintLayout(modifier = Modifier.padding(vertical = 4.dp)) {

        val (card, canvas) = createRefs()
        val middleGuideline = createGuidelineFromEnd(80.dp)

        Canvas(
            modifier = Modifier
                .constrainAs(canvas) {
                    end.linkTo(middleGuideline)
                    top.linkTo(parent.top)
                }

        ) {
            drawRoundRect(
                color = albumItemDarkColor,
                size = Size(160f, 50f),
                cornerRadius = CornerRadius(16f)
            )
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(top = 8.dp, bottom = 8.dp)
                .aspectRatio(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                albumItemLightColor,
                                albumItemMediumColor
                            )
                        )
                    )
            ) {
                Icon(
                    painter = icon,
                    contentDescription = "Playlist",
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(albumItemDarkColor)
                        .padding(8.dp)
                        .size(32.dp),
                    tint = Silver
                )
                Text(
                    text = title,
                    color = Silver,
                    style = Typography.body1,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = subtitle,
                    color = Silver,
                    fontWeight = FontWeight(FontWeight.Thin.weight),
                    style = Typography.caption,
                    fontSize = TextUnit(10f, TextUnitType.Sp),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
private fun ActivitySection() {
    activityList().run {
        forEachIndexed { index, item ->
            ActivityItem(icon = item.icon, title = item.title)
            if (index != lastIndex) {
                Divider(
                    color = Silver.copy(alpha = 0.12f),
                    thickness = 1.dp,
                    startIndent = 64.dp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ActivityItem(icon: Int, title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Silver
        )
        Text(
            text = title,
            color = Silver,
            style = Typography.body1,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_right_chevron),
            contentDescription = title,
            tint = Silver,
            modifier = Modifier.size(12.dp)
        )
    }
}

private fun activityList(): List<ActivityItemData> {
    return listOf(
        ActivityItemData("Your Playlists", R.drawable.ic_playlist),
        ActivityItemData("Liked Songs", R.drawable.ic_like),
        ActivityItemData("Followed Artists", R.drawable.ic_artist),
        ActivityItemData("History", R.drawable.ic_history)
    )
}

@Composable
@Preview
fun PreviewMyMusicScreen() {
    MyMusicScreen()
}