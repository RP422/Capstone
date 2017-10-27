package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by Mike on 10/26/2017.
 */

public class Waypoint
{
    public final Marker marker;
    public final int altitude;
    // TODO Do I need more data than this?

    public Waypoint(Marker marker, int altitude)
    {
        this.marker = marker;
        this.altitude = altitude;
    }
}
