package com.ekosp.mvvmretrofit.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ekosp.mvvmretrofit.helper.Coupon;
import com.ekosp.mvvmretrofit.model.RetrofitRepository;

/**
 * Created by eko on 15/05/18.
 * Email : eko.purnomo@salt.co.id
 */

public class CouponViewModel extends ViewModel {

    private LiveData<Coupon> liveCoupon;
    private RetrofitRepository couponRepository = new RetrofitRepository();

    public LiveData<Coupon> getLiveCoupon() {
        if(liveCoupon == null){
            liveCoupon = couponRepository.getTopCouponLive();
        }
        return liveCoupon;
    }

}