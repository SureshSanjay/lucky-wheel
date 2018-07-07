package com.example.user.luckydrawlib.luckydraw.model.luckydrawpaint;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.user.luckydrawlib.R;
import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;
import com.example.user.luckydrawlib.luckydraw.model.pojo.OnDrawHandlerListener;


/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyWheelView extends RelativeLayout implements WheelDrawView.PieRotateListener {
    private int mBackgroundColor;
    private int mTextColor;
    private Drawable mCenterImage;
    private Drawable mCursorImage;

    private WheelDrawView pielView;

    private ImageView ivCursorView;

    private ImageView ivCenterView;

    private Activity activity;

    OnDrawHandlerListener onDrawHandlerListener;

    private LuckyRoundItemSelectedListener mLuckyRoundItemSelectedListener;

    @Override
    public void rotateDone(int index) {
        if (mLuckyRoundItemSelectedListener != null) {
            mLuckyRoundItemSelectedListener.LuckyRoundItemSelected(index);
        }
    }

    public interface LuckyRoundItemSelectedListener {
        void LuckyRoundItemSelected(int index);
    }

    public void setLuckyRoundItemSelectedListener(LuckyRoundItemSelectedListener listener) {
        this.mLuckyRoundItemSelectedListener = listener;
    }

    public LuckyWheelView(Context context) {
        super(context);
        init(context, null);
    }

    public LuckyWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * @param ctx
     * @param attrs
     */
    private void init(Context ctx, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.LuckyWheelView);
            mBackgroundColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwBackgroundColor, 0xffcc0000);
            mTextColor = typedArray.getColor(R.styleable.LuckyWheelView_lkwTextColor, 0xffffffff);
            mCursorImage = typedArray.getDrawable(R.styleable.LuckyWheelView_lkwCursor);
            mCenterImage = typedArray.getDrawable(R.styleable.LuckyWheelView_lkwCenterImage);
            typedArray.recycle();
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        ConstraintLayout frameLayout = (ConstraintLayout) inflater.inflate(R.layout.lucky_wheel_layout, this, false);

        pielView = (WheelDrawView) frameLayout.findViewById(R.id.pieView);
        //ivCursorView = (ImageView) frameLayout.findViewById(R.id.cursorView);
        ivCenterView = (ImageView) frameLayout.findViewById(R.id.centerImage);

        pielView.setPieRotateListener(this);
        pielView.setPieBackgroundColor(mBackgroundColor);
        //pielView.setPieCenterImage(mCenterImage);
        pielView.setPieTextColor(mTextColor);

        //ivCursorView.setImageDrawable(mCursorImage);
        ivCenterView.setImageDrawable(mCenterImage);
        addView(frameLayout);
    }

    public void setLuckyWheelBackgrouldColor(int color) {
        pielView.setPieBackgroundColor(color);
    }

    public void setLuckyWheelCursorImage(int drawable) {
        ivCursorView.setBackgroundResource(drawable);
    }

    public void setLuckyWheelCenterImage(Drawable drawable) {
        pielView.setPieCenterImage(drawable);
    }

    public void setLuckyWheelTextColor(int color) {
        pielView.setPieTextColor(color);
    }

    /**
     * @param data
     * @param status
     */
    public void setData(LuckyDrawResponse data, Activity activity, SwipeListeners swipeListener, boolean status) {
        this.activity = activity;
        pielView.setData(data, activity, swipeListener, status);

    }

    /**
     * @param numberOfRound
     */
    public void setRound(int numberOfRound) {
        pielView.setRound(numberOfRound);
    }

    public void startLuckyWheelWithTargetIndex(int index) {
        pielView.rotateTo(index);
    }
}
