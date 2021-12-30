package com.doubleclick.ecommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created By Eslam Ghazy on 12/23/2021
 */
public class Cart implements Parcelable {

    private String SellerID;
    private String iamge;
    private String name;
    private String price;
    private String quntity;
    private String trade;
    private String pushId;
    private String BuyerId;

    protected Cart(Parcel in) {
        SellerID = in.readString();
        iamge = in.readString();
        name = in.readString();
        price = in.readString();
        quntity = in.readString();
        trade = in.readString();
        pushId = in.readString();
        BuyerId = in.readString();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public Cart() {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(SellerID);
        dest.writeString(iamge);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(quntity);
        dest.writeString(trade);
        dest.writeString(pushId);
        dest.writeString(BuyerId);
    }
}
