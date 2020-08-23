package com.agrictech.techrice.frgament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.agrictech.techrice.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;

public class DashBoardFragment extends Fragment {

    /** ButterKnife Code **/
    @BindView(R.id.measure_title)
    TextView mMeasureTitle;
    @BindView(R.id.guideline7)
    Guideline mGuideline7;
    @BindView(R.id.guideline8)
    Guideline mGuideline8;
    @BindView(R.id.textView13)
    TextView mTextView13;
    @BindView(R.id.land_area_edt)
    EditText mLandAreaEdt;
    @BindView(R.id.proceed_btn)
    Button mProceedBtn;
    @BindView(R.id.progress4)
    AVLoadingIndicatorView mProgress4;
    /** ButterKnife Code **/

    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        return view;

    }

    //Method to set fragment immediately Onclick
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}
