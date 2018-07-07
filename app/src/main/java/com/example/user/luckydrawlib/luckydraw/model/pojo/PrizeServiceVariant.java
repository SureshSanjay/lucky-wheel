package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PrizeServiceVariant {

    @SerializedName("CreatedDate")
    private String mCreatedDate;
    @SerializedName("IsCanTransfer")
    private Boolean mIsCanTransfer;
    @SerializedName("IsDeleted")
    private Boolean mIsDeleted;
    @SerializedName("PrizeServiceSettingId")
    private String mPrizeServiceSettingId;
    @SerializedName("PrizeServiceVarientId")
    private String mPrizeServiceVarientId;
    @SerializedName("ServiceEndDate")
    private String mServiceEndDate;
    @SerializedName("ServiceStartDate")
    private String mServiceStartDate;

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        mCreatedDate = CreatedDate;
    }

    public Boolean getIsCanTransfer() {
        return mIsCanTransfer;
    }

    public void setIsCanTransfer(Boolean IsCanTransfer) {
        mIsCanTransfer = IsCanTransfer;
    }

    public Boolean getIsDeleted() {
        return mIsDeleted;
    }

    public void setIsDeleted(Boolean IsDeleted) {
        mIsDeleted = IsDeleted;
    }

    public String getPrizeServiceSettingId() {
        return mPrizeServiceSettingId;
    }

    public void setPrizeServiceSettingId(String PrizeServiceSettingId) {
        mPrizeServiceSettingId = PrizeServiceSettingId;
    }

    public String getPrizeServiceVarientId() {
        return mPrizeServiceVarientId;
    }

    public void setPrizeServiceVarientId(String PrizeServiceVarientId) {
        mPrizeServiceVarientId = PrizeServiceVarientId;
    }

    public String getServiceEndDate() {
        return mServiceEndDate;
    }

    public void setServiceEndDate(String ServiceEndDate) {
        mServiceEndDate = ServiceEndDate;
    }

    public String getServiceStartDate() {
        return mServiceStartDate;
    }

    public void setServiceStartDate(String ServiceStartDate) {
        mServiceStartDate = ServiceStartDate;
    }

}
