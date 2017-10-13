package com.capstone.mike.a3_in_1flightmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Mike on 10/11/2017.
 */

public class PopupBuilder
{
    // Do I want to keep the String saveFileInput variable a String or should it be some kind of JSON object?
    public static void CreateSaveDialog(Context context, String prompt, String saveFileInput, String path)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(prompt);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                // TODO save to file
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
}
