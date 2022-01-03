package com.micro_summer_whisper.flower_supplier.common

import android.content.Context
import android.util.AttributeSet
import com.qmuiteam.qmui.util.QMUIViewHelper

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable

import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout


class QMUIRoundConstraintLayout : ConstraintLayout {
    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val bg = QMUIRoundButtonDrawable.fromAttributeSet(context, attrs, defStyleAttr)
        QMUIViewHelper.setBackgroundKeepingPadding(this, bg)
    }
}
