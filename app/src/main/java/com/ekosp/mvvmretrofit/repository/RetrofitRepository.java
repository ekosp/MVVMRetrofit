package com.ekosp.mvvmretrofit.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ekosp.mvvmretrofit.repository.model.Coupon;
import com.ekosp.mvvmretrofit.helper.StoreApi;
import com.ekosp.mvvmretrofit.repository.model.StoreInfo;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public class RetrofitRepository {

    public static final String BASE_URL = "http://www.zoftino.com/api/";
    private static MutableLiveData<StoreInfo> data = new MutableLiveData<StoreInfo>();

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //execute call back in background thread
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();
        }
        return retrofit;
    }

    public static LiveData<StoreInfo> getIntData() {
        return data;
    }

    public static void getStoreInfo() {
        Log.d("", "PROCESSING IN THREAD BEFORE RETROFIT CALL " + Thread.currentThread().getName());
        Call<StoreInfo> call = getRetrofitClient().create(StoreApi.class).getStoreInfo();

        //rest service call runs on background thread and Callback also runs on background thread
        call.enqueue(new Callback<StoreInfo>() {
            @Override
            public void onResponse(Call<StoreInfo> call, Response<StoreInfo> response) {
                StoreInfo si = response.body();
                //use postValue since it is running on background thread.
                data.postValue(si);
                Log.d("", "PROCESSING IN THREAD IN RETROFIT RESPONSE HANDLER " + Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<StoreInfo> call, Throwable t) {
                Log.e("", "Error RETROFIT");
            }
        });
    }

    public LiveData<Coupon> getTopCouponLive() {
        final MutableLiveData<Coupon> coupon = new MutableLiveData<Coupon>();

        getRetrofitClient().create(StoreApi.class).getTopCoupon().enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {
                Coupon cpn = response.body();
                coupon.postValue(cpn);
            }

            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {
                Log.e("", "Error Getting TOP COUPON Data Retrofit");
            }
        });
        return coupon;
    }
}