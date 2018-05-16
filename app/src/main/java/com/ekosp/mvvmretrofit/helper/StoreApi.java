package com.ekosp.mvvmretrofit.helper;

import com.ekosp.mvvmretrofit.repository.model.Coupon;
import com.ekosp.mvvmretrofit.repository.model.StoreInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public interface StoreApi {

    @GET("storeOffers/")
    Call<StoreInfo> getStoreInfo();

    @GET("topCoupon/")
    Call<Coupon>  getTopCoupon();

    @POST("AccessMobile/GetUserPrivateSecret")
    Call<ResponseBody> getPrivateSecret(
            @Query("username") String userName);
}