package me.mwild.fitdoughnut;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class FitDoughnut extends ViewGroup {

    private FitDoughnutView fitDoughnutView;

    private Paint paintPrimary;
    private Paint paintSecondary;
    private Paint paintTextPrimary;
    private Paint paintTextSecondary;

    private RectF oval = new RectF();
    private float radius;
    private float width;

    private float textSizePrimary;
    private float textSizeSecondary;

    private int colorPrimary;
    private int colorSecondary;
    private int colorTextPrimary;
    private int colorTextSecondary;


    private float percentDeg;
    public void setPercent(float percent) {
        percentDeg = (percent / 100.f) * 360.f;
    }
    public float getPercent() {
        return (percentDeg / 360.f) * 100.f;
    }


    public FitDoughnut(Context ctx) {
        super(ctx);
        init();
    }

    public FitDoughnut(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        //todo get vars from xml resources

        init();
    }

    private void init() {

        fitDoughnutView = new FitDoughnutView(getContext());
        addView(fitDoughnutView);

        //width = 80.f;

        textSizePrimary = 10.f;
        textSizeSecondary = 10.f;

        setPercent(50);

        colorPrimary = Color.rgb(225, 140, 80);
        colorSecondary = Color.rgb(200,200,200);
        colorTextPrimary = Color.BLACK;
        colorTextSecondary = Color.BLACK;


        paintPrimary = new Paint();
        paintPrimary.setAntiAlias(true);
        paintPrimary.setColor(colorPrimary);
        paintPrimary.setStyle(Paint.Style.STROKE);
        paintPrimary.setStrokeCap(Paint.Cap.ROUND);
        //paintPrimary.setStrokeWidth(width);

        paintSecondary = new Paint();
        paintSecondary.setAntiAlias(true);
        paintSecondary.setColor(colorSecondary);
        paintSecondary.setStyle(Paint.Style.STROKE);
        //paintSecondary.setStrokeWidth(width);

    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {
        // do nothing
        // don't call super.onLayout() -- this would cause a layout pass on the children
        // children will lay out in onSizeChanged()
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // account for padding
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        // figure out how big the doughnut can be
        float ww = (float) w - xpad;
        float hh = (float) h - ypad;
        float diameter = Math.min(ww, hh);

        oval = new RectF(0.f, 0.f, diameter, diameter);
        oval.offsetTo(getPaddingLeft(), getPaddingTop());

        // set stroke width..
        width = diameter / 15.f;
        paintPrimary.setStrokeWidth(width);
        paintSecondary.setStrokeWidth(width);

        // lay out the child view that actually draws the doughnut
        fitDoughnutView.layout((int) oval.left, (int) oval.top, (int) oval.right, (int) oval.bottom);
    }


    /**
     * FitDoughnutView class
     */
    class FitDoughnutView extends View {

        private RectF _oval;

        public FitDoughnutView(Context ctx) {
            super(ctx);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawArc(_oval, 0, 360, false, paintSecondary);

            canvas.drawArc(_oval, 270, percentDeg, false, paintPrimary);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            _oval = new RectF(width, width, w - width, h - width);
        }
    }
}
