package com.example.user.luckydrawlib.luckydraw.model;

import android.app.Activity;
import android.widget.EditText;

import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;
import com.example.user.luckydrawlib.luckydraw.view.ILuckyDrawListener;
import com.example.user.luckydrawlib.luckydraw.view.fragment.OnFragmentInteractionConfirmViewListener;

/**
 * Created by DELL on 11/20/2017.
 */

public interface ILuckyDrawModelListener {

    void onLuckyModelListener(Activity activity, String luckyNumber, String scanTrackId, ILuckyDrawListener luckyDrawListener, EditText luckyNumberEdit);

    void onLuckyModelListenerForVerify(LuckyDrawResponse luckyDrawResponse);

    void onLuckyModelConfirmListenerForVerify(Activity activity, OnFragmentInteractionConfirmViewListener mListener);
}
