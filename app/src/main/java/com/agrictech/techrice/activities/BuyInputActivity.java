package com.agrictech.techrice.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.agrictech.techrice.adapters.CropCalendarListAdapter;
import com.agrictech.techrice.adapters.InputCategoryListAdapter;
import com.agrictech.techrice.adapters.InputListAdapter;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.frgament.DashBoardFragment;
import com.agrictech.techrice.model.CategoryList;
import com.agrictech.techrice.model.OnlyFarmerIDPost;
import com.agrictech.techrice.model.CategoryList;
import com.agrictech.techrice.model.products.ProductResponseData;
import com.agrictech.techrice.model.products.ProductResponseHead;
import com.agrictech.techrice.model.season_guild.ProcessResponseHead;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyInputActivity extends AppCompatActivity {
    /**
     * ButterKnife Code
     **/
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.title_txt)
    TextView mTitleTxt;
    @BindView(R.id.recycler_inputs_category)
    RecyclerView mRecyclerInputsCategory;
    @BindView(R.id.progress)
    AVLoadingIndicatorView mProgress;
    /**
     * ButterKnife Code
     **/

    UserPreferences userPreferences;
    OnlyFarmerIDPost farmerIDPost;
    String message;
    List<ProductResponseData> productResponseDataList;
    List<CategoryList> categoryLists;
    InputCategoryListAdapter inputCategoryListAdapter;

    ArrayList<ProductResponseData> seedList ;
    ArrayList<ProductResponseData> fertList ;
    ArrayList<ProductResponseData> herbList ;
    ArrayList<ProductResponseData> consultList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_input);

        ButterKnife.bind(this);
        categoryLists = new ArrayList<>();
        seedList = new ArrayList<>();
        fertList = new ArrayList<>();
        herbList = new ArrayList<>();
        consultList = new ArrayList<>();

        setSupportActionBar( mToolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);


        userPreferences = new UserPreferences(this);
        //farmerIDPost = new OnlyFarmerIDPost(userPreferences.getFarmerId());

        getAllInputCategory(userPreferences.getFarmerId());

    }

    private void getAllInputCategory(int farmerIDPost) {
        mRecyclerInputsCategory.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<ProductResponseHead> call = client.product_list(farmerIDPost);

            call.enqueue(new Callback<ProductResponseHead>() {
                @Override
                public void onResponse(Call<ProductResponseHead> call, Response<ProductResponseHead> response) {

                    try {
                        if (response.code() == 200) {

                            boolean status = response.body().getStatus();

                            if (status) {

                                productResponseDataList = response.body().getData();

                                Log.i("Allproduct",productResponseDataList.toString());

                                    for (int i = 0; i < productResponseDataList.size(); i++) {
                                        if (productResponseDataList.get(i).getCategory().
                                                equals("Seeds")) {
                                            seedList.add(productResponseDataList.get(i));

                                        } else if (productResponseDataList.get(i).getCategory().
                                                equals("Herbicide")) {
                                            herbList.add(productResponseDataList.get(i));

                                        } else if (productResponseDataList.get(i).getCategory().
                                                equals("Fertilizer")) {
                                            fertList.add(productResponseDataList.get(i));

                                        } else if (productResponseDataList.get(i).getCategory().
                                                equals("Consultancy")) {
                                            consultList.add(productResponseDataList.get(i));
                                        }
                                    }

                                Log.i("Seedproduct",seedList.toString());
                                    mRecyclerInputsCategory.setVisibility(View.VISIBLE);
                                    mProgress.setVisibility(View.GONE);
                                    callRecyclerList();


                            } else {
                                mRecyclerInputsCategory.setVisibility(View.GONE);
                                mProgress.setVisibility(View.GONE);
                                message = response.body().getMessage();
                                ErrorAlert(message);
                            }
                        }

                    } catch (Exception e) {
                        ErrorAlert("Failed: " + e.getMessage());
                        mRecyclerInputsCategory.setVisibility(View.GONE);
                        mProgress.setVisibility(View.GONE);
                        Log.i("Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ProductResponseHead> call, Throwable t) {
                    if (message != null) {
                        ErrorAlert(message);
                    } else {
                        ErrorAlert("Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mRecyclerInputsCategory.setVisibility(View.GONE);
                    mProgress.setVisibility(View.GONE);
                }
            });

        } catch (Exception e) {
            Log.i("GenError", e.getMessage());
        }

    }

    private void callRecyclerList() {

        CategoryList m = new CategoryList("Seeds",seedList );
        categoryLists.add(m);

        m = new CategoryList("Fertilizer",fertList );
        categoryLists.add(m);

        m = new CategoryList("Herbicide",herbList );
        categoryLists.add(m);

        m = new CategoryList("Consultancy",consultList );
        categoryLists.add(m);


        inputCategoryListAdapter = new InputCategoryListAdapter(this, categoryLists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        mRecyclerInputsCategory.setLayoutManager(linearLayoutManager);
        mRecyclerInputsCategory.setItemAnimator(new DefaultItemAnimator());
        mRecyclerInputsCategory.setAdapter(inputCategoryListAdapter);
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
                        getAllInputCategory(userPreferences.getFarmerId());

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        startActivity(new Intent(BuyInputActivity.this, Dashboard.class));

                    }
                })
                .show();
    }

}
