package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.PopupBuilder;

public class FlightPlannerMainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_main);
    }

    public void newPlan(View view)
    {
//        Intent intent = new Intent(this, FlightPlannerEditRouteActivity.class);
//        intent.putExtra("NEW_PLAN", true);
//        startActivity(intent);
    }

    public void editPlan(View view)
    {
//        PopupBuilder.selectFlightPlan(this, FlightPlannerEditRouteActivity.class);
    }

    public void reviewPlan(View view)
    {
//        PopupBuilder.selectFlightPlan(this, FlightPlannerReviewPlanActivity.class);
    }
}
