package com.capstone.mike.a3_in_1flightmanager.common;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.flightPlanner.FlightPlan;
import com.capstone.mike.a3_in_1flightmanager.flightPlanner.FlightPlanStep;
import com.capstone.mike.a3_in_1flightmanager.flightPlanner.FlightPlannerMainActivity;
import com.capstone.mike.a3_in_1flightmanager.logbook.LogbookMainActivity;
import com.capstone.mike.a3_in_1flightmanager.preflightChecklist.ChecklistMainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class CoreActivity extends AppCompatActivity {

    public NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);

        DBHandler db = DBHandler.getInstance(this);

        JSONObject json = db.getJSONfromReferenceName("Test Flight Plan");

        if(json == null)
        {
            FlightPlan testPlan = new FlightPlan();
            testPlan.add(new FlightPlanStep("TOC", 213, 8, 2500, 90, 280, 13, 6, 0, null, null));
            testPlan.add(new FlightPlanStep("VAY", 213, 12, 2500, 110, 280, 13, 6, 0, null, null));
            testPlan.add(new FlightPlanStep("VCN", 195, 25, 2500, 110, 280, 13, 6, 0, 115.2f, "205"));
            testPlan.add(new FlightPlanStep("ATR", 195, 44, 2500, 110, 280, 13, 6, 0, 112.6f, "205"));
            testPlan.add(new FlightPlanStep("TOD", 172, 3, 2500, 110, 280, 13, 6, 0, 112.6f, "180"));
            testPlan.add(new FlightPlanStep("OBX", 172, 27, 1011, 110, 280, 13, 6, 0, null, null));

            JSONObject testPlaneInfo = new JSONObject();
            JSONObject testAirportInfo = new JSONObject();

            try
            {
                testPlaneInfo.put("model", "Paper");
                testPlaneInfo.put("gph", 7.2);

                testAirportInfo.put("departureAirport", "N87");
                testAirportInfo.put("departureATIS", "126.775");
                testAirportInfo.put("departureDep", "124.15");
                testAirportInfo.put("departureCTAF", "123.0");
                testAirportInfo.put("departureTPA", "1100");
                testAirportInfo.put("departureFieldElev", "115");

                testAirportInfo.put("destinationAirport", "OBX");
                testAirportInfo.put("destinationRunway", "2/20 14/32");
                testAirportInfo.put("destinationCTAF", "123.05");
                testAirportInfo.put("destinationTPA", "1010");
                testAirportInfo.put("destinationFieldElev", "10");

                JSONObject jsonPlan = new JSONObject(testPlan.toJSON().toString());
                jsonPlan.put("planeInfo", testPlaneInfo);
                jsonPlan.put("airportInfo", testAirportInfo);


                db.insertJSON("Test Flight Plan", JSONSchema.FLIGHT_PLAN, jsonPlan);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                System.err.println("There was an error stopping the test plan from inserting");
            }
        }
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
