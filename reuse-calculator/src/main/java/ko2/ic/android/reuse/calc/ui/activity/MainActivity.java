package ko2.ic.android.reuse.calc.ui.activity;

import java.util.List;

import ko2.ic.android.common.core.utils.LogUtil;
import ko2.ic.android.common.roboguice.activity.AbstractRoboActivity;
import ko2.ic.android.reuse.calc.R;
import ko2.ic.android.reuse.calc.domain.DisplayListener;
import ko2.ic.android.reuse.calc.domain.model.Calculator;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Number;
import ko2.ic.android.reuse.calc.domain.valueobject.datatype.enums.Operation;
import ko2.ic.android.reuse.calc.ui.component.WrapLayout;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.ClipData;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.inject.Inject;

@ContentView(R.layout.content_main)
public class MainActivity extends AbstractRoboActivity {

    @InjectView(R.id.display)
    private TextView display;

    @InjectView(R.id.displayOp)
    private TextView displayOp;

    @InjectView(R.id.zero)
    private Button zero;

    @InjectView(R.id.doublezero)
    private Button doublezero;

    @InjectView(R.id.one)
    private Button one;

    @InjectView(R.id.two)
    private Button two;

    @InjectView(R.id.three)
    private Button three;

    @InjectView(R.id.four)
    private Button four;

    @InjectView(R.id.five)
    private Button five;

    @InjectView(R.id.six)
    private Button six;

    @InjectView(R.id.seven)
    private Button seven;

    @InjectView(R.id.eight)
    private Button eight;

    @InjectView(R.id.nine)
    private Button nine;

    @InjectView(R.id.comma)
    private Button comma;

    @InjectView(R.id.plus)
    private Button plus;

    @InjectView(R.id.minus)
    private Button minus;

    @InjectView(R.id.times)
    private Button times;

    @InjectView(R.id.divide)
    private Button divide;

    @InjectView(R.id.percent)
    private Button percent;

    @InjectView(R.id.sign)
    private Button sign;

    @InjectView(R.id.equal)
    private Button equal;

    @InjectView(R.id.allclear)
    private Button allclear;

    @InjectView(R.id.clear)
    private Button clear;

    @InjectView(R.id.dustbox)
    private Button dustBox;

    @InjectView(R.id.reuseGroupArea)
    private RelativeLayout reuseGroupArea;

    @Inject
    private WindowManager windowManager;

    @Inject
    private Calculator calculator;

    // private int reusePointX = 0;

    private int reusePointY = 0;

    private final OnTouchListener moving = new OnTouchListener() {

        private float downX;

        private float downY;

        private int downLeftMargin;

        private int downTopMargin;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                onDown(event, param);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                onMove(v, event, param);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                onUp(v, param);
            }
            zero.setVisibility(View.VISIBLE);
            dustBox.setVisibility(View.GONE);
            return false;
        }

        private void onUp(View v, final ViewGroup.MarginLayoutParams param) {
            int[] location = new int[2];
            zero.getLocationInWindow(location);
            int x = zero.getWidth();
            int y = zero.getHeight();

            if (param.leftMargin - x <= location[0] && param.leftMargin + x >= location[0]) {
                if (param.topMargin - y <= location[1] && param.topMargin + y >= location[1]) {
                    reuseGroupArea.removeView(v);
                }
            }
        }

        private void onMove(View v, MotionEvent event, final ViewGroup.MarginLayoutParams param) {
            int moveWidth = (int) (event.getRawX() - downX);
            int moveHeight = (int) (event.getRawY() - downY);
            param.leftMargin = downLeftMargin + moveWidth;
            param.topMargin = downTopMargin + moveHeight;

            v.layout(param.leftMargin, param.topMargin, param.leftMargin + v.getWidth(), param.topMargin + v.getHeight());
            if (downLeftMargin == 0 && downTopMargin == 0) {
                // reusePointX = 0;
                reusePointY = 0;
            }
        }

        private void onDown(MotionEvent event, final ViewGroup.MarginLayoutParams param) {
            zero.setVisibility(View.GONE);
            dustBox.setVisibility(View.VISIBLE);

            downX = event.getRawX();
            downY = event.getRawY();

            downLeftMargin = param.leftMargin;
            downTopMargin = param.topMargin;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calculator.init(new DisplayListener() {
            @Override
            public void display(String result) {

                display.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.reuse_text));
                Paint paint = new Paint();

                int viewWidth = display.getWidth() - 30;
                float textSize = display.getTextSize();

                paint.setTextSize(textSize);
                float textWidth = paint.measureText(result);

                while (viewWidth < textWidth) {

                    if (10f >= textSize) {
                        textSize = 10f;
                        break;
                    }
                    textSize--;

                    paint.setTextSize(textSize);
                    textWidth = paint.measureText(result);
                }
                display.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                display.setText(result);
            }

        });

        display.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                TextView srcView = (TextView) v;
                ViewGroup srcParent = (ViewGroup) srcView.getParent();
                TextView distView = (TextView) event.getLocalState();
                int action = event.getAction();
                if (action == DragEvent.ACTION_DRAG_STARTED) {
                    distView.setTextColor(getResources().getColor(R.color.drag_start));
                    srcParent.setBackgroundResource(R.drawable.textview_display_drag);
                    LogUtil.d(MainActivity.class, "ACTION_DRAG_STARTED");
                } else if (action == DragEvent.ACTION_DRAG_ENTERED) {
                    LogUtil.d(MainActivity.class, String.format("ACTION_DRAG_ENTERED:%8.3f, %8.3f", event.getX(), event.getY()));
                } else if (action == DragEvent.ACTION_DRAG_EXITED) {
                    LogUtil.d(MainActivity.class, String.format("ACTION_DRAG_EXITED:%8.3f, %8.3f", event.getX(), event.getY()));
                } else if (action == DragEvent.ACTION_DRAG_ENDED) {
                    distView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    srcParent.setBackgroundResource(R.drawable.textview_display);
                    LogUtil.d(MainActivity.class, "ACTION_DRAG_ENDED");
                } else if (action == DragEvent.ACTION_DROP) {
                    ClipData clipData = event.getClipData();
                    String str = clipData.getItemAt(0).getText().toString();
                    str = str.replaceAll(" ", "");
                    if (isNumeric(str)) {
                        calculator.onDropNumber(str);
                    } else {
                        if (str.contains("%")) {
                            calculator.onDropPercent(str);
                        } else if (str.equals(Operation.PLUS.getValue())) {
                            calculator.onButtonOp(Operation.PLUS);
                            displayOp.setText(Operation.PLUS.getValue());
                        } else if (str.equals(Operation.MINUS.getValue())) {
                            calculator.onButtonOp(Operation.MINUS);
                            displayOp.setText(Operation.MINUS.getValue());
                        } else if (str.equals(Operation.TIMES.getValue())) {
                            calculator.onButtonOp(Operation.TIMES);
                            displayOp.setText(Operation.TIMES.getValue());
                        } else if (str.equals(Operation.DIVIDE.getValue())) {
                            calculator.onButtonOp(Operation.DIVIDE);
                            displayOp.setText(Operation.DIVIDE.getValue());
                        } else if (str.equals("=")) {
                            calculator.onButtonEqual();
                            displayOp.setText("");
                        }

                    }
                } else if (action == DragEvent.ACTION_DRAG_LOCATION) {
                    LogUtil.d(MainActivity.class, String.format("ACTION_DRAG_LOCATION:%8.3f, %8.3f", event.getX(), event.getY()));
                }

                return true;
            }
        });

        allclear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonAllClear();
                displayOp.setText("");
                reuseGroupArea.removeAllViews();
                // reusePointX = 0;
                reusePointY = 0;
            }
        });

        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonAllClear();
                displayOp.setText("");
            }
        });

        zero.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.ZERO);
            }
        });

        doublezero.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.DOUBLE_ZERO);
            }
        });

        one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.ONE);
            }
        });

        two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.TWO);
            }
        });

        three.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.THREE);
            }
        });

        four.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.FOUR);
            }
        });

        five.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.FIVE);
            }
        });

        six.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.SIX);
            }
        });

        seven.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.SEVEN);
            }
        });

        eight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.EIGHT);
            }
        });

        nine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonNumber(Number.NINE);
            }
        });

        comma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonComma();
            }
        });

        plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayOp.setText(Operation.PLUS.getValue());
                calculator.onButtonOp(Operation.PLUS);
            }
        });

        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayOp.setText(Operation.MINUS.getValue());
                calculator.onButtonOp(Operation.MINUS);
            }
        });

        times.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayOp.setText(Operation.TIMES.getValue());
                calculator.onButtonOp(Operation.TIMES);
            }
        });

        divide.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayOp.setText(Operation.DIVIDE.getValue());
                calculator.onButtonOp(Operation.DIVIDE);
            }
        });

        percent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.onButtonPercent();
            }
        });

        sign.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.changeSign();
            }
        });

        equal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayOp.setText("");
                List<String> list = calculator.onButtonEqual();
                if (list.size() == 0) {
                    return;
                }

                WrapLayout outerArea = createOuterArea();

                Display screen = windowManager.getDefaultDisplay();
                Point screenPoint = new Point();
                screen.getSize(screenPoint);
                int screenSize = screenPoint.x;

                for (String string : list) {
                    final TextView textView = createTextCanBeMoved(screenSize, string);
                    outerArea.addView(textView);
                }

                adjustSize(outerArea, screenSize);

                outerArea.setOnTouchListener(moving);

                RelativeLayout.LayoutParams reuseGroupAreaParam = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                reuseGroupAreaParam.setMargins(0, reusePointY, 0, 0);
                reuseGroupArea.addView(outerArea, reuseGroupAreaParam);

                // reusePointX += 30;
                reusePointY += 30;
            }

            private void adjustSize(WrapLayout outerArea, int screenSize) {
                int allWidth = 0;
                int tempAllWidth = 0;
                int allHeight = 0;
                int count = outerArea.getChildCount();
                for (int i = 0; i < count; i++) {
                    TextView view = (TextView) outerArea.getChildAt(i);
                    float textSize = view.getTextSize();
                    Paint paint = new Paint();
                    paint.setTextSize(textSize);
                    int width = (int) paint.measureText(view.getText().toString());

                    WrapLayout.LayoutParams params = (WrapLayout.LayoutParams) view.getLayoutParams();

                    allWidth += width + view.getPaddingLeft() + view.getPaddingRight() + params.leftMargin + params.rightMargin;
                    tempAllWidth += width + view.getPaddingLeft() + view.getPaddingRight() + params.leftMargin + params.rightMargin;
                    if (i == 0) {
                        allHeight += paint.getTextSize() * 1.5 + params.topMargin + params.bottomMargin;
                    }
                    if (tempAllWidth >= screenSize) {
                        allHeight += paint.getTextSize() * 1.5 + params.topMargin + params.bottomMargin;
                        tempAllWidth = width + view.getPaddingLeft() + view.getPaddingRight() + params.leftMargin + params.rightMargin;
                    }
                }

                outerArea.setMinimumWidth(allWidth + 10);
                outerArea.setMinimumHeight(allHeight);
            }

            private TextView createTextCanBeMoved(int screenSize, String string) {
                final TextView textView = new TextView(MainActivity.this);

                // textView.setBackgroundResource(R.drawable.reuse_part);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.reuse_text));
                textView.setText(string);

                Paint paint = new Paint();
                float textSize = textView.getTextSize();
                paint.setTextSize(textSize);

                int width = (int) paint.measureText(string);
                if (screenSize <= width + textView.getPaddingLeft() + textView.getPaddingRight()) {

                    while (screenSize < width) {

                        if (10f >= textSize) {
                            textSize = 10f;
                            break;
                        }
                        textSize--;

                        paint.setTextSize(textSize);
                        width = (int) paint.measureText(string);
                    }
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.setMargins((int) toPX(6), 0, (int) toPX(6), 0);
                textView.setLayoutParams(params);

                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(getResources().getColor(android.R.color.darker_gray));

                textView.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ClipData data = ClipData.newPlainText("", textView.getText());
                        textView.startDrag(data, new View.DragShadowBuilder(textView) {
                            @Override
                            public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
                                super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
                            }
                        }, v, 0);
                        return true;
                    }
                });
                return textView;
            }

            private WrapLayout createOuterArea() {
                WrapLayout outerArea = new WrapLayout(MainActivity.this);
                LinearLayout.LayoutParams outerAreaParam = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                outerArea.setLayoutParams(outerAreaParam);
                outerArea.setGravity(Gravity.CENTER);
                outerArea.setOrientation(LinearLayout.HORIZONTAL);
                outerArea.setBackgroundResource(R.drawable.reuse_outer_frame);
                return outerArea;
            }
        });

    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private float toPX(float dp) {
        float d = getResources().getDisplayMetrics().density;
        return dp * d;
    }
}
