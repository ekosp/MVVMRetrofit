package com.ekosp.mvvmretrofit.helper;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public interface StoreApi {

    @GET("storeOffers/")
    Call<StoreInfo> getStoreInfo();

    @GET("topCoupon/")
    Call<Coupon>  getTopCoupon();
}