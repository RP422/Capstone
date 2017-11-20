package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;
import com.capstone.mike.a3_in_1flightmanager.common.JSONSchema;
import com.capstone.mike.a3_in_1flightmanager.common.PopupBuilder;
import com.capstone.mike.a3_in_1flightmanager.preflightChecklist.ChecklistRunthroughActivity;

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
//        Intent intent = new Intent(this, FlightPlannerEditActivity.class);
//        startActivity(intent);
    }

    public void editPlan(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditActivity.class);
        intent.putExtra("TEST", true);
        startActivity(intent);
//        final Context context = this;
//
//        DBHandler db = DBHandler.getInstance(context);
//        final String[] files = db.getFilesOfSchema(JSONSchema.FLIGHT_PLAN);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Which list do you want?");
//        builder.setItems(files, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(context, FlightPlannerEditActivity.class);
//                intent.putExtra("FILE", files[which]);
//                context.startActivity(intent);
//            }
//        });
//        builder.show();
    }

    public void reviewPlan(View view)
    {
//        PopupBuilder.selectFlightPlan(this, FlightPlannerReviewPlanActivity.class);
    }
}
