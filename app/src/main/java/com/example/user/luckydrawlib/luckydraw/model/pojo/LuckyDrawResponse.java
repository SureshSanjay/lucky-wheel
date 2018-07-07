package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LuckyDrawResponse {

    @SerializedName("Color1")
    private String mColor1;

    @SerializedName("Color2")
    private String mColor2;

    @SerializedName("Color3")
    private String mColor3;

    @SerializedName("Color4")
    private String mColor4;

    @SerializedName("CreatedDate")
    private String mCreatedDate;

    @SerializedName("Duration")
    private Long mDuration;

    @SerializedName("PromotionPackageId")
    private String mPromotionPackageId;

    @SerializedName("SpinSpeed")
    private String mSpinSpeed;

    @SerializedName("TextColor")
    private String mTextColor;

    @SerializedName("TextOrientation")
    private String mTextOrientation;

    @SerializedName("TextSize")
    private Long mTextSize;

    @SerializedName("WheelDetails")
    private List<WheelDetail> mWheelDetails;

    @SerializedName("WheelSettingId")
    private String mWheelSettingId;

    @SerializedName("WinningColor")
    private String mWinningColor;

    public String getColor1() {
        return mColor1;
    }

    public void setColor1(String Color1) {
        mColor1 = Color1;
    }

    public String getColor2() {
        return mColor2;
    }

    public void setColor2(String Color2) {
        mColor2 = Color2;
    }

    public String getColor3() {
        return mColor3;
    }

    public void setColor3(String Color3) {
        mColor3 = Color3;
    }

    public String getColor4() {
        return mColor4;
    }

    public void setColor4(String Color4) {
        mColor4 = Color4;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        mCreatedDate = CreatedDate;
    }

    public Long getDuration() {
        return mDuration;
    }

    public void setDuration(Long Duration) {
        mDuration = Duration;
    }

    public String getPromotionPackageId() {
        return mPromotionPackageId;
    }

    public void setPromotionPackageId(String PromotionPackageId) {
        mPromotionPackageId = PromotionPackageId;
    }

    public String getSpinSpeed() {
        return mSpinSpeed;
    }

    public void setSpinSpeed(String SpinSpeed) {
        mSpinSpeed = SpinSpeed;
    }

    public String getTextColor() {
        return mTextColor;
    }

    public void setTextColor(String TextColor) {
        mTextColor = TextColor;
    }

    public String getTextOrientation() {
        return mTextOrientation;
    }

    public void setTextOrientation(String TextOrientation) {
        mTextOrientation = TextOrientation;
    }

    public Long getTextSize() {
        return mTextSize;
    }

    public void setTextSize(Long TextSize) {
        mTextSize = TextSize;
    }

    public List<WheelDetail> getWheelDetails() {
        return mWheelDetails;
    }

    public void setWheelDetails(List<WheelDetail> WheelDetails) {
        mWheelDetails = WheelDetails;
    }

    public String getWheelSettingId() {
        return mWheelSettingId;
    }

    public void setWheelSettingId(String WheelSettingId) {
        mWheelSettingId = WheelSettingId;
    }

    public String getWinningColor() {
        return mWinningColor;
    }

    public void setWinningColor(String WinningColor) {
        mWinningColor = WinningColor;
    }

}
