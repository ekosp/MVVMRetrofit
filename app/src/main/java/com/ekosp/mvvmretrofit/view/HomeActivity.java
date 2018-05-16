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
    TextView tvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = (TextView) findViewById(R.id.counter);


        //add observer to LiveData
        //observer gets called every time data changes in LiveData
        DataRepository.getData().observe(this, time -> {
            ((TextView)findViewById(R.id.time_t)).setText(""+time);
        });

        RetrofitRepository.getIntData().observe(this, storeInfo -> {
            ((TextView)findViewById(R.id.store_t)).setText(""+storeInfo.getStore());
        });

     //   tv = (TextView) findViewById(R.id.coupon);
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel.class);
        couponViewModel.getLiveCoupon().observe(this, coupon -> {
            ((TextView)findViewById(R.id.coupon)).setText(""+coupon.getCoupon()+" "+coupon.getCouponCode());
        });


        // Get a new or existing ViewModel from the ViewModelProvider.
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        counterViewModel.getCounter().observe(this, counter -> {
            // Update the cached copy of the words in the adapter.
            ((TextView)findViewById(R.id.counter)).setText("last inserted : "+counter.getValue());
          //  Toast.makeText(this, "add new counter"+counter.getValue(), Toast.LENGTH_SHORT).show();
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

    public void addCounter (View view){
        Random r = new Random();
        int random = r.nextInt(555 - 1) + 1;
        counterViewModel.insert(new Counter(random));
        Toast.makeText(this, "insert: "+random, Toast.LENGTH_SHORT).show();
    }

    public void getTopCoupon(View v){

    }

}