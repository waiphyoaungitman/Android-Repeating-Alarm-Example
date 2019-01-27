package com.example.waiphyo.alarmexample;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;



public class Main extends AppCompatActivity{


    private TimePicker pickerTime;
    private Button buttonSetAlarm;
    private TextView info;
    private NestedScrollView nestedScrollView;
    private Button edit;
    final static int RQS_1 = 1;
    public static final String key_hour = "myhour";
    public static final String key_minute = "myminute";
    private  String am_pm;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.edit_time);
        info = findViewById(R.id.info);
        pickerTime = findViewById(R.id.pickertime);
        nestedScrollView = findViewById(R.id.nestscroll);
        if(getHour(this,key_hour) == "")
        {
            setSharedPrefString(this,key_hour,"6",key_minute,"0");
        }

        pickerTime.setCurrentHour(Integer.parseInt(getHour(this,key_hour)));
        pickerTime.setCurrentMinute(Integer.parseInt(getMinute(this,key_minute)));
        am_pm = (Integer.parseInt(getHour(this,key_hour)) < 12) ? "AM" : "PM";
        setAlarm(Integer.parseInt(getHour(Main.this,key_hour)),Integer.parseInt(getMinute(Main.this,key_minute)));
        info.setText("Your Alarm will start in Every Day at: " + pickerTime.getCurrentHour()+ " : " + pickerTime.getCurrentMinute() + am_pm
        + "\nYou can change time form picker");

        buttonSetAlarm = findViewById(R.id.setalarm);
        buttonSetAlarm.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                setSharedPrefString(Main.this,key_hour,pickerTime.getCurrentHour().toString(),
                        key_minute,pickerTime.getCurrentMinute().toString());
                setAlarm(Integer.parseInt(getHour(Main.this,key_hour)),Integer.parseInt(getMinute(Main.this,key_minute)));
            }
        });

        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pickerTime.setVisibility(View.VISIBLE);
                buttonSetAlarm.setVisibility(View.VISIBLE);
            }
        });



    }

    public static void setSharedPrefString(Context context,String key_hour, String val_hour,String key_min,String val_min) {

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key_hour, val_hour);
        editor.putString(key_min,val_min);
        editor.apply();
    }

    public static String getHour(Context context, String key_hour) {

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        if (preference != null) {
            return preference.getString(key_hour, "");
        }
        return "";
    }
    public static String getMinute(Context context, String key_min) {

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        if (preference != null) {
            return preference.getString(key_min, "");
        }
        return "";
    }
    public void setAlarm(int hour,int minute){
        AlarmManager  alarmMgr = (AlarmManager)Main.this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(Main.this, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(Main.this, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);

        if(alarmMgr != null) {
            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, alarmIntent);


            setSharedPrefString(Main.this,key_hour,pickerTime.getCurrentHour().toString(),
                    key_minute,pickerTime.getCurrentMinute().toString());
            info.setText("Your Alarm will start in Every Day at : \n" + hour+ " : " + minute
                    + " "+ am_pm);
        }
    }



}
