package com.agrictech.techrice.adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.agrictech.techrice.Constant;
import com.agrictech.techrice.R;
import com.agrictech.techrice.activities.DetailCalendarActivity;
import com.agrictech.techrice.alarm.NotificationPublisher;
import com.agrictech.techrice.api.ItemClickListener;
import com.agrictech.techrice.model.season_guild.ProcessResponseData;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CropCalendarListAdapter extends RecyclerView.Adapter<CropCalendarListAdapter.MyViewHolder> {

    private Context context;
    private List<ProcessResponseData> cardList;
    String mPlantDate;
    String month_Strg = "-";
    int day = 0;
    int month_Int=0;
    int year;
    Uri uri;
    Calendar calendar;
    Calendar c;
    

    // Animation variable
    Animation item_animation;

    public CropCalendarListAdapter(Context context, List<ProcessResponseData> cardList, String plant_date) {
        this.context = context;
        this.cardList = cardList;
        this.mPlantDate = plant_date;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cropping_list, parent, false);
        ButterKnife.bind(this, view);

        return new MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        // load the animation
        item_animation = AnimationUtils.loadAnimation(context,
                R.anim.item_scale_anim);
        //start animation
        holder.mCardView.startAnimation(item_animation);

        ProcessResponseData cardOption = cardList.get(i);
        //schedule period
        LocalDate dateTag = LocalDate.parse(mPlantDate);
        LocalDate schedule_date = LocalDate.parse(String.valueOf(toDate(dateTag, Long.parseLong(cardOption.getPeriod()))));
        month_Int = schedule_date.getMonthValue();
        day = schedule_date.getDayOfMonth();
        year=schedule_date.getYear();
        Log.i("dateData",day+"-"+month_Int+"-"+year);



        if (month_Int == 1) {
            month_Strg = "Jan";

        } else if (month_Int == 2) {
            month_Strg = "Feb";
        } else if (month_Int == 3) {
            month_Strg = "Mar";
        } else if (month_Int == 4) {
            month_Strg = "Apr";
        } else if (month_Int == 5) {
            month_Strg = "May";
        } else if (month_Int == 6) {
            month_Strg = "Jun";
        } else if (month_Int == 7) {
            month_Strg = "Jul";
        } else if (month_Int == 8) {
            month_Strg = "Aug";
        } else if (month_Int == 9) {
            month_Strg = "Sept";
        } else if (month_Int == 10) {
            month_Strg = "Oct";
        } else if (month_Int == 11) {
            month_Strg = "Nov";
        } else if (month_Int == 12) {
            month_Strg = "Dec";
        }


        holder.mMonths.setText(month_Strg);
        holder.mDayTxt.setText(String.valueOf(day));
        holder.mCalendarTitle.setText(cardOption.getProcess());

        if(cardOption.getProcessId()==1){
            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.YEAR, year);
            c1.set(Calendar.MONTH, --month_Int);
            c1.set(Calendar.DAY_OF_MONTH, day);
            c1.set(Calendar.HOUR_OF_DAY, 0);
            c1.set(Calendar.MINUTE, 0);
            c1.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent1 = null;
            schedule_deletion(1,pendingIntent1);
            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c1,1,pendingIntent1);

        }else if(cardOption.getProcessId()==2){
            Calendar c2 = Calendar.getInstance();
            c2.set(Calendar.YEAR, year);
            c2.set(Calendar.MONTH, --month_Int);
            c2.set(Calendar.DAY_OF_MONTH, day);
            c2.set(Calendar.HOUR_OF_DAY, 0);
            c2.set(Calendar.MINUTE, 0);
            c2.set(Calendar.SECOND, 0);
            PendingIntent pendingIntent2 = null;

            schedule_deletion(2,pendingIntent2);
            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c2,2,pendingIntent2);
        }else if(cardOption.getProcessId()==3){
            Calendar c3 = Calendar.getInstance();
            c3.set(Calendar.YEAR, year);
            c3.set(Calendar.MONTH, --month_Int);
            c3.set(Calendar.DAY_OF_MONTH, day);
            c3.set(Calendar.HOUR_OF_DAY, 0);
            c3.set(Calendar.MINUTE, 0);
            c3.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent3 = null;
            schedule_deletion(3,pendingIntent3);

            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c3,3,pendingIntent3);

        }else if(cardOption.getProcessId()==4){
            Calendar c4 = Calendar.getInstance();
            c4.set(Calendar.YEAR, year);
            c4.set(Calendar.MONTH, --month_Int);
            c4.set(Calendar.DAY_OF_MONTH, day);
            c4.set(Calendar.HOUR_OF_DAY, 0);
            c4.set(Calendar.MINUTE, 0);
            c4.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent4 = null;
            schedule_deletion(4,pendingIntent4);

            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c4,4,pendingIntent4);
        }else if(cardOption.getProcessId()==5){
            Calendar c5 = Calendar.getInstance();
            c5.set(Calendar.YEAR, year);
            c5.set(Calendar.MONTH, --month_Int);
            c5.set(Calendar.DAY_OF_MONTH, day);
            c5.set(Calendar.HOUR_OF_DAY, 0);
            c5.set(Calendar.MINUTE, 0);
            c5.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent5 = null;
            schedule_deletion(5,pendingIntent5);
            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c5,5,pendingIntent5);
        }else if(cardOption.getProcessId()==6){
            Calendar c6 = Calendar.getInstance();
            c6.set(Calendar.YEAR, year);
            c6.set(Calendar.MONTH, --month_Int);
            c6.set(Calendar.DAY_OF_MONTH, day);
            c6.set(Calendar.HOUR_OF_DAY, 0);
            c6.set(Calendar.MINUTE, 0);
            c6.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent6 = null;
            schedule_deletion(6,pendingIntent6);
            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c6,6,pendingIntent6);
        }else if(cardOption.getProcessId()==7){
            Calendar c7 = Calendar.getInstance();
            c7.set(Calendar.YEAR, year);
            c7.set(Calendar.MONTH, --month_Int);
            c7.set(Calendar.DAY_OF_MONTH, day);
            c7.set(Calendar.HOUR_OF_DAY, 0);
            c7.set(Calendar.MINUTE, 0);
            c7.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent7 = null;
            schedule_deletion(7,pendingIntent7);

            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c7,7,pendingIntent7);
        }else if(cardOption.getProcessId()==8){
            Calendar c8 = Calendar.getInstance();
            c8.set(Calendar.YEAR, year);
            c8.set(Calendar.MONTH, --month_Int);
            c8.set(Calendar.DAY_OF_MONTH, day);
            c8.set(Calendar.HOUR_OF_DAY, 0);
            c8.set(Calendar.MINUTE, 0);
            c8.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent8 = null;
            schedule_deletion(8,pendingIntent8);

            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c8,8,pendingIntent8);

        }else if(cardOption.getProcessId()==9){
            Calendar c9 = Calendar.getInstance();
            c9.set(Calendar.YEAR, year);
            c9.set(Calendar.MONTH, --month_Int);
            c9.set(Calendar.DAY_OF_MONTH, day);
            c9.set(Calendar.HOUR_OF_DAY, 0);
            c9.set(Calendar.MINUTE, 0);
            c9.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent9 = null;
            schedule_deletion(9,pendingIntent9);
            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c9,9,pendingIntent9);

        }else if(cardOption.getProcessId()==10){
            Calendar c10 = Calendar.getInstance();
            c10.set(Calendar.YEAR, year);
            c10.set(Calendar.MONTH, --month_Int);
            c10.set(Calendar.DAY_OF_MONTH, day);
            c10.set(Calendar.HOUR_OF_DAY, 0);
            c10.set(Calendar.MINUTE, 0);
            c10.set(Calendar.SECOND, 0);

            PendingIntent pendingIntent10 = null;
            schedule_deletion(10,pendingIntent10);

            schedule_notify("Please your calendar for more info on "+
                    cardOption.getProcess(),c10,10, pendingIntent10);
        }
     
       


        if (cardOption.getProcessId() == 1) {
            holder.mCaledarImg.setImageResource(R.drawable.landclearing);
        } else if (cardOption.getProcessId() == 2) {
            holder.mCaledarImg.setImageResource(R.drawable.seed_manage);
        } else if (cardOption.getProcessId() == 3) {
            holder.mCaledarImg.setImageResource(R.drawable.planting);
        } else if (cardOption.getProcessId() == 4) {
            holder.mCaledarImg.setImageResource(R.drawable.fertilizer_apply);
        } else if (cardOption.getProcessId() == 5) {
            holder.mCaledarImg.setImageResource(R.drawable.water_manage);
        } else if (cardOption.getProcessId() == 6) {
            holder.mCaledarImg.setImageResource(R.drawable.weeding);
        } else if (cardOption.getProcessId() == 7) {
            holder.mCaledarImg.setImageResource(R.drawable.weed1_pick);
        } else if (cardOption.getProcessId() == 8) {
            holder.mCaledarImg.setImageResource(R.drawable.harvest);
        } else if (cardOption.getProcessId() == 9) {
            holder.mCaledarImg.setImageResource(R.drawable.treshing);
        } else if (cardOption.getProcessId() == 10) {
            holder.mCaledarImg.setImageResource(R.drawable.bagging_rice);
        }


        holder.setItemClickListener(new ItemClickListener() {
                                        @Override
                                        public void onItemClick(int pos) {
                                            CropCalendarListAdapter.this.
                                                    nextActivity(cardList.get(pos).getProcess(), String.valueOf(cardList.get(pos).getProcessId()),
                                                            cardList.get(pos).getInstruction(), DetailCalendarActivity.class);
                                        }
                                    }

        );


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static LocalDate toDate(LocalDate date, long days) {
        //adding days to date
        return ChronoUnit.DAYS.addTo(date, days);
    }


    private void schedule_notify(String message,Calendar c,int requestCode,
                                 PendingIntent pendingIntent){
        
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationPublisher.class);
        intent.putExtra(Constant.NOTIFY_MESSAGE,message);
        pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        //check compatibility
        if (Build.VERSION.SDK_INT >= 23) {

            assert alarmManager != null;
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        } else if (Build.VERSION.SDK_INT >= 19) {

            assert alarmManager != null;
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        } else {

            assert alarmManager != null;
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        }


    }

    private void schedule_deletion(int requestCode, PendingIntent pendingIntent){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationPublisher.class);
        pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        assert alarmManager != null;
        alarmManager.cancel(pendingIntent);
    }


    private void nextActivity(String process_title, String process_id, String instruction, Class cardActivityClass) {
        Intent i = new Intent(context, cardActivityClass);
        i.putExtra(Constant.PROCESS_TITLE, process_title);
        i.putExtra(Constant.PROCESS_ID, process_id);
        i.putExtra(Constant.INSTRUCTION, instruction);
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
        @BindView(R.id.carlendar_layout)
        ConstraintLayout mCarlendarLayout;
        @BindView(R.id.card_view)
        CardView mCardView;
        @BindView(R.id.caledar_img)
        ImageView mCaledarImg;
        @BindView(R.id.months)
        TextView mMonths;
        @BindView(R.id.day_txt)
        TextView mDayTxt;

        @BindView(R.id.calendar_title)
        TextView mCalendarTitle;

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
