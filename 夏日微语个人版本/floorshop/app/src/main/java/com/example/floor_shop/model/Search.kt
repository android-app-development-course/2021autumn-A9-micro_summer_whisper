

package com.example.floor_shop.model

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * A fake repo for searching.
 */
object SearchRepo {
    fun getCategories(): List<SearchCategoryCollection> = searchCategoryCollections
    fun getSuggestions(): List<SearchSuggestionGroup> = searchSuggestions

    suspend fun search(query: String): List<Snack> = withContext(Dispatchers.Default) {
        delay(200L) // simulate an I/O delay
        snacks.filter { it.name.contains(query, ignoreCase = true) }
    }
}

@Immutable
data class SearchCategoryCollection(
    val id: Long,
    val name: String,
    val categories: List<SearchCategory>
)

@Immutable
data class SearchCategory(
    val name: String,
    val imageUrl: String
)

@Immutable
data class SearchSuggestionGroup(
    val id: Long,
    val name: String,
    val suggestions: List<String>
)

/**
 * Static data
 */

private val searchCategoryCollections = listOf(
    SearchCategoryCollection(
        id = 0L,
        name = "分类",
        categories = listOf(
            SearchCategory(
                name = "韩式花束",
                imageUrl = "https://s4.ax1x.com/2021/12/29/Tgsp3F.jpg"
            ),
            SearchCategory(
                name = "花束鲜花",
                imageUrl = "https://s4.ax1x.com/2021/12/29/Tgs0Ds.jpg"
            ),
            SearchCategory(
                name = "零食花束",
                imageUrl = "https://s4.ax1x.com/2021/12/29/TgsoUx.jpg"
            ),
            SearchCategory(
                name = "花盒鲜花",
                imageUrl = "https://s4.ax1x.com/2021/12/29/TgsLxe.jpg"
            )
        )
    ),
    SearchCategoryCollection(
        id = 1L,
        name = "生活方式",
        categories = listOf(
            SearchCategory(
                name = "惊喜生日",
                imageUrl = "https://s4.ax1x.com/2021/12/29/TgyoLj.jpg"
            ),
            SearchCategory(
                name = "结婚纪念",
                imageUrl = "https://s4.ax1x.com/2021/12/29/TgyOYV.jpg"
            ),
            SearchCategory(
                name = "商务庆贺",
                imageUrl = "https://s4.ax1x.com/2021/12/29/TgyxlF.jpg"
            ),
            SearchCategory(
                name = "赠礼贵人",
                imageUrl = "https://s4.ax1x.com/2021/12/29/Tgyboq.jpg"
            ),
            SearchCategory(
                name = "问候长辈",
                imageUrl = "https://source.unsplash.com/IGfIGP5ONV0"
            ),
            SearchCategory(
                name = "友人聚会",
                imageUrl = "https://s4.ax1x.com/2021/12/29/TgyHwn.jpg"
            )
        )
    )
)

private val searchSuggestions = listOf(
    SearchSuggestionGroup(
        id = 0L,
        name = "历史搜索",
        suggestions = listOf(
            "月季",
            "百合"
        )
    ),
    SearchSuggestionGroup(
        id = 1L,
        name = "热门搜索",
        suggestions = listOf(
            "牡丹",
            "生日鲜花",
            "向日葵",
            "满天星",
            "罗拉红玫瑰",
            "粉雪山"
        )
    )
)
