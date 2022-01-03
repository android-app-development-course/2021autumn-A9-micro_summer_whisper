

package com.example.floor_shop.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
class Filter(
    val name: String,
    enabled: Boolean = false,
    val icon: ImageVector? = null
) {
    val enabled = mutableStateOf(enabled)
}
val filters = listOf(
    Filter(name = "玫瑰"),
    Filter(name = "蓝色妖姬"),
    Filter(name = "向日葵"),
    Filter(name = "百合"),
    Filter(name = "康乃馨")
)
val priceFilters = listOf(
    Filter(name = "$"),
    Filter(name = "$$"),
    Filter(name = "$$$"),
    Filter(name = "$$$$")
)
val sortFilters = listOf(
    Filter(name = "智能推荐 (default)", icon = Icons.Filled.Android),
    Filter(name = "评分", icon = Icons.Filled.Star),
    Filter(name = "字母排序", icon = Icons.Filled.SortByAlpha)
)

val categoryFilters = listOf(
    Filter(name = "韩式花束"),
    Filter(name = "花束鲜花"),
    Filter(name = "零食花束"),
    Filter(name = "花盒鲜花")
)
val lifeStyleFilters = listOf(
    Filter(name = "惊喜生日"),
    Filter(name = "结婚纪念"),
    Filter(name = "商务庆贺"),
    Filter(name = "赠礼贵人"),
    Filter(name = "问候长辈")
)

var sortDefault = sortFilters.get(0).name
