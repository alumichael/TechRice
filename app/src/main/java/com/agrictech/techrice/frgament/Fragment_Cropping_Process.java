package com.agrictech.techrice.frgament;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;


public class Fragment_Cropping_Process extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /** ButterKnife Code **/
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.guideline15)
    Guideline mGuideline15;
    @BindView(R.id.guideline16)
    Guideline mGuideline16;
    @BindView(R.id.sub_title)
    TextView mSubTitle;
    @BindView(R.id.recycler_calendar)
    RecyclerView mRecyclerCalendar;
    @BindView(R.id.floatingActionButton_speakConsut)
    FloatingActionButton mFloatingActionButtonSpeakConsut;
    /** ButterKnife Code **/


    public Fragment_Cropping_Process() {
        // Required empty public constructor
    }


    public static Fragment_Cropping_Process newInstance(String param1, String param2) {
        Fragment_Cropping_Process fragment = new Fragment_Cropping_Process();
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
        View view = inflater.inflate(R.layout.fragment_crooping_calendar, container, false);


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
