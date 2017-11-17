package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.JSONSchema;
import com.capstone.mike.a3_in_1flightmanager.common.PopupBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateChecklistActivity extends AppCompatActivity
{
    private static String[] testitems = new String[] {"Check tyres", "Check Fuel", "Check Flight Stick", "Check Instruments", "Check Wings", "Check Throttle"};
    private static ArrayList<String> items = new ArrayList<String>();
    private static CreateChecklistAdapter adapter;

    private static boolean TESTING = true;

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;

        setContentView(R.layout.activity_checklist_create);

        FloatingActionButton newRowFAB = (FloatingActionButton) findViewById(R.id.newRowFAB);
        newRowFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("What would you like this checklist item to be?");

                final EditText input = new EditText(context);
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
            };
        });
        FloatingActionButton saveListFab = (FloatingActionButton) findViewById(R.id.saveListFAB);
        saveListFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveList();
            };
        });

        if(TESTING)
        {
            for(String s : testitems)
            {
                items.add(s);
            }
        }

        list = (ListView)findViewById(R.id.newChecklistItems);

        list.setAdapter(new CreateChecklistAdapter(this, items));
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

    public void saveList()
    {
        JSONObject json = new JSONObject();
        JSONArray jsonItems = new JSONArray();

        for(String item : items)
        {
            jsonItems.put(item);
        }

        try
        {
            json.put("CHECKLIST", jsonItems);

            // TODO Modify this when the time comes
            PopupBuilder.createSaveDialog(this, "What do you want to name the checklist?", JSONSchema.CHECKLIST, json);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
