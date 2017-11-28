package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogbookNewEntryActivity extends AppCompatActivity
{
    EditText datePicker;
    DatePickerDialog dpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_new_entry);

        datePicker = (EditText) findViewById(R.id.newEntryDatePicker);

        datePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get the current day
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                dpDialog = new DatePickerDialog(LogbookNewEntryActivity.this,
                        new DatePickerDialog.OnDateSetListener()
                        {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth)
                            {
                                // Set the text in the date picker box
                                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                                java.util.Date date = new java.util.Date(year - 1900, monthOfYear, dayOfMonth);
                                datePicker.setText(format.format(date));
                            }
                        }, mYear, mMonth, mDay);
                dpDialog.show();
            }
        });

        Spinner classSpinner = (Spinner) findViewById(R.id.newEntryClassSpinner);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.aircraftClasses, android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        Spinner catSpinner = (Spinner) findViewById(R.id.newEntryCategorySpinner);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this, R.array.aircraftCategories, android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(categoryAdapter);
    }

    public void saveEntry(View view)
    {
        LogbookEntry entry = new LogbookEntry();
        DBHandler db = DBHandler.getInstance(this);

        try {
            int iNum = 0;
            float fNum = 0f;

            // TODO Reject saves in the future
            EditText dateET = (EditText)findViewById(R.id.newEntryDatePicker);
            SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
            entry.date = new Date(format.parse(dateET.getText().toString()).getTime());

            EditText model = (EditText)findViewById(R.id.newEntryModelET);
            entry.aircraftModel = model.getText().toString();

            EditText id = (EditText)findViewById(R.id.newEntryAircraftIDET);
            entry.aircraftID = id.getText().toString();

            EditText departure = (EditText)findViewById(R.id.newEntryDepartureIDET);
            entry.flightDeparture = departure.getText().toString();

            EditText arrival = (EditText)findViewById(R.id.newEntryArrivalIDET);
            entry.flightArrival = arrival.getText().toString();

            EditText instApr = (EditText)findViewById(R.id.newEntryInstAprET);
            if(instApr.getText().toString().length() > 0)
            {
                iNum = Integer.parseInt(instApr.getText().toString());
                entry.numInstrumentApproach = iNum;
            }

            EditText remarks = (EditText)findViewById(R.id.newEntryRemarksET);
            entry.remarksAndEndorsements = remarks.getText().toString();

            EditText dayLandings = (EditText)findViewById(R.id.newEntryDayLandingsET);
            if(dayLandings.getText().toString().length() > 0)
            {
                iNum = Integer.parseInt(dayLandings.getText().toString());
                entry.numDayLandings = iNum;
            }

            EditText ngtLandings = (EditText)findViewById(R.id.newEntryNgtLandingsET);
            if(ngtLandings.getText().toString().length() > 0)
            {
                iNum = Integer.parseInt(ngtLandings.getText().toString());
                entry.numNightLandings = iNum;
            }

            Spinner classSpinner = (Spinner)findViewById(R.id.newEntryClassSpinner);
            entry.aircraftClass = AircraftClass.values()[classSpinner.getSelectedItemPosition()];

            Spinner categorySpinner = (Spinner)findViewById(R.id.newEntryCategorySpinner);
            entry.aircraftCategory = AircraftCategory.values()[categorySpinner.getSelectedItemPosition()];

            EditText flightTime = (EditText)findViewById(R.id.newEntryTotalTimeET);
            if(flightTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(flightTime.getText().toString());
                entry.flightTime = fNum;
            }

            EditText nightTime = (EditText)findViewById(R.id.newEntryNightTimeET);
            if(nightTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(nightTime.getText().toString());
                entry.nightFlyingTime = fNum;
            }

            EditText actInstTime = (EditText)findViewById(R.id.newEntryActInstTimeET);
            if(actInstTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(actInstTime.getText().toString());
                entry.actualInstrumentTime = fNum;
            }

            EditText simInstTime = (EditText)findViewById(R.id.newEntrySimInstTimeET);
            if(simInstTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(simInstTime.getText().toString());
                entry.simulatedInstrumentTime = fNum;
            }

            EditText flgtSimTime = (EditText)findViewById(R.id.newEntryFlgtSimTimeET);
            if(flgtSimTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(flgtSimTime.getText().toString());
                entry.flightSimulatorTime = fNum;
            }

            EditText xCountry = (EditText)findViewById(R.id.newEntryXCountryTimeET);
            if(xCountry.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(xCountry.getText().toString());
                entry.crossCountryTime = fNum;
            }

            EditText flgtInstTime = (EditText)findViewById(R.id.newEntryFlgtInstTimeET);
            if(flgtInstTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(flgtInstTime.getText().toString());
                entry.asFlightInstructorTime = fNum;
            }

            EditText dualTime = (EditText)findViewById(R.id.newEntryDualTimeET);
            if(dualTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(dualTime.getText().toString());
                entry.dualRecievedTime = fNum;
            }

            EditText picTime = (EditText)findViewById(R.id.newEntryPICTimeET);
            if(picTime.getText().toString().length() > 0)
            {
                fNum = Float.parseFloat(picTime.getText().toString());
                entry.pilotInCommandTime = fNum;
            }

            db.createLogbookEntry(entry);
            Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Save Failed: Parse Error", Toast.LENGTH_LONG).show();
        }
    }
}
