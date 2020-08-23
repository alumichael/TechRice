package com.agrictech.techrice.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.R;

import butterknife.BindView;

public class SettingsActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar4)
    androidx.appcompat.widget.Toolbar mToolbar4;
    @BindView(R.id.guideline27)
    Guideline mGuideline27;
    @BindView(R.id.guideline28)
    Guideline mGuideline28;
    @BindView(R.id.cardView_paymt)
    CardView mCardViewPaymt;
    @BindView(R.id.imageView222)
    ImageView mImageView222;
    @BindView(R.id.textView1555)
    TextView mTextView1555;
    @BindView(R.id.textView1666)
    TextView mTextView1666;
    @BindView(R.id.cardView2)
    CardView mCardView2;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.textView15)
    TextView mTextView15;
    @BindView(R.id.textView16)
    TextView mTextView16;
    @BindView(R.id.help_card)
    CardView mHelpCard;
    @BindView(R.id.imageView22)
    ImageView mImageView22;
    @BindView(R.id.textView155)
    TextView mTextView155;
    @BindView(R.id.textView166)
    TextView mTextView166;
    /** ButterKnife Code **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
