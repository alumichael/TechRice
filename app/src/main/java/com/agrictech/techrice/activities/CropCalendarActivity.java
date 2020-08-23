package com.agrictech.techrice.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.R;

import butterknife.BindView;

public class CropCalendarActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar4)
    androidx.appcompat.widget.Toolbar mToolbar4;
    @BindView(R.id.guideline21)
    Guideline mGuideline21;
    @BindView(R.id.guideline22)
    Guideline mGuideline22;
    @BindView(R.id.image_card)
    androidx.cardview.widget.CardView mImageCard;
    @BindView(R.id.picker_caledar_avatar)
    ImageView mPickerCaledarAvatar;
    @BindView(R.id.textView31)
    TextView mTextView31;
    @BindView(R.id.barrier2)
    Barrier mBarrier2;
    @BindView(R.id.choose_date_btn)
    Button mChooseDateBtn;
    /** ButterKnife Code **/
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_calendar);
    }
}
