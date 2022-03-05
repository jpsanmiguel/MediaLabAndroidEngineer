package sanmi.labs.medialabandroidengineer.feature_user.util

import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class CustomDividerItemDecorator(
    @DrawableRes private val mDivider: Int,
    private val skipFirst: Int = 0,
    private val skipLast: Int = 0,
) : ItemDecoration() {

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerDrawable = ResourcesCompat.getDrawable(parent.resources, mDivider, null)
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in skipFirst..childCount - (skipLast + 1)) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop: Int = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + (dividerDrawable?.intrinsicHeight ?: 0)
            dividerDrawable?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            dividerDrawable?.draw(canvas)
        }
    }
}