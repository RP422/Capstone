package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.PopupBuilder;

import java.util.ArrayList;

public class CreateChecklistActivity extends AppCompatActivity
{
    private static String[] testitems = new String[] {"Check tyres", "Check Fuel", "Check Flight Stick", "Check Instruments", "Check Wings", "Check Throttle"};
    private static ArrayList<String> items = new ArrayList<String>();
    private static CreateChecklistAdapter adapter;

    private static boolean TESTING = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;

        setContentView(R.layout.activity_create_checklist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // TODO AddRow
                PopupBuilder.promptForString(context, "What would you like this checklist item to be?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Add text to items ArrayList
                        // Refresh adapter?
                    }
                });
            };
        });

        if(TESTING)
        {
            for(String s : testitems)
            {
                items.add(s);
            }
        }

        ListView list = (ListView)findViewById(R.id.newChecklistItems);

        adapter = new CreateChecklistAdapter(this, items);
        list.setAdapter(adapter);
    }

    public void editText(View view)
    {
        // TODO Edit the text inside the view

        PopupBuilder.promptForString(this, "What would you like this checklist item to be?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Replace it with the proper element in the list
                // Refresh the adapter?
            }
        });
    }
    public void deleteRow(View view)
    {
        // TODO Remove the row from the list

        // Delete the item form the list
        // Refresh the adapter?
    }

    public void saveList(View view)
    {
        // TODO Ask for a file name and save the list
    }
}
