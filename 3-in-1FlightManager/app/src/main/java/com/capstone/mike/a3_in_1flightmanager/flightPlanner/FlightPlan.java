package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mike on 10/29/2017.
 */

public class FlightPlan
{
    private FlightPlanNode start, end;
    private int nodeCount = 0;
    private GoogleMap map;

    public FlightPlan(GoogleMap map, Waypoint start, Waypoint end)
    {
        this.map = map;
        this.start = new FlightPlanNode(start);
        this.end = new FlightPlanNode(end);

        this.start.nextNode = this.end;
        this.end.previousNode = this.start;

        nodeCount = 2;
    }
    public FlightPlan(GoogleMap map, JSONObject flightPlan)
    {
        this.map = map;

        // TODO Deserialize the flightPlan
        try {
            JSONArray waypoints = flightPlan.getJSONArray("waypoints");

            for(int x = 0; x < waypoints.length(); x++)
            {
                JSONObject obj = waypoints.getJSONObject(x);
                double lat = obj.getDouble("latitude");
                double lng = obj.getDouble("longitude");
                int alt = obj.getInt("altitude");

                LatLng pos = new LatLng(lat, lng);
                Waypoint waypoint = new Waypoint(pos, alt);
                FlightPlanNode node = new FlightPlanNode(waypoint);

                if(end == null)
                {
                    start = node;
                    end = node;
                }
                else
                {
                    end.nextNode = node;
                    node.previousNode = end;
                    end = node;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getNodeCount()
    {
        return nodeCount;
    }

    public void addToEnd(Waypoint waypoint)
    {
        FlightPlanNode newNode = new FlightPlanNode(waypoint);
        FlightPlanNode currentLast = end.previousNode;

        newNode.previousNode = currentLast;
        currentLast.nextNode = newNode;

        newNode.nextNode = end;
        end.previousNode = newNode;

        nodeCount++;

        refreshRoute();
    }
    public void insert(Waypoint waypoint, int index)
    {
        if (index < 0 || index >= nodeCount)
        {
            throw new IndexOutOfBoundsException();
        }

        FlightPlanNode newNode = new FlightPlanNode(waypoint);
        FlightPlanNode currentNode = start;

        for(int x = 0; x < (index - 1); x++)
        {
            currentNode = currentNode.nextNode;
        }

        newNode.previousNode = currentNode;
        currentNode.nextNode = newNode;

        newNode.nextNode = currentNode.nextNode;
        currentNode.nextNode.previousNode = newNode;

        nodeCount++;

        refreshRoute();
    }

    public Waypoint get(int index)
    {
        // Standard out of bounds exception
        if(index < 0 || index >= nodeCount)
        {
            throw new IndexOutOfBoundsException();
        }

        FlightPlanNode currentNode = start;

        for(int x = 0; x < index; x++)
        {
            currentNode = currentNode.nextNode;
        }

        return currentNode.waypoint;
    }
    public int search(Waypoint waypoint)
    {
        boolean found = false;
        int index = -1;
        FlightPlanNode currentNode = start;

        for (int x = 0; x < nodeCount && !found; x++) {
            if (currentNode.waypoint.equals(waypoint)) {
                index = x;
                found = true;
                break;
            }
        }

        return index;
    }

    public void removeNode(int index)
    {
        // Standard out of bounds exception
        if(index < 0 || index >= nodeCount)
        {
            throw new IndexOutOfBoundsException();
        }
        // Make sure that you don't end up removing the start or end nodes
        else if(index == 0 || index == (nodeCount - 1))
        {
            throw new UnsupportedOperationException();
        }

        FlightPlanNode currentNode = start.nextNode;

        for(int x = 1; x < index; x++)
        {
            currentNode = currentNode.nextNode;
        }

        currentNode.previousNode.nextNode = currentNode.nextNode;
        currentNode.nextNode.previousNode = currentNode.previousNode;

        nodeCount--;

        refreshRoute();
    }
    public void removeNode(Waypoint waypoint)
    {
        removeNode(search(waypoint)); // This feels like cheating.
    }

    public void clear()
    {
        start.nextNode = end;
        end.previousNode = start;

        refreshRoute();
    }

    public Waypoint[] toArray()
    {
        Waypoint[] waypoints = new Waypoint[nodeCount];
        FlightPlanNode currentNode = start;

        for(int x = 0; x < nodeCount; x++)
        {
            waypoints[x] = currentNode.waypoint;
            currentNode = currentNode.nextNode;
        }

        return waypoints;
    }

    public void refreshRoute()
    {
        ArrayList<LatLng> points = new ArrayList<LatLng>();
        map.clear();

        FlightPlanNode currentNode = start;

        for(int x = 0; x < nodeCount; x++)
        {
            LatLng point = currentNode.waypoint.pos;
            points.add(point);
            map.addMarker(new MarkerOptions().position(point));

            currentNode = currentNode.nextNode;
        }

        map.addPolyline(new PolylineOptions().addAll(points)
                                             .width(5)
                                             .color(Color.RED)
                                             .geodesic(false));
    }

    public JSONArray serialize()
    {
        JSONArray json = new JSONArray();
        FlightPlanNode currentNode = start;

        for(int x = 0; x < nodeCount; x++)
        {
            Waypoint waypoint = currentNode.waypoint;

            JSONObject node = new JSONObject();
            try
            {
                node.put("latitude",  waypoint.pos.latitude);
                node.put("longitude", waypoint.pos.longitude);
                node.put("altitude",  waypoint.altitude);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                // TODO Do something relevant here?
            }

            json.put(node);
            currentNode = currentNode.nextNode;
        }

        return json;
    }
}