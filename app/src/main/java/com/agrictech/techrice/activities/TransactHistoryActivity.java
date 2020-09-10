package com.agrictech.techrice.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.adapters.InputCategoryListAdapter;
import com.agrictech.techrice.adapters.TransactionListAdapter;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.model.CategoryList;
import com.agrictech.techrice.model.OnlyFarmerIDPost;

import com.agrictech.techrice.model.transaction.HistoryData;
import com.agrictech.techrice.model.transaction.HistoryHead;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactHistoryActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title_txt)
    TextView mTitleTxt;
    @BindView(R.id.progress)
    AVLoadingIndicatorView mProgress;
    @BindView(R.id.recycler_history)
    RecyclerView mRecyclerHistory;
    @BindView(R.id.nothing_txt)
    TextView mNothingTxt;
    /** ButterKnife Code **/

    UserPreferences userPreferences;
    OnlyFarmerIDPost farmerIDPost;
    List<HistoryData> historyDataList;
    TransactionListAdapter transactionListAdapter;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroy);

        ButterKnife.bind(this);

        setSupportActionBar( mToolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);

        userPreferences = new UserPreferences(this);

        //farmerIDPost = new OnlyFarmerIDPost();

        getAllTransaction(userPreferences.getFarmerId());

    }
    
    private void getAllTransaction(int farmerIDPost){
        mRecyclerHistory.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<HistoryHead> call = client.transact_history(farmerIDPost);

            call.enqueue(new Callback<HistoryHead>() {
                @Override
                public void onResponse(Call<HistoryHead> call, Response<HistoryHead> response) {

                    try {
                        if (response.code() == 200) {

                            boolean status = response.body().getStatus();

                            if (status) {
                                historyDataList = response.body().getData();
                                if(historyDataList.size()!=0) {
                                    mRecyclerHistory.setVisibility(View.VISIBLE);
                                    mProgress.setVisibility(View.GONE);
                                    callRecyclerList();
                                }else{
                                    mRecyclerHistory.setVisibility(View.GONE);
                                    mProgress.setVisibility(View.GONE);
                                    mNothingTxt.setVisibility(View.VISIBLE);
                                }

                            } else {
                                mRecyclerHistory.setVisibility(View.GONE);
                                mProgress.setVisibility(View.GONE);
                                message = response.body().getMessage();
                                ErrorAlert(message);
                            }
                        }

                    } catch (Exception e) {
                        ErrorAlert("Failed: " + e.getMessage());
                        mRecyclerHistory.setVisibility(View.GONE);
                        mProgress.setVisibility(View.GONE);
                        Log.i("Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<HistoryHead> call, Throwable t) {
                    if (message != null) {
                        ErrorAlert(message);
                    } else {
                        ErrorAlert("Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mRecyclerHistory.setVisibility(View.GONE);
                    mProgress.setVisibility(View.GONE);
                }
            });

        } catch (Exception e) {
            Log.i("GenError", e.getMessage());
        }

    }

    private void callRecyclerList() {

        transactionListAdapter = new TransactionListAdapter(this, historyDataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        mRecyclerHistory.setLayoutManager(linearLayoutManager);
        mRecyclerHistory.setItemAnimator(new DefaultItemAnimator());
        mRecyclerHistory.setAdapter(transactionListAdapter);
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
                        getAllTransaction(userPreferences.getFarmerId());

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        startActivity(new Intent(TransactHistoryActivity.this, Dashboard.class));


                    }
                })
                .show();
    }

}
