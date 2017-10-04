package com.capstone.mike.a3_in_1flightmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
    }

    // I really want to replace these lame buttons with something bigger.
    // Maybe image buttons?
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
}
