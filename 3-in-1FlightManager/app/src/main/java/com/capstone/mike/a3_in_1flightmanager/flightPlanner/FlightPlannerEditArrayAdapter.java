package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

import org.w3c.dom.Text;

/**
 * Created by Mike on 11/20/2017.
 */

public class FlightPlannerEditArrayAdapter  extends ArrayAdapter<FlightPlanStep>
{
    private FlightPlanStep[] steps;
    private Context context;

    public FlightPlannerEditArrayAdapter(Context context, FlightPlanStep[] steps)
    {
        super(context, R.layout.flight_plan_edit_row, steps);

        this.context = context;
        this.steps = steps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.flight_plan_edit_row, parent, false);

        FlightPlanStep step = steps[position];

        TextView rowIndicator = convertView.findViewById(R.id.rowNumTV);
        rowIndicator.setText("" + position);

        TextView checkpoint = convertView.findViewById(R.id.checkpointTV);
        checkpoint.setText(step.getCheckpointName());

        if(step.getFrequency() != null)
        {
            TextView freq = convertView.findViewById(R.id.freqTV);
            freq.setText("" + step.getFrequency());
        }

        if(step.getIdentification() != null)
        {
            TextView ident = convertView.findViewById(R.id.identTV);
            ident.setText("" + step.getIdentification());
        }

        // Course
        TextView course = convertView.findViewById(R.id.courseTV);
        course.setText("" + step.getCourse());

        // Altitude
        TextView altitude = convertView.findViewById(R.id.altitudeTV);
        altitude.setText("" + step.getAltitude());

        // Wind
        TextView windDir = convertView.findViewById(R.id.windDirTV);
        TextView windVel = convertView.findViewById(R.id.windVelTV);

        windDir.setText("" + step.getWindDirection());
        windVel.setText("" + step.getWindSpeed());

        // True Air Speed
        TextView tas = convertView.findViewById(R.id.tasTV);
        tas.setText("" + step.getTrueAirSpeed());

        // Final Corrected Heading
        TextView correctedHeading = convertView.findViewById(R.id.correctedHeadingTV);
        correctedHeading.setText("" + step.getFinalCorrectedHeading());

        // Distance
        TextView distLeg = convertView.findViewById(R.id.distanceLegTV);
        TextView distRemain = convertView.findViewById(R.id.distanceRemainTV);

        distLeg.setText("" + step.getLegDistance());
        distRemain.setText("" + step.getRemainingDistance());

        // Ground Speed
        TextView groundSpeed = convertView.findViewById(R.id.groundSpeedTV);
        groundSpeed.setText("" + step.getEstimatedGroundSpeed());

        // ETE
        TextView ete = convertView.findViewById(R.id.eteTV);
        ete.setText("" + step.getEstimatedTimeEnroute());

        // Fuel
        TextView fuelLeg = convertView.findViewById(R.id.fuelLegTV);
        TextView fuelRemain = convertView.findViewById(R.id.fuelRemainTV);

        fuelLeg.setText("" + String.format("%1$.3f", step.getLegFuelUsage()));
        fuelRemain.setText("" + String.format("%1$.3f", step.getRemainingFuel()));

        return convertView;
    }
}
