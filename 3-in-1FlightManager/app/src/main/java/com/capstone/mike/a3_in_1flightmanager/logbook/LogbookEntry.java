package com.capstone.mike.a3_in_1flightmanager.logbook;

import java.sql.Date;

/**
 * Created by Mike on 10/11/2017.
 */

public class LogbookEntry
{
    private int id;
    private Date date;

    private String aircraftModel;
    private String aircraftID;

    private String flightDeparture;
    private String flightArrival;

    private int numInsturmentApproach;

    private String remarksAndEndorsements;

    private int numDayLandings;
    private int numNightLandings;

    private AircraftCategory aircraftCategory;
    private float flightTime;


    private String aircraftClass; // Change this to an enum later?
    private float classFlightTime;

    private float nightFlyingTime;
    private float actualInstrumentTime;
    private float simulatedInstrumentTime;

    private float flightSimulatorTime;

    private float crossCountryTime;
    private float asFlightInstructorTime;
    private float dualRecievedTime;
    private float pilotInCommandTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public String getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(String aircraftID) {
        this.aircraftID = aircraftID;
    }

    public String getFlightDeparture() {
        return flightDeparture;
    }

    public void setFlightDeparture(String flightDeparture) {
        this.flightDeparture = flightDeparture;
    }

    public String getFlightArrival() {
        return flightArrival;
    }

    public void setFlightArrival(String flightArrival) {
        this.flightArrival = flightArrival;
    }

    public int getNumInsturmentApproach() {
        return numInsturmentApproach;
    }

    public void setNumInsturmentApproach(int numInsturmentApproach) {
        this.numInsturmentApproach = numInsturmentApproach;
    }

    public String getRemarksAndEndorsements() {
        return remarksAndEndorsements;
    }

    public void setRemarksAndEndorsements(String remarksAndEndorsements) {
        this.remarksAndEndorsements = remarksAndEndorsements;
    }

    public int getNumDayLandings() {
        return numDayLandings;
    }

    public void setNumDayLandings(int numDayLandings) {
        this.numDayLandings = numDayLandings;
    }

    public int getNumNightLandings() {
        return numNightLandings;
    }

    public void setNumNightLandings(int numNightLandings) {
        this.numNightLandings = numNightLandings;
    }

    public AircraftCategory getAircraftCategory() {
        return aircraftCategory;
    }

    public void setAircraftCategory(AircraftCategory aircraftCategory) {
        this.aircraftCategory = aircraftCategory;
    }

    public float getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(float flightTime) {
        this.flightTime = flightTime;
    }

    public String getAircraftClass() {
        return aircraftClass;
    }

    public void setAircraftClass(String aircraftClass) {
        this.aircraftClass = aircraftClass;
    }

    public float getClassFlightTime() {
        return classFlightTime;
    }

    public void setClassFlightTime(float classFlightTime) {
        this.classFlightTime = classFlightTime;
    }

    public float getNightFlyingTime() {
        return nightFlyingTime;
    }

    public void setNightFlyingTime(float nightFlyingTime) {
        this.nightFlyingTime = nightFlyingTime;
    }

    public float getActualInstrumentTime() {
        return actualInstrumentTime;
    }

    public void setActualInstrumentTime(float actualInstrumentTime) {
        this.actualInstrumentTime = actualInstrumentTime;
    }

    public float getSimulatedInstrumentTime() {
        return simulatedInstrumentTime;
    }

    public void setSimulatedInstrumentTime(float simulatedInstrumentTime) {
        this.simulatedInstrumentTime = simulatedInstrumentTime;
    }

    public float getFlightSimulatorTime() {
        return flightSimulatorTime;
    }

    public void setFlightSimulatorTime(float flightSimulatorTime) {
        this.flightSimulatorTime = flightSimulatorTime;
    }

    public float getCrossCountryTime() {
        return crossCountryTime;
    }

    public void setCrossCountryTime(float crossCountryTime) {
        this.crossCountryTime = crossCountryTime;
    }

    public float getAsFlightInstructorTime() {
        return asFlightInstructorTime;
    }

    public void setAsFlightInstructorTime(float asFlightInstructorTime) {
        this.asFlightInstructorTime = asFlightInstructorTime;
    }

    public float getDualRecievedTime() {
        return dualRecievedTime;
    }

    public void setDualRecievedTime(float dualRecievedTime) {
        this.dualRecievedTime = dualRecievedTime;
    }

    public float getPilotInCommandTime() {
        return pilotInCommandTime;
    }

    public void setPilotInCommandTime(float pilotInCommandTime) {
        this.pilotInCommandTime = pilotInCommandTime;
    }
}
