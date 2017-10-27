package com.capstone.mike.a3_in_1flightmanager.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.logbook.LogbookMainActivity;
import com.capstone.mike.a3_in_1flightmanager.preflightChecklist.ChecklistMainActivity;

import java.io.FileOutputStream;

public class CoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);


    }

    // I really want to replace these lame buttons with something bigger and nicer.
    // Maybe image buttons???
    public void goToPlanner(View view)
    {
        Intent intent = new Intent(this, PlannerMainActivity.class);
        startActivity(intent);
    }
    public void goToChecklist(View view)
    {
        Intent intent = new Intent(this, ChecklistMainActivity.class);
        startActivity(intent);
    }
    public void goToLogbook(View view)
    {
        Intent intent = new Intent(this, LogbookMainActivity.class);
        startActivity(intent);
    }

    public void createTestFiles()
    {
        String checklistFileName = "checklistTest.json";
        String flightPlanFileName = "flightPlanTest.json";

        String checklistFileContent = "{ \"Title\": \"checklistTest\",\n\"Content\": \"this\" }";
        String flightPlanFileContent = "{ \"Title\": \"flightPlanTest\" }";

        FileOutputStream fileOutputStream = null;

        // Checklist File
        try
        {
            fileOutputStream = openFileOutput("checklists/" + checklistFileName, MODE_PRIVATE);
            fileOutputStream.write(checklistFileContent.getBytes());
            fileOutputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // Flight Plan File
        try
        {
            fileOutputStream = openFileOutput("flight_plans/" + flightPlanFileName, MODE_PRIVATE);
            fileOutputStream.write(flightPlanFileContent.getBytes());
            fileOutputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
