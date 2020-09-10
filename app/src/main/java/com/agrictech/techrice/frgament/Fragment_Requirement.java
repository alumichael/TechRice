package com.agrictech.techrice.frgament;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.adapters.RequiremntListAdapter;
import com.agrictech.techrice.api.ApiInterface;
import com.agrictech.techrice.api.ServiceGenerator;
import com.agrictech.techrice.model.Requirements;
import com.agrictech.techrice.model.fertilizer.FertResponseData;
import com.agrictech.techrice.model.fertilizer.FertResponseHead;
import com.agrictech.techrice.model.herbicide.HerbResponseData;
import com.agrictech.techrice.model.herbicide.HerbResponseHead;
import com.agrictech.techrice.model.OnlyFarmerIDPost;
import com.agrictech.techrice.model.schedule_period.ScheduleResponseHead;
import com.agrictech.techrice.model.seed_require.SeedResponseHead;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.agrictech.techrice.Util.ShowMessage;


public class Fragment_Requirement extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FARMER_ID = "farmer_id";

    // TODO: Rename and change types of parameters
    private String mFarmer_Id;


    /**
     * ButterKnife Code
     **/
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.framLayout)
    FrameLayout mFramLayout;
    @BindView(R.id.recycler_requrement)
    RecyclerView mRecyclerRequrement;
    @BindView(R.id.start_planting_btn)
    Button mStartPlantingBtn;
    @BindView(R.id.progress5)
    AVLoadingIndicatorView mProgress5;
    /**
     * ButterKnife Code
     **/

    UserPreferences userPreferences;
    String message, fertilizer_info, herbizide_info;
    int seed_count;
    Fragment fragment;
    List<FertResponseData> fertResponseDataList;
    List<HerbResponseData> herbResponseDataList;
    List<Requirements> cardList;
    RequiremntListAdapter requiremntListAdapter;

    public Fragment_Requirement() {
        // Required empty public constructor
    }


    public static Fragment_Requirement newInstance(String farmer_id) {
        Fragment_Requirement fragment = new Fragment_Requirement();
        Bundle args = new Bundle();
        args.putString(FARMER_ID, farmer_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFarmer_Id = getArguments().getString(FARMER_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requirement, container, false);
        ButterKnife.bind(this, view);
        userPreferences = new UserPreferences(getContext());
        cardList = new ArrayList<>();

        Log.i("farmer_id", mFarmer_Id);
        setAction();

        getSeedAllOthers(userPreferences.getLandNature(),userPreferences.getLandArea());

        return view;
    }

    private void setAction() {
        mStartPlantingBtn.setOnClickListener(this);
    }


    private void getSeedAllOthers(String nature,String area) {
        mFramLayout.setVisibility(View.GONE);
        mStartPlantingBtn.setVisibility(View.GONE);
        mProgress5.setVisibility(View.VISIBLE);
        //final OnlyFarmerIDPost farmerIDPost=new OnlyFarmerIDPost(farmer_id);

        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<SeedResponseHead> call = client.seed_request(nature,area);

            call.enqueue(new Callback<SeedResponseHead>() {
                @Override
                public void onResponse(Call<SeedResponseHead> call, Response<SeedResponseHead> response) {

                    try {
                        if (response.code() == 200) {

                            boolean status = response.body().getStatus();

                            if (status) {

                                seed_count = response.body().getSeedResponseData().getSeedRequired();
                                Log.i("seed_count", String.valueOf(seed_count));
                                getFertilizer(nature,area);

                            } else {
                                message = response.body().getMessage();
                                ErrorAlert(message);
                            }
                        }

                    } catch (Exception e) {
                        ErrorAlert("Failed: " + e.getMessage());

                        Log.i(" Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<SeedResponseHead> call, Throwable t) {
                    if (message != null) {
                        ErrorAlert(message);
                    } else {
                        ErrorAlert("Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.i("GenError", e.getMessage());
        }


    }


    private void getFertilizer(String nature,String area) {
        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<FertResponseHead> call = client.fertilizer_request(nature,area);

            call.enqueue(new Callback<FertResponseHead>() {
                @Override
                public void onResponse(Call<FertResponseHead> call, Response<FertResponseHead> response) {

                    try {
                        if (response.code() == 200) {

                            boolean status = response.body().getStatus();

                            if (status) {
                                fertResponseDataList = response.body().getFertResponseData();
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < fertResponseDataList.size(); i++) {


                                    stringBuilder.append("OPTION: ").append(i + 1);
                                    stringBuilder.append("\nFertilizer Required: ").append(fertResponseDataList.get(i).
                                            getFertilizerRequired()).append(" bag(s) (50kg/bag)");
                                    stringBuilder.append("\nFertilizer Type: ").append(fertResponseDataList.get(i).
                                            getFertilizerType());
                                    if (userPreferences.getLandNature().equals("upland")) {
                                        if (fertResponseDataList.get(i).
                                                getFertilizerType().equals("Urea") || fertResponseDataList.get(i).
                                                getFertilizerType().equals("USG")) {
                                            stringBuilder.append("\nApplication Period: ").append("2 weeks after planting");
                                        } else {
                                            stringBuilder.append("\nApplication Period: ").append(fertResponseDataList.get(i).
                                                    getApplicationPeriod());

                                        }

                                    } else {
                                        if (fertResponseDataList.get(i).
                                                getFertilizerType().equals("Urea") || fertResponseDataList.get(i).
                                                getFertilizerType().equals("USG")) {
                                            stringBuilder.append("\nApplication Period: ").append("7 days after transplanting");

                                        } else {
                                            stringBuilder.append("\nApplication Period: ").append(fertResponseDataList.get(i).
                                                    getApplicationPeriod());

                                        }
                                    }

                                    stringBuilder.append("\n\n");

                                }
                                fertilizer_info = stringBuilder.toString();
                                Log.i("FerInfo", fertilizer_info);
                                getHerbizide(nature,area);

                            } else {
                                message = response.body().getMessage();
                                ErrorAlert(message);
                            }
                        }

                    } catch (Exception e) {
                        ErrorAlert("Failed: " + e.getMessage());

                        Log.i(" Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<FertResponseHead> call, Throwable t) {
                    if (message != null) {
                        ErrorAlert(message);
                    } else {
                        ErrorAlert("Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.i("GenError", e.getMessage());
        }

    }


    private void getHerbizide(String nature,String area) {

        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<HerbResponseHead> call = client.herbicide_request(nature,area);

            call.enqueue(new Callback<HerbResponseHead>() {
                @Override
                public void onResponse(Call<HerbResponseHead> call, Response<HerbResponseHead> response) {

                    try {
                        if (response.code() == 200) {

                            boolean status = response.body().getStatus();

                            if (status) {
                                herbResponseDataList = response.body().getData();
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < herbResponseDataList.size(); i++) {


                                    stringBuilder.append("OPTION: ").append(i + 1);
                                    stringBuilder.append("\nHerbicide Required: ").
                                            append(herbResponseDataList.get(i).
                                                    getHerbicideRequired()).
                                            append(" litre(s)");
                                    stringBuilder.append("\nHerbicide Type: ").
                                            append(herbResponseDataList.get(i).
                                                    getHerbicideType());
                                    stringBuilder.append("\nLand Nature: ").
                                            append(herbResponseDataList.get(i).
                                                    getLand_nature());

                                    stringBuilder.append("\n\n");

                                }
                                herbizide_info = stringBuilder.toString();

                                Log.i("HerbInfo", herbizide_info);

                                mFramLayout.setVisibility(View.VISIBLE);
                                mStartPlantingBtn.setVisibility(View.VISIBLE);
                                mProgress5.setVisibility(View.GONE);

                                insertRequireElement(String.valueOf(seed_count),
                                        fertilizer_info, herbizide_info);

                            } else {
                                message = response.body().getMessage();
                                ErrorAlert(message);
                            }
                        }

                    } catch (Exception e) {
                        ErrorAlert("Failed: " + e.getMessage());

                        Log.i(" Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<HerbResponseHead> call, Throwable t) {
                    if (message != null) {
                        ErrorAlert(message);
                    } else {
                        ErrorAlert("Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.i("GenError", e.getMessage());
        }

    }


    private void insertRequireElement(String seeds, String fertilizer_info, String herbizide_info
    ) {
//        referencing drawable for the logo
        int[] icons = new int[]{
                R.drawable.ic_seeds,
                R.drawable.ic_fertilizer_3,
                R.drawable.ic_herbizide_4

        };


        Requirements m = new Requirements("Seeds Requirement", icons[0], seeds);
        cardList.add(m);

        m = new Requirements("Fertilizer Requirement", icons[1], fertilizer_info);
        cardList.add(m);


        m = new Requirements("Herbicide Requirement", icons[2], herbizide_info);
        cardList.add(m);

        Log.i("CardInfo", cardList.toString());

        requiremntListAdapter = new RequiremntListAdapter(getContext(), cardList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        mRecyclerRequrement.setLayoutManager(linearLayoutManager);
        mRecyclerRequrement.setItemAnimator(new DefaultItemAnimator());
        mRecyclerRequrement.setAdapter(requiremntListAdapter);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_planting_btn:
                // OnlyFarmerIDPost farmerIDPost=new OnlyFarmerIDPost(Integer.parseInt(mFarmer_Id));
                checkPlantPeriod(userPreferences.getLandNature(),userPreferences.getRequestState());
                break;

        }
    }


    private void checkPlantPeriod(String nature,String state) {
        mStartPlantingBtn.setVisibility(View.GONE);
        mProgress5.setVisibility(View.VISIBLE);

        try {

            //get client and call object for request
            ApiInterface client = ServiceGenerator.createService(ApiInterface.class);

            Call<ScheduleResponseHead> call = client.request_calendar(nature,state);

            call.enqueue(new Callback<ScheduleResponseHead>() {
                @Override
                public void onResponse(Call<ScheduleResponseHead> call,
                                       Response<ScheduleResponseHead> response) {

                    try {
                        if (response.code() == 200) {

                            boolean status = response.body().getStatus();

                            if (status) {

                                mStartPlantingBtn.setVisibility(View.VISIBLE);
                                mProgress5.setVisibility(View.GONE);

                                String location = response.body().getScheduleResponseData().
                                        getLocation();
                                String period = response.body().getScheduleResponseData().
                                        getPeriod();

                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.fragment_container, Fragment_Choose_Date
                                        .newInstance(location, period), Fragment_Choose_Date.class.getSimpleName());
                                ft.commit();


                            } else {

                                message = response.body().getMessage();
                                mStartPlantingBtn.setVisibility(View.VISIBLE);
                                mProgress5.setVisibility(View.GONE);
                                ShowMessage(getContext(), message);
                            }
                        }

                    } catch (Exception e) {
                        mStartPlantingBtn.setVisibility(View.VISIBLE);
                        mProgress5.setVisibility(View.GONE);
                        ShowMessage(getContext(), message);
                        Log.i(" Failed", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ScheduleResponseHead> call, Throwable t) {
                    mStartPlantingBtn.setVisibility(View.VISIBLE);
                    mProgress5.setVisibility(View.GONE);
                    if (message != null) {

                        ShowMessage(getContext(), message);
                    } else {

                        ShowMessage(getContext(), "Failed");

                    }
                    Log.i("GEtError", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.i("GenError", e.getMessage());
        }

    }


    private void ErrorAlert(String message) {

        new AlertDialog.Builder(getContext())
                .setTitle("Error ")
                .setIcon(R.drawable.ic_baseline_error_outline_24)
                .setMessage(message)
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        getSeedAllOthers(userPreferences.getLandNature(),userPreferences.getLandArea());

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        fragment = new DashBoardFragment();
                        showFragment(fragment);

                    }
                })
                .show();

    }

    //Method to set fragment immediately Onclick
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


}
