package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class WheelDetail {

    @SerializedName("CreatedDate")
    private String mCreatedDate;

    @SerializedName("DisplayName")
    private String mDisplayName;

    @SerializedName("DisplayOrder")
    private Long mDisplayOrder;

    @SerializedName("IsForWinning")
    private Boolean mIsForWinning;

    @SerializedName("PackageId")
    private String mPackageId;

    @SerializedName("ParentWheelDetailId")
    private String mParentWheelDetailId;

    @SerializedName("WheelDetailId")
    private String mWheelDetailId;

    @SerializedName("WheelDetailType")
    private String mWheelDetailType;

    @SerializedName("WheelDetails")
    private List<WheelDetail> mWheelDetails;

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        mCreatedDate = CreatedDate;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String DisplayName) {
        mDisplayName = DisplayName;
    }

    public Long getDisplayOrder() {
        return mDisplayOrder;
    }

    public void setDisplayOrder(Long DisplayOrder) {
        mDisplayOrder = DisplayOrder;
    }

    public Boolean getIsForWinning() {
        return mIsForWinning;
    }

    public void setIsForWinning(Boolean IsForWinning) {
        mIsForWinning = IsForWinning;
    }

    public String getPackageId() {
        return mPackageId;
    }

    public void setPackageId(String PackageId) {
        mPackageId = PackageId;
    }

    public String getParentWheelDetailId() {
        return mParentWheelDetailId;
    }

    public void setParentWheelDetailId(String ParentWheelDetailId) {
        mParentWheelDetailId = ParentWheelDetailId;
    }

    public String getWheelDetailId() {
        return mWheelDetailId;
    }

    public void setWheelDetailId(String WheelDetailId) {
        mWheelDetailId = WheelDetailId;
    }

    public String getWheelDetailType() {
        return mWheelDetailType;
    }

    public void setWheelDetailType(String WheelDetailType) {
        mWheelDetailType = WheelDetailType;
    }

    public List<WheelDetail> getWheelDetails() {
        return mWheelDetails;
    }

    public void setWheelDetails(List<WheelDetail> WheelDetails) {
        mWheelDetails = WheelDetails;
    }

}
