package com.doubleclick.ecommerce.model;

/**
 * Created By Eslam Ghazy on 12/30/2021
 */
public class Order {
    private String address,email,buyer,name,phone,price,pushId,quantity,seller;

    @Override
    public String toString() {
        return "Order{" +
                "address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", buyer='" + buyer + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", price='" + price + '\'' +
                ", pushId='" + pushId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", seller='" + seller + '\'' +
                '}';
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }


}
