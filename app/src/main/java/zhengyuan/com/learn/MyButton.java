package zhengyuan.com.learn;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.ButtonBarLayout;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;



/**
 * Created by root on 18-9-4.
 */

public class MyButton extends AppCompatButton {
    private int width;
    private int heigh;
    private float startAngle;
    private boolean isMorphing;
    private Paint paint;

    private ValueAnimator arcValueAnimator;

    private GradientDrawable backDrawable;
    public MyButton(Context context){
        super(context);
        init(context);
    }
    public MyButton(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }
    public MyButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        isMorphing = false;
        backDrawable = new GradientDrawable();
        int colorDrawable = context.getColor(R.color.colorAccent);
        backDrawable.setColor(colorDrawable);
        backDrawable.setCornerRadius(120);
        setBackground(backDrawable);
        setText("动画、自定义view");

        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.antiquewhite));
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(2);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            width = widthSize;
        }
        if (heightMode==MeasureSpec.EXACTLY){
            heigh=heightSize;
        }
        setMeasuredDimension(width,heigh);

    }
    public void startAnim(){
        isMorphing = true;
        setText("");
        ValueAnimator valueAnimator=ValueAnimator.ofInt(width,heigh);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value= (int) animation.getAnimatedValue();
                int leftOffset=(width-value)/2;
                int rightOffset=width-leftOffset;

                backDrawable.setBounds(leftOffset,0,rightOffset,heigh);
            }
        });

        ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(backDrawable,"cornerRadius",120,heigh/2);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator,objectAnimator);
        animatorSet.start();

        showArc();
    }
    public void showArc(){
        arcValueAnimator=ValueAnimator.ofInt(0,360);
        arcValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle= (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        //arcValueAnimator.setInterpolator(new LinearInterpolator());
        arcValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        arcValueAnimator.setDuration(1000);
        arcValueAnimator.start();
    }
    @Override
    public void onDraw(final Canvas canvas){
        super.onDraw(canvas);
        if(isMorphing){
            final RectF rectF=new RectF(getWidth()*5/12,getHeight()/8,getWidth()*7/12,getHeight()-getHeight()/8);
            canvas.drawArc(rectF,startAngle,270,false,paint);
        }

    }

    public void gotoNew(){
        isMorphing=false;

        arcValueAnimator.cancel();
        setVisibility(GONE);

    }

    public void regainBackground(){
        setVisibility(VISIBLE);
        backDrawable.setBounds(0,0,width,heigh);
        backDrawable.setCornerRadius(24);
        setBackground(backDrawable);
        setText("已回收");
        isMorphing=false;
    }

}
