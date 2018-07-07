package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PrizeCategory {

    @SerializedName("CreatedDate")
    private String mCreatedDate;
    @SerializedName("IsActive")
    private Boolean mIsActive;
    @SerializedName("Name")
    private String mName;
    @SerializedName("PrizeCategoryId")
    private String mPrizeCategoryId;
    @SerializedName("PrizeItemSetting")
    private PrizeItemSetting mPrizeItemSetting;
    @SerializedName("PrizeServiceSetting")
    private PrizeServiceSetting mPrizeServiceSetting;
    @SerializedName("PrizeType")
    private PrizeType mPrizeType;
    @SerializedName("PrizeTypeId")
    private String mPrizeTypeId;

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        mCreatedDate = CreatedDate;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean IsActive) {
        mIsActive = IsActive;
    }

    public String getName() {
        return mName;
    }

    public void setName(String Name) {
        mName = Name;
    }

    public String getPrizeCategoryId() {
        return mPrizeCategoryId;
    }

    public void setPrizeCategoryId(String PrizeCategoryId) {
        mPrizeCategoryId = PrizeCategoryId;
    }

    public PrizeItemSetting getPrizeItemSetting() {
        return mPrizeItemSetting;
    }

    public void setPrizeItemSetting(PrizeItemSetting PrizeItemSetting) {
        mPrizeItemSetting = PrizeItemSetting;
    }

    public PrizeServiceSetting getPrizeServiceSetting() {
        return mPrizeServiceSetting;
    }

    public void setPrizeServiceSetting(PrizeServiceSetting PrizeServiceSetting) {
        mPrizeServiceSetting = PrizeServiceSetting;
    }

    public PrizeType getPrizeType() {
        return mPrizeType;
    }

    public void setPrizeType(PrizeType PrizeType) {
        mPrizeType = PrizeType;
    }

    public String getPrizeTypeId() {
        return mPrizeTypeId;
    }

    public void setPrizeTypeId(String PrizeTypeId) {
        mPrizeTypeId = PrizeTypeId;
    }

}
