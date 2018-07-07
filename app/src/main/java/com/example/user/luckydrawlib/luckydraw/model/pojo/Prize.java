package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Prize {

    @SerializedName("Claim")
    private Long mClaim;
    @SerializedName("CreatedDate")
    private String mCreatedDate;
    @SerializedName("IsAmountType")
    private Boolean mIsAmountType;
    @SerializedName("IsDeleted")
    private Boolean mIsDeleted;
    @SerializedName("Priority")
    private Long mPriority;
    @SerializedName("PrizeAmount")
    private Double mPrizeAmount;
    @SerializedName("PrizeCategory")
    private PrizeCategory mPrizeCategory;
    @SerializedName("PrizeCategoryId")
    private String mPrizeCategoryId;
    @SerializedName("PrizeId")
    private String mPrizeId;
    @SerializedName("PrizeServiceVariant")
    private Object mPrizeServiceVariant;
    @SerializedName("PrizeServiceVariantId")
    private String mPrizeServiceVariantId;
    @SerializedName("PrizeType")
    private PrizeType mPrizeType;
    @SerializedName("PrizeTypeId")
    private String mPrizeTypeId;
    @SerializedName("ProfileId")
    private String mProfileId;
    @SerializedName("TotalPrize")
    private Long mTotalPrize;

    public Long getClaim() {
        return mClaim;
    }

    public void setClaim(Long Claim) {
        mClaim = Claim;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        mCreatedDate = CreatedDate;
    }

    public Boolean getIsAmountType() {
        return mIsAmountType;
    }

    public void setIsAmountType(Boolean IsAmountType) {
        mIsAmountType = IsAmountType;
    }

    public Boolean getIsDeleted() {
        return mIsDeleted;
    }

    public void setIsDeleted(Boolean IsDeleted) {
        mIsDeleted = IsDeleted;
    }

    public Long getPriority() {
        return mPriority;
    }

    public void setPriority(Long Priority) {
        mPriority = Priority;
    }

    public Double getPrizeAmount() {
        return mPrizeAmount;
    }

    public void setPrizeAmount(Double PrizeAmount) {
        mPrizeAmount = PrizeAmount;
    }

    public PrizeCategory getPrizeCategory() {
        return mPrizeCategory;
    }

    public void setPrizeCategory(PrizeCategory PrizeCategory) {
        mPrizeCategory = PrizeCategory;
    }

    public String getPrizeCategoryId() {
        return mPrizeCategoryId;
    }

    public void setPrizeCategoryId(String PrizeCategoryId) {
        mPrizeCategoryId = PrizeCategoryId;
    }

    public String getPrizeId() {
        return mPrizeId;
    }

    public void setPrizeId(String PrizeId) {
        mPrizeId = PrizeId;
    }

    public Object getPrizeServiceVariant() {
        return mPrizeServiceVariant;
    }

    public void setPrizeServiceVariant(Object PrizeServiceVariant) {
        mPrizeServiceVariant = PrizeServiceVariant;
    }

    public String getPrizeServiceVariantId() {
        return mPrizeServiceVariantId;
    }

    public void setPrizeServiceVariantId(String PrizeServiceVariantId) {
        mPrizeServiceVariantId = PrizeServiceVariantId;
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

    public String getProfileId() {
        return mProfileId;
    }

    public void setProfileId(String ProfileId) {
        mProfileId = ProfileId;
    }

    public Long getTotalPrize() {
        return mTotalPrize;
    }

    public void setTotalPrize(Long TotalPrize) {
        mTotalPrize = TotalPrize;
    }

}
