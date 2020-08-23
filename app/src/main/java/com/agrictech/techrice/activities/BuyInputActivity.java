package com.agrictech.techrice.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;

import butterknife.BindView;

public class BuyInputActivity extends AppCompatActivity {
    /** ButterKnife Code **/
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title_txt)
    TextView mTitleTxt;
    @BindView(R.id.guideline23)
    Guideline mGuideline23;
    @BindView(R.id.guideline24)
    Guideline mGuideline24;
    @BindView(R.id.recycler_inputs_category)
    RecyclerView mRecyclerInputsCategory;
    /** ButterKnife Code **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_input);
    }
}
