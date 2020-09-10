package com.agrictech.techrice.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.agrictech.techrice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultantActivity extends AppCompatActivity {

    /** ButterKnife Code **/
    @BindView(R.id.toolbar2)
    Toolbar mToolbar2;
    @BindView(R.id.head_img)
    ImageView mConsultantAvatar;
    @BindView(R.id.months_txt)
    TextView mTextView31;
    @BindView(R.id.booking_btn)
    Button mBookingBtn;
    /** ButterKnife Code **/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_to_consultant);
        ButterKnife.bind(this);

        setSupportActionBar( mToolbar2 );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back);
        getSupportActionBar().setTitle(null);


        mBookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWhatsapp("09030571904");
            }
        });
    }

    public void goWhatsapp(String number) {
        try {
            Uri uri = Uri.parse("smsto:" + number);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(i, ""));
        }catch (Exception e){
            Toast.makeText(this,"Error/n"+ e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
