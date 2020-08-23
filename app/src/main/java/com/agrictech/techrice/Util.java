package com.agrictech.techrice;

import android.content.Context;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class Util {
    public static void ShowMessage(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }



}
