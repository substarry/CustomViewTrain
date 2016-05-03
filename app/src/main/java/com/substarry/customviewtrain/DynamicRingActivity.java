package com.substarry.customviewtrain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DynamicRingActivity extends AppCompatActivity {


    private CustomViewDynamicRing mCustomView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_ring);

        mCustomView = (CustomViewDynamicRing) findViewById(R.id.cv1);

        new Thread(mCustomView.getRunnable()).start();
    }

}
