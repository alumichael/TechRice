package com.agrictech.techrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.Guideline;

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.NetworkConnection;
import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.model.login.LoginPost;
import com.agrictech.techrice.model.login.LoginResponseHead;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agrictech.techrice.Util.ShowMessage;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /** ButterKnife Code **/
    @BindView(R.id.progress2)
    AVLoadingIndicatorView mProgress2;
    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.phone_edt)
    EditText mPhoneEdt;
    @BindView(R.id.password_edt)
    EditText mPasswordEdt;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.twitter_btn)
    ImageButton mTwitterBtn;
    @BindView(R.id.facebook_btn)
    ImageButton mFacebookBtn;
    @BindView(R.id.instagram_btn)
    ImageButton mInstagramBtn;
    @BindView(R.id.register_txt)
    TextView mRegisterTxt;
    /** ButterKnife Code **/

    NetworkConnection networkConnection=new NetworkConnection();
    String phone,password,message;
    UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userPreferences = new UserPreferences(this);

        Intent intent=getIntent();
        phone=intent.getStringExtra(Constant.PHONE);
        password=intent.getStringExtra(Constant.PASSWORD);


        setAction();

        if(phone!=null) {
            mPhoneEdt.setText(phone);
            mPasswordEdt.setText(password);
        }


    }

    private void setAction(){
        mRegisterTxt.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_txt:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                 break;

            case R.id.login_btn:

                ValidateForm();

                break;

        }
    }


    private void ValidateForm() {

        if (networkConnection.isNetworkConnected(this)) {
            boolean isValid = true;

           if (mPhoneEdt.getText().toString().isEmpty()) {
                isValid = false;
            } else if (mPhoneEdt.getText().toString().trim().length()<8) {
                isValid = false;
            }else if (mPasswordEdt.getText().toString().isEmpty()) {
                isValid = false;
            } else if (mPasswordEdt.getText().toString().trim().length()<6) {
                isValid = false;
            }
            if (isValid) {
                //Post Request to Api
                sentNetworkRequest();
            }else{
                ShowMessage(this,"Invalid input entered");
            }

            return;
        }
        ShowMessage(this,"No Internet connection discovered!");
    }

  /*  private void sendLoginData(){


        LoginPost loginPost =new LoginPost();
        Log.i("LoginObj", loginPost.toString());

        sentNetworkRequest();
    }
*/
    private  void sentNetworkRequest(){
        mLoginBtn.setVisibility(View.GONE);
        mProgress2.setVisibility(View.VISIBLE);

        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<LoginResponseHead> call = client.login(mPhoneEdt.getText().toString(),
                    mPasswordEdt.getText().toString());

            call.enqueue(new Callback<LoginResponseHead>() {
                @Override
                public void onResponse(Call<LoginResponseHead> call, Response<LoginResponseHead> response) {

                    try {
                        if(response.code()==200) {
                            mLoginBtn.setVisibility(View.VISIBLE);
                            mProgress2.setVisibility(View.GONE);

                            boolean status=response.body().getStatus();

                            if(status) {
                                phone =response.body().getLoginResponseData().getPhone();
                                password =response.body().getLoginResponseData().getPassword();
                                userPreferences.setPhone(phone);
                                userPreferences.setPassword(password);
                                userPreferences.setEmail(response.body().getLoginResponseData()
                                        .getEmail());
                                userPreferences.setUserFirstname(response.body()
                                        .getLoginResponseData().getFirstname());
                                userPreferences.setUserLastname(response.body()
                                        .getLoginResponseData().getLastname());
                                userPreferences.setState(response.body()
                                        .getLoginResponseData().getState());
                                userPreferences.setLGA(response.body()
                                        .getLoginResponseData().getLga());
                                userPreferences.setFarmerId(response.body()
                                        .getLoginResponseData().getFarmerId());
                                userPreferences.setNotification(true);
                                userPreferences.setFullName(userPreferences.getUserFirstname()+" "+
                                        userPreferences.getUserLastname());

                                if(response.body().getLoginResponseData().getLandArea()==null){
                                    userPreferences.setLandArea("0");
                                    userPreferences.setLandNature("--");
                                }else{
                                    userPreferences.setLandArea(String.valueOf(response.body()
                                            .getLoginResponseData().getLandArea()));
                                    userPreferences.setLandNature(response.body()
                                            .getLoginResponseData().getLandNature());
                                }

                                try {



                                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                    startActivity(intent);
                                    finish();


                                } catch (Exception e) {
                                    Log.i("PrefError", e.getMessage());
                                    mLoginBtn.setVisibility(View.VISIBLE);
                                    mProgress2.setVisibility(View.GONE);
                                }

                            }else{
                                message=response.body().getMessage();
                                ShowMessage(getApplicationContext(),message);
                            }
                        }

                    } catch (Exception e) {
                        ShowMessage(getApplicationContext(),"Login Failed: " + e.getMessage());
                        mLoginBtn.setVisibility(View.VISIBLE);
                        mProgress2.setVisibility(View.GONE);
                        Log.i("Registration Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseHead> call, Throwable t) {
                    if(message!=null){
                        ShowMessage(getApplicationContext(),message);
                    }else{
                        ShowMessage(getApplicationContext(),"Login Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mLoginBtn.setVisibility(View.VISIBLE);
                    mProgress2.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            Log.i("GenError", e.getMessage());
        }


    }

}
