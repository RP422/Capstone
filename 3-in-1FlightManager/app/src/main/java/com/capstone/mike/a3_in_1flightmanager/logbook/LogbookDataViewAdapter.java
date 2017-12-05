package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

import java.text.SimpleDateFormat;

/**
 * Created by Mike on 10/18/2017.
 */

public class LogbookDataViewAdapter extends ArrayAdapter<LogbookEntry>
{
    LogbookEntry[] logbookEntries;
    Context context;

    public LogbookDataViewAdapter(Context context, LogbookEntry[] listItems)
    {
        super(context, R.layout.checklist_item, listItems);
        this.context = context;
        this.logbookEntries = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.logbook_data_view_row, parent, false);

        LogbookEntry entry = logbookEntries[position];

        TextView entryID = convertView.findViewById(R.id.entryID);
        entryID.setText("" + entry.id);

        TextView date = convertView.findViewById(R.id.EntryDate);
        if(entry.date != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            date.setText(format.format(entry.date));
        }
        else
        {
            date.setText("N/A");
        }

        TextView model = convertView.findViewById(R.id.AircraftModel);
        if(entry.aircraftModel != null && !entry.aircraftModel.isEmpty())
        {
            model.setText(entry.aircraftModel);
        }
        else
        {
            model.setText("N/A");
        }

        TextView aircraftID = convertView.findViewById(R.id.AircraftID);
        if(entry.aircraftID != null && !entry.aircraftID.isEmpty())
        {
            aircraftID.setText(entry.aircraftID);
        }
        else
        {
            aircraftID.setText("N/A");
        }

        TextView depatrure = (TextView)convertView.findViewById(R.id.Departure);
        if(entry.flightDeparture != null && !entry.flightDeparture.isEmpty())
        {
            depatrure.setText(entry.flightDeparture);
        }
        else
        {
            depatrure.setText("N/A");
        }

        TextView arrival = (TextView)convertView.findViewById(R.id.Arrival);
        if(entry.flightArrival != null && !entry.aircraftID.isEmpty())
        {
            arrival.setText(entry.flightArrival);
        }
        else
        {
            arrival.setText("N/A");
        }

        TextView instApr = (TextView)convertView.findViewById(R.id.NumInstApr);
        if(entry.numInstrumentApproach != null && entry.numInstrumentApproach != 0)
        {
            instApr.setText("" + entry.numInstrumentApproach);
        }
        else
        {
            instApr.setText("N/A");
        }

        TextView remarks = (TextView)convertView.findViewById(R.id.RemarksAndEndorsments);
        if(entry.remarksAndEndorsements != null && !entry.remarksAndEndorsements.isEmpty())
        {
            remarks.setText(entry.remarksAndEndorsements);
        }
        else
        {
            remarks.setText("N/A");
        }

        TextView aircraftClass = (TextView)convertView.findViewById(R.id.AircraftClass);
        if(entry.aircraftClass != null)
        {
            aircraftClass.setText(AircraftClass.asString(entry.aircraftClass));
        }
        else
        {
            aircraftClass.setText("N/A");
        }

        TextView category = (TextView)convertView.findViewById(R.id.AircraftCategory);
        if(entry.aircraftCategory != null)
        {
            category.setText(AircraftCategory.asString(entry.aircraftCategory));
        }
        else
        {
            category.setText("N/A");
        }

        TextView totalTime = (TextView)convertView.findViewById(R.id.FlightTime);
        if(entry.flightTime != null && entry.flightTime > 0)
        {
            totalTime.setText("" + entry.flightTime);
        }
        else
        {
            totalTime.setText("N/A");
        }

        TextView night = (TextView)convertView.findViewById(R.id.NightTime);
        if(entry.nightFlyingTime != null && entry.nightFlyingTime > 0)
        {
            night.setText("" + entry.nightFlyingTime);
        }
        else
        {
            night.setText("N/A");
        }

        TextView actInst = (TextView)convertView.findViewById(R.id.ActInstTime);
        if(entry.actualInstrumentTime != null && entry.actualInstrumentTime > 0)
        {
            actInst.setText("" + entry.actualInstrumentTime);
        }
        else
        {
            actInst.setText("N/A");
        }

        TextView simInst = (TextView)convertView.findViewById(R.id.SimInstTime);
        if(entry.simulatedInstrumentTime != null && entry.simulatedInstrumentTime > 0)
        {
            simInst.setText("" + entry.simulatedInstrumentTime);
        }
        else
        {
            simInst.setText("N/A");
        }

        TextView flgtSim = (TextView)convertView.findViewById(R.id.FlgtSimTime);
        if(entry.flightSimulatorTime != null && entry.flightSimulatorTime > 0)
        {
            flgtSim.setText("" + entry.flightSimulatorTime);
        }
        else
        {
            flgtSim.setText("N/A");
        }

        TextView xCountry = (TextView)convertView.findViewById(R.id.CrossCountryTime);
        if(entry.crossCountryTime != null && entry.crossCountryTime > 0)
        {
            xCountry.setText("" + entry.crossCountryTime);
        }
        else
        {
            xCountry.setText("N/A");
        }

        TextView instr = (TextView)convertView.findViewById(R.id.FlgtInstructorTime);
        if(entry.asFlightInstructorTime != null && entry.asFlightInstructorTime > 0)
        {
            instr.setText("" + entry.asFlightInstructorTime);
        }
        else
        {
            instr.setText("N/A");
        }

        TextView dual = (TextView)convertView.findViewById(R.id.DualReceivedTime);
        if(entry.dualRecievedTime != null && entry.dualRecievedTime > 0)
        {
            dual.setText("" + entry.dualRecievedTime);
        }
        else
        {
            dual.setText("N/A");
        }

        TextView pic = (TextView)convertView.findViewById(R.id.PicTime);
        if(entry.pilotInCommandTime != null && entry.pilotInCommandTime > 0)
        {
            pic.setText("" + entry.pilotInCommandTime);
        }
        else
        {
            pic.setText("N/A");
        }

        return convertView;
    }


}
