package com.agrictech.techrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.model.delivery.DeliverPost;
import com.agrictech.techrice.model.delivery.DeliveryRespHead;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agrictech.techrice.Util.ShowMessage;

public class ConfirmDeliveryActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.payment_btn)
    Button mConfirmBtn;
    @BindView(R.id.progress)
    AVLoadingIndicatorView mProgress;
    @BindView(R.id.toolbar4)
    Toolbar mToolbar4;
    @BindView(R.id.payment_title_bar)
    TextView mPaymentTitleBar;
    @BindView(R.id.cost_txt)
    TextView mCostTxt;
    @BindView(R.id.name_txt)
    TextView mNameTxt3;
    @BindView(R.id.payemnt_healing_txt2)
    TextView mPayemntHealingTxt2;
    @BindView(R.id.order_id)
    TextView mOrderIdTxt2;
    /** ButterKnife Code **/
    UserPreferences userPreferences;
    DeliverPost deliverPost;
    String name,cost,order_id,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delivery);

        ButterKnife.bind(this);

        setSupportActionBar( mToolbar4 );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);


        userPreferences = new UserPreferences(this);

        Intent intent = getIntent();
        name= intent.getStringExtra(Constant.PRODUCT_NAME);
        cost= intent.getStringExtra(Constant.PRODUCT_COST);
        order_id= intent.getStringExtra(Constant.ORDER_ID);

        mNameTxt3.setText(name);
        mCostTxt.setText(cost);
        mOrderIdTxt2.setText(order_id);

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelivery();
            }
        });



    }

    private void confirmDelivery(){
        mConfirmBtn.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
        //deliverPost=new DeliverPost();

        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<DeliveryRespHead> call = client.delivery(userPreferences.getFarmerId(),
                    Integer.parseInt(order_id));

            call.enqueue(new Callback<DeliveryRespHead>() {
                @Override
                public void onResponse(Call<DeliveryRespHead> call, Response<DeliveryRespHead> response) {

                    try {
                        if(response.code()==200) {
                            mConfirmBtn.setVisibility(View.VISIBLE);
                            mProgress.setVisibility(View.GONE);
                            boolean status=response.body().getStatus();

                            if(status) {
                                ShowMessage(getApplicationContext(),"Thank You!");
                                startActivity(new Intent(ConfirmDeliveryActivity.this,Dashboard.class));
                                finish();

                            }else{
                                message=response.body().getMessage();
                                ShowMessage(getApplicationContext(),message);
                            }
                        }

                    } catch (Exception e) {
                        ShowMessage(getApplicationContext(),"Confirmation Failed: " + e.getMessage());
                        mConfirmBtn.setVisibility(View.VISIBLE);
                        mProgress.setVisibility(View.GONE);
                        Log.i("Confirmation Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<DeliveryRespHead> call, Throwable t) {
                    if(message!=null){
                        ShowMessage(getApplicationContext(),message);
                    }else{
                        ShowMessage(getApplicationContext(),"Confirmation Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mConfirmBtn.setVisibility(View.VISIBLE);
                    mProgress.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            Log.i("GenError", e.getMessage());
        }




    }



}
