package com.agrictech.techrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailCalendarActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar5)
    Toolbar mToolbar5;
    @BindView(R.id.title_txt)
    TextView mTitleTxt;
    @BindView(R.id.image_card)
    androidx.cardview.widget.CardView mImageCard;
    @BindView(R.id.calendar_img)
    ImageView mCalendarImg;
    @BindView(R.id.calendar_detail)
    TextView mCalendarDetail;
    @BindView(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.floatingAction_Consult_Btn)
    FloatingActionButton mFloatingActionConsultBtn;
    @BindView(R.id.speakconsult_txt)
    TextView mSpeakconsultTxt;
    /** ButterKnife Code **/
    UserPreferences userPreferences;
    String title,instruction;
    int process_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_calendar_note);
        ButterKnife.bind(this);

        setSupportActionBar( mToolbar5 );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);

        userPreferences = new UserPreferences(this);

        Intent intent = getIntent();
        process_id= Integer.parseInt(intent.getStringExtra(Constant.PROCESS_ID));
        title= intent.getStringExtra(Constant.PROCESS_TITLE);
        instruction= intent.getStringExtra(Constant.INSTRUCTION);

        if (process_id == 1) {
            mCalendarImg.setImageResource(R.drawable.landclearing);
        } else if (process_id == 2) {
            mCalendarImg.setImageResource(R.drawable.seed_manage);
        } else if (process_id == 3) {
            mCalendarImg.setImageResource(R.drawable.planting);
        } else if (process_id == 4) {
            mCalendarImg.setImageResource(R.drawable.fertilizer_apply);
        } else if (process_id == 5) {
            mCalendarImg.setImageResource(R.drawable.water_manage);
        } else if (process_id == 6) {
            mCalendarImg.setImageResource(R.drawable.weeding);
        } else if (process_id == 7) {
            mCalendarImg.setImageResource(R.drawable.weed1_pick);
        } else if (process_id == 8) {
            mCalendarImg.setImageResource(R.drawable.harvest);
        } else if (process_id == 9) {
            mCalendarImg.setImageResource(R.drawable.treshing);
        } else if (process_id == 10) {
            mCalendarImg.setImageResource(R.drawable.bagging_rice);
        }

        mTitleTxt.setText(title);
        mCalendarDetail.setText(Html.fromHtml(instruction));
        mFloatingActionConsultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailCalendarActivity.this,ConsultantActivity.class));
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
