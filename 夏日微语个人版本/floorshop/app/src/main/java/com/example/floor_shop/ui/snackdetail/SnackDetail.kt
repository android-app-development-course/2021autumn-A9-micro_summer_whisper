

package com.example.floor_shop.ui.snackdetail

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import floor_shop.R
import com.example.floor_shop.model.Snack
import com.example.floor_shop.model.SnackCollection
import com.example.floor_shop.model.SnackRepo
import com.example.floor_shop.ui.components.JetsnackButton
import com.example.floor_shop.ui.components.JetsnackDivider
import com.example.floor_shop.ui.components.JetsnackSurface
import com.example.floor_shop.ui.components.QuantitySelector
import com.example.floor_shop.ui.components.SnackCollection
import com.example.floor_shop.ui.components.SnackImage
import com.example.floor_shop.ui.theme.JetsnackTheme
import com.example.floor_shop.ui.theme.Neutral8
import com.example.floor_shop.ui.utils.formatPrice
import com.example.floor_shop.ui.utils.mirroringBackIcon
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun SnackDetail(
    snackId: Long,
    upPress: () -> Unit
) {
    val snack = remember(snackId) { SnackRepo.getSnack(snackId) }
    val related = remember(snackId) { SnackRepo.getRelated(snackId) }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(related, scroll)
        Title(snack, scroll.value)
        Image(snack.imageUrl, scroll.value)
        Up(upPress)
        CartBottomBar(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(JetsnackTheme.colors.tornado1))
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Neutral8.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = JetsnackTheme.colors.iconInteractive,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}

@Composable
private fun Body(
    related: List<SnackCollection>,
    scroll: ScrollState
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            JetsnackSurface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(ImageOverlap))
                    Spacer(Modifier.height(TitleHeight))

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.detail_header),
                        style = MaterialTheme.typography.overline,
                        color = JetsnackTheme.colors.textHelp,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(16.dp))
                    var seeMore by remember { mutableStateOf(true) }
                    Text(
                        text = stringResource(R.string.detail_placeholder),
                        style = MaterialTheme.typography.body1,
                        color = JetsnackTheme.colors.textHelp,
                        maxLines = if (seeMore) 5 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                        modifier = HzPadding
                    )
                    val textButton = if (seeMore) {
                        stringResource(id = R.string.see_more)
                    } else {
                        stringResource(id = R.string.see_less)
                    }
                    Text(
                        text = textButton,
                        style = MaterialTheme.typography.button,
                        textAlign = TextAlign.Center,
                        color = JetsnackTheme.colors.textLink,
                        modifier = Modifier
                            .heightIn(20.dp)
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .clickable {
                                seeMore = !seeMore
                            }
                    )
                    Spacer(Modifier.height(40.dp))
                    Text(
                        text = stringResource(R.string.ingredients),
                        style = MaterialTheme.typography.overline,
                        color = JetsnackTheme.colors.textHelp,
                        modifier = HzPadding
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.ingredients_list),
                        style = MaterialTheme.typography.body1,
                        color = JetsnackTheme.colors.textHelp,
                        modifier = HzPadding
                    )

                    Spacer(Modifier.height(16.dp))
                    JetsnackDivider()

                    related.forEach { snackCollection ->
                        key(snackCollection.id) {
                            SnackCollection(
                                snackCollection = snackCollection,
                                onSnackClick = { },
                                highlight = false
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = BottomBarHeight)
                            .navigationBarsPadding(start = false, end = false)
                            .height(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Title(snack: Snack, scroll: Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .graphicsLayer { translationY = offset }
            .background(color = JetsnackTheme.colors.uiBackground)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = snack.name,
            style = MaterialTheme.typography.h4,
            color = JetsnackTheme.colors.textSecondary,
            modifier = HzPadding
        )
        Text(
            text = snack.tagline,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 20.sp,
            color = JetsnackTheme.colors.textHelp,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = formatPrice(snack.price),
            style = MaterialTheme.typography.h6,
            color = JetsnackTheme.colors.textPrimary,
            modifier = HzPadding
        )

        Spacer(Modifier.height(8.dp))
        JetsnackDivider()
    }
}

@Composable
private fun Image(
    imageUrl: String,
    scroll: Int
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFraction = (scroll / collapseRange).coerceIn(0f, 1f)

    CollapsingImageLayout(
        collapseFraction = collapseFraction,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        SnackImage(
            imageUrl = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

@Composable
private fun CartBottomBar(modifier: Modifier = Modifier) {
    val (count, updateCount) = remember { mutableStateOf(1) }
    JetsnackSurface(modifier) {
        Column {
            JetsnackDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .navigationBarsPadding(start = false, end = false)
                    .then(HzPadding)
                    .heightIn(min = BottomBarHeight)
            ) {
                QuantitySelector(
                    count = count,
                    decreaseItemCount = { if (count > 0) updateCount(count - 1) },
                    increaseItemCount = { updateCount(count + 1) }
                )
                Spacer(Modifier.width(16.dp))
                JetsnackButton(
                    onClick = { /* todo */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.add_to_cart),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun SnackDetailPreview() {
    JetsnackTheme {
        SnackDetail(
            snackId = 1L,
            upPress = { }
        )
    }
}
