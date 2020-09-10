package com.agrictech.techrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.agrictech.techrice.Constant;
import com.agrictech.techrice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoneActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.back_btn_txt)
    TextView mBackBtn;

    @BindView(R.id.succes_txt)
    TextView mSuccessMsg;

    @BindView(R.id.done_img)
    ImageView done_img;
    Animation slide_infrom_buttom;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        message= intent.getStringExtra(Constant.SUCCESS_MSG);
        mSuccessMsg.setText(message);

        mBackBtn.setOnClickListener(this);

        // load the animation
        slide_infrom_buttom = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_from_buttom);

        //start animation
        done_img.startAnimation(slide_infrom_buttom);
    }




    @Override
    public void onBackPressed() {
        startActivity(new Intent(DoneActivity.this, Dashboard.class));
        this.finish();
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_btn_txt:
                startActivity(new Intent(DoneActivity.this, Dashboard.class));
                this.finish();
                break;

        }
    }




}
