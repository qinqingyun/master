package com.meituan.qa.meishi.Hui.dto.cashier;

public class OpbVipInfo {
    private boolean vipMutex;
    private String mutexDesc;
    private String userAsset;
    private boolean vipEnable;
    private long superUserID;
    private long memberID;
    private String cardID;

    public boolean getVipMutex() { return vipMutex; }
    public void setVipMutex(boolean value) { this.vipMutex = value; }

    public String getMutexDesc() { return mutexDesc; }
    public void setMutexDesc(String value) { this.mutexDesc = value; }

    public String getUserAsset() { return userAsset; }
    public void setUserAsset(String value) { this.userAsset = value; }

    public boolean getVipEnable() { return vipEnable; }
    public void setVipEnable(boolean value) { this.vipEnable = value; }

    public long getSuperUserID() { return superUserID; }
    public void setSuperUserID(long value) { this.superUserID = value; }

    public long getMemberID() { return memberID; }
    public void setMemberID(long value) { this.memberID = value; }

    public String getCardID() { return cardID; }
    public void setCardID(String value) { this.cardID = value; }
}
