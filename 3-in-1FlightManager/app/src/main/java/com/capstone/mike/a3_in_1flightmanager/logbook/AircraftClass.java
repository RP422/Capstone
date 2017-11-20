package com.capstone.mike.a3_in_1flightmanager.logbook;

/**
 * Created by Mike on 10/25/2017.
 */

// Make sure to update the strings resource if this changes
public enum AircraftClass
{
    TAIL_WHEEL,
    GLIDER,
    NA;

    public static String[] getStrings()
    {
        return new String[] { "Tail Wheel", "Glider" };
    }

    public static String asString(AircraftClass cls)
    {
        switch (cls)
        {
            case TAIL_WHEEL:
                return "Tail Wheel";
            case GLIDER:
                return "Glider";
            case NA:
                return "N/A";
            default:
                return null;
        }
    }
}
