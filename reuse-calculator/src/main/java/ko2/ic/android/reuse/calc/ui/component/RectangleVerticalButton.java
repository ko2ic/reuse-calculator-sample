package ko2.ic.android.reuse.calc.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class RectangleVerticalButton extends Button {

    public RectangleVerticalButton(Context context) {
        super(context);
    }

    public RectangleVerticalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int measureSpecWidth = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        int measureSpecHeight = MeasureSpec.makeMeasureSpec(widthSize * 2, MeasureSpec.EXACTLY);

        super.onMeasure(measureSpecWidth, measureSpecHeight);
    }

}
