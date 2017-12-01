package com.capstone.mike.a3_in_1flightmanager.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.preflightChecklist.ChecklistRunthroughActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Mike on 10/11/2017.
 */

public class PopupBuilder
{
    // Do I want to keep the String saveFileInput variable a String or should it be some kind of JSON object?
    public static void createSaveDialog(final Context context, String prompt, final JSONSchema schema, final JSONObject saveFileInput)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(prompt);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // TODO NOTIM: Look into ways to get this box to stay open in case of bad input
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                DBHandler db = DBHandler.getInstance(context);

                if(!db.insertJSON(input.getText().toString(), schema, saveFileInput))
                {
                    Toast.makeText(context, "Save failed: that name is already in use", Toast.LENGTH_LONG).show();
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
    }

    public static void promptForString(final Context context, String prompt, DialogInterface.OnClickListener listener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(prompt);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", listener);
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

    public static void selectChecklist(final Context context)
    {

    }

    public static void selectFlightPlan(final Context context, final Class activityToStart)
    {
        DBHandler db = DBHandler.getInstance(context);
        final String[] files = db.getFilesOfSchema(JSONSchema.FLIGHT_PLAN);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Which list do you want?");
        builder.setItems(files, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, activityToStart);
                intent.putExtra("FILE", files[which]);
                context.startActivity(intent);
            }
        });
        builder.show();
    }
}
