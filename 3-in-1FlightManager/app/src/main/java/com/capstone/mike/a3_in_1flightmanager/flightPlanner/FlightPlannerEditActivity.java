package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightPlannerEditActivity extends AppCompatActivity {
    ListView stepsList;
    ArrayList<FlightPlanStep> steps = new ArrayList<>();

    FlightPlan flightPlan;

    JSONObject json = new JSONObject();

    private static final int REQUEST_CODE_NEW_ROW = 0;
    private static final int REQUEST_CODE_PLANE_INFO = 1;
    private static final int REQUEST_CODE_AIRPORT_INFO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_edit);

        Intent data = getIntent();

        stepsList = (ListView)findViewById(R.id.flightPlanSteps);
        boolean testing = data.getBooleanExtra("TEST", false);

        String startingAirport = "";

        if(testing)
        {
            startingAirport = "N87";
            flightPlan = new FlightPlan("N87", 500);
            flightPlan.setFuelRate(11.5f);
            flightPlan.add(new FlightPlanStep("TOC 1", 265, 10, 3000, 90, 36, 15, 13, 0));
            flightPlan.add(new FlightPlanStep("Turn 1", 275, 40, 3000, 110, 36, 15, 13, 0));
            flightPlan.add(new FlightPlanStep("TOC 2", 272, 15, 6500, 90, 1, 10, 13, 0));
            flightPlan.add(new FlightPlanStep("TOD", 272, 182, 6500, 110, 1, 10, 14, 0));
            flightPlan.add(new FlightPlanStep("AGC", 276, 12, 2200, 110, 1, 10, 14, 0));
        }
        else if(data.hasExtra("FILE"))
        {
            String file = data.getStringExtra("FILE");

            // TODO Get the steps from the returned JSON
            DBHandler db = DBHandler.getInstance(this);
            JSONObject json = db.getJSONfromReferenceName(file);
            try
            {
                JSONArray steps = json.getJSONArray("steps");

                for(int x = 0; x < steps.length(); x++)
                {
                    JSONObject jsonStep = steps.getJSONObject(x);

                    String checkpointName = jsonStep.getString("checkpointName");
                    int course = jsonStep.getInt("course");
                    int legDistance = jsonStep.getInt("legDistance");
                    int altitude = jsonStep.getInt("altitude");
                    int tas = jsonStep.getInt("tas");
                    int windDir = jsonStep.getInt("windDir");
                    int windSpeed = jsonStep.getInt("windSpeed");
                    int headAdjust = jsonStep.getInt("headAdjust");
                    int magHeadAdjust = jsonStep.getInt("magHeadAdjust");

                    flightPlan.add(new FlightPlanStep(checkpointName, course, legDistance, altitude, tas, windDir, windSpeed, headAdjust, magHeadAdjust));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(this, "There was an error loading the Flight Plan", Toast.LENGTH_LONG).show();
            }
        }

        refreshAdapter();
    }

    private void refreshAdapter()
    {
        stepsList.setAdapter(new FlightPlannerEditArrayAdapter(this, flightPlan.toArray()));
    }

    public void addRow(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditNewRowActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NEW_ROW);
    }

    public void editPlaneInfo(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditPlaneInfoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_PLANE_INFO);
    }
    public void editAirportInfo(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditAirportInfoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_AIRPORT_INFO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE_NEW_ROW)
        {
            if(resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject newCheckpoint = new JSONObject(data.getStringExtra("CHECKPOINT_INFO"));

                    // TODO Translate the JSON to a new FlightPlanStep and enter it into the flightPlan

                    refreshAdapter();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == REQUEST_CODE_PLANE_INFO)
        {
            if(resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject planeInfo = new JSONObject(data.getStringExtra("PLANE_INFO"));
                    json.put("planeInfo", planeInfo);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == REQUEST_CODE_AIRPORT_INFO)
        {
            if(resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject airportInfo = new JSONObject(data.getStringExtra("AIRPORT_INFO"));
                    json.put("airportInfo", airportInfo);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
