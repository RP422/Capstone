package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.capstone.mike.a3_in_1flightmanager.logbook.LogbookQuerySelectionActivity.*;

public class LogbookDataViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_data_view);

        Intent intent = this.getIntent();

        if(!intent.hasExtra("QUERY"))
        {
            Toast.makeText(this, "Whoops. Something went wrong here", Toast.LENGTH_LONG).show();
        }

        refreshAdapter();
        // TODO NOTIM: Put an edit button for these entries?
    }

    public void deleteLogbookEntry(View view)
    {
        ConstraintLayout parent = (ConstraintLayout)view.getParent();
        TextView idBox = parent.findViewById(R.id.entryID);
        int entryID = Integer.parseInt(idBox.getText().toString());

        DBHandler db = DBHandler.getInstance(this);
        db.removeLogbookEntry(entryID);

        refreshAdapter();
    }

    public void refreshAdapter()
    {
        Intent intent = getIntent();
        int query = intent.getIntExtra("QUERY", Integer.MIN_VALUE);

        DBHandler db = DBHandler.getInstance(this);

        LogbookEntry[] entries = new LogbookEntry[] {};

        switch(query)
        {
            case LogbookQuerySelectionActivity.FILTER_NONE:
                entries = db.getAll();
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS:
                entries = db.getAll(30);
                break;
            case LogbookQuerySelectionActivity.FILTER_90_DAYS:
                entries = db.getAll(90);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_NIGHT:
                entries = db.getSpecialConditionFlights(SpecialConditions.NIGHT_FLYING, 30);
                break;
            case LogbookQuerySelectionActivity.FILTER_90_DAYS_NIGHT:
                entries = db.getSpecialConditionFlights(SpecialConditions.NIGHT_FLYING, 90);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_SIM_INST:
                entries = db.getSpecialConditionFlights(SpecialConditions.SIMULATED_INSTRUMENT, 30);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_ACT_INST:
                entries = db.getSpecialConditionFlights(SpecialConditions.ACUTAL_INSTRUMENT, 30);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_FLGT_SIM:
                entries = db.getSpecialConditionFlights(SpecialConditions.FLIGHT_SIMULATOR, 30);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_XCOUNTRY:
                entries = db.getSpecialConditionFlights(SpecialConditions.CROSS_COUNTRY, 30);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_FLGT_INSTR:
                entries = db.getSpecialConditionFlights(SpecialConditions.AS_FLIGHT_INSTRUCTOR, 30);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_DUAL_RECEIVED:
                entries = db.getSpecialConditionFlights(SpecialConditions.DUAL_RECIEVED, 30);
                break;
            case LogbookQuerySelectionActivity.FILTER_30_DAYS_PIC:
                entries = db.getSpecialConditionFlights(SpecialConditions.PILOT_IN_COMMAND, 30);
                break;
            default:
                Toast.makeText(this, "Sorry, that filter is not currently supported", Toast.LENGTH_LONG).show();
                finish();
        }

        if(entries.length == 0)
        {
            Toast.makeText(this, "There are no flights that fit that filter.", Toast.LENGTH_LONG).show();
            finish();
        }

        LogbookDataViewAdapter adapter = new LogbookDataViewAdapter(this, entries);

        ListView list = (ListView)findViewById(R.id.logbookDataViewListView);

        list.setAdapter(adapter);
    }
}
