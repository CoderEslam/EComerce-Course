package com.doubleclick.ecommerce.model;

/**
 * Created By Eslam Ghazy on 12/23/2021
 */
public class Cart {

    private String SellerID;
    private String iamge;
    private String name;
    private String price;
    private String quntity;
    private String trade;
    private String pushId;
    private String BuyerId;

    public String getSellerID() {
        return SellerID;
    }

    public void setSellerID(String sellerID) {
        SellerID = sellerID;
    }

    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuntity() {
        return quntity;
    }

    public void setQuntity(String quntity) {
        this.quntity = quntity;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getBuyerId() {
        return BuyerId;
    }

    public void setBuyerId(String buyerId) {
        BuyerId = buyerId;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "SellerID='" + SellerID + '\'' +
                ", image='" + iamge + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quntity='" + quntity + '\'' +
                ", trade='" + trade + '\'' +
                ", pushId='" + pushId + '\'' +
                ", BuyerId='" + BuyerId + '\'' +
                '}';
    }
}
