package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableLayout;
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

        int query = intent.getIntExtra("QUERY", -1);

        DBHandler db = DBHandler.getInstance(this);

        LogbookEntry[] entries = new LogbookEntry[] {};

        switch(query)
        {
            case FILTER_NONE:
                entries = db.getAll();
                break;
            case FILTER_30_DAYS:
                entries = db.getAll(30);
                break;
            case FILTER_90_DAYS:
                db.getAll(90);
                break;
            case FILTER_30_DAYS_NIGHT:
                db.getSpecialConditionFlights(SpecialConditions.NIGHT_FLYING, 30);
                break;
            case FILTER_90_DAYS_NIGHT:
                db.getSpecialConditionFlights(SpecialConditions.NIGHT_FLYING, 90);
                break;
            case FILTER_30_DAYS_SIM_INST:
                db.getSpecialConditionFlights(SpecialConditions.SIMULATED_INSTRUMENT, 30);
                break;
            case FILTER_30_DAYS_ACT_INST:
                db.getSpecialConditionFlights(SpecialConditions.ACUTAL_INSTRUMENT, 30);
                break;
            case FILTER_30_DAYS_FLGT_SIM:
                db.getSpecialConditionFlights(SpecialConditions.FLIGHT_SIMULATOR, 30);
                break;
            case FILTER_30_DAYS_XCOUNTRY:
                db.getSpecialConditionFlights(SpecialConditions.CROSS_COUNTRY, 30);
                break;
            case FILTER_30_DAYS_FLGT_INSTR:
                db.getSpecialConditionFlights(SpecialConditions.AS_FLIGHT_INSTRUCTOR, 30);
                break;
            case FILTER_30_DAYS_DUAL_RECEIVED:
                db.getSpecialConditionFlights(SpecialConditions.DUAL_RECIEVED, 30);
                break;
            case FILTER_30_DAYS_PIC:
                db.getSpecialConditionFlights(SpecialConditions.PILOT_IN_COMMAND, 30);
                break;
            default:
                Toast.makeText(this, "Sorry, that filter is not currently supported", Toast.LENGTH_LONG).show();
                
        }

        LogbookDataViewAdapter adapter = new LogbookDataViewAdapter(this, entries);

        ListView list = (ListView)findViewById(R.id.logbookDataViewListView);

        list.setAdapter(adapter);

        // TODO Put edit and delete buttons for these entries
    }
}
