package com.agrictech.techrice.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.agrictech.techrice.Constant;
import com.agrictech.techrice.R;
import com.agrictech.techrice.activities.PaymentActivity;
import com.agrictech.techrice.api.ItemClickListener;
import com.agrictech.techrice.frgament.DashBoardFragment;
import com.agrictech.techrice.model.products.ProductResponseData;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InputListAdapter extends RecyclerView.Adapter<InputListAdapter.MyViewHolder> {

    private Context context;
    private List<ProductResponseData> cardList;

    public InputListAdapter(Context context, List<ProductResponseData> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_products_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        ProductResponseData cardOption = cardList.get(i);
        String price=cardOption.getCost()+"/"+cardOption.getUnit();

        holder.mCropName.setText(cardOption.getName());
        holder.mUnitTxt.setText(price);

        holder.setItemClickListener(pos -> {
                    setQuantity(cardList.get(pos).getName(),cardList.get(pos).getUnit(),
                            String.valueOf(cardList.get(pos).getCost()),
                            String.valueOf(cardList.get(pos).getDelivery()));


                }

        );


    }


    //On clicking repeat interval Button
    public void setQuantity(String name,String unit,String cost,String delivery) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Enter Number of "+ unit);

        //Create EditText box to input repeat number
        final EditText input = new EditText(context);
        input.setInputType( InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        alert.setPositiveButton("Proceed",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (input.getText().toString().trim().isEmpty()) {
                            dialog.dismiss();
                            ErrorAlert("Invalid Input");
                        } else {
                            int estimate_cost=Integer.parseInt(cost) * Integer.parseInt(input.getText().toString());
                            String estimate_convert=String.valueOf(estimate_cost);
                            nextActivity(name,estimate_convert,delivery, PaymentActivity.class);
                        }
                    }
                });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    }
                });


        alert.show();
    }

    private void nextActivity(String name,String cost,String delivery, Class cardActivityClass) {
        Intent i = new Intent(context, cardActivityClass);
         i.putExtra(Constant.PRODUCT_NAME, name);
        i.putExtra(Constant.PRODUCT_COST, cost);
        i.putExtra(Constant.PRODUCT_DELIVERY_COST, delivery);
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
       /* @BindView(R.id.card_img)
        ImageView mCardImg;*/
        @BindView(R.id.crop_name)
        TextView mCropName;
        @BindView(R.id.unit_txt)
        TextView mUnitTxt;

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

    private void ErrorAlert(String message) {

        new AlertDialog.Builder(context)
                .setTitle("Error ")
                .setIcon(R.drawable.ic_baseline_error_outline_24)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();

                    }
                })


                .show();

    }

}
