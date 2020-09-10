package com.agrictech.techrice.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar4)
    Toolbar mToolbar4;
    @BindView(R.id.cardView_paymt)
    CardView mCardViewPaymt;
    @BindView(R.id.imageView222)
    ImageView mImageView222;
    @BindView(R.id.account_txt)
    TextView mAccountTxt;
    @BindView(R.id.username_txt)
    TextView mUsernameTxt;
    @BindView(R.id.state_txt)
    TextView mStateTxt;
    @BindView(R.id.phone_txt2)
    TextView mPhoneTxt2;
    @BindView(R.id.cardView2)
    CardView mCardView2;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.textView15)
    TextView mTextView15;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @BindView(R.id.switch_notify)
    Switch mSwitchNotify;
    @BindView(R.id.help_card)
    CardView mHelpCard;
    @BindView(R.id.imageView22)
    ImageView mImageView22;
    @BindView(R.id.textView155)
    TextView mTextView155;
    @BindView(R.id.textView166)
    TextView mTextView166;
    /** ButterKnife Code **/

    UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        setSupportActionBar( mToolbar4 );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);

        userPreferences = new UserPreferences(this);
        mUsernameTxt.setText(userPreferences.getFullName());
        mPhoneTxt2.setText(userPreferences.getPhone());
        mStateTxt.setText(userPreferences.getState());

        if(userPreferences.getNotification()){
            mSwitchNotify.setChecked(true);
        }else{
            mSwitchNotify.setChecked(false);
        }

        mSwitchNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSwitchNotify.isChecked()){
                    mSwitchNotify.setChecked(false);
                    userPreferences.setNotification(false);
                }else{
                    mSwitchNotify.setChecked(true);
                    userPreferences.setNotification(true);
                }
            }
        });

        mHelpCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpAlert("Hi, "+userPreferences.getUserFirstname()+", You can always reach us" +
                        "through farmhelp@gmail.com");
            }
        });




    }

    private void HelpAlert(String message) {

        new AlertDialog.Builder(this)
                .setTitle("Help!")
                .setCancelable(false)
                .setIcon(R.drawable.ic_baseline_help_24)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
