package com.agrictech.techrice.frgament;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.agrictech.techrice.NetworkConnection;
import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.activities.Dashboard;
import com.agrictech.techrice.activities.LoginActivity;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.model.land_info.LandInfoPost;
import com.agrictech.techrice.model.land_info.LandInfoRespHead;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agrictech.techrice.Util.ShowMessage;


public class Fragment_StartPlantingInput extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LAND_AREA = "land_area";
  

    // TODO: Rename and change types of parameters
    private String mLandArea;



    /** ButterKnife Code **/
    @BindView(R.id.proceed_btn)
    Button mProceedBtn;
    @BindView(R.id.progress6)
    AVLoadingIndicatorView mProgress6;
    TextView mTextView18;
    @BindView(R.id.spinner_location)
    Spinner mSpinnerLocation;
    @BindView(R.id.spinner_crop)
    Spinner mSpinnerCrop;
    @BindView(R.id.textView19)
    TextView mTextView19;
    @BindView(R.id.textView20)
    TextView mTextView20;
    @BindView(R.id.fadama_card)
    CardView mFadamaCard;
    @BindView(R.id.textView22)
    TextView mTextView22;
    @BindView(R.id.textView23)
    TextView mTextView23;
    @BindView(R.id.fadama_radio)
    RadioButton mFadamaRadio;
    @BindView(R.id.lowland_card)
    CardView mLowlandCard;
    @BindView(R.id.textView222)
    TextView mTextView222;
    @BindView(R.id.textView223)
    TextView mTextView223;
    @BindView(R.id.lowland_radio)
    RadioButton mLowlandRadio;
    @BindView(R.id.upland_card)
    CardView mUplandCard;

    @BindView(R.id.textView2222)
    TextView mTextView2222;
    @BindView(R.id.textView2223)
    TextView mTextView2223;
    @BindView(R.id.upland_radio)
    RadioButton mUplandRadio;

    /** ButterKnife Code **/

    UserPreferences userPreferences;

    String locationString,cropString,message;
    String landNature="fadama";

    Fragment fragment;

    NetworkConnection networkConnection=new NetworkConnection();
    
    public Fragment_StartPlantingInput() {
        // Required empty public constructor
    }


    public static Fragment_StartPlantingInput newInstance(String land_area) {
        Fragment_StartPlantingInput fragment = new Fragment_StartPlantingInput();
        Bundle args = new Bundle();
        args.putString(LAND_AREA, land_area);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLandArea = getArguments().getString(LAND_AREA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_planting_input, container, false);
        ButterKnife.bind(this,view);
        userPreferences = new UserPreferences(getContext());

        setAction();
        locationSpinner();
        cropSpinner();


        return view;
    }

    private void setAction(){
        mProceedBtn.setOnClickListener(this);
        mFadamaRadio.setOnClickListener(this);
        mUplandRadio.setOnClickListener(this);
        mLowlandRadio.setOnClickListener(this);
    }

    private void locationSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.state_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinnerLocation.setAdapter(staticAdapter);

        mSpinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSpinnerLocation.getItemAtPosition(0);

            }
        });

    }

    private void cropSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.crop_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinnerCrop.setAdapter(staticAdapter);

        mSpinnerCrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String stringText = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSpinnerCrop.getItemAtPosition(0);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.proceed_btn:

                ValidateForm();

                break;

            case R.id.fadama_radio:
                mFadamaRadio.setChecked(true);
                mUplandRadio.setChecked(false);
                mLowlandRadio.setChecked(false);
                landNature="fadama";
                userPreferences.setLandNature(landNature);

                break;

            case R.id.upland_radio:

                mFadamaRadio.setChecked(false);
                mUplandRadio.setChecked(true);
                mLowlandRadio.setChecked(false);
                landNature="upland";
                userPreferences.setLandNature(landNature);

                break;

            case R.id.lowland_radio:

                mFadamaRadio.setChecked(false);
                mUplandRadio.setChecked(false);
                mLowlandRadio.setChecked(true);
                landNature="lowland";
                userPreferences.setLandNature(landNature);

                break;

        }
    }


    private void ValidateForm() {

        if (networkConnection.isNetworkConnected(getContext())) {
            boolean isValid = true;

            locationString = mSpinnerLocation.getSelectedItem().toString();
            cropString = mSpinnerCrop.getSelectedItem().toString();

            if (locationString.equals("Select State")) {
                isValid = false;
            }else if(locationString.equals("Select Crop")){
                isValid = false;
            }
            
            if (isValid) {
                sendLandData();
            }else{
                ShowMessage(getContext(),"Invalid input entered");
            }

            return;
        }
        ShowMessage(getContext(),"No Internet connection discovered!");
    }
    
    private void sendLandData(){

        mProceedBtn.setVisibility(View.GONE);
        mProgress6.setVisibility(View.VISIBLE);

       // LandInfoPost landInfoPost=new LandInfoPost();

        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<LandInfoRespHead> call = client.land_info(Double.parseDouble(mLandArea),landNature,
                    userPreferences.getFarmerId());

            call.enqueue(new Callback<LandInfoRespHead>() {
                @Override
                public void onResponse(Call<LandInfoRespHead> call, Response<LandInfoRespHead> response) {

                    try {
                        if(response.code()==200) {

                            boolean status=response.body().getStatus();

                            if(status) {

                                userPreferences.setRequestState(locationString);
                                userPreferences.setLandNature(landNature);
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.fragment_container, Fragment_Requirement
                                        .newInstance(String.valueOf(response.body().getLandInfoRespData().getFarmerId())), Fragment_Requirement.class.getSimpleName());
                                ft.commit();

                                }else{
                                    message=response.body().getMessage();
                                    ShowMessage(getContext(),message);
                                }



                            }


                    } catch (Exception e) {
                        ShowMessage(getContext(),"Failed: " + e.getMessage());
                        mProceedBtn.setVisibility(View.VISIBLE);
                        mProgress6.setVisibility(View.GONE);
                        Log.i("Registration Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LandInfoRespHead> call, Throwable t) {
                    if(message!=null){
                        ShowMessage(getContext(),message);
                    }else{
                        ShowMessage(getContext(),"Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                    mProceedBtn.setVisibility(View.VISIBLE);
                    mProgress6.setVisibility(View.GONE);
                }
            });

        }catch (Exception e){
            Log.i("GenError", e.getMessage());
        }

    }


}
