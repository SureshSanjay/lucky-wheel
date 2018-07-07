package com.example.user.luckydrawlib.luckydraw.view;


import com.example.user.luckydrawlib.luckydraw.model.pojo.LuckyDrawResponse;

/**
 * Created by DELL on 11/20/2017.
 */

public interface ILuckyDrawListener {

    void onDrawViewListener(LuckyDrawResponse luckyDrawResponse);

    void onDrawViewListener();
}
