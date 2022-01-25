package com.doubleclick.ecommerce.model;

/**
 * Created By Eslam Ghazy on 1/6/2022
 */
public class Favorit {

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPushIdFavorit() {
        return pushIdFavorit;
    }

    public void setPushIdFavorit(String pushIdFavorit) {
        this.pushIdFavorit = pushIdFavorit;
    }

    public String getPushIdItem() {
        return pushIdItem;
    }

    public void setPushIdItem(String pushIdItem) {
        this.pushIdItem = pushIdItem;
    }

    private String UserId;
    private String pushIdFavorit;
    private String pushIdItem;


}
