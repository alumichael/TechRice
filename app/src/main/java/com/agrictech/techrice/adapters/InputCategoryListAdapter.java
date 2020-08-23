package com.agrictech.techrice.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;
import com.agrictech.techrice.api.ItemClickListener;
import com.agrictech.techrice.model.CategoryList;


import java.util.List;

import butterknife.ButterKnife;


public class InputCategoryListAdapter extends RecyclerView.Adapter<InputCategoryListAdapter.MyViewHolder> {

private Context context;
private List<CategoryList> cardList;

public InputCategoryListAdapter(Context context, List<CategoryList> cardList) {
        this.context = context;
        this.cardList = cardList;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cropping_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        CategoryList cardOption = cardList.get(i);


        }



@Override
public int getItemCount() {
        return cardList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    ItemClickListener itemClickListener;

    MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
}
