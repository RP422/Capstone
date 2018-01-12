package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;
import com.capstone.mike.a3_in_1flightmanager.common.JSONSchema;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightPlannerMainActivity extends AppCompatActivity
{
    final FirebaseDatabase fb = FirebaseDatabase.getInstance();
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_main);
    }

    public void newPlan(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditActivity.class);
        startActivity(intent);
    }

    public void viewPlan(View view)
    {
        final Context context = this;

        DBHandler db = DBHandler.getInstance(context);
        final String[] files = db.getFilesOfSchema(JSONSchema.FLIGHT_PLAN);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Which flight plan do you want?");
        builder.setItems(files, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, FlightPlannerEditActivity.class);
                intent.putExtra("FILE", files[which]);
                context.startActivity(intent);
            }
        });
        builder.show();
    }
    // TODO: Add a button for this function
    public void viewFBPlan(View view)
    {
        final DatabaseReference ref = fb.getReference();

        Toast.makeText(this, "Sorry, this is going to take a bit. Please be patient.", Toast.LENGTH_LONG).show();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Gather all the flight plan names
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                final ArrayList<String> planNames = new ArrayList<>();

                final DataSnapshot snapshot = dataSnapshot;

                for(DataSnapshot child : children)
                {
                    planNames.add(child.getKey());
                }
                final String[] files = planNames.toArray(new String[] {});

                // TODO: Pop up the list of flight plans
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Which flight plan do you want?");
                builder.setItems(files, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: Get the flight plan data
                        String chosenPlan = files[which];
                        DataSnapshot planSnapshot = snapshot.child(chosenPlan);
                        String data = planSnapshot.getValue(String.class);

                        Intent intent = new Intent(context, FlightPlannerEditActivity.class);
                        intent.putExtra("FBFILE", data);
                        intent.putExtra("FILE_NAME", chosenPlan);
                        context.startActivity(intent);
                    }
                });
                builder.show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "The read failed: " + databaseError.getCode(), Toast.LENGTH_LONG);
            }
        });
    }

    public void deletePlan(View view)
    {
        final Context context = this;

        DBHandler db = DBHandler.getInstance(context);
        final String[] files = db.getFilesOfSchema(JSONSchema.FLIGHT_PLAN);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Which flight plan do you want to delete?");
        builder.setItems(files, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHandler db = DBHandler.getInstance(context);
                db.deleteJSON(files[which]);
                Toast.makeText(context, "Flight Plan deleted.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
