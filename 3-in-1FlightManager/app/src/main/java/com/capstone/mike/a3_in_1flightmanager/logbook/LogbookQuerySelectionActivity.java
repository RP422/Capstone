package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.capstone.mike.a3_in_1flightmanager.R;

public class LogbookQuerySelectionActivity extends AppCompatActivity {
    public String[] presetQueries = new String[] {
            "Get all entries within 30 days",
            "Get total hours flown",
            "Get total hours flown within 30 days",
            "Get total landings within 30 days",
            "Get total night landings within 30 days",
            "Get total night flying time"
            // TODO fill the rest of this in later
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_query_selection);

        // Gonna need to figure out how I'm gonna add all this stuff in there
    }
}
