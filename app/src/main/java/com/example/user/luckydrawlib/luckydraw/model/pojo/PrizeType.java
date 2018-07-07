package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PrizeType {

    @SerializedName("PrizeTypeId")
    private String mPrizeTypeId;
    @SerializedName("PrizeTypeName")
    private String mPrizeTypeName;

    public String getPrizeTypeId() {
        return mPrizeTypeId;
    }

    public void setPrizeTypeId(String PrizeTypeId) {
        mPrizeTypeId = PrizeTypeId;
    }

    public String getPrizeTypeName() {
        return mPrizeTypeName;
    }

    public void setPrizeTypeName(String PrizeTypeName) {
        mPrizeTypeName = PrizeTypeName;
    }

}
