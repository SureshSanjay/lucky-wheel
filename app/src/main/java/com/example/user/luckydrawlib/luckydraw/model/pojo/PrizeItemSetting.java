package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PrizeItemSetting {

    @SerializedName("CreatedDate")
    private String mCreatedDate;
    @SerializedName("EmailAddress")
    private String mEmailAddress;
    @SerializedName("HowToClaim")
    private String mHowToClaim;
    @SerializedName("IsNoticeByEmail")
    private Boolean mIsNoticeByEmail;
    @SerializedName("IsNoticeBySms")
    private Boolean mIsNoticeBySms;
    @SerializedName("PhoneNumber")
    private String mPhoneNumber;
    @SerializedName("PrizeCategoryId")
    private String mPrizeCategoryId;
    @SerializedName("PrizeItemSettingId")
    private String mPrizeItemSettingId;
    @SerializedName("Remark")
    private String mRemark;
    @SerializedName("Validity")
    private String mValidity;
    @SerializedName("ValidityType")
    private String mValidityType;
    @SerializedName("WhereToClaim")
    private String mWhereToClaim;

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

    public String getHowToClaim() {
        return mHowToClaim;
    }

    public void setHowToClaim(String HowToClaim) {
        mHowToClaim = HowToClaim;
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

    public String getPrizeItemSettingId() {
        return mPrizeItemSettingId;
    }

    public void setPrizeItemSettingId(String PrizeItemSettingId) {
        mPrizeItemSettingId = PrizeItemSettingId;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String Remark) {
        mRemark = Remark;
    }

    public String getValidity() {
        return mValidity;
    }

    public void setValidity(String Validity) {
        mValidity = Validity;
    }

    public String getValidityType() {
        return mValidityType;
    }

    public void setValidityType(String ValidityType) {
        mValidityType = ValidityType;
    }

    public String getWhereToClaim() {
        return mWhereToClaim;
    }

    public void setWhereToClaim(String WhereToClaim) {
        mWhereToClaim = WhereToClaim;
    }

}
