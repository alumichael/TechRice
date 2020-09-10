package com.agrictech.techrice.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;
import com.agrictech.techrice.api.ItemClickListener;
import com.agrictech.techrice.model.CategoryList;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InputCategoryListAdapter extends RecyclerView.Adapter<InputCategoryListAdapter.MyViewHolder> {

    private Context context;
    private List<CategoryList> cardList;
    InputListAdapter inputListAdapter;

    public InputCategoryListAdapter(Context context, List<CategoryList> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_category_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        CategoryList cardOption = cardList.get(i);
        holder.mSeedTxt.setText(cardOption.getTitle());
        if(cardOption.getData().size()==0){
            holder.mSeedTxt.setVisibility(View.GONE);
            holder.mSeeAllSeeds.setVisibility(View.GONE);
            holder.mRecyclerInputs.setVisibility(View.GONE);
        }

        holder.mSeeAllSeeds.setVisibility(View.GONE);
        if(cardOption.getData().size()==0){
            holder.mNothingTxt.setVisibility(View.VISIBLE);
            holder.mRecyclerInputs.setVisibility(View.GONE);
        }else {
            inputListAdapter = new InputListAdapter(context, cardOption.getData());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,
                    RecyclerView.HORIZONTAL, false);
            holder.mRecyclerInputs.setLayoutManager(linearLayoutManager);
            holder.mRecyclerInputs.setItemAnimator(new DefaultItemAnimator());
            holder.mRecyclerInputs.setAdapter(inputListAdapter);
        }


    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * ButterKnife Code
         **/
        @BindView(R.id.seed_txt)
        TextView mSeedTxt;
        @BindView(R.id.see_all_seeds)
        TextView mSeeAllSeeds;
        @BindView(R.id.recycler_inputs)
        RecyclerView mRecyclerInputs;
        @BindView(R.id.nothing_txt)
        TextView mNothingTxt;
        /**
         * ButterKnife Code
         **/

        ItemClickListener itemClickListener;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

          //  itemView.setOnClickListener(this);
        }



    }
}
