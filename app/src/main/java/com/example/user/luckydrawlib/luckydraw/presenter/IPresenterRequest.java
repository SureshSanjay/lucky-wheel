package com.example.user.luckydrawlib.luckydraw.presenter;

import android.app.Activity;
import android.widget.EditText;

import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;
import com.example.user.luckydrawlib.luckydraw.view.ILuckyDrawListener;
import com.example.user.luckydrawlib.luckydraw.view.fragment.OnFragmentInteractionConfirmViewListener;

/**
 * Created by User on 11/14/2017.
 */

public interface IPresenterRequest {

    void onRequestFromView(String luckyNumber, String scanTrackId, ILuckyDrawListener luckyDrawListener, Activity activity, EditText number);

    void onRequestFromViewForVerify(LuckyDrawResponse luckyDrawResponse);

    void onRequestFromLuckyConfirmView(Activity activity, OnFragmentInteractionConfirmViewListener mListener);
}
