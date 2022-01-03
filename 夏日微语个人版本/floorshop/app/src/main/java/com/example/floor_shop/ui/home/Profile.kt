

package com.example.floor_shop.ui.home

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floor_shop.ui.theme.JetsnackTheme
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
            .padding(24.dp)
    ) {
        Image(
            painterResource(R.drawable.empty_state_search),
            contentDescription = null
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.work_in_progress),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.grab_beverage),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MeListTopBar() {
    Row(
        Modifier
            .background(color = Color(248, 246, 246, 50))
            .fillMaxWidth()
            .height(224.dp)
            .statusBarsPadding()
    ) {
        Image(
            painterResource(id = R.drawable.avatar_rengwuxian), contentDescription = "Me",
            Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 24.dp)
                .clip(RoundedCornerShape(6.dp))
                .size(64.dp)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                "test",
                Modifier.padding(top = 64.dp),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = JetsnackTheme.colors.textPrimary
            )
            Text(
                "账号id：coderpwh",
                Modifier.padding(top = 16.dp),
                fontSize = 14.sp,
                color = JetsnackTheme.colors.textSecondary
            )
            Text(
                "+ 状态",
                Modifier
                    .padding(top = 16.dp)
                    .border(1.dp, Color(22,22,22), RoundedCornerShape(50))
                    .padding(8.dp, 2.dp),
                fontSize = 16.sp,
                color = Color(22,22,22)
            )
        }
        Icon(
            painterResource(id = R.drawable.ic_qrcode), contentDescription = "qrcode",
            Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 20.dp)
                .size(14.dp),
            tint = Color(22,22,22)
        )
        Icon(
            painterResource(R.drawable.ic_arrow_more), contentDescription = "更多",
            Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
                .size(16.dp),
            tint = Color(22,22,22)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MeListTopBarPreview() {
    MeListTopBar()
}

@Composable
fun MeListItem(
    @DrawableRes icon: Int,
    title: String,
    modifier: Modifier = Modifier,
    badge: @Composable (() -> Unit)? = null,
    endBadge: @Composable (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(icon), "title", Modifier
                .padding(12.dp, 8.dp, 8.dp, 8.dp)
                .size(36.dp)
                .padding(8.dp)
        )
        Text(
            title,
            fontSize = 17.sp,
            color = JetsnackTheme.colors.textPrimary
        )
        badge?.invoke()
        Spacer(Modifier.weight(1f))
        endBadge?.invoke()
        Icon(
            painterResource(R.drawable.ic_arrow_more), contentDescription = "更多",
            Modifier
                .padding(0.dp, 0.dp, 12.dp, 0.dp)
                .size(16.dp),
            tint = JetsnackTheme.colors.brand
        )
    }
}

@Composable
fun MeList() {
    Box(
        Modifier
            .background(color = Color(247, 242, 242, 255))
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .background(color = Color(250, 243, 243, 255))
                .fillMaxWidth()
        ) {
            MeListTopBar()
            Spacer(
                Modifier
                    .background(JetsnackTheme.colors.background)
                    .fillMaxWidth()
                    .height(8.dp)
            )
            MeListItem(R.drawable.ic_pay, "支付")
            Spacer(
                Modifier
                    .background(JetsnackTheme.colors.background)
                    .fillMaxWidth()
                    .height(8.dp)
            )
            MeListItem(R.drawable.shouchan, "我的收藏")
            Divider(startIndent = 56.dp, color = JetsnackTheme.colors.uiBackground, thickness = 0.8f.dp)
            MeListItem(R.drawable.guanzu, "我的关注")
            Divider(startIndent = 56.dp, color = JetsnackTheme.colors.uiBackground, thickness = 0.8f.dp)
            MeListItem(R.drawable.yh, "优惠劵")
            Divider(startIndent = 56.dp, color = JetsnackTheme.colors.uiBackground, thickness = 0.8f.dp)
            MeListItem(R.drawable.souhou, "售后")
            Spacer(
                Modifier
                    .background(JetsnackTheme.colors.background)
                    .fillMaxWidth()
                    .height(8.dp)
            )
            MeListItem(R.drawable.ic_settings, "设置")
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ProfilePreview() {
    JetsnackTheme {
        Profile()
    }
}
