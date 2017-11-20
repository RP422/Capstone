package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

/**
 * Created by Mike on 11/20/2017.
 */

public class FlightPlanStep
{
    public String checkpointName;
    public Float frequency;
    public String identification;

    public int course;
    public int altitude;

    public int windDirection;
    public int windSpeed;
    public Integer windTemp;

    public Integer trueAirSpeed;

    public Integer windAdjustment;
    public Integer windAdjustmentAngle;

    public Integer trueHeadingAdjustment;
    public Integer headingCorrectedAngle;

    public Integer magneticHeadingAdjustment;
    public Integer finalCorrectedHeading;

    public Integer legDistance;
    public Integer remainingDistance;

    public Integer estimatedGroundSpeed;
    public Integer actualGroundSpeed;

    public Integer estimatedTimeEnroute;
    public Integer actualTimeEnroute;
    public Integer estimatedTimeArrival;
    public Integer actualTimeArrival;

    public Integer legFuelUsage;
    public Integer remainingFuel;

    public FlightPlanStep(String checkpointName, int course, int legDistance,
                          int altitude, int trueAirSpeed, int windDirection,
                          int windSpeed, int headingAdjustment, int magneticHeadingAdjustment)
    {
        this.checkpointName = checkpointName;
        this.course = course;
        this.legDistance = legDistance;
        this.altitude = altitude;
        this.trueAirSpeed = trueAirSpeed;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.trueHeadingAdjustment = headingAdjustment;
        this.magneticHeadingAdjustment = magneticHeadingAdjustment;

        int awa = Math.abs(course - windDirection) <= 90 ? course - windDirection : (course + 180) - windDirection;

        double wca = Math.asin(((double)windSpeed * (Math.sin((double)awa) / this.trueAirSpeed)));
        double roundingTemp = wca - (int)wca;
        if(roundingTemp >= .5)
        {
            windAdjustment = (int)wca + 1;
        }
        else
        {
            windAdjustment = (int)wca;
        }

        windAdjustmentAngle = course + windAdjustment;
        if(windAdjustmentAngle < 0)
        {
            windAdjustmentAngle += 360;
        }
        else if(windAdjustmentAngle >= 360)
        {
            windAdjustmentAngle -= 360;
        }

        headingCorrectedAngle = windAdjustmentAngle + headingAdjustment;
        if(headingCorrectedAngle < 0)
        {
            headingCorrectedAngle += 360;
        }
        else if(headingCorrectedAngle >= 360)
        {
            headingCorrectedAngle -= 360;
        }

        finalCorrectedHeading = headingCorrectedAngle + magneticHeadingAdjustment;
        if(finalCorrectedHeading < 0)
        {
            finalCorrectedHeading += 360;
        }
        else if(finalCorrectedHeading >= 360)
        {
            finalCorrectedHeading -= 360;
        }
    }

    public String getCheckpointName() {
        return checkpointName;
    }

    public Float getFrequency() {
        return frequency;
    }

    public String getIdentification() {
        return identification;
    }

    public int getCourse() {
        return course;
    }

    public int getAltitude() {
        return altitude;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindTemp() {
        return windTemp;
    }

    public Integer getTrueAirSpeed() {
        return trueAirSpeed;
    }

    public Integer getWindAdjustment() {
        return windAdjustment;
    }

    public Integer getWindAdjustmentAngle() {
        return windAdjustmentAngle;
    }

    public Integer getTrueHeadingAdjustment() {
        return trueHeadingAdjustment;
    }

    public Integer getHeadingCorrectedAngle() {
        return headingCorrectedAngle;
    }

    public Integer getMagneticHeadingAdjustment() {
        return magneticHeadingAdjustment;
    }

    public Integer getFinalCorrectedHeading() {
        return finalCorrectedHeading;
    }

    public Integer getLegDistance() {
        return legDistance;
    }

    public Integer getRemainingDistance() {
        return remainingDistance;
    }

    public Integer getEstimatedGroundSpeed() {
        return estimatedGroundSpeed;
    }

    public Integer getActualGroundSpeed() {
        return actualGroundSpeed;
    }

    public Integer getEstimatedTimeEnroute() {
        return estimatedTimeEnroute;
    }

    public Integer getActualTimeEnroute() {
        return actualTimeEnroute;
    }

    public Integer getEstimatedTimeArrival() {
        return estimatedTimeArrival;
    }

    public Integer getActualTimeArrival() {
        return actualTimeArrival;
    }

    public Integer getLegFuelUsage() {
        return legFuelUsage;
    }

    public Integer getRemainingFuel() {
        return remainingFuel;
    }
}
