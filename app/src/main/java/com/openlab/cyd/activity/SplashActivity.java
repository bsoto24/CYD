package com.openlab.cyd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.openlab.cyd.R;
import com.openlab.cyd.widget.WaterProgress;

/**
 * Created by Bryam Soto on 05/05/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private int progress = 99;
    private WaterProgress waterProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        waterProgress = (WaterProgress) findViewById(R.id.water_progress);
        startAnimation();
    }

    private void startAnimation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress >= 0) {
                    try {
                        progress--;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                waterProgress.setProgress(progress);
                            }
                        });
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }).start();
    }
}
