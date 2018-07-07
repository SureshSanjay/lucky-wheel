package com.example.user.luckydrawlib.luckydraw.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.luckydrawlib.luckydraw.model.luckydrawpaint.LuckyWheelView;
import com.example.user.luckydrawlib.luckydraw.model.luckydrawpaint.SwipeListeners;
import com.example.user.luckydrawlib.luckydraw.model.pojo.ConfirmPojo;
import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawDummyResponse;
import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;
import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyItem;
import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyRootPojo;
import com.example.user.luckydrawlib.luckydraw.model.pojo.VerifyRootPojo;
import com.example.user.luckydrawlib.luckydraw.presenter.IPresenterResponse;
import com.example.user.luckydrawlib.luckydraw.view.ILuckyDrawListener;
import com.example.user.luckydrawlib.luckydraw.view.fragment.OnFragmentInteractionConfirmViewListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import retrofit2.Response;


/**
 * Created by DELL on 11/20/2017.
 */

public class LuckyDrawModel implements SpinningWheelView.OnRotationListener<String>,
         SwipeListeners {

    Context context;

    Runnable runnable;

    android.os.Handler handler = new android.os.Handler();

    FrameLayout frameLayout;

    private ObjectlDownView mSimulationView;

    SwipeListeners swipeListener;

    Activity activity;

    LuckyDrawResponse luckyDrawResponse;

    LuckyDrawResponse luckyDrawResponse1;

    ILuckyDrawListener luckyDrawListener;

    List<LuckyItem> data = new ArrayList<>();

    public static int threadClosed;

    OnFragmentInteractionConfirmViewListener mListener;

    public LuckyDrawResponse luckyDrawResponseFinal = null;

    private int tokenFrom;

    boolean fistTme = true;

    boolean validSubDetails = false;

    int subDetailIndex = 0;

    Timer timer;

    LuckyDrawDummyResponse luckyDrawDummyResponse;

    LuckyWheelView luckyWheelView;

    IPresenterResponse iPresenterResponse;

    String luckyNumber;

    String scanTrackId;

    EditText luckyNumberEdit;

    public LuckyDrawModel(Activity activity, LuckyWheelView luckyWheelView, IPresenterResponse iPresenterResponse) {
        this.activity = activity;
        this.iPresenterResponse = iPresenterResponse;
        this.luckyWheelView = luckyWheelView;
        threadClosed = 0;
    }

    public LuckyDrawModel() {
    }

    private int getRandomIndex() {
        Random rand = new Random();
        //return rand.nextInt(data.size() - 1) + 0;
        return 0;
    }

    private int getRandomRound() {
        Random rand = new Random();
        return rand.nextInt(10) + 15;
        //return 5;
    }

    private void cloneObject(LuckyDrawResponse luckyDrawResponse) {
        Gson gson = new Gson();
        String serializedObject = gson.toJson(luckyDrawResponse);
        luckyDrawDummyResponse = gson.fromJson(serializedObject, LuckyDrawDummyResponse.class);
    }

    /**
     * Remove thread handler
     */
    public void finishHandler() {
        threadClosed = 1;
    }

    private void cloneDummyObject(LuckyDrawDummyResponse luckyDrawDummyResponse) {
        Gson gson = new Gson();
        String serializedObject = gson.toJson(luckyDrawDummyResponse);
        luckyDrawResponseFinal = gson.fromJson(serializedObject, LuckyDrawResponse.class);
    }






    public void onDrawMethod() {
        cloneDummyObject(luckyDrawDummyResponse);
        luckyWheelView.setData(luckyDrawResponseFinal, activity, swipeListener, false);
        luckyWheelView.setRound(Integer.parseInt(luckyDrawResponseFinal.getSpinSpeed()));
        luckyWheelView.startLuckyWheelWithTargetIndex(8);
    }

    public void drawMethod(final LuckyDrawDummyResponse luckyDrawResponse) {
        try {

            if (fistTme != true) {
                for (int i = 0; i < luckyDrawResponse.getWheelDetails().size(); i++) {

                    if (luckyDrawResponse1.getWheelDetails().get(i).getWheelDetails().size() > 0) {
                        if (luckyDrawResponse1.getWheelDetails().get(i).getWheelDetails().size() > subDetailIndex) {
                            validSubDetails = true;
                            luckyDrawResponse1.getWheelDetails().get(i).setWheelDetailId(luckyDrawResponse.getWheelDetails().get(i).getWheelDetails().get(subDetailIndex).getWheelDetailId());
                            luckyDrawResponse1.getWheelDetails().get(i).setPackageId(luckyDrawResponse.getWheelDetails().get(i).getWheelDetails().get(subDetailIndex).getPackageId());
                            luckyDrawResponse1.getWheelDetails().get(i).setWheelDetailType(luckyDrawResponse.getWheelDetails().get(i).getWheelDetails().get(subDetailIndex).getWheelDetailType());
                            luckyDrawResponse1.getWheelDetails().get(i).setDisplayName(luckyDrawResponse.getWheelDetails().get(i).getWheelDetails().get(subDetailIndex).getDisplayName());
                            luckyDrawResponse1.getWheelDetails().get(i).setParentWheelDetailId(luckyDrawResponse.getWheelDetails().get(i).getWheelDetails().get(subDetailIndex).getParentWheelDetailId());
                            luckyDrawResponse1.getWheelDetails().get(i).setDisplayOrder(luckyDrawResponse.getWheelDetails().get(i).getWheelDetails().get(subDetailIndex).getDisplayOrder());
                            luckyDrawResponse1.getWheelDetails().get(i).setIsForWinning(luckyDrawResponse.getWheelDetails().get(i).getWheelDetails().get(subDetailIndex).getIsForWinning());
                        }
                    }
                }
                if (validSubDetails != true) {
                    subDetailIndex = 0;
                    cloneDummyObject(luckyDrawResponse);
                    luckyWheelView.setData(luckyDrawResponseFinal, activity, swipeListener, false);
                    luckyWheelView.setRound(Integer.parseInt(luckyDrawResponseFinal.getSpinSpeed()));
                } else {
                    subDetailIndex++;
                    validSubDetails = false;
                    luckyWheelView.setData(luckyDrawResponse1, activity, swipeListener, false);
                    luckyWheelView.setRound(Integer.parseInt(luckyDrawResponse1.getSpinSpeed()));
                }
            } else {
                fistTme = false;
                cloneDummyObject(luckyDrawResponse);
                luckyWheelView.setData(luckyDrawResponseFinal, activity, swipeListener, false);
                luckyWheelView.setRound(Integer.parseInt(luckyDrawResponseFinal.getSpinSpeed()));
            }
        } catch (Exception e) {
            Log.d("Exception ", "e");
        }
    }


    @Override
    public void onWheelSwipeListener() {
        Log.i("Onclick status ", "model ");
        onDrawMethod();
    }

    @Override
    public void onRotation() {

    }

    @Override
    public void onStopRotation(String item) {

    }
    
    private void initial() {
        swipeListener = this;
        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                handler.removeCallbacks(runnable);
                cloneDummyObject(luckyDrawDummyResponse);
                luckyWheelView.setData(luckyDrawResponseFinal, activity, swipeListener, true);

                iPresenterResponse.onResponseFromModel();
            }
        });
    }

    public void onLuckyModelListener(LuckyDrawResponse luckyDrawResponse) {
        initial();
        this.luckyDrawResponse = luckyDrawResponse;
        this.luckyDrawResponse = luckyDrawResponse;
        cloneObject(luckyDrawResponse);
        this.luckyDrawResponseFinal = luckyDrawResponse;
        this.luckyDrawResponseFinal = luckyDrawResponse;
        luckyDrawResponse1 = luckyDrawResponse;
        cloneObject(luckyDrawResponse);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                drawMethod(luckyDrawDummyResponse);
                if (threadClosed == 0)
                    handler.postDelayed(this, 1000);
                else
                    handler.removeCallbacks(runnable);
            }
        };
        handler.postDelayed(runnable, 500);

    }


    private static final Paint PAINT = new Paint();

    static {
        PAINT.setStyle(Paint.Style.FILL);
    }

    private static Interpolator defaultAlphaInterpolator;

    public static Interpolator getDefaultAlphaInterpolator() {
        if (defaultAlphaInterpolator == null) {
            defaultAlphaInterpolator = new Interpolator() {
                @Override
                public float getInterpolation(float v) {
                    return v >= 0.9f ? 2f - (v - 0.9f) * 10f : 2f;
                }
            };
        }
        return defaultAlphaInterpolator;
    }

    public static List<Bitmap> generateConfettiBitmaps(ArrayList<Integer> snowflake, Context context, int size) {
        final List<Bitmap> bitmaps = new ArrayList<>();
        int screenWith = Resources.getSystem().getDisplayMetrics().widthPixels;

        for (int i = 0; i < snowflake.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), snowflake.get(i));
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                    bitmap, screenWith / 3, screenWith / 3, false);
            bitmaps.add(resizedBitmap);
        }
        return bitmaps;
    }

}
