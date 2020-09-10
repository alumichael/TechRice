package com.agrictech.techrice.activities;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.adapters.CropCalendarListAdapter;
import com.agrictech.techrice.adapters.RequiremntListAdapter;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.frgament.DashBoardFragment;
import com.agrictech.techrice.frgament.Fragment_Requirement;
import com.agrictech.techrice.model.OnlyFarmerIDPost;
import com.agrictech.techrice.model.land_info.LandInfoRespHead;
import com.agrictech.techrice.model.season_guild.ProcessResponseData;
import com.agrictech.techrice.model.season_guild.ProcessResponseHead;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agrictech.techrice.Util.ShowMessage;

public class CropCalendarActivity extends AppCompatActivity implements View.OnClickListener  {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar3)
    Toolbar mToolbar3;
    @BindView(R.id.nothing_txt)
    TextView mNothingTxt;
    @BindView(R.id.sub_title)
    TextView mSubTitle;
    @BindView(R.id.progress)
    AVLoadingIndicatorView mProgress;
    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.recycler_calendar)
    RecyclerView mRecyclerCalendar;
    @BindView(R.id.floatingActionButton_speakConsut)
    FloatingActionButton mFloatingActionButtonSpeakConsut;
    /** ButterKnife Code **/
    UserPreferences userPreferences;
    String plant_date,message,land_nature,location;
    Fragment fragment;
    OnlyFarmerIDPost farmerIDPost;
    List<ProcessResponseData> processResponseDataList;
    CropCalendarListAdapter cropCalendarListAdapter;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crooping_calendar);
        ButterKnife.bind(this);

        setSupportActionBar( mToolbar3 );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);

        userPreferences = new UserPreferences(this);
        //get cropping calendar parameter
        plant_date=userPreferences.getPlantDate();
        land_nature=userPreferences.getLandNature();
        location=userPreferences.getRequestState();


        if(plant_date.equals("")){
            ErrorAlert("You have not choose any planting date");
        }else{
            //passing a field parameter
            getCalendar(land_nature,location);
        }

        mFloatingActionButtonSpeakConsut.setOnClickListener(this);

    }

    private void getCalendar(String land_nature,String location){
        mRecyclerCalendar.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<ProcessResponseHead> call = client.process_request(land_nature, location);

            call.enqueue(new Callback<ProcessResponseHead>() {
                @Override
                public void onResponse(Call<ProcessResponseHead> call, Response<ProcessResponseHead> response) {

                    try {
                        if(response.code()==200) {

                            boolean status=response.body().getStatus();

                            if(status) {
                                processResponseDataList=response.body().getData();

                                mRecyclerCalendar.setVisibility(View.VISIBLE);
                                mProgress.setVisibility(View.GONE);
                                callRecyclerList(plant_date);


                            }else{
                                mRecyclerCalendar.setVisibility(View.GONE);
                                mProgress.setVisibility(View.GONE);
                                message=response.body().getMessage();
                                ErrorAlert(message);
                            }
                        }

                    } catch (Exception e) {
                        ErrorAlert("Failed: " + e.getMessage());
                        mRecyclerCalendar.setVisibility(View.GONE);
                        mProgress.setVisibility(View.GONE);
                        Log.i("Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ProcessResponseHead> call, Throwable t) {
                    if(message!=null){
                        ErrorAlert(message);
                    }else{
                        ErrorAlert("Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mRecyclerCalendar.setVisibility(View.GONE);
                    mProgress.setVisibility(View.GONE);
                }
            });

        }catch (Exception e){
            Log.i("GenError", e.getMessage());
        }

    }

    private void callRecyclerList(String plant_date){
        cropCalendarListAdapter = new CropCalendarListAdapter(this, processResponseDataList,plant_date);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        mRecyclerCalendar.setLayoutManager(linearLayoutManager);
        mRecyclerCalendar.setItemAnimator(new DefaultItemAnimator());
        mRecyclerCalendar.setAdapter(cropCalendarListAdapter);
    }

    private void ErrorAlert(String message) {

        new AlertDialog.Builder(this)
                .setTitle("Error ")
                .setIcon(R.drawable.ic_baseline_error_outline_24)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        if(plant_date.equals("")){
                            ErrorAlert("You have not choose any planting date");
                        }else{
                            //passing a field parameter
                            getCalendar(land_nature,location);
                        }

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        startActivity(new Intent(CropCalendarActivity.this,
                                Dashboard.class));

                    }
                })
                .show();

    }

    //Method to set fragment immediately Onclick
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton_speakConsut:
                startActivity(new Intent(CropCalendarActivity.this, ConsultantActivity.class));
                this.finish();
                break;
        }
    }
}
