package com.example.user.luckydrawlib.luckydraw.model.luckydrawpaint;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;


import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;
import com.example.user.luckydrawlib.luckydraw.model.pojo.OnDrawHandlerListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;


/**
 * Created by kiennguyen on 11/5/16.
 */

public class WheelDrawView extends View {
    private RectF mRange = new RectF();
    private int mRadius;

    private Paint mArcPaint;
    private Paint mBackgroundPaint;
    private Paint mTextPaint;

    private float mStartAngle = 0;
    private int mCenter;
    private int mPadding;
    private int mTargetIndex;
    private int mRoundOfNumber = 4;
    private boolean isRunning = false;

    private int defaultBackgroundColor = -1;
    private Drawable drawableCenterImage;
    private int textColor = 0xffffffff;

    private LuckyDrawResponse mLuckyItemList;

    private Circle circle;

    private float wheelStrokeWidth;

    private float wheelStrokeRadius;

    private final static float ANGLE = 360f;

    private int targetPosition;
    private String orientation;
    private PieRotateListener mPieRotateListener;

    private float previousY;

    OnDrawHandlerListener onDrawHandlerListener;

    private Activity activity;

    Timer timer;

    Canvas canvasTest;
    float tmpAngleTest;
    float sweepAngleTest;
    String mStrTest;

    SwipeListeners swipeListener;

    private float previousX;

    private float angle = 0;

    private final static float TRIANGLE_WIDTH = 80;

    private boolean winningStatus;

    private int touchEvent = 0;

    public static boolean rotateStatus = true;

    boolean texChangeThread = false;

    private int changeIndex = 0;

    ArrayList<Integer> randomColorList ;

    public interface PieRotateListener {
        void rotateDone(int index);
    }

    public WheelDrawView(Context context) {
        super(context);
    }

    public WheelDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPieRotateListener(PieRotateListener listener) {
        this.mPieRotateListener = listener;
    }

    private void init() {
        textColor = Color.parseColor(mLuckyItemList.getTextColor());
        orientation = mLuckyItemList.getTextOrientation();
        //orientation = "vertical" ;
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mLuckyItemList.getTextSize(),
                getResources().getDisplayMetrics()));

        mRange = new RectF(mPadding, mPadding, mPadding + mRadius, mPadding + mRadius);
    }

    public void setData(LuckyDrawResponse luckyItemList, Activity activity, SwipeListeners swipeListener, boolean status) {
        this.activity = activity;
        winningStatus = status;
        this.mLuckyItemList = luckyItemList;
        this.swipeListener = swipeListener;
        invalidate();
    }

    public void setPieBackgroundColor(int color) {
        defaultBackgroundColor = color;
        invalidate();
    }

    public void setPieCenterImage(Drawable drawable) {
        drawableCenterImage = drawable;
        invalidate();
    }

    public void setPieTextColor(int color) {
        textColor = color;
        invalidate();
    }

    private void drawPieBackgroundWithBitmap(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, null, new Rect(mPadding / 2, mPadding / 2,
                getMeasuredWidth() - mPadding / 2, getMeasuredHeight() - mPadding / 2), null);
    }

    private void initCircle() {
        int width = getMeasuredWidth() == 0 ? getWidth() : getMeasuredWidth();
        int height = getMeasuredHeight() == 0 ? getHeight() : getMeasuredHeight();

        circle = new Circle(width, height);
    }

    private void initWheelStrokeRadius() {
        wheelStrokeRadius = wheelStrokeWidth / 2;
        wheelStrokeRadius = wheelStrokeRadius == 0 ? 1 : wheelStrokeRadius;
    }

    private float getAnglePerItem() {
        return ANGLE / (float) getItemSize();
    }

    private int getItemSize() {
        return mLuckyItemList == null ? 0 : mLuckyItemList.getWheelDetails().size();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (circle == null || !isEnabled()) {
            return false;
        }
        float x = event.getX();
        float y = event.getY();

        if (!circle.contains(x, y)) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("Onclick", "Up");
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = x - previousX;
                float dy = y - previousY;

                // reverse direction of rotation above the mid-line
                if (y > circle.getCy()) {
                    dx = dx * -1;
                    //swipeListener.onWheelSwipeListener() ;
                    Log.i("Onclick", "Up");
                }

                // reverse direction of rotation to left of the mid-line
                if (x < circle.getCx()) {
                    dy = dy * -1;
                    //swipeListener.onWheelSwipeListener() ;
                    Log.i("Onclick", "Up");
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.i("Onclick", "Up");
                break;
        }

        previousX = x;
        previousY = y;
        if (rotateStatus) {
            swipeListener.onWheelSwipeListener();
            Log.i("Onclick status ", "true");
            rotateStatus = false;
            return true;
        } else {
            Log.i("Onclick status ", "false");
            return false;
        }

    }

    public void rotate(float angle) {
        this.angle += angle;
        this.angle %= ANGLE;
        invalidate();

    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLuckyItemList == null) {
            return;
        }
        if (circle == null) {
            initCircle();
        }
        timer = new Timer();
        drawBackgroundColor(canvas, defaultBackgroundColor);

        init();
        getRandomColor() ;
        float tmpAngle = mStartAngle;
        float sweepAngle = 360 / mLuckyItemList.getWheelDetails().size();

        int j = 1;
        for (int i = 0; i < mLuckyItemList.getWheelDetails().size(); i++) {
            if (j == 1) {
                j++;
                int color1 = Color.parseColor(mLuckyItemList.getColor1());
                try {
                    mArcPaint.setColor(randomColorList.get(0));
                }catch (ArrayIndexOutOfBoundsException indexOutofBoundExe){
                    Log.i("Exception",""+indexOutofBoundExe) ;
                }
            } else if (j == 2) {
                j++;
                int color2 = Color.parseColor(mLuckyItemList.getColor2());
                try {
                    mArcPaint.setColor(randomColorList.get(1));
                }catch (ArrayIndexOutOfBoundsException indexOutofBoundExe){
                    Log.i("Exception",""+indexOutofBoundExe) ;
                }
            } else if (j == 3) {
                j++;
                int color3 = Color.parseColor(mLuckyItemList.getColor3());
                try {
                    mArcPaint.setColor(randomColorList.get(2));
                }catch (ArrayIndexOutOfBoundsException indexOutofBoundExe){
                    Log.i("Exception",""+indexOutofBoundExe) ;
                }
            } else if (j == 4) {
                int color4 = Color.parseColor(mLuckyItemList.getColor4());
                //mArcPaint.setColor(color4);
                try {
                    mArcPaint.setColor(randomColorList.get(3));
                }catch (ArrayIndexOutOfBoundsException indexOutofBoundExe){
                    Log.i("Exception",""+indexOutofBoundExe) ;
                }
                j = 1;
            }

            if (mLuckyItemList.getWheelDetails().get(i).getIsForWinning() == true) {
                targetPosition = i;
            }
            if (mLuckyItemList.getWheelDetails().get(i).getIsForWinning() == true && winningStatus) {
                int colorWinning = Color.parseColor(mLuckyItemList.getWinningColor());
                mArcPaint.setColor(colorWinning);
            }

            //mArcPaint.setShader(gradient) ;
            canvas.drawArc(mRange, tmpAngle, sweepAngle, true, mArcPaint);

            drawText(canvas, tmpAngle, sweepAngle, mLuckyItemList.getWheelDetails().get(i).getDisplayName());
            //drawImage(canvas, tmpAngle, BitmapFactory.decodeResource(getResources(), mLuckyItemList.get(i).icon));

            tmpAngle += sweepAngle;
        }

        //drawCenterImage(canvas, activity.getResources().getDrawable(R.drawable.ok));
    }

    private void getRandomColor() {
        randomColorList = new ArrayList<>();
        randomColorList.add(Color.parseColor(mLuckyItemList.getColor1())) ;
        randomColorList.add(Color.parseColor(mLuckyItemList.getColor2())) ;
        randomColorList.add(Color.parseColor(mLuckyItemList.getColor3())) ;
        randomColorList.add(Color.parseColor(mLuckyItemList.getColor4())) ;
        long seed = System.nanoTime();
        Collections.shuffle(randomColorList, new Random(seed));

    }
    private void drawBackgroundColor(Canvas canvas, int color) {
        if (color == -1)
            return;
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.WHITE);
/*        mBackgroundPaint.setShader(new RadialGradient(getWidth() / 2, getHeight() / 2,
                getHeight() / 3, Color.YELLOW, Color.RED, Shader.TileMode.REPEAT));*/

        mBackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(mCenter, mCenter, mCenter, mBackgroundPaint);

    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());

        mPadding = getPaddingLeft() == 0 ? 10 : getPaddingLeft();
        mRadius = width - mPadding * 2;

        mCenter = width / 2;
        initCircle();
        setMeasuredDimension(width, width);
    }

    /**
     * @param canvas
     * @param tmpAngle
     * @param bitmap
     */
    private void drawImage(Canvas canvas, float tmpAngle, Bitmap bitmap) {
        int imgWidth = mRadius / mLuckyItemList.getWheelDetails().size();
        float angle = (float) ((tmpAngle + 360 / mLuckyItemList.getWheelDetails().size() / 2) * Math.PI / 180);
        int x = (int) (mCenter + mRadius / 2 / 2 * Math.cos(angle));
        int y = (int) (mCenter + mRadius / 2 / 2 * Math.sin(angle));
        Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth / 2, y + imgWidth / 2);
        canvas.drawBitmap(bitmap, null, rect, null);
    }

    private void drawCenterImage(Canvas canvas, Drawable drawable) {
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
        Bitmap bitmap = LuckyWheelUtils.drawableToBitmap(drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, 90, 90, false);
        canvas.drawBitmap(bitmap, getMeasuredWidth() / 2 - bitmap.getWidth() / 2, getMeasuredHeight() / 2 - bitmap.getHeight() / 2, null);
    }

    private void drawTriangle(Canvas canvas, Paint paint, float x, float y, float width) {
        float halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x - halfWidth, y - halfWidth); // Top left
        path.lineTo(x + halfWidth, y - halfWidth); // Top right
        path.lineTo(x, y + halfWidth); // Bottom Center
        path.lineTo(x - halfWidth, y - halfWidth); // Back to top left
        path.close();
        canvas.clipPath(path);
        canvas.drawPath(path, paint);
    }

    /**
     * @param canvas
     * @param tmpAngle
     * @param sweepAngle
     * @param mStr
     */
    private void drawText(Canvas canvas, float tmpAngle, float sweepAngle, String mStr) {

        Path path = new Path();
        path.addArc(mRange, tmpAngle, sweepAngle);

        float textWidth = mTextPaint.measureText(mStr);
        int hOffset = (int) (mRadius * Math.PI / mLuckyItemList.getWheelDetails().size() / 2 - textWidth / 2);

        int vOffset = mRadius / 2 / 4;

        if (!orientation.equals("Horizontal")) {
            try {
                canvas.drawTextOnPath(mStr, path, hOffset, vOffset, mTextPaint);

            } catch (Exception e) {
                Log.e("Exception ", "" + e);
            }

        } else {
            float cx = circle.getCx();
            float cy = circle.getCy();
            float radius = circle.getRadius();
            float x = cx - radius + (wheelStrokeRadius * 5);
            float y = cy;
            float textWidth1 = radius - (wheelStrokeRadius * 10);
            TextPaint textPaint = new TextPaint();

            textPaint.set(mTextPaint);
            float angle = getAnglePerItem() / 2;

            for (int i = 0; i < getItemSize(); i++) {
                CharSequence item = TextUtils
                        .ellipsize(mLuckyItemList.getWheelDetails().get(i).getDisplayName(), textPaint, textWidth1 + 20, TextUtils.TruncateAt.END);
                Rect bounds = new Rect();
                mTextPaint.getTextBounds(mLuckyItemList.getWheelDetails().get(i).getDisplayName(), 0, mLuckyItemList.getWheelDetails().get(i).getDisplayName().length(), bounds);
                Shader shader = new LinearGradient(0, mTextPaint.getTextSize() + bounds.top, 0, mTextPaint.getTextSize(), Color.BLACK, textColor, Shader.TileMode.MIRROR);
                mTextPaint.setShader(shader);
                canvas.save();
                canvas.rotate(angle + 180, cx, cy); // +180 for start from right
                canvas.drawText(item.toString(), x + 30, y, mTextPaint);
                canvas.restore();

                angle += getAnglePerItem();
            }
        }

    }

    /**
     * @return
     */
    private float getAngleOfIndexTarget() {
        int tempIndex = mTargetIndex == 0 ? 1 : mTargetIndex;
        return (360 / mLuckyItemList.getWheelDetails().size()) * tempIndex;
    }

    /**
     * @param numberOfRound
     */
    public void setRound(int numberOfRound) {
        mRoundOfNumber = numberOfRound;
    }

    /**
     * @param index
     */
    public void rotateTo(int index) {
        Log.i("Onclick status ", "rotation ");
        if (isRunning) {
            return;
        }
        mTargetIndex = targetPosition + 1;
        setRotation(10);
        float targetAngle = 360 * mRoundOfNumber + 270 - getAngleOfIndexTarget() + (360 / mLuckyItemList.getWheelDetails().size()) / 2;
        animate()
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(mRoundOfNumber * 650 + 900L)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        isRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isRunning = false;
                        if (mPieRotateListener != null) {
                            //drawText(canvas, tmpAngle, sweepAngle, mLuckyItemList.getWheelDetails().get(i).getDisplayName());
                            mPieRotateListener.rotateDone(mTargetIndex);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                })
                .rotation(targetAngle)
                .start();
    }


}
