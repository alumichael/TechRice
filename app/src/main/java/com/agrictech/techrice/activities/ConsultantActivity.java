package com.agrictech.techrice.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.R;

import butterknife.BindView;

public class ConsultantActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar2)
    Toolbar mToolbar2;
    @BindView(R.id.guideline21)
    Guideline mGuideline21;
    @BindView(R.id.guideline22)
    Guideline mGuideline22;
    @BindView(R.id.image_card)
    androidx.cardview.widget.CardView mImageCard;
    @BindView(R.id.consultant_avatar)
    ImageView mConsultantAvatar;
    @BindView(R.id.textView31)
    TextView mTextView31;
    @BindView(R.id.barrier2)
    Barrier mBarrier2;
    @BindView(R.id.start_planting_btn3)
    Button mStartPlantingBtn3;
    /** ButterKnife Code **/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_to_consultant);
    }
}
