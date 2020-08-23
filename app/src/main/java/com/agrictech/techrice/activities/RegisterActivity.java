package com.agrictech.techrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.NetworkConnection;
import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.model.register.RegisterPost;
import com.agrictech.techrice.model.register.RegisterResponseHead;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agrictech.techrice.Util.ShowMessage;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    /** ButterKnife Code **/
    @BindView(R.id.create_account_txt)
    TextView mCreateAccountTxt;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;
    @BindView(R.id.progress3)
    AVLoadingIndicatorView mProgress3;
    @BindView(R.id.scrollView2)
    ScrollView mScrollView2;
    @BindView(R.id.confirm_password_edt)
    EditText mConfirmPasswordEdt;
    @BindView(R.id.password_edt)
    EditText mPasswordEdt;
    @BindView(R.id.phone_edt)
    EditText mPhoneEdt;
    @BindView(R.id.email_edt)
    EditText mEmailEdt;
    @BindView(R.id.lastname_edt)
    EditText mLastnameEdt;
    @BindView(R.id.firstname_edt)
    EditText mFirstnameEdt;
    @BindView(R.id.lga_edt)
    EditText mLgaEdt;
    @BindView(R.id.guideline3)
    Guideline mGuideline3;
    @BindView(R.id.guideline4)
    Guideline mGuideline4;
    @BindView(R.id.spinner_state)
    Spinner mSpinnerState;
    /** ButterKnife Code **/
    String stateString,phone,password,message;
    UserPreferences userPreferences;
    NetworkConnection networkConnection=new NetworkConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        userPreferences = new UserPreferences(this);

        setAction();
    }

    private void setAction(){
        mRegisterBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register_btn:

                ValidateForm();

                break;

        }
    }


    private void stateSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.state_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinnerState.setAdapter(staticAdapter);

        mSpinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSpinnerState.getItemAtPosition(0);


            }
        });

    }

    private void ValidateForm() {

        stateString = mSpinnerState.getSelectedItem().toString();


        if (networkConnection.isNetworkConnected(this)) {
            boolean isValid = true;

            if (mEmailEdt.getText().toString().isEmpty()) {
                isValid = false;
            } else if (!isValidEmailAddress(mEmailEdt.getText().toString())) {
                isValid = false;
            } else if (mFirstnameEdt.getText().toString().isEmpty()) {
                isValid = false;
            } else if (mLastnameEdt.getText().toString().isEmpty()) {
                isValid = false;
            } else if (mPhoneEdt.getText().toString().isEmpty()) {
                isValid = false;
            } else if (mPhoneEdt.getText().toString().trim().length()<8) {
                isValid = false;
            }else if (mLgaEdt.getText().toString().isEmpty()) {
                isValid = false;
            }else if (mPasswordEdt.getText().toString().isEmpty()) {
                isValid = false;
            } else if (mPasswordEdt.getText().toString().trim().length()<6) {
                isValid = false;
            } else if (stateString.equals("Select State")) {
                isValid = false;
            }

            if (isValid) {
                //Post Request to Api
                sendRegData();
            }else{
                ShowMessage(this,"Invalid input entered");
            }

            return;
        }
        ShowMessage(this,"No Internet connection discovered!");
    }


    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        if (null != email) {
            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                result = false;
            }
        }

        return result;
    }


    private void sendRegData(){
        mRegisterBtn.setVisibility(View.GONE);
        mProgress3.setVisibility(View.VISIBLE);

        RegisterPost registerPost =new RegisterPost(mFirstnameEdt.getText().toString(),
                mLastnameEdt.getText().toString(), mEmailEdt.getText().toString(),
                mPhoneEdt.getText().toString(),
                mPasswordEdt.getText().toString(),stateString,
                mLgaEdt.getText().toString());
        Log.i("RegisterObj", registerPost.toString());

        sentNetworkRequest(registerPost);
    }


    private  void sentNetworkRequest(RegisterPost registerPost){
        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<RegisterResponseHead> call = client.register(registerPost);

            call.enqueue(new Callback<RegisterResponseHead>() {
                @Override
                public void onResponse(Call<RegisterResponseHead> call, Response<RegisterResponseHead> response) {

                    try {


                        if(response.code()==200) {

                            boolean status=response.body().getStatus();

                            if(status) {

                                phone =response.body().getRegisterResponseData().getPhone();
                                password =response.body().getRegisterResponseData().getPassword();
                                userPreferences.setPhone(phone);
                                userPreferences.setPassword(password);

                                userPreferences.setEmail(response.body().getRegisterResponseData()
                                        .getEmail());
                                userPreferences.setUserFirstname(response.body()
                                        .getRegisterResponseData().getFirstname());
                                userPreferences.setUserLastname(response.body()
                                        .getRegisterResponseData().getLastname());
                                userPreferences.setState(response.body()
                                        .getRegisterResponseData().getState());
                                userPreferences.setLandArea(response.body()
                                        .getRegisterResponseData().getLga());

                                userPreferences.setFarmerId(response.body()
                                        .getRegisterResponseData().getFarmerId());

                                try {

                                    mRegisterBtn.setVisibility(View.VISIBLE);
                                    mProgress3.setVisibility(View.GONE);

                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    intent.putExtra(Constant.PHONE,phone);
                                    intent.putExtra(Constant.PASSWORD,password);
                                    startActivity(intent);
                                    finish();


                                } catch (Exception e) {
                                    Log.i("PrefError", e.getMessage());
                                    mRegisterBtn.setVisibility(View.VISIBLE);
                                    mProgress3.setVisibility(View.GONE);
                                }

                            }else{
                                message=response.body().getMessage();
                                ShowMessage(getApplicationContext(),message);
                            }
                        }

                    } catch (Exception e) {
                        ShowMessage(getApplicationContext(),"Registration Failed: " + e.getMessage());
                        mRegisterBtn.setVisibility(View.VISIBLE);
                        mProgress3.setVisibility(View.GONE);
                        Log.i("Registration Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponseHead> call, Throwable t) {
                    if(message!=null){
                        ShowMessage(getApplicationContext(),message);
                    }else{
                        ShowMessage(getApplicationContext(),"Registration Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mRegisterBtn.setVisibility(View.VISIBLE);
                    mProgress3.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            Log.i("GenError", e.getMessage());
        }


    }

}
