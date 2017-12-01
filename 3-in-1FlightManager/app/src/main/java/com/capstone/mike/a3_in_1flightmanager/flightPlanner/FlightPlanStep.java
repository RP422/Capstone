package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import org.json.JSONException;
import org.json.JSONObject;

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

    public int trueAirSpeed;

    public int windAdjustment;
    public int windAdjustmentAngle;

    public int trueHeadingAdjustment;
    public int headingCorrectedAngle;

    public int magneticHeadingAdjustment;
    public int finalCorrectedHeading;

    public int legDistance;
    public int remainingDistance = 0;

    public int estimatedGroundSpeed;
    public Integer actualGroundSpeed;

    public int estimatedTimeEnroute;
    public Integer actualTimeEnroute;
    public Integer estimatedTimeArrival;
    public Integer actualTimeArrival;

    public Float legFuelUsage;
    public Float remainingFuel = 0f;

    public FlightPlanStep(String checkpointName, int course, int legDistance,
                          int altitude, int trueAirSpeed, int windDirection,
                          int windSpeed, int headingAdjustment, int magneticHeadingAdjustment,
                          Float frequency, String identification)
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

        if(frequency != null)
        {
            this.frequency = frequency;
        }
        if(identification != null && !identification.isEmpty())
        {
            this.identification = identification;
        }

        int awa = 0;
        int windAngleToCourse = windDirection - course;
        int absWindAngle = Math.abs(windAngleToCourse);

        if(absWindAngle == 0 || absWindAngle == 180)
        {
            awa = 0;
        }
        else if(absWindAngle <= 90)
        {
            awa = windAngleToCourse;
        }
        else if(absWindAngle > 90 && absWindAngle < 180)
        {
            if(windAngleToCourse > 0)
            {
                awa = -(windAngleToCourse - 90);
            }
            else
            {
                awa = -(windAngleToCourse + 90);
            }
        }
        else if(absWindAngle > 180 && absWindAngle < 270)
        {
            if(windAngleToCourse > 0)
            {
                awa = -(windAngleToCourse - 180);
            }
            else
            {
                awa = -(windAngleToCourse + 180);
            }
        }
        else // absRawAWA <= 270
        {
            if(windAngleToCourse > 0)
            {
                awa = -(360 - windAngleToCourse);
            }
            else
            {
                awa = -(-360 - windAngleToCourse);
            }
        }

        // This next line is kinda dense on the Math functions.
        // Here's an easier to understand way of putting it:

        //          /               sin(Acute Wind Angle)  \
        //  arcsin |  Wind Speed X -----------------------  |
        //          \                   True Air Speed     /

        double wca = Math.toDegrees(Math.asin((double)windSpeed * (Math.sin(Math.toRadians((double)awa)) / (double)trueAirSpeed)));

        double roundingVar = wca - (int)wca;
        if(Math.abs(roundingVar) >= .5)
        {
            if(wca > 0)
            {
                windAdjustment = (int)wca + 1;
            }
            else
            {
                windAdjustment = (int)wca - 1;
            }
        }
        else
        {
            windAdjustment = (int)wca;
        }

        // Apply the wind adjustment
        windAdjustmentAngle = course + windAdjustment;
        if(windAdjustmentAngle < 0)
        {
            windAdjustmentAngle += 360;
        }
        else if(windAdjustmentAngle >= 360)
        {
            windAdjustmentAngle -= 360;
        }

        // Apply the plane's heading correction
        headingCorrectedAngle = windAdjustmentAngle + headingAdjustment;
        if(headingCorrectedAngle < 0)
        {
            headingCorrectedAngle += 360;
        }
        else if(headingCorrectedAngle >= 360)
        {
            headingCorrectedAngle -= 360;
        }

        // Finalize the heading with the magnetic heading adjustment
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

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();

        json.put("checkpointName", checkpointName);
        json.put("legDistance", legDistance);
        json.put("course", course);
        json.put("altitude", altitude);
        json.put("tas", trueAirSpeed);
        json.put("windDir", windDirection);
        json.put("windSpeed", windSpeed);
        json.put("headAdjust", trueHeadingAdjustment);
        json.put("magHeadAdjust", magneticHeadingAdjustment);

        if(frequency != null)
        {
            json.put("freq", frequency);
        }
        if(identification != null && !identification.isEmpty())
        {
            json.put("ident", identification);
        }

        return json;
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

    public int getTrueAirSpeed() {
        return trueAirSpeed;
    }

    public int getWindAdjustment() {
        return windAdjustment;
    }

    public int getWindAdjustmentAngle() {
        return windAdjustmentAngle;
    }

    public int getTrueHeadingAdjustment() {
        return trueHeadingAdjustment;
    }

    public int getHeadingCorrectedAngle() {
        return headingCorrectedAngle;
    }

    public int getMagneticHeadingAdjustment() {
        return magneticHeadingAdjustment;
    }

    public int getFinalCorrectedHeading() {
        return finalCorrectedHeading;
    }

    public int getLegDistance() {
        return legDistance;
    }

    public int getRemainingDistance() {
        return remainingDistance;
    }

    public int getEstimatedGroundSpeed() {
        return estimatedGroundSpeed;
    }

    public int getActualGroundSpeed() {
        return actualGroundSpeed;
    }

    public int getEstimatedTimeEnroute() {
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

    public Float getLegFuelUsage() {
        return legFuelUsage;
    }

    public Float getRemainingFuel() {
        return remainingFuel;
    }
}
