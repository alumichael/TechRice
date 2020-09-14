package com.agrictech.techrice.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.NetworkConnection;
import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;

import com.agrictech.techrice.model.purchase.PurchasePost;
import com.agrictech.techrice.model.purchase.PurchaseRespHead;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PaymentActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.payment_btn)
    Button mPaymentBtn;
    @BindView(R.id.progress)
    AVLoadingIndicatorView mProgress;
    @BindView(R.id.toolbar4)
    Toolbar mToolbar4;
    @BindView(R.id.payment_title_bar)
    TextView mPaymentTitleBar;

    @BindView(R.id.cardView_paymt)
    CardView mCardViewPaymt;
    @BindView(R.id.spinner_month)
    Spinner mSpinnerMonth;
    @BindView(R.id.spinner_year)
    Spinner mSpinnerYear;
    @BindView(R.id.card_no_edt)
    EditText mCardNoEdt;
    @BindView(R.id.cvv_edt)
    EditText mCvvEdt;
    @BindView(R.id.card_name_edt)
    EditText mCardNameEdt;
    @BindView(R.id.cost_txt)
    TextView mCostTxt;
    @BindView(R.id.delivery_txt)
    TextView mDeliveryTxt;
    @BindView(R.id.name_txt)
    TextView mNameTxt;
    @BindView(R.id.payemnt_healing_txt2)
    TextView mPayemntHealingTxt2;
    /** ButterKnife Code **/

    private Charge charge;
    ProgressDialog dialog;


    UserPreferences userPreferences;
    String name,cost,delivery_cost,transact_ref,message;
    String expiryMonth,expiryYear;
    Integer total_cost;
    String provider_ref = "";

    NetworkConnection networkConnection=new NetworkConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ButterKnife.bind(this);

        PaystackSdk.initialize(getApplicationContext());

        setSupportActionBar( mToolbar4 );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);

        userPreferences = new UserPreferences(this);

        Intent intent = getIntent();
        name= intent.getStringExtra(Constant.PRODUCT_NAME);
        cost= intent.getStringExtra(Constant.PRODUCT_COST);
        delivery_cost= intent.getStringExtra(Constant.PRODUCT_DELIVERY_COST);
        String curr_cost="NGN"+cost;
        String delivery_append="Delivery Charge: NGN"+delivery_cost;

        mNameTxt.setText(name);
        mCostTxt.setText(curr_cost);
        mDeliveryTxt.setText(delivery_append);

        total_cost=Integer.parseInt(cost)+Integer.parseInt(delivery_cost);
        Random random=new Random();
        transact_ref= String.valueOf(random.nextInt());

        mm();
        yy();
        doPayment();
    }

    private void mm() {
        // MM Spinner
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> mmAdapter = ArrayAdapter
                .createFromResource(this, R.array.mm_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        mmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinnerMonth.setAdapter(mmAdapter);

        mSpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //String stringTextLevel = (String) parent.getItemAtPosition(position);
                //electedView = view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void yy() {
// YY Spinner
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> yyAdapter = ArrayAdapter
                .createFromResource(this, R.array.yy_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        yyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinnerYear.setAdapter(yyAdapter);

        mSpinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //String stringTextLevel = (String) parent.getItemAtPosition(position);
                //electedView = view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }




    private void doPayment(){
        if (total_cost != null || total_cost!=0) {
            mPaymentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (networkConnection.isNetworkConnected(getBaseContext())) {
                        boolean isValid = true;

                        expiryMonth = mSpinnerMonth.getSelectedItem().toString();
                        expiryYear = mSpinnerYear.getSelectedItem().toString();
                        
                        if (mCardNoEdt.getText().toString().isEmpty()) {
                            isValid = false;
                        } else if (mCardNoEdt.getText().toString().length() < 15) {
                            isValid = false;
                        }else if (mCardNoEdt.getText().toString().isEmpty()) {
                            isValid = false;
                        }else if (mCvvEdt.getText().toString().isEmpty()) {
                            isValid = false;
                        } else if (expiryMonth.equals("MM") || expiryYear.equals("YY")) {
                            isValid = false;
                        }



                        if (isValid) {
                            mPaymentBtn.setEnabled(false);
//                        mPaymentBtn.setBackgroundColor(Color.RED);
                            charge = new Charge();
                            dialogMessage("Performing transaction... please wait!");

                            String cardNumber = mCardNoEdt.getText().toString();
                            String cvv = mCvvEdt.getText().toString().trim();// cvv of the test card
//                          String cardNumber = "5060666666666666666";
                            int expiryMonthInt = Integer.parseInt(expiryMonth); //any month in the future
                            int expiryYearInt = Integer.parseInt(expiryYear); // any year in the future.
//                        String cvv = "123";  // cvv of the test card


                            Card card = new Card(cardNumber, expiryMonthInt, expiryYearInt, cvv);
                            if (card.isValid()) {
                                charge.setCard(card);
                                int cost = (int) Math.round(total_cost);

                                charge.setAmount(cost * 100);
                                charge.setEmail(userPreferences.getEmail());
                                charge.setReference(transact_ref);


                                PaystackSdk.chargeCard(PaymentActivity.this, charge, new Paystack.TransactionCallback() {
                                    @Override
                                    public void onSuccess(Transaction transaction) {

                                        mPaymentBtn.setEnabled(false);
                                        // This is called only after transaction is deemed successful.
                                        // Retrieve the transaction, and send its reference to your server
                                        // for verification.
                                        provider_ref = transaction.getReference();
                                        Log.i("provider_ref", provider_ref);
                                        dialogMessage("Transaction Successfully, Please hold on");

                                        mPaymentBtn.setVisibility(View.GONE);
                                        mProgress.setVisibility(View.VISIBLE);
                                        sendData(provider_ref);


                                    }

                                    @Override
                                    public void beforeValidate(Transaction transaction) {
                                        mPaymentBtn.setEnabled(false);
//                                    mPaymentBtn.setBackgroundColor(Color.RED);
                                        dialogMessage("Validating...please wait!");
                                        // This is called only before requesting OTP.
                                        // Save reference so you may send to server. If
                                        // error occurs with OTP, you should still verify on server.

                                        dialogMessage("loading...");
                                    }

                                    @Override
                                    public void onError(Throwable error, Transaction transaction) {
                                        mPaymentBtn.setEnabled(true);
                                        dismissDialog();
                                        //handle error here
                                        ErrorAlert("Failed: " + error.getMessage());
                                    }

                                });
                                // charge card
                                try {
                                    charge.putCustomField("Charged From", "Android SDK");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                mPaymentBtn.setEnabled(true);
                                ErrorAlert("Invalid card!");
                                dismissDialog();
                            }

                        }else{
                            ErrorAlert("Invalid entry!");
                        }
                    } else {
                        ErrorAlert("Kindly, connect to the internet!");
                    }


                }
            });
        } else {
            ErrorAlert("Payment Reference is Null");
        }

    }

    private void sendData(String provider_ref){
       // PurchasePost purchasePost=new PurchasePost();
        
        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<PurchaseRespHead> call = client.buy_input(userPreferences.getFarmerId(),provider_ref,
                    name,total_cost);

            call.enqueue(new Callback<PurchaseRespHead>() {
                @Override
                public void onResponse(Call<PurchaseRespHead> call, Response<PurchaseRespHead> response) {

                    try {
                        if(response.code()==200) {

                            boolean status=response.body().getStatus();

                            if(status) {
                                message=response.body().getMessage();
                                Intent intent =new Intent(PaymentActivity.this,DoneActivity.class);
                                intent.putExtra(Constant.SUCCESS_MSG,message);
                                startActivity(intent);
                                finish();
                            }else{
                                message=response.body().getMessage();
                                ErrorAlert(message);
                            }
                        }

                    } catch (Exception e) {
                        ErrorAlert("Transaction Error, contact us! ");
                        mPaymentBtn.setVisibility(View.VISIBLE);
                        mProgress.setVisibility(View.GONE);
                        Log.i("Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<PurchaseRespHead> call, Throwable t) {
                    if(message!=null){
                        ErrorAlert(message);
                    }else{
                        ErrorAlert("Login Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mPaymentBtn.setVisibility(View.VISIBLE);
                    mProgress.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            Log.i("GenError", e.getMessage());
        }

    }



    private void dialogMessage(String s) {
        dialog = new ProgressDialog(PaymentActivity.this);
        dialog.setMessage("Performing transaction... please wait");
        dialog.setCancelable(false);
        dialog.show();
    }

    private void dismissDialog() {
        if ((dialog != null) && dialog.isShowing()) {
            dialog.dismiss();
        }
    }



    private void ErrorAlert(String message) {

        new AlertDialog.Builder(this)
                .setTitle("Error ")
                .setCancelable(false)
                .setIcon(R.drawable.ic_baseline_error_outline_24)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();

                    }
                })
                .show();
    }


    private void SuccessAlert(String message) {

        new AlertDialog.Builder(this)
                .setTitle("Message ")
                .setCancelable(false)
                .setIcon(R.drawable.ic_baseline_done_outline_24)
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
