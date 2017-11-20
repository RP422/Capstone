package com.capstone.mike.a3_in_1flightmanager.common;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.flightPlanner.FlightPlannerMainActivity;
import com.capstone.mike.a3_in_1flightmanager.logbook.LogbookMainActivity;
import com.capstone.mike.a3_in_1flightmanager.preflightChecklist.ChecklistMainActivity;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class CoreActivity extends AppCompatActivity {

    public NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        DBHandler db = DBHandler.getInstance(this);
        db.getAll();

//        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        ArrayList<NotificationChannel> channels = new ArrayList<NotificationChannel>();
//
//        String channelID = "3_in_1_flightmanager_reminders";
//        CharSequence channelName = "Reminders";
//        channels.add(new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT));
//
//        channelID = "3_in_1_flightmanager_weather_alert";
//        channelName = "Weather Alerts";
//        channels.add(new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT));
//
//        notificationManager.createNotificationChannels(channels);
    }

    // I really want to replace these lame buttons with something bigger and nicer.
    // Maybe image buttons???
    public void goToPlanner(View view)
    {
        Intent intent = new Intent(this, FlightPlannerMainActivity.class);
        startActivity(intent);
    }
    public void goToChecklist(View view)
    {
        Intent intent = new Intent(this, ChecklistMainActivity.class);
        startActivity(intent);
    }
    public void goToLogbook(View view)
    {
        Intent intent = new Intent(this, LogbookMainActivity.class);
        startActivity(intent);
    }
}
