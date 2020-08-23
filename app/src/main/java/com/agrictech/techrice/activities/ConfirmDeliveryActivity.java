package com.agrictech.techrice.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;

public class ConfirmDeliveryActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.payment_btn)
    Button mPaymentBtn;
    @BindView(R.id.progress)
    AVLoadingIndicatorView mProgress;
    @BindView(R.id.toolbar4)
    Toolbar mToolbar4;
    @BindView(R.id.payment_title_bar)
    TextView mPaymentTitleBar;
    @BindView(R.id.guideline27)
    Guideline mGuideline27;
    @BindView(R.id.guideline28)
    Guideline mGuideline28;
    @BindView(R.id.payemnt_healing_txt)
    TextView mPayemntHealingTxt;
    @BindView(R.id.payemnt_healing_txt3)
    TextView mPayemntHealingTxt3;
    @BindView(R.id.payemnt_healing_txt2)
    TextView mPayemntHealingTxt2;
    /** ButterKnife Code **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delivery);
    }
}
