package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;
import com.capstone.mike.a3_in_1flightmanager.common.JSONSchema;
import com.capstone.mike.a3_in_1flightmanager.common.PopupBuilder;

public class ChecklistMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_main);
    }

    public void selectAndLaunchChecklist(View view)
    {
        final Context context = this;

        DBHandler db = DBHandler.getInstance(context);
        final String[] files = db.getFilesOfSchema(JSONSchema.CHECKLIST);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Which list do you want?");
        builder.setItems(files, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, ChecklistRunthroughActivity.class);
                intent.putExtra("FILE", files[which]);
                context.startActivity(intent);
            }
        });
        builder.show();
    }

    public void editChecklist(View view)
    {
        Intent intent = new Intent(this, ChecklistRunthroughActivity.class);
        final Context context = this;

        DBHandler db = DBHandler.getInstance(context);
        final String[] files = db.getFilesOfSchema(JSONSchema.CHECKLIST);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Which list do you want to edit?");
        builder.setItems(files, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, CreateChecklistActivity.class);
                intent.putExtra("FILE", files[which]);
                context.startActivity(intent);
            }
        });
        builder.show();
    }

    public void createNewChecklist(View view)
    {
        Intent intent = new Intent(this, CreateChecklistActivity.class);
        startActivity(intent);
    }

    public void deleteChecklist(View view)
    {
        final Context context = this;

        DBHandler db = DBHandler.getInstance(context);
        final String[] files = db.getFilesOfSchema(JSONSchema.CHECKLIST);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Which list do you want to edit?");
        builder.setItems(files, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHandler db = DBHandler.getInstance(context);
                db.deleteJSON(files[which]);
                Toast.makeText(context, "Checklist deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
