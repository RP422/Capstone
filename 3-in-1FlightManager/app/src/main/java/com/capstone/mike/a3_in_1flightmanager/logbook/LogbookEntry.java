package com.capstone.mike.a3_in_1flightmanager.logbook;

import java.sql.Date;

/**
 * Created by Mike on 10/11/2017.
 */

public class LogbookEntry
{
    public int id;
    public Date date;

    public String aircraftModel;
    public String aircraftID;

    public String flightDeparture;
    public String flightArrival;

    public int numInsturmentApproach;

    public String remarksAndEndorsements;

    public int numDayLandings;
    public int numNightLandings;

    public AircraftCategory aircraftCategory;
    public float flightTime;

    public AircraftClass aircraftClass;
    public float classFlightTime;

    public float nightFlyingTime;
    public float actualInstrumentTime;
    public float simulatedInstrumentTime;

    public float flightSimulatorTime;

    public float crossCountryTime;
    public float asFlightInstructorTime;
    public float dualRecievedTime;
    public float pilotInCommandTime;
}
