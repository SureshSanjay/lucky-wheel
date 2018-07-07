package com.example.user.luckydrawlib.luckydraw.model.pojo;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ConfirmPojo {

    @SerializedName("Amount")
    private Long mAmount;
    @SerializedName("CreatedDate")
    private String mCreatedDate;
    @SerializedName("DestinationNumber")
    private String mDestinationNumber;
    @SerializedName("IsProfileMatched")
    private Boolean mIsProfileMatched;
    @SerializedName("IsWon")
    private Boolean mIsWon;
    @SerializedName("LuckyNumber")
    private String mLuckyNumber;
    @SerializedName("OkTransactionId")
    private String mOkTransactionId;
    @SerializedName("Prize")
    private Prize mPrize;
    @SerializedName("PrizeId")
    private String mPrizeId;
    @SerializedName("ProfileId")
    private String mProfileId;
    @SerializedName("ScanResultId")
    private String mScanResultId;
    @SerializedName("ScanTrackingId")
    private String mScanTrackingId;
    @SerializedName("SourceNumber")
    private String mSourceNumber;
    @SerializedName("TraditionId")
    private String mTraditionId;

    public Long getAmount() {
        return mAmount;
    }

    public void setAmount(Long Amount) {
        mAmount = Amount;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        mCreatedDate = CreatedDate;
    }

    public String getDestinationNumber() {
        return mDestinationNumber;
    }

    public void setDestinationNumber(String DestinationNumber) {
        mDestinationNumber = DestinationNumber;
    }

    public Boolean getIsProfileMatched() {
        return mIsProfileMatched;
    }

    public void setIsProfileMatched(Boolean IsProfileMatched) {
        mIsProfileMatched = IsProfileMatched;
    }

    public Boolean getIsWon() {
        return mIsWon;
    }

    public void setIsWon(Boolean IsWon) {
        mIsWon = IsWon;
    }

    public String getLuckyNumber() {
        return mLuckyNumber;
    }

    public void setLuckyNumber(String LuckyNumber) {
        mLuckyNumber = LuckyNumber;
    }

    public String getOkTransactionId() {
        return mOkTransactionId;
    }

    public void setOkTransactionId(String OkTransactionId) {
        mOkTransactionId = OkTransactionId;
    }

    public Prize getPrize() {
        return mPrize;
    }

    public void setPrize(Prize Prize) {
        mPrize = Prize;
    }

    public String getPrizeId() {
        return mPrizeId;
    }

    public void setPrizeId(String PrizeId) {
        mPrizeId = PrizeId;
    }

    public String getProfileId() {
        return mProfileId;
    }

    public void setProfileId(String ProfileId) {
        mProfileId = ProfileId;
    }

    public String getScanResultId() {
        return mScanResultId;
    }

    public void setScanResultId(String ScanResultId) {
        mScanResultId = ScanResultId;
    }

    public String getScanTrackingId() {
        return mScanTrackingId;
    }

    public void setScanTrackingId(String ScanTrackingId) {
        mScanTrackingId = ScanTrackingId;
    }

    public String getSourceNumber() {
        return mSourceNumber;
    }

    public void setSourceNumber(String SourceNumber) {
        mSourceNumber = SourceNumber;
    }

    public String getTraditionId() {
        return mTraditionId;
    }

    public void setTraditionId(String TraditionId) {
        mTraditionId = TraditionId;
    }

}
