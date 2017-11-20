package com.capstone.mike.a3_in_1flightmanager.logbook;

/**
 * Created by Mike on 10/25/2017.
 */

// Make sure to update the strings resource if this changes
public enum AircraftCategory
{
    SINGLE_ENGINE_LAND,
    MULTI_ENGINE_LAND,
    NA;

    public static String[] getStrings()
    {
        return new String[] { "Single-Engine Land", "Multi-Engine Land" };
    }

    public static String asString(AircraftCategory cat)
    {
        switch (cat)
        {
            case SINGLE_ENGINE_LAND:
                return "Single-Engine Land";
            case MULTI_ENGINE_LAND:
                return "Multi-Engine Land";
            case NA:
                return "N/A";
            default:
                return null;
        }
    }
}
