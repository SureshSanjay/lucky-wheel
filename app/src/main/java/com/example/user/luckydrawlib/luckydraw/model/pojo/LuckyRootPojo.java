package com.example.user.luckydrawlib.luckydraw.model.pojo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LuckyRootPojo {

    @SerializedName("Message")
    private String mMessage;
    @SerializedName("Result")
    private LuckyDrawResponse mResult;
    @SerializedName("Results")
    private Object mResults;
    @SerializedName("Status")
    private Boolean mStatus;
    @SerializedName("StatusCode")
    private Long mStatusCode;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String Message) {
        mMessage = Message;
    }

    public LuckyDrawResponse getResult() {
        return mResult;
    }

    public void setResult(LuckyDrawResponse Result) {
        mResult = Result;
    }

    public Object getResults() {
        return mResults;
    }

    public void setResults(Object Results) {
        mResults = Results;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean Status) {
        mStatus = Status;
    }

    public Long getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(Long StatusCode) {
        mStatusCode = StatusCode;
    }

}
