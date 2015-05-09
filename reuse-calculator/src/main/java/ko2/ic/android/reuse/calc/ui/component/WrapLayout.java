package ko2.ic.android.reuse.calc.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * WrapLayout.<br>
 * @author $Author$
 * @version $Revision$
 */
public class WrapLayout extends LinearLayout {

    public WrapLayout(Context context) {
        super(context);
    }

    public WrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int width = View.resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = View.resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        this.setMeasuredDimension(width, height);

        int childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.UNSPECIFIED);
        int childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED);

        measureChildren(childWidthSpec, childHeightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = this.getChildCount();

        // 行の高さ
        int rowHeight = 0;

        // 現在の行の幅
        int currentRowWidth = 0;

        int rowMaxWidth = this.getWidth();

        int top = 0;

        for (int i = 0; i < count; i++) {
            View child = this.getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                WrapLayout.LayoutParams lp = (WrapLayout.LayoutParams) child.getLayoutParams();

                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();

                int childTotaWidth = childWidth + lp.rightMargin + lp.leftMargin;
                int childTotalHeight = childHeight + lp.topMargin + lp.bottomMargin;

                if (rowMaxWidth < currentRowWidth + childTotaWidth) {
                    top += rowHeight < childTotalHeight ? childTotalHeight : rowHeight;
                    rowHeight = 0;
                    currentRowWidth = 0;
                }

                child.layout(currentRowWidth + lp.leftMargin, top + lp.topMargin, currentRowWidth + childWidth + lp.rightMargin, top + childHeight + lp.bottomMargin);

                rowHeight = rowHeight < childTotalHeight ? childTotalHeight : rowHeight;
                currentRowWidth += childTotaWidth;
            }
        }
    }

}
