package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;
import com.capstone.mike.a3_in_1flightmanager.common.JSONSchema;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateChecklistActivity extends AppCompatActivity
{
    private static String[] testitems = new String[] {"Check tyres", "Check Fuel", "Check Flight Stick", "Check Instruments", "Check Wings", "Check Throttle"};
    private static ArrayList<String> items;
    private static CreateChecklistAdapter adapter;

    private String fileName;
    private boolean editingExistingList = false;

    private static final boolean TESTING = false;

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_create_x);

        Intent intent = getIntent();
        items = new ArrayList<>();
        list = (ListView)findViewById(R.id.newChecklistItems);

        if(intent.getBooleanExtra("TEST", false))
        {
            for(String s : testitems)
            {
                items.add(s);
            }
        }
        else if(intent.hasExtra("FILE"))
        {
            editingExistingList = true;
            fileName = intent.getStringExtra("FILE");

            DBHandler db = DBHandler.getInstance(this);
            JSONObject json = db.getJSONfromReferenceName(fileName);

            try
            {
                JSONArray jsonItems = json.getJSONArray("CHECKLIST");
                String [] listItems = new String[jsonItems.length()];

                for(int x = 0; x < listItems.length; x++)
                {
                    items.add((String)jsonItems.get(x));
                }
            }
            catch (JSONException e)
            {
                Toast.makeText(this, "Sorry, there was a problem with the file.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        refreshAdapter();
    }

    public void newRow(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What would you like this checklist item to be?");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                items.add(input.getText().toString());
                refreshAdapter();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void editText(View view)
    {
        ConstraintLayout parent = (ConstraintLayout)view.getParent();
        TextView textView = (TextView)parent.findViewById(R.id.newRowTextView);
        final CharSequence oldText = textView.getText();

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("What would you like this checklist item to be?");

        final EditText input = new EditText(this);
        input.setText(oldText);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int index = -1;
                for(int x = 0; x < items.size(); x++)
                {
                    if(items.get(x).equals(oldText))
                    {
                        index = x;
                        break;
                    }
                }

                items.set(index, input.getText().toString());
                refreshAdapter();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public void deleteRow(View view)
    {
        ConstraintLayout parent = (ConstraintLayout)view.getParent();
        TextView textView = (TextView)parent.findViewById(R.id.newRowTextView);
        final CharSequence textToRemove = textView.getText();

        items.remove(textToRemove);
        refreshAdapter();
    }

    public void refreshAdapter()
    {
        list.setAdapter(new CreateChecklistAdapter(this, items));
    }

    public void saveList(View view)
    {
        // TODO Update the file if the activity started with a file
        JSONObject json = new JSONObject();
        JSONArray jsonItems = new JSONArray();

        for(String item : items)
        {
            jsonItems.put(item);
        }

        try
        {
            json.put("CHECKLIST", jsonItems);

            final Context context = this;

            // TODO Modify this when the time comes I guess
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("What do you want to name the checklist?");

            final EditText input = new EditText(context);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
//            input.setText(fileName);
            builder.setView(input);

            final JSONObject jsonToSave = json;

//            if(editingExistingList)
//            {
//                DBHandler db = DBHandler.getInstance(this);
//
//                db.updateJSON(fileName, JSONSchema.CHECKLIST, json);
//                finish();
//                Toast.makeText(context, "Save Sucessful", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        DBHandler db = DBHandler.getInstance(context);

                        // TODO Look into ways to get this box to stay open in case of bad input
                        if(!db.insertJSON(input.getText().toString(), JSONSchema.CHECKLIST, jsonToSave))
                        {
                            Toast.makeText(context, "Save failed: that name is already in use", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            finish();
                            Toast.makeText(context, "Save Sucessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                builder.show();
//            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
