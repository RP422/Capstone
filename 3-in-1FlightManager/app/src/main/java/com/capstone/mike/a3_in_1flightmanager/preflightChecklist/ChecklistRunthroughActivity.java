package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.capstone.mike.a3_in_1flightmanager.R;

public class ChecklistRunthroughActivity extends AppCompatActivity {

    String[] testItems = new String[] { "Check Wheels", "Check Wings", "Check Rudders" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_runthrough);

        boolean useTestData = false;

        CustomChecklistAdapter adapter;

        Intent intent = this.getIntent();

        // TODO Populate the ListView
        if(intent.hasExtra("TEST"))
        {
            useTestData = intent.getBooleanExtra("TEST", false);
        }

        if(useTestData)
        {
            adapter = new CustomChecklistAdapter(this, testItems);
        }
        else
        {
            String filepath = intent.getStringExtra("FILEPATH");
        }
    }
}
