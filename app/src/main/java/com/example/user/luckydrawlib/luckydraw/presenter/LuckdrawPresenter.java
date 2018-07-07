package com.example.user.luckydrawlib.luckydraw.presenter;

import android.app.Activity;
import android.widget.EditText;

import com.example.user.luckydrawlib.luckydraw.model.LuckyDrawModel;
import com.example.user.luckydrawlib.luckydraw.model.luckydrawpaint.LuckyWheelView;
import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;
import com.example.user.luckydrawlib.luckydraw.view.ILuckyDrawListener;
import com.example.user.luckydrawlib.luckydraw.view.fragment.OnFragmentInteractionConfirmViewListener;


/**
 * Created by User on 11/14/2017.
 */

public class LuckdrawPresenter implements IPresenterRequest, IPresenterResponse {

    Activity activity;

    LuckyWheelView luckyWheelView;

    ILuckyDrawListener luckyDrawListener;


    public LuckdrawPresenter(Activity activity, LuckyWheelView luckyWheelView, ILuckyDrawListener luckyDrawListener) {
        this.activity = activity;
        this.luckyWheelView = luckyWheelView;
        this.luckyDrawListener = luckyDrawListener;
    }

    public LuckdrawPresenter() {

    }

    @Override
    public void onRequestFromView(String luckyNumber, String scanTrackId, ILuckyDrawListener luckyDrawListener, Activity activity, EditText luckyNumberEdit) {
        LuckyDrawModel luckyDrawModel = new LuckyDrawModel(activity, luckyWheelView, this);
    }

    @Override
    public void onRequestFromViewForVerify(LuckyDrawResponse luckyDrawResponse) {
        LuckyDrawModel luckyDrawModel = new LuckyDrawModel(activity, luckyWheelView, this);
    }

    @Override
    public void onRequestFromLuckyConfirmView(Activity activity, OnFragmentInteractionConfirmViewListener mListener) {
        LuckyDrawModel luckyDrawModel = new LuckyDrawModel();
    }

    @Override
    public void onResponseFromModel() {
        luckyDrawListener.onDrawViewListener();
    }

    public void onRequestFromView(LuckyDrawResponse luckyDrawResponse) {
        LuckyDrawModel luckyDrawModel = new LuckyDrawModel(activity, luckyWheelView, this);
        luckyDrawModel.onLuckyModelListener(luckyDrawResponse);
    }
}
