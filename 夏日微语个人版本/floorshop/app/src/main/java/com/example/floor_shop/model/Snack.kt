

package com.example.floor_shop.model

import androidx.compose.runtime.Immutable

@Immutable
data class Snack(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    val tags: Set<String> = emptySet()
)

/**
 * Static data
 */

val snacks = listOf(
    Snack(
        id = 1L,
        name = "大花浓香月季",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i1/1684653495/O1CN011bgkiRfNYAQJMU5_!!1684653495.jpg",
        price = 299
    ),
    Snack(
        id = 2L,
        name = "大花藤本月季",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/1684653495/TB2xNCzuYorBKNjSZFjXXc_SpXa_!!1684653495.jpg",
        price = 399
    ),
    Snack(
        id = 3L,
        name = "北京天卉苑",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/2213154912/O1CN01jgTEVf1m9kCqSheZU_!!0-item_pic.jpg",
        price = 299
    ),
    Snack(
        id = 4L,
        name = "睡莲",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i3/29318314/O1CN01BexwIq2BHrV01p94z_!!0-saturn_solar.jpg",
        price = 299
    ),
    Snack(
        id = 5L,
        name = "天狼月季",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/60960562/O1CN01bJV3zx1G1RGqGXf9m_!!60960562.jpg",
        price = 499
    ),
    Snack(
        id = 6L,
        name = "蔓花园",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/3002719051/O1CN01MY7tSb2GjPVQxFenH_!!3002719051.jpg",
        price = 299
    ),
    Snack(
        id = 7L,
        name = "大花浓香月季",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i1/1684653495/O1CN011bgkiRfNYAQJMU5_!!1684653495.jpg",
        price = 1299
    ),
    Snack(
        id = 8L,
        name = "满天星花苗",
        tagline = "A tag line",
        imageUrl = "https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/2207929514536/O1CN01ZUkE761jNXGd1Zewg_!!0-item_pic.jpg",
        price = 99
    ),
    Snack(
        id = 9L,
        name = "橙粉色凌霄花",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2988946131/O1CN01eAc5TP1vA2xOSj7wy_!!2988946131.jpg",
        price = 549
    ),
    Snack(
        id = 10L,
        name = "白玉兰花树苗",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/2219697791/O1CN0127QKDWpXDa6zt2S_!!2219697791.jpg",
        price = 299
    ),
    Snack(
        id = 11L,
        name = "重瓣矮牵牛",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/3002719051/O1CN01MY7tSb2GjPVQxFenH_!!3002719051.jpg",
        price = 299
    ),
    Snack(
        id = 12L,
        name = "暖暖半岛",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/409201288/O1CN01APKXdm1LNwhoH16aH_!!409201288.jpg",
        price = 299
    ),
    Snack(
        id = 13L,
        name = "虹越月季花苗",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i1/341955249/O1CN010quaGL1oe5dom9rK7_!!341955249.jpg",
        price = 299
    ),
    Snack(
        id = 14L,
        name = "四季草莓盆栽",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/2219697791/O1CN01ljaRRv27QKSJLskQT_!!2219697791.jpg",
        price = 299
    ),
    Snack(
        id = 15L,
        name = "月季丰花灌木",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/60960562/O1CN01Aw7lRa1G1RFXeIqGl_!!60960562.jpg",
        price = 299
    ),
    Snack(
        id = 16L,
        name = "蓝花诗人直立月季",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/104365526/O1CN01mnPOfa1qgxOBY2Unc_!!0-item_pic.jpg",
        price = 299
    ),
    Snack(
        id = 17L,
        name = "抹茶棒棒糖花苗",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/341955249/O1CN01AoWDgY1oe5V0ix58v_!!341955249.jpg",
        price = 299
    ),
    Snack(
        id = 18L,
        name = "龙船花盆栽",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/2564640280/O1CN01gKkme71DwHb8yOt8w_!!2564640280.jpg",
        price = 299
    ),
    Snack(
        id = 19L,
        name = "重瓣八仙花",
        imageUrl = "https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/104365526/O1CN01lnrKQy1qgxOCD8Jjk_!!0-item_pic.jpg",
        price = 299
    ),


    /*    海蒂的花园 伊芙飞溅乙女心蓝花诗人直立月季阳台花卉植物盆栽苗,//g-search3.alicdn.com/img/bao/uploaded/i4/i3/104365526/O1CN01mnPOfa1qgxOBY2Unc_!!0-item_pic.jpg

海蒂的花园 水天一色绣球大花平顶重瓣八仙花阳台庭院花卉盆栽苗,//g-search2.alicdn.com/img/bao/uploaded/i4/i4/104365526/O1CN01lnrKQy1qgxOCD8Jjk_!!0-item_pic.jpg
19元6颗6年玫瑰花苗室内阳台花卉月季盆栽四季开花庭院观花绿植物,//g-search1.alicdn.com/img/bao/uploaded/i4/i3/2207396829312/O1CN01eHzQ8R2IewrwagGfH_!!2207396829312.jpg
19元6颗玫瑰花苗大花月季四季开花带花苞植物室内外观花阳台盆栽,//g-search1.alicdn.com/img/bao/uploaded/i4/i4/2207396829312/O1CN01aryJBu2Iews1I6Y4p_!!2207396829312.jpg
河本新品绊月季日本品种中香切花大花期长抗病对版多季盛开,//g-search1.alicdn.com/img/bao/uploaded/i4/i1/1684653495/O1CN01fXXuHd1bgkwdHSU6a_!!1684653495.jpg
推荐藤本粉色达芬奇月季四季开花机器庭院阳台爬藤蔷薇花小苗,//g-search1.alicdn.com/img/bao/uploaded/i4/i1/1684653495/O1CN01vp5MGP1bgkvH1oc1Q_!!1684653495.png
清风园艺庭院花园植物大型灌木中华木绣球花 中华斗球  扦插小苗,//g-search1.alicdn.com/img/bao/uploaded/i4/i2/52579293/O1CN01LHniwF2IWFJdWyQfk_!!52579293.jpg
半边莲盆栽六倍利花苗带花苞阳台庭院花卉趣味美观净化空气开花苗,//g-search2.alicdn.com/img/bao/uploaded/i4/i2/2219697791/O1CN01crjSCZ27QKO4wNrbe_!!2219697791.jpg
虹越铁线莲大苗爬藤植物盆栽乌托邦铃铛约瑟芬蓝光早晚花卉苗对版,//g-search3.alicdn.com/img/bao/uploaded/i4/i1/341955249/O1CN01cKGUTc1oe5a5MvtOO_!!341955249.jpg
菊花盆栽四季开花清新菊小米菊美人菊带花苞阳台客厅小雏菊除甲醛,//g-search3.alicdn.com/img/bao/uploaded/i4/i3/2219697791/O1CN010rMBMn27QKS7bWNC6_!!2219697791.jpg
球菊盆栽小苗糖果菊甜心花卉冰粉菊花千头菊带花苞彩色乒乓菊花苗,//g-search3.alicdn.com/img/bao/uploaded/i4/i1/886299707/O1CN01BlE5s32LZrInOykB4_!!886299707.jpg
欧洲木绣球玫瑰白色木本绣球八仙花荚蒾耐晒花园植物好养包活花卉,//g-search3.alicdn.com/img/bao/uploaded/i4/i1/886299707/O1CN01IkZFXE2LZrHv5Nwex_!!886299707.jpg
云南新鲜绣球花婚礼婚庆搭配家用送人节日用花办公室插花一件包邮,//g-search3.alicdn.com/img/bao/uploaded/i4/i3/3056437851/O1CN01FBwG6A27ro9VBm4nj_!!0-item_pic.jpg*/
    Snack(
        id = 20L,
        name = "果菊甜心花卉",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/886299707/O1CN01BlE5s32LZrInOykB4_!!886299707.jpg",
        price = 299
    ),
    Snack(
        id = 21L,
        name = "达芬奇月季",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i1/1684653495/O1CN01vp5MGP1bgkvH1oc1Q_!!1684653495.png",
        price = 299
    ),
    Snack(
        id = 22L,
        name = "虹越铁线莲",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/341955249/O1CN01cKGUTc1oe5a5MvtOO_!!341955249.jpg",
        price = 299
    ),
    Snack(
        id = 23L,
        name = "中华木绣球花",
        tagline = "A tag line",
        imageUrl = "https://g-search1.alicdn.com/img/bao/uploaded/i4/i2/52579293/O1CN01LHniwF2IWFJdWyQfk_!!52579293.jpg",
        price = 299
    ),
    Snack(
        id = 24L,
        name = "云南新鲜绣球花",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/3056437851/O1CN01FBwG6A27ro9VBm4nj_!!0-item_pic.jpg",
        price = 299
    ),
    Snack(
        id = 25L,
        name = "欧洲木绣球玫瑰",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/3056437851/O1CN01FBwG6A27ro9VBm4nj_!!0-item_pic.jpg",
        price = 299
    ),
    Snack(
        id = 26L,
        name = "蓝花鼠尾草盆栽",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i3/2219697791/O1CN01pLfZHf27QKTYTUZcv_!!2219697791.jpg",
        price = 299
    ),
    Snack(
        id = 27L,
        name = "切花月季花苗",
        tagline = "A tag line",
        imageUrl = "https://g-search2.alicdn.com/img/bao/uploaded/i4/i3/885409254/O1CN01vxmtXt2IENtsDAhnA_!!885409254.jpg",
        price = 299
    ),
    Snack(
        id = 28L,
        name = "龙船花盆栽",
        tagline = "A tag line",
        imageUrl = "https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/20247975/O1CN01h5LOrT28mbGAkt2Te_!!20247975.jpg",
        price = 299
    )
)
