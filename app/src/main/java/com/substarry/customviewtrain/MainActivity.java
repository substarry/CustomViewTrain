package com.substarry.customviewtrain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rlRoot;

    private CustomView1 mCustomView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);

        mCustomView = (CustomView1) findViewById(R.id.cv1);

        new Thread(mCustomView.getRunnable()).start();
    }

}
