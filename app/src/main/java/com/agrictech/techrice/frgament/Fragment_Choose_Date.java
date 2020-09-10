package com.agrictech.techrice.frgament;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;

import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.activities.CropCalendarActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_Choose_Date extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LOCATION = "location";
    private static final String PERIOD = "period";

    // TODO: Rename and change types of parameters
    private String mLocation;
    private String mPeriod;

    /** ButterKnife Code **/
    @BindView(R.id.title2)
    TextView mTitle2;
    @BindView(R.id.picker_caledar_avatar)
    ImageView mPickerCaledarAvatar;
    @BindView(R.id.months_txt)
    TextView mMonthsTxt;
    @BindView(R.id.choose_date_btn)
    Button mChooseDateBtn;
    /** ButterKnife Code **/
    UserPreferences userPreferences;
    String info;
    DatePickerDialog datePickerDialog;
    private int year, month, dayOfMonth;

    public Fragment_Choose_Date() {
        // Required empty public constructor
    }


    public static Fragment_Choose_Date newInstance(String param1, String param2) {
        Fragment_Choose_Date fragment = new Fragment_Choose_Date();
        Bundle args = new Bundle();
        args.putString(LOCATION, param1);
        args.putString(PERIOD, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLocation = getArguments().getString(LOCATION);
            mPeriod = getArguments().getString(PERIOD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_date, container, false);
        ButterKnife.bind(this,view);
        userPreferences = new UserPreferences(getContext());
        info= "In "+mLocation+", it is best to plant rice between "+mPeriod;
        mMonthsTxt.setText(info);
        setAction();
        return view;
    }

    private void setAction(){
        mChooseDateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_date_btn:

                Calendar now = Calendar.getInstance();
                int mYear = now.get(Calendar.YEAR);
                int mMonth = now.get(Calendar.MONTH);
                int mDay = now.get(Calendar.DAY_OF_MONTH);



                datePickerDialog = new DatePickerDialog( getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        String dateString = mYear+"-"+ (mMonth + 1 ) +"-"+mDay;
                        if(mMonth + 1 <10 ||  mDay<10){
                            dateString = mYear+"-"+ "0"+(mMonth + 1 ) +"-"+"0"+mDay;
                        }
                        mMonth ++;
                        dayOfMonth = mDay;
                        month = mMonth;
                        year = mYear;
                       // mDateText.setText( dateString );
                        ConfirmAlert(dateString);

                    }
                }, mYear, mMonth, mDay );

                datePickerDialog.show();

                break;

        }
    }

    private void ConfirmAlert(final String message_text) {

        new AlertDialog.Builder(getContext())
                .setTitle("Confirmation")
                .setIcon(R.drawable.ic_baseline_calendar_today_24)
                .setMessage("Date Picked: "+message_text)
                .setCancelable(false)
                .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        //take to next page and schedule the process here
                        //getSeedAllOthers(Integer.parseInt(mFarmer_Id));
                        userPreferences.setPlantDate(message_text);
                        Intent intent=new Intent(getActivity(), CropCalendarActivity.class);
                        startActivity(intent);


                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();


                    }
                })
                .show();

    }

}
