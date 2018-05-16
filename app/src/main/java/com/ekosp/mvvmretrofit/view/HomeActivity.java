package com.ekosp.mvvmretrofit.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ekosp.mvvmretrofit.R;
import com.ekosp.mvvmretrofit.repository.DataRepository;
import com.ekosp.mvvmretrofit.repository.RetrofitRepository;
import com.ekosp.mvvmretrofit.repository.db.Counter;
import com.ekosp.mvvmretrofit.viewmodel.CounterViewModel;
import com.ekosp.mvvmretrofit.viewmodel.CouponViewModel;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    private CouponViewModel couponViewModel;
    private CounterViewModel counterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // menu #1 : just LiveDate
        DataRepository.getData().observe(this, time -> {
            ((TextView) findViewById(R.id.time_t)).setText("" + time);
        });

        // menu #2 : Retrofit + LiveData
        RetrofitRepository.getIntData().observe(this, storeInfo -> {
            ((TextView) findViewById(R.id.store_t)).setText("" + storeInfo.getStore());
        });

        // menu #3 : Retrofit LiveData ModelView
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel.class);
        couponViewModel.getLiveCoupon().observe(this, coupon -> {
            ((TextView) findViewById(R.id.coupon)).setText("" + coupon.getCoupon() + " " + coupon.getCouponCode());
        });

        // menu #4 : Room LiveData ModelView
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        counterViewModel.getCounter().observe(this, counter -> {
            try {
                ((TextView) findViewById(R.id.counter)).setText("last inserted : " + counter.getValue());
            } catch (Exception e) {
                e.printStackTrace();
                ((TextView) findViewById(R.id.counter)).setText("last inserted : ---- ");
            }
        });
    }

    public void getTime(View view) {
        DataRepository.getData();
    }

    public void getStore(View view) {
        RetrofitRepository.getStoreInfo();
    }

    public void addCounter(View view) {
        Random r = new Random();
        int random = r.nextInt(555 - 1) + 1;
        counterViewModel.insert(new Counter(random));
        Toast.makeText(this, "insert: " + random, Toast.LENGTH_SHORT).show();
    }

    public void getTopCoupon(View v) {
        // no need click because livedata, it automate to get data from repository
    }

}