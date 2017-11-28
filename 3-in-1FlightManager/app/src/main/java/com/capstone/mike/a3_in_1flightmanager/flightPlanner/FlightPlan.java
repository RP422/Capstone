package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import java.util.ArrayList;

/**
 * Created by Mike on 11/26/2017.
 */

public class FlightPlan
{
    private ArrayList<FlightPlanStep> flightPlan = new ArrayList<>();
    private String startingAirportName;
    private int startingAirportMSL;

    private String aircraft;
    private float fuelRate;
    private int fuelCapacity;

    private int totalDistance;
    private int totalFuel;

    public FlightPlan(String startingAirportName, int startingAirportMSL)
    {
        this.startingAirportName = startingAirportName;
        this.startingAirportMSL = startingAirportMSL;
    }

    public FlightPlanStep[] toArray()
    {
        return flightPlan.toArray(new FlightPlanStep[] {});
    }

    public String getStartingAirportName()
    {
        return startingAirportName;
    }
    public void setStartingAirportName(String startingAirportName)
    {
        this.startingAirportName = startingAirportName;
    }

    public float getFuelRate()
    {
        return fuelRate;
    }
    public void setFuelRate(float fuelRate)
    {
        this.fuelRate = fuelRate;
    }
    public void setFuelRate(String aircraft)
    {
        // TODO Query for the plane's fuel consumption
        throw new UnsupportedOperationException();
    }

    public int size()
    {
        return flightPlan.size();
    }

    public void add(FlightPlanStep step)
    {
        flightPlan.add(step);
        refreshSteps();
    }
    public void add(int index, FlightPlanStep step)
    {
        flightPlan.add(index, step);
        refreshSteps();
    }

    public void remove(int index)
    {
        flightPlan.remove(index);
    }

    public FlightPlanStep get(int index)
    {
        return flightPlan.get(index);
    }
    public FlightPlanStep get(String checkpointName)
    {
        for(int x = 0; x < flightPlan.size(); x++)
        {
            if(flightPlan.get(x).checkpointName.equals(checkpointName))
            {
                return flightPlan.get(x);
            }
        }
        return null;
    }

    private void refreshSteps()
    {
        int remainDistance = 0;
        int legDistance;

        float remainFuel = fuelRate * 0.45f;

        int[] altMSL = new int[flightPlan.size() + 1];
        altMSL[0] = startingAirportMSL;

        for(int x = 0; x < flightPlan.size(); x++)
        {
            altMSL[x + 1] = flightPlan.get(x).altitude;
        }

        for(int x = flightPlan.size() - 1; x >= 0; x--)
        {
            FlightPlanStep step = flightPlan.get(x);

            legDistance = step.legDistance;
            step.remainingDistance = remainDistance;
            remainDistance += legDistance;

            // Finding estimatedGroundSpeed
            int windAngleToCourse = step.windDirection - step.course;
            int headingToCourse = step.windAdjustmentAngle - step.course;

            double courseCos = Math.cos(Math.toRadians(headingToCourse));
            double windCos = -(Math.cos(Math.toRadians(windAngleToCourse)));

            double courseComponent = (double)step.trueAirSpeed * courseCos;
            double windComponent = (double)step.windSpeed * windCos;

            double windAdjustedSpeed = courseComponent + windComponent;

            int altChange = altMSL[x + 1] - altMSL[x];
            double lateralComponent;

            if(altChange != 0)
            {
                double nauticalMilesClimb = (double)altChange / 6076;

                double climbAngle = Math.atan(nauticalMilesClimb / legDistance);
                double climbAngleDegrees = Math.toDegrees(climbAngle);

                lateralComponent = windAdjustedSpeed * Math.cos(climbAngle);
            }
            else
            {
                lateralComponent = windAdjustedSpeed;
            }

            double roundingVar = lateralComponent - (int)lateralComponent;

            if(roundingVar >= .5)
            {
                step.estimatedGroundSpeed = (int)lateralComponent + 1;
            }
            else
            {
                step.estimatedGroundSpeed = (int)lateralComponent;
            }

            // Finding estimatedTimeEnroute
            double groundSpeedPerMinute = lateralComponent / 60; // Convert from "per hour" to "per minute"

            double tempETE = (double)legDistance / groundSpeedPerMinute;
            roundingVar = tempETE - (int)tempETE;

            if(roundingVar >= .5)
            {
                step.estimatedTimeEnroute = (int)tempETE + 1;
            }
            else
            {
                step.estimatedTimeEnroute = (int)tempETE;
            }

            step.legFuelUsage = ((float)flightPlan.get(x).estimatedTimeEnroute / 60) * fuelRate;
            flightPlan.get(x).remainingFuel = remainFuel;
            remainFuel += step.legFuelUsage;
        }
    }
}
