package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;
import com.capstone.mike.a3_in_1flightmanager.common.JSONSchema;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightPlannerEditActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_NEW_ROW = 0;
    private static final int REQUEST_CODE_EDIT_ROW = 1;
    private static final int REQUEST_CODE_PLANE_INFO = 2;
    private static final int REQUEST_CODE_AIRPORT_INFO = 3;

    private int rowBeingEdited = -1;

    private ListView stepsList;
    private FlightPlan flightPlan;

    private JSONObject json = new JSONObject();

    private boolean editing = false;
    String file = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_edit);

        Intent data = getIntent();

        stepsList = (ListView)findViewById(R.id.flightPlanSteps);
        boolean testing = data.getBooleanExtra("TEST", false);

        flightPlan = new FlightPlan();

        if(testing)
        {
            flightPlan.setFuelRate(11.5f);
            flightPlan.add(new FlightPlanStep("TOC 1", 265, 10, 3000, 90, 36, 15, 13, 0, null, null));
            flightPlan.add(new FlightPlanStep("Turn 1", 275, 40, 3000, 110, 36, 15, 13, 0, null, null));
            flightPlan.add(new FlightPlanStep("TOC 2", 272, 15, 6500, 90, 1, 10, 13, 0, null, null));
            flightPlan.add(new FlightPlanStep("TOD", 272, 182, 6500, 110, 1, 10, 14, 0, null, null));
            flightPlan.add(new FlightPlanStep("AGC", 276, 12, 2200, 110, 1, 10, 14, 0, null, null));

            refreshAdapter();
        }
        else if(data.hasExtra("FILE"))
        {
            editing = true;
            file = data.getStringExtra("FILE");

            DBHandler db = DBHandler.getInstance(this);
            JSONObject json = db.getJSONfromReferenceName(file);

            try
            {
                JSONArray steps = json.getJSONArray("steps");

                for(int x = 0; x < steps.length(); x++)
                {
                    JSONObject jsonStep = steps.getJSONObject(x);

                    String checkpointName = jsonStep.getString("checkpointName");
                    int course = jsonStep.getInt("course");
                    int legDistance = jsonStep.getInt("legDistance");
                    int altitude = jsonStep.getInt("altitude");
                    int tas = jsonStep.getInt("tas");
                    int windDir = jsonStep.getInt("windDir");
                    int windSpeed = jsonStep.getInt("windSpeed");
                    int headAdjust = jsonStep.getInt("headAdjust");
                    int magHeadAdjust = jsonStep.getInt("magHeadAdjust");

                    Float freq = null;
                    String ident = null;

                    if(jsonStep.has("freq"))
                    {
                        freq = (float)jsonStep.getDouble("freq");
                    }
                    if(jsonStep.has("ident"))
                    {
                        ident = jsonStep.getString("ident");
                    }

                    flightPlan.add(new FlightPlanStep(checkpointName, course, legDistance, altitude, tas, windDir, windSpeed, headAdjust, magHeadAdjust, freq, ident));
                }

                if(json.has("planeInfo"))
                {
                    JSONObject planeInfo = json.getJSONObject("planeInfo");
                    if(planeInfo.has("gph"))
                    {
                        flightPlan.setFuelRate((float)planeInfo.getDouble("gph"));

                        TextView totalFuelTV = (TextView)findViewById(R.id.totalFuelTV);
                        if(flightPlan.getTotalFuel() > 0)
                        {
                            String fuelBlurb = String.format("%.2f gal", flightPlan.getTotalFuel());
                            totalFuelTV.setText(fuelBlurb);
                        }
                        else
                        {
                            totalFuelTV.setText("N/A");
                        }
                    }
                }
                if(json.has("airportInfo"))
                {
                    JSONObject airportInfo = json.getJSONObject("airportInfo");
                    if(airportInfo.has("departureTPA"))
                    {
                        flightPlan.setStartingAltitude(airportInfo.getInt("departureTPA"));
                    }
                }


                this.json = json;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(this, "There was an error loading the Flight Plan", Toast.LENGTH_LONG).show();
                finish();
            }

            refreshAdapter();
        }
        else if(data.hasExtra("FBFILE"))
        {
            editing = true;
            String rawJSON = data.getStringExtra("FBFILE");
            JSONObject json = null;

            try
            {
                json = new JSONObject(rawJSON);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            file = data.getStringExtra("FILE_NAME");

            try
            {
                JSONArray steps = json.getJSONArray("steps");

                for(int x = 0; x < steps.length(); x++)
                {
                    JSONObject jsonStep = steps.getJSONObject(x);

                    String checkpointName = jsonStep.getString("checkpointName");
                    int course = jsonStep.getInt("course");
                    int legDistance = jsonStep.getInt("legDistance");
                    int altitude = jsonStep.getInt("altitude");
                    int tas = jsonStep.getInt("tas");
                    int windDir = jsonStep.getInt("windDir");
                    int windSpeed = jsonStep.getInt("windSpeed");
                    int headAdjust = jsonStep.getInt("headAdjust");
                    int magHeadAdjust = jsonStep.getInt("magHeadAdjust");

                    Float freq = null;
                    String ident = null;

                    if(jsonStep.has("freq"))
                    {
                        freq = (float)jsonStep.getDouble("freq");
                    }
                    if(jsonStep.has("ident"))
                    {
                        ident = jsonStep.getString("ident");
                    }

                    flightPlan.add(new FlightPlanStep(checkpointName, course, legDistance, altitude, tas, windDir, windSpeed, headAdjust, magHeadAdjust, freq, ident));
                }

                if(json.has("planeInfo"))
                {
                    JSONObject planeInfo = json.getJSONObject("planeInfo");
                    if(planeInfo.has("gph"))
                    {
                        flightPlan.setFuelRate((float)planeInfo.getDouble("gph"));

                        TextView totalFuelTV = (TextView)findViewById(R.id.totalFuelTV);
                        if(flightPlan.getTotalFuel() > 0)
                        {
                            String fuelBlurb = String.format("%.2f gal", flightPlan.getTotalFuel());
                            totalFuelTV.setText(fuelBlurb);
                        }
                        else
                        {
                            totalFuelTV.setText("N/A");
                        }
                    }
                }
                if(json.has("airportInfo"))
                {
                    JSONObject airportInfo = json.getJSONObject("airportInfo");
                    if(airportInfo.has("departureTPA"))
                    {
                        flightPlan.setStartingAltitude(airportInfo.getInt("departureTPA"));
                    }
                }


                this.json = json;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(this, "There was an error loading the Flight Plan", Toast.LENGTH_LONG).show();
                finish();
            }

            refreshAdapter();
        }
    }

    private void refreshAdapter()
    {
        stepsList.setAdapter(new FlightPlannerEditArrayAdapter(this, flightPlan.toArray()));
    }

    public void addRow(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditNewRowActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NEW_ROW);
    }
    public void editRow(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditNewRowActivity.class);

        ConstraintLayout layout = (ConstraintLayout)view.getParent();
        TextView rowIndicator = layout.findViewById(R.id.rowNumTV);

        rowBeingEdited = Integer.parseInt((String)rowIndicator.getText());

        try
        {
            String editJSON = flightPlan.get(rowBeingEdited).toJSON().toString();
            intent.putExtra("EDIT_DATA", editJSON.toString());
            startActivityForResult(intent, REQUEST_CODE_EDIT_ROW);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Whoops. Something went wrong there.", Toast.LENGTH_LONG).show();
        }

    }
    public void deleteRow(View view)
    {
        ConstraintLayout layout = (ConstraintLayout)view.getParent();
        TextView rowIndicator = layout.findViewById(R.id.rowNumTV);

        int rowToDelete = Integer.parseInt((String)rowIndicator.getText());
        flightPlan.remove(rowToDelete);

        refreshAdapter();
    }

    public void editPlaneInfo(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditPlaneInfoActivity.class);

        if(json.has("planeInfo"))
        {
            try
            {
                intent.putExtra("PLANE_INFO", json.getString("planeInfo"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        startActivityForResult(intent, REQUEST_CODE_PLANE_INFO);
    }
    public void editAirportInfo(View view)
    {
        Intent intent = new Intent(this, FlightPlannerEditAirportInfoActivity.class);

        if(json.has("airportInfo"))
        {
            try
            {
                intent.putExtra("AIRPORT_INFO", json.getString("airportInfo"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        startActivityForResult(intent, REQUEST_CODE_AIRPORT_INFO);
    }

    public void saveFlightPlan(View view)
    {
        try
        {
            if(json.has("steps"))
            {
                json.remove("steps");
            }
            json.put("steps", flightPlan.toJSONArray());

            if(editing)
            {
                DBHandler db = DBHandler.getInstance(this);
                Intent data = getIntent();

                db.updateJSON(file, JSONSchema.FLIGHT_PLAN, json);
                finish();
                Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show();
            }
            else
            {
                final Context context = this;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("What do you want to name the Flight Plan?");

                final JSONObject jsonToSave = json;

                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        DBHandler db = DBHandler.getInstance(context);

                        // TODO NOTIM: Look into ways to get this box to stay open in case of bad input?
                        if(!input.getText().toString().contains("\"") && !db.insertJSON(input.getText().toString(), JSONSchema.FLIGHT_PLAN, jsonToSave))
                        {
                            Toast.makeText(context, "Save failed: that name is already in use", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            finish();
                            Toast.makeText(context, "Save Successful", Toast.LENGTH_SHORT).show();
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
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    // TODO: Add a button for this function
    public void saveFlightPlanFB(View view)
    {
        try
        {
            if(json.has("steps"))
            {
                json.remove("steps");
            }
            json.put("steps", flightPlan.toJSONArray());

            if(editing)
            {
                Intent data = getIntent();
                saveToFB(file);

                finish();
                Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show();
            }
            else
            {
                final Context context = this;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("What do you want to name the Flight Plan?");

                final JSONObject jsonToSave = json;

                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        saveToFB(input.getText().toString());

                        dialog.dismiss();
                        finish();
                        Toast.makeText(context, "Save Successful", Toast.LENGTH_SHORT).show();
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
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    public void saveToFB(String fileName)
    {
        // TODO: Add relevant Firebase Code relevant

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
        dataRef.child(fileName).setValue(json.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE_NEW_ROW)
        {
            if(resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject newCheckpoint = new JSONObject(data.getStringExtra("CHECKPOINT_INFO"));

                    String checkpointName = newCheckpoint.getString("checkpointName");
                    int course = newCheckpoint.getInt("course");
                    int legDistance = newCheckpoint.getInt("legDistance");
                    int altitude = newCheckpoint.getInt("altitude");
                    int tas = newCheckpoint.getInt("tas");
                    int windDir = newCheckpoint.getInt("windDir");
                    int windSpeed = newCheckpoint.getInt("windSpeed");
                    int headAdjust = newCheckpoint.getInt("headAdjust");
                    int magHeadAdjust = newCheckpoint.getInt("magHeadAdjust");

                    Float freq = null;
                    String ident = null;

                    if (newCheckpoint.has("freq"))
                    {
                        freq = (float) newCheckpoint.getDouble("freq");
                    }
                    if (newCheckpoint.has("ident"))
                    {
                        ident = newCheckpoint.getString("ident");
                    }

                    flightPlan.add(new FlightPlanStep(checkpointName, course, legDistance, altitude, tas, windDir, windSpeed, headAdjust, magHeadAdjust, freq, ident));

                    TextView totalFuelTV = (TextView)findViewById(R.id.totalFuelTV);
                    if(flightPlan.getTotalFuel() > 0)
                    {
                        String fuelBlurb = String.format("%.2f gal", flightPlan.getTotalFuel());
                        totalFuelTV.setText(fuelBlurb);
                    }
                    else
                    {
                        totalFuelTV.setText("N/A");
                    }

                    refreshAdapter();
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(requestCode == REQUEST_CODE_EDIT_ROW)
        {
            if(resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject newCheckpoint = new JSONObject(data.getStringExtra("CHECKPOINT_INFO"));

                    String checkpointName = newCheckpoint.getString("checkpointName");
                    int course = newCheckpoint.getInt("course");
                    int legDistance = newCheckpoint.getInt("legDistance");
                    int altitude = newCheckpoint.getInt("altitude");
                    int tas = newCheckpoint.getInt("tas");
                    int windDir = newCheckpoint.getInt("windDir");
                    int windSpeed = newCheckpoint.getInt("windSpeed");
                    int headAdjust = newCheckpoint.getInt("headAdjust");
                    int magHeadAdjust = newCheckpoint.getInt("magHeadAdjust");

                    Float freq = null;
                    String ident = null;

                    if (newCheckpoint.has("freq"))
                    {
                        freq = (float) newCheckpoint.getDouble("freq");
                    }
                    if (newCheckpoint.has("ident"))
                    {
                        ident = newCheckpoint.getString("ident");
                    }

                    FlightPlanStep updatedStep = new FlightPlanStep(checkpointName, course, legDistance, altitude, tas, windDir, windSpeed, headAdjust, magHeadAdjust, freq, ident);

                    flightPlan.remove(rowBeingEdited);
                    flightPlan.add(rowBeingEdited, updatedStep);

                    TextView totalFuelTV = (TextView)findViewById(R.id.totalFuelTV);
                    if(flightPlan.getTotalFuel() > 0)
                    {
                        String fuelBlurb = String.format("%.2f gal", flightPlan.getTotalFuel());
                        totalFuelTV.setText(fuelBlurb);
                    }
                    else
                    {
                        totalFuelTV.setText("N/A");
                    }

                    refreshAdapter();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == REQUEST_CODE_PLANE_INFO)
        {
            if(resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject planeInfo = new JSONObject(data.getStringExtra("PLANE_INFO"));
                    json.put("planeInfo", planeInfo);

                    if(planeInfo.has("gph"))
                    {
                        flightPlan.setFuelRate((float)planeInfo.getDouble("gph"));

                        TextView totalFuelTV = (TextView)findViewById(R.id.totalFuelTV);
                        if(flightPlan.getTotalFuel() > 0)
                        {
                            String fuelBlurb = String.format("%.2f gal", flightPlan.getTotalFuel());
                            totalFuelTV.setText(fuelBlurb);
                        }
                        else
                        {
                            totalFuelTV.setText("N/A");
                        }
                        refreshAdapter();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == REQUEST_CODE_AIRPORT_INFO)
        {
            if(resultCode == RESULT_OK)
            {
                try
                {
                    JSONObject airportInfo = new JSONObject(data.getStringExtra("AIRPORT_INFO"));
                    json.put("airportInfo", airportInfo);

                    if(airportInfo.has("departureTPA"))
                    {
                        flightPlan.setStartingAltitude(airportInfo.getInt("departureTPA"));
                        refreshAdapter();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
