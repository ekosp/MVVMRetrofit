package com.ekosp.mvvmretrofit.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Network;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ekosp.mvvmretrofit.R;
import com.ekosp.mvvmretrofit.model.DataRepository;
import com.ekosp.mvvmretrofit.model.RetrofitRepository;
import com.ekosp.mvvmretrofit.viewmodel.CouponViewModel;

public class HomeActivity extends AppCompatActivity {

    private CouponViewModel couponViewModel;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add observer to LiveData
        //observer gets called every time data changes in LiveData
        DataRepository.getData().observe(this, time -> {
            ((TextView)findViewById(R.id.time_t)).setText(""+time);
        });

        RetrofitRepository.getIntData().observe(this, storeInfo -> {
            ((TextView)findViewById(R.id.store_t)).setText(""+storeInfo.getStore());
        });

        tv = (TextView) findViewById(R.id.coupon);
        //get ViewModel using ViewModelProviders and then tech data
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel.class);
        //livedata
        couponViewModel.getLiveCoupon().observe(this, coupon -> {
            tv.setText(""+coupon.getCoupon()+" "+coupon.getCouponCode());
        });
    }

    //on click of button, set latest time to LiveData
    public void getTime(View view){
       DataRepository.getData();
    }

    // sample call retrofit repo, set response to LiveData
    public void getStore(View view){
        RetrofitRepository.getStoreInfo();
    }

    public void getTopCoupon(View view){
//       String coupon =  couponViewModel.getLiveCoupon().getValue().getStore()+" "+
//               couponViewModel.getLiveCoupon().getValue().getCoupon()
//              +" "+ couponViewModel.getLiveCoupon().getValue().getCouponCode();
//       tv.setText(coupon);
    }

}