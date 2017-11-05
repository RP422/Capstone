package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

/**
 * Created by Mike on 10/29/2017.
 */

public class FlightPlanNode
{
    public Waypoint waypoint;

    public FlightPlanNode nextNode, previousNode;

    public FlightPlanNode(Waypoint waypoint)
    {
        this.waypoint = waypoint;
    }
}
