package com.agrictech.techrice.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.R;
import com.agrictech.techrice.api.ItemClickListener;
import com.agrictech.techrice.model.Requirements;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RequiremntListAdapter extends RecyclerView.Adapter<RequiremntListAdapter.MyViewHolder> {

    private Context context;
    private List<Requirements> cardList;

    // Animation variable
    Animation item_animation;

    public RequiremntListAdapter(Context context, List<Requirements> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.require_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {


        // load the animation
        item_animation = AnimationUtils.loadAnimation(context,
                R.anim.item_scale_anim);
        //start animation
        holder.mCardView.startAnimation(item_animation);

        Requirements cardOption = cardList.get(i);
        holder.mProductTitle.setText(cardOption.getTitle());
        holder.mStatusIcon.setImageResource(cardOption.getIcon());
        holder.mAmountTxt.setText(cardOption.getDetail());

    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * ButterKnife Code
         **/
        @BindView(R.id.card_layout)
        ConstraintLayout mCardLayout;
        @BindView(R.id.card_view)
        CardView mCardView;
        @BindView(R.id.status_icon)
        ImageView mStatusIcon;
        @BindView(R.id.product_title)
        TextView mProductTitle;
        @BindView(R.id.amount_txt)
        TextView mAmountTxt;
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
