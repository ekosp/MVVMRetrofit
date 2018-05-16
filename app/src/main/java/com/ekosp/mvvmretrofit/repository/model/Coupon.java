package com.ekosp.mvvmretrofit.repository.model;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public class Coupon {
    private String store;
    private String coupon;
    private String couponCode;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}