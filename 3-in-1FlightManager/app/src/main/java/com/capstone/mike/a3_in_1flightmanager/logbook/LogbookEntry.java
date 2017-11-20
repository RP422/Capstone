package com.capstone.mike.a3_in_1flightmanager.logbook;

import java.sql.Date;

/**
 * Created by Mike on 10/11/2017.
 */

public class LogbookEntry
{
    public Integer id;
    public Date date;

    public String aircraftModel;
    public String aircraftID;

    public String flightDeparture;
    public String flightArrival;

    public Integer numInstrumentApproach;

    public String remarksAndEndorsements;

    public Integer numDayLandings;
    public Integer numNightLandings;

    public AircraftCategory aircraftCategory;
    public AircraftClass aircraftClass;
    public Float flightTime;

    public Float nightFlyingTime;
    public Float actualInstrumentTime;
    public Float simulatedInstrumentTime;

    public Float flightSimulatorTime;

    public Float crossCountryTime;
    public Float asFlightInstructorTime;
    public Float dualRecievedTime;
    public Float pilotInCommandTime;
}
