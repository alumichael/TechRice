package com.agrictech.techrice.frgament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.agrictech.techrice.NetworkConnection;
import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.activities.LoginActivity;
import com.agrictech.techrice.activities.RegisterActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.agrictech.techrice.Util.ShowMessage;

public class DashBoardFragment extends Fragment implements View.OnClickListener{

    /** ButterKnife Code **/
    @BindView(R.id.measure_title)
    TextView mMeasureTitle;
    @BindView(R.id.image_card)
    CardView mImageCard;
    @BindView(R.id.head_img)
    ImageView mHeadImg;
    @BindView(R.id.textView13)
    TextView mTextView13;
    @BindView(R.id.land_area_edt)
    EditText mLandAreaEdt;
    @BindView(R.id.proceed_btn)
    Button mProceedBtn;
    @BindView(R.id.progress4)
    AVLoadingIndicatorView mProgress4;
    @BindView(R.id.textView2)
    TextView mTextView2;
    /** ButterKnife Code **/

    Fragment fragment;

    NetworkConnection networkConnection=new NetworkConnection();
    UserPreferences userPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this,view);
        userPreferences = new UserPreferences(getContext());

        setAction();



        return view;

    }

    private void setAction(){
        mProceedBtn.setOnClickListener(this);
        //mLoginBtn.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.proceed_btn:

                ValidateForm();

                break;

        }

    }


    private void ValidateForm() {

        if (networkConnection.isNetworkConnected(getContext())) {
            boolean isValid = true;

            if (mLandAreaEdt.getText().toString().isEmpty()) {
                isValid = false;
            }
            if (isValid) {
                userPreferences.setLandArea(mLandAreaEdt.getText().toString());
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, Fragment_StartPlantingInput
                        .newInstance(mLandAreaEdt.getText().toString()), Fragment_StartPlantingInput.class.getSimpleName());
                        ft.commit();
            }else{
                ShowMessage(getContext(),"Invalid input entered");
            }

            return;
        }
        ShowMessage(getContext(),"No Internet connection discovered!");
    }




}
