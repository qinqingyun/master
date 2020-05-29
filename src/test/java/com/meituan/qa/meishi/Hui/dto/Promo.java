package com.meituan.qa.meishi.Hui.dto;

public class Promo {
    private String id;
    private double amount;
    private double orderpricelimit;
    private double orderpriceuplimit;
    private double afterpromolimit;
    private double quantitylimit;
    private boolean isavailable;
    private String title;
    private String description;
    private String cipher;
    private long type;
    private long reductiontype;
    private long freequantity;
    private long remainquantity;
    private long[] disablepromo;
    private long maxquantity;
    private double maxpromolimit;
    private long promotionproductid;

    public String getID() {
        return id;
    }

    public void setID(String value) {
        this.id = value;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    public double getOrderpricelimit() {
        return orderpricelimit;
    }

    public void setOrderpricelimit(double value) {
        this.orderpricelimit = value;
    }

    public double getOrderpriceuplimit() {
        return orderpriceuplimit;
    }

    public void setOrderpriceuplimit(double value) {
        this.orderpriceuplimit = value;
    }

    public double getAfterpromolimit() {
        return afterpromolimit;
    }

    public void setAfterpromolimit(double value) {
        this.afterpromolimit = value;
    }

    public double getQuantitylimit() {
        return quantitylimit;
    }

    public void setQuantitylimit(double value) {
        this.quantitylimit = value;
    }

    public boolean getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(boolean value) {
        this.isavailable = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String value) {
        this.cipher = value;
    }

    public long getType() {
        return type;
    }

    public void setType(long value) {
        this.type = value;
    }

    public long getReductiontype() {
        return reductiontype;
    }

    public void setReductiontype(long value) {
        this.reductiontype = value;
    }

    public long getFreequantity() {
        return freequantity;
    }

    public void setFreequantity(long value) {
        this.freequantity = value;
    }

    public long getRemainquantity() {
        return remainquantity;
    }

    public void setRemainquantity(long value) {
        this.remainquantity = value;
    }

    public long[] getDisablepromo() {
        return disablepromo;
    }

    public void setDisablepromo(long[] value) {
        this.disablepromo = value;
    }

    public long getMaxquantity() {
        return maxquantity;
    }

    public void setMaxquantity(long value) {
        this.maxquantity = value;
    }

    public double getMaxpromolimit() {
        return maxpromolimit;
    }

    public void setMaxpromolimit(double value) {
        this.maxpromolimit = value;
    }

    public long getPromotionproductid() {
        return promotionproductid;
    }

    public void setPromotionproductid(long value) {
        this.promotionproductid = value;
    }
}
