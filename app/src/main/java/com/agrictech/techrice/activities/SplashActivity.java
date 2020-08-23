package com.agrictech.techrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.logo_txt)
    TextView mLogoTxt;
    /** ButterKnife Code **/
    // Animation
    Animation  blink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        UserPreferences userPreferences = new UserPreferences(this);

        if (userPreferences.isFirstTimeLaunch()) {

            blink = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.blink);

            //start animation
            mLogoTxt.startAnimation(blink);



            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);
                        userPreferences.setFirstTimeLaunch(false);
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
        }else{



            blink = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.blink);


            mLogoTxt.startAnimation(blink);

            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);

                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();



        }



    }




}
