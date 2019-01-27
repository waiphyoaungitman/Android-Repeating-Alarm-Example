package com.example.waiphyo.alarmexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override

	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
		Intent intent1 = new Intent(context,AlarmReceice.class);
		context.startActivity(intent1);
		abortBroadcast();



	}

}
