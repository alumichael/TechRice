package com.agrictech.techrice.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.agrictech.techrice.R;

import butterknife.BindView;

public class WalkthroughActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.welcome_txt)
    TextView mWelcomeTxt;
    @BindView(R.id.skip_btn)
    Button mSkipBtn;
    @BindView(R.id.viewPager2)
    ViewPager2 mViewPager2;
    /** ButterKnife Code **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
    }
}
