package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

import java.util.ArrayList;

public class FlightPlannerEditActivity extends AppCompatActivity {
    ListView stepsList;
    ArrayList<FlightPlanStep> steps = new ArrayList<>();

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
            steps.add(new FlightPlanStep("TOC 1", 265, 10, 3000, 90, 36, 15, 13, 0));
            steps.add(new FlightPlanStep("Turn 1", 275, 40, 3000, 110, 36, 15, 13, 0));
            steps.add(new FlightPlanStep("TOC 2", 272, 15, 6500, 90, 1, 10, 13, 0));
            steps.add(new FlightPlanStep("TOD", 272, 182, 6500, 110, 1, 10, 14, 0));
            steps.add(new FlightPlanStep("AGC", 276, 12, 2200, 110, 1, 10, 14, 0));
        }
        else if(data.hasExtra("FILE"))
        {
            String file = data.getStringExtra("FILE");

            // TODO Get the steps from the returned JSON
        }

        TextView startingAirportTV = (TextView)findViewById(R.id.startingAirportTV);
        startingAirportTV.setText(startingAirport);
        refreshAdapter();
    }

    public void addRow(View view)
    {
        // TODO Start the new Row activity
    }

    private void refreshAdapter()
    {
        stepsList.setAdapter(new FlightPlannerEditArrayAdapter(this, steps.toArray(new FlightPlanStep[] {})));
    }
}
