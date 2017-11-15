package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

/**
 * Created by Mike on 11/15/2017.
 */

public class ReviewPlanAdapter extends ArrayAdapter<FlightPlanNode>
{
    FlightPlanNode[] steps;
    Context context;

    public ReviewPlanAdapter(Context context, FlightPlanNode[] steps)
    {
        super(context, R.layout.review_plan_row, steps);
        this.context = context;
        this.steps = steps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.review_plan_row, parent, false);

        // TODO Fill in the row, once the layout for the row is complete

        return convertView;
    }
}
