package com.capstone.mike.a3_in_1flightmanager.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Mike on 10/11/2017.
 */

public class PopupBuilder
{
    // Do I want to keep the String saveFileInput variable a String or should it be some kind of JSON object?
    public static void createSaveDialog(final Context context, String prompt, final String saveFileInput, final String path)
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
                FileOutputStream outputStream;
                OutputStreamWriter writer;
                try
                {
                    outputStream = context.openFileOutput(path + "/" + input.getText().toString(), Context.MODE_PRIVATE);
                    writer = new OutputStreamWriter(outputStream);

                    writer.write(saveFileInput.toString());

                    // TODO See to putting these lines somewhere better. They might not execute if an exception is thrown.
                    writer.close();
                    outputStream.close();
                }
                catch (IOException e)
                {
                    Toast.makeText(context, "SAVE FAILED", Toast.LENGTH_SHORT);
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
}
