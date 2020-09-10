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

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.R;
import com.agrictech.techrice.activities.ConfirmDeliveryActivity;
import com.agrictech.techrice.api.ItemClickListener;
import com.agrictech.techrice.model.transaction.HistoryData;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.MyViewHolder> {

    private Context context;
    private List<HistoryData> cardList;
    // Animation variable
    Animation item_animation;

    public TransactionListAdapter(Context context, List<HistoryData> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list, parent, false);
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

        HistoryData cardOption = cardList.get(i);
        holder.mProductTitle.setText(cardOption.getProducts());
        String amt="NGN"+cardOption.getAmount();
        holder.mAmountTxt.setText(amt);
        String statusFalse=cardOption.getOrderId()+" --Not yet delivered";
        String statusTrue=cardOption.getOrderId()+" --Delivered";


        if(cardOption.getOrderStatus()==0){
            holder.mOrderIdTxt.setText(statusFalse);
            holder.mStatusIcon.setImageResource(R.drawable.ic_baseline_airport_shuttle_24);
        }else if(cardOption.getOrderStatus()==1){
            holder.mOrderIdTxt.setText(statusTrue);
            holder.mStatusIcon.setImageResource(R.drawable.ic_baseline_checked);
        }

        holder.setItemClickListener(pos -> {
            nextActivity(String.valueOf(cardList.get(pos).getOrderId()),cardList.get(pos).getProducts(),
                            String.valueOf(cardList.get(pos).getAmount()) , ConfirmDeliveryActivity.class
                            );

                }

        );


    }

    private void nextActivity(String order_id,String name,String amount, Class cardActivityClass) {
        Intent i = new Intent(context, cardActivityClass);
        i.putExtra(Constant.ORDER_ID, order_id);
        i.putExtra(Constant.PRODUCT_NAME, name);
        i.putExtra(Constant.PRODUCT_COST, amount);
        context.startActivity(i);

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * ButterKnife Code
         **/
        @BindView(R.id.card_layout)
        ConstraintLayout mCardLayout;
        @BindView(R.id.status_icon)
        ImageView mStatusIcon;
        @BindView(R.id.card_view)
        CardView mCardView;
        @BindView(R.id.product_title)
        TextView mProductTitle;
        @BindView(R.id.amount_txt)
        TextView mAmountTxt;
        @BindView(R.id.order_id_txt)
        TextView mOrderIdTxt;
        /**
         * ButterKnife Code
         **/

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
