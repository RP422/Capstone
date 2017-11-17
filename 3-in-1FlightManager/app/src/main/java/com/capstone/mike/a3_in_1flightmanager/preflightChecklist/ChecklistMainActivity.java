package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.PopupBuilder;

public class ChecklistMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_main);
    }

    public void selectAndLaunchChecklist(View view)
    {
        Intent intent = new Intent(this, ChecklistRunthroughActivity.class);
        intent.putExtra("TEST", true);
        this.startActivity(intent);
        //PopupBuilder.selectChecklist(this);
    }

    public void createNewChecklist(View view)
    {
        Intent intent = new Intent(this, CreateChecklistActivity.class);
        startActivity(intent);
    }
}
