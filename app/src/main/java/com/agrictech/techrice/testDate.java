package com.agrictech.techrice;

import android.util.Log;
import android.view.Display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;



import static android.content.ContentValues.TAG;

public class testDate {
    //public final long MAGIC=86400000L;
    public static void main(String[] args) {
        Date d = new Date();



        //  convert a date to an integer and back again
        long currentTime=d.getTime();
        currentTime=currentTime/86400000L;


        System.out.println(currentTime);
    }





}
