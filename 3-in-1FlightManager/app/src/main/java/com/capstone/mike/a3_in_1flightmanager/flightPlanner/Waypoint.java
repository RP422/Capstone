package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Mike on 10/26/2017.
 */

public class Waypoint
{
    public final LatLng pos;
    public final int altitude;
    // TODO Do I need more data than this?

    public Waypoint(LatLng pos, int altitude)
    {
        this.pos = pos;
        this.altitude = altitude;
    }
}
