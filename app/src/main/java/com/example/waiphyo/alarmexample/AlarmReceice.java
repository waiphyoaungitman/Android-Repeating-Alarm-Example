package com.example.waiphyo.alarmexample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlarmReceice extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yourlayout);
        Button stop = findViewById(R.id.stop);
        Button edit_time = findViewById(R.id.edit_time);
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.aa);
        mediaPlayer.start();
        TextView textView = findViewById(R.id.receive_text);
        String hour = Main.getHour(this, Main.key_hour);
        final String am_pm = (Integer.parseInt(hour) < 12) ? "AM" : "PM";
        String minute = Main.getMinute(this, Main.key_minute);
        textView.setText("Your Alarm receive at " + hour + " " + minute + ":" + am_pm +"\n tomorrow again");
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.stop();
                }
            }
        });
        edit_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    mediaPlayer.stop();

                }
                Intent intent = new Intent(AlarmReceice.this,Main.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }
}
