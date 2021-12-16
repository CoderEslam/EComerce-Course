package com.doubleclick.ecommerce.model;

/**
 * Created By Eslam Ghazy on 12/13/2021
 */
public class ItemProduct {

    private String Name;
    private String Description;
    private String userId;
    private String price;
    private String discountPrice;
    private String image;
    private String trade;
    private String pushId;
    private String Category;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }


    @Override
    public String toString() {
        return "ItemProduct{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", userId='" + userId + '\'' +
                ", price='" + price + '\'' +
                ", discountPrice='" + discountPrice + '\'' +
                ", image='" + image + '\'' +
                ", trade='" + trade + '\'' +
                ", pushId='" + pushId + '\'' +
                ", Category='" + Category + '\'' +
                '}';
    }
}
