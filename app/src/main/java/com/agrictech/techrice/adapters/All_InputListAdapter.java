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
import com.agrictech.techrice.model.products.ProductResponseData;


import java.util.List;

import butterknife.ButterKnife;


public class All_InputListAdapter extends RecyclerView.Adapter<All_InputListAdapter.MyViewHolder> {

private Context context;
private List<ProductResponseData> cardList;

public All_InputListAdapter(Context context, List<ProductResponseData> cardList) {
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
        ProductResponseData cardOption = cardList.get(i);


        }

private void nextActivity(String title, Class cardActivityClass) {
        Intent i = new Intent(context, cardActivityClass);
       // i.putExtra(Constant.CARD_OPTION_TITLE, title);
        context.startActivity(i);

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
