package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class PrizeServiceSetting {

    @SerializedName("CreatedDate")
    private String mCreatedDate;
    @SerializedName("EmailAddress")
    private String mEmailAddress;
    @SerializedName("FromDate")
    private String mFromDate;
    @SerializedName("HowTo")
    private String mHowTo;
    @SerializedName("IsNoticeByEmail")
    private Boolean mIsNoticeByEmail;
    @SerializedName("IsNoticeBySms")
    private Boolean mIsNoticeBySms;
    @SerializedName("PhoneNumber")
    private String mPhoneNumber;
    @SerializedName("PrizeCategoryId")
    private String mPrizeCategoryId;
    @SerializedName("PrizeServiceSettingId")
    private String mPrizeServiceSettingId;
    @SerializedName("PrizeServiceVariants")
    private List<PrizeServiceVariant> mPrizeServiceVariants;
    @SerializedName("Remark")
    private String mRemark;
    @SerializedName("ToDate")
    private String mToDate;
    @SerializedName("WhereTo")
    private String mWhereTo;

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        mCreatedDate = CreatedDate;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        mEmailAddress = EmailAddress;
    }

    public String getFromDate() {
        return mFromDate;
    }

    public void setFromDate(String FromDate) {
        mFromDate = FromDate;
    }

    public String getHowTo() {
        return mHowTo;
    }

    public void setHowTo(String HowTo) {
        mHowTo = HowTo;
    }

    public Boolean getIsNoticeByEmail() {
        return mIsNoticeByEmail;
    }

    public void setIsNoticeByEmail(Boolean IsNoticeByEmail) {
        mIsNoticeByEmail = IsNoticeByEmail;
    }

    public Boolean getIsNoticeBySms() {
        return mIsNoticeBySms;
    }

    public void setIsNoticeBySms(Boolean IsNoticeBySms) {
        mIsNoticeBySms = IsNoticeBySms;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        mPhoneNumber = PhoneNumber;
    }

    public String getPrizeCategoryId() {
        return mPrizeCategoryId;
    }

    public void setPrizeCategoryId(String PrizeCategoryId) {
        mPrizeCategoryId = PrizeCategoryId;
    }

    public String getPrizeServiceSettingId() {
        return mPrizeServiceSettingId;
    }

    public void setPrizeServiceSettingId(String PrizeServiceSettingId) {
        mPrizeServiceSettingId = PrizeServiceSettingId;
    }

    public List<PrizeServiceVariant> getPrizeServiceVariants() {
        return mPrizeServiceVariants;
    }

    public void setPrizeServiceVariants(List<PrizeServiceVariant> PrizeServiceVariants) {
        mPrizeServiceVariants = PrizeServiceVariants;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String Remark) {
        mRemark = Remark;
    }

    public String getToDate() {
        return mToDate;
    }

    public void setToDate(String ToDate) {
        mToDate = ToDate;
    }

    public String getWhereTo() {
        return mWhereTo;
    }

    public void setWhereTo(String WhereTo) {
        mWhereTo = WhereTo;
    }

}
