package com.agrictech.techrice.frgament;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;

import com.agrictech.techrice.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;


public class Fragment_StartPlantingInput extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /** ButterKnife Code **/
    @BindView(R.id.proceed_btn)
    Button mProceedBtn;
    @BindView(R.id.progress6)
    AVLoadingIndicatorView mProgress6;
    @BindView(R.id.guideline11)
    Guideline mGuideline11;
    @BindView(R.id.guideline12)
    Guideline mGuideline12;
    @BindView(R.id.textView18)
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
    @BindView(R.id.guideline13)
    Guideline mGuideline13;
    @BindView(R.id.guideline14)
    Guideline mGuideline14;
    @BindView(R.id.textView22)
    TextView mTextView22;
    @BindView(R.id.textView23)
    TextView mTextView23;
    @BindView(R.id.fadama_radio)
    RadioButton mFadamaRadio;
    @BindView(R.id.lowland_card)
    CardView mLowlandCard;
    @BindView(R.id.guideline113)
    Guideline mGuideline113;
    @BindView(R.id.guideline114)
    Guideline mGuideline114;
    @BindView(R.id.textView222)
    TextView mTextView222;
    @BindView(R.id.textView223)
    TextView mTextView223;
    @BindView(R.id.lowland_radio)
    RadioButton mLowlandRadio;
    @BindView(R.id.upland_card)
    CardView mUplandCard;
    @BindView(R.id.guideline1113)
    Guideline mGuideline1113;
    @BindView(R.id.guideline1114)
    Guideline mGuideline1114;
    @BindView(R.id.textView2222)
    TextView mTextView2222;
    @BindView(R.id.textView2223)
    TextView mTextView2223;
    @BindView(R.id.upland_radio)
    RadioButton mUplandRadio;

    /** ButterKnife Code **/

    public Fragment_StartPlantingInput() {
        // Required empty public constructor
    }


    public static Fragment_StartPlantingInput newInstance(String param1, String param2) {
        Fragment_StartPlantingInput fragment = new Fragment_StartPlantingInput();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_planting_input, container, false);


        return view;
    }

/*

    private void insertAdminElement() {
//        referencing drawable for the logo
        int[] icons = new int[]{


                R.drawable.ic_fashion,
                R.drawable.ic_filter_b_and_w_black_24dp,
                R.drawable.ic_check_food,
                R.drawable.ic_check_wash,
                R.drawable.ic_diet

        };



        Card m = new Card("Check Wash Order", icons[3]);
        cardList.add(m);

        m = new Card("Check Food Order", icons[2]);
        cardList.add(m);


        m = new Card("Manage Clothing", icons[0]);
        cardList.add(m);

        m = new Card("Manage Foods", icons[4]);
        cardList.add(m);


        m = new Card("Manage Banner", icons[1]);
        cardList.add(m);



        cardAdapter = new CardAdapter(getContext(), cardList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardAdapter);


    }*/





    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }






    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }

}
