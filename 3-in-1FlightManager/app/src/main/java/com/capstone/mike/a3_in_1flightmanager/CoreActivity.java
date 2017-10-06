package com.capstone.mike.a3_in_1flightmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoreActivity extends AppCompatActivity {
    public static final int FILE_SELECT = 4221997;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
    }

    // I really want to replace these lame buttons with something bigger and nicer.
    // Maybe image buttons???
    public void goToPlanner()
    {
        Intent intent = new Intent(this, PlannerMainActivity.class);
        startActivity(intent);
    }
    public void goToChecklist()
    {
        Intent intent = new Intent(this, ChecklistMainActivity.class);
        startActivity(intent);
    }
    public void goToLogbook()
    {
        Intent intent = new Intent(this, LogbookMainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == FILE_SELECT)
        {
            if(resultCode == RESULT_OK)
            {
                // TODO Read the data and change something on screen to verify that it worked
            }
        }
    }

    public void createTestFiles()
    {
        // TODO create files to be looked at for testing
    }

    public void testFileSelect()
    {
        Intent intent = new Intent(this, FileSelectActivity.class);
        intent.putExtra("folder", "/checklists");
        startActivityForResult(intent, FILE_SELECT);
    }
}
