package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.capstone.mike.a3_in_1flightmanager.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FlightPlannerEditPlaneInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_edit_plane_info);

        // TODO Grab JSON from the intent and populate the fields where relevant
        Intent data = getIntent();

        try
        {
            if(data.hasExtra("PLANE_INFO"))
            {
                JSONObject json = new JSONObject((String)data.getStringExtra("PLANE_INFO"));

//                planeModel
                if(json.has("planeModel"))
                {
                    EditText planeModel = (EditText) findViewById(R.id.planeModelET);
                    planeModel.setText((String)json.get("planeModel"));
                }

//                fuelGPH
                if(json.has("fuelGPH"))
                {
                    EditText fuelGPH = (EditText) findViewById(R.id.fuelUseET);
                    fuelGPH.setText((String)json.get("fuelGPH"));
                }

//                takeoffWeight
                if(json.has("takeoffWeight"))
                {
                    EditText takeoffWeight = (EditText) findViewById(R.id.takeoffWeight);
                    takeoffWeight.setText((String)json.get("takeoffWeight"));
                }

//                maxWeight
                if(json.has("maxWeight"))
                {
                    EditText maxWeight = (EditText) findViewById(R.id.maxWeight);
                    maxWeight.setText((String)json.get("maxWeight"));
                }

//                landingWeight
                if(json.has("landingWeight"))
                {
                    EditText landingWeight = (EditText) findViewById(R.id.landingWeight);
                    landingWeight.setText((String)json.get("landingWeight"));
                }

//                takeoffMoment
                if(json.has("takeoffMoment"))
                {
                    EditText takeoffMoment = (EditText) findViewById(R.id.takeoffMoment);
                    takeoffMoment.setText((String)json.get("takeoffMoment"));
                }

//                landingMoment
                if(json.has("landingMoment"))
                {
                    EditText landingMoment = (EditText) findViewById(R.id.landingMoment);
                    landingMoment.setText((String)json.get("landingMoment"));
                }

//                takeoffGroundRoll
                if(json.has("takeoffGroundRoll"))
                {
                    EditText takeoffGroundRoll = (EditText) findViewById(R.id.takeoffGroundRoll);
                    takeoffGroundRoll.setText((String)json.get("takeoffGroundRoll"));
                }

//                maxGroundRoll
                if(json.has("departureATIS"))
                {
                    EditText maxGroundRoll = (EditText) findViewById(R.id.maxGroundRoll);
                    maxGroundRoll.setText((String)json.get("maxGroundRoll"));
                }

//                landingGroundRoll
                if(json.has("landingGroundRoll"))
                {
                    EditText landingGroundRoll = (EditText) findViewById(R.id.landingGroundRoll);
                    landingGroundRoll.setText((String)json.get("landingGroundRoll"));
                }

//                takeoffDistanceClear
                if(json.has("takeoffDistanceClear"))
                {
                    EditText takeoffDistanceClear = (EditText) findViewById(R.id.takeoffDistanceClear);
                    takeoffDistanceClear.setText((String)json.get("takeoffDistanceClear"));
                }

//                maxDistanceClear
                if(json.has("maxDistanceClear"))
                {
                    EditText maxDistanceClear = (EditText) findViewById(R.id.maxDistanceClear);
                    maxDistanceClear.setText((String)json.get("maxDistanceClear"));
                }

//                landingDistanceClear
                if(json.has("landingDistanceClear"))
                {
                    EditText landingDistanceClear = (EditText) findViewById(R.id.landingDistanceClear);
                    landingDistanceClear.setText((String)json.get("landingDistanceClear"));
                }

//                takeoffRunwayLength
                if(json.has("takeoffRunwayLength"))
                {
                    EditText takeoffRunwayLength = (EditText) findViewById(R.id.takeoffRunwayLength);
                    takeoffRunwayLength.setText((String)json.get("takeoffRunwayLength"));
                }

//                maxRunwayLength
                if(json.has("departureATIS"))
                {
                    EditText departureATIS = (EditText) findViewById(R.id.departureATIS);
                    departureATIS.setText((String)json.get("departureATIS"));
                }

//                landingRunwayLength
                if(json.has("landingRunwayLength"))
                {
                    EditText landingRunwayLength = (EditText) findViewById(R.id.landingRunwayLength);
                    landingRunwayLength.setText((String)json.get("landingRunwayLength"));
                }

            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void savePlaneInfo(View view)
    {
        Intent intent = getIntent();
        JSONObject json = new JSONObject();

        try {
            EditText planeModel = (EditText)findViewById(R.id.planeModelET);
            if(!planeModel.getText().toString().isEmpty())
            {
                json.put("model", planeModel.getText().toString());
            }

            EditText fuelGPH = (EditText)findViewById(R.id.fuelUseET);
            if(!fuelGPH.getText().toString().isEmpty())
            {
                json.put("gph", Double.parseDouble(fuelGPH.getText().toString()));
            }

            EditText takeoffWeight = (EditText)findViewById(R.id.takeoffWeight);
            if(!takeoffWeight.getText().toString().isEmpty())
            {
                json.put("takeoffWeight", Integer.parseInt(takeoffWeight.getText().toString()));
            }

            EditText maxWeight = (EditText)findViewById(R.id.maxWeight);
            if(!maxWeight.getText().toString().isEmpty())
            {
                json.put("maxWeight", Integer.parseInt(maxWeight.getText().toString()));
            }

            EditText landingWeight = (EditText)findViewById(R.id.landingWeight);
            if(!landingWeight.getText().toString().isEmpty())
            {
                json.put("landingWeight", Integer.parseInt(landingWeight.getText().toString()));
            }

            EditText takeoffMoment = (EditText)findViewById(R.id.takeoffMoment);
            if(!takeoffMoment.getText().toString().isEmpty())
            {
                json.put("takeoffMoment", Integer.parseInt(takeoffMoment.getText().toString()));
            }

            EditText landingMoment = (EditText)findViewById(R.id.landingMoment);
            if(!landingMoment.getText().toString().isEmpty())
            {
                json.put("landingMoment", Integer.parseInt(landingMoment.getText().toString()));
            }

            EditText takeoffGroundRoll = (EditText)findViewById(R.id.takeoffGroundRoll);
            if(!takeoffGroundRoll.getText().toString().isEmpty())
            {
                json.put("takeoffGroundRoll", Integer.parseInt(takeoffGroundRoll.getText().toString()));
            }

            EditText maxGroundRoll = (EditText)findViewById(R.id.maxGroundRoll);
            if(!maxGroundRoll.getText().toString().isEmpty())
            {
                json.put("maxGroundRoll", Integer.parseInt(maxGroundRoll.getText().toString()));
            }

            EditText landingGroundRoll = (EditText)findViewById(R.id.landingGroundRoll);
            if(!landingGroundRoll.getText().toString().isEmpty())
            {
                json.put("landingGroundRoll", Integer.parseInt(landingGroundRoll.getText().toString()));
            }

            EditText takeoffDistanceClear = (EditText)findViewById(R.id.takeoffDistanceClear);
            if(!takeoffDistanceClear.getText().toString().isEmpty())
            {
                json.put("takeoffDistanceClear", Integer.parseInt(takeoffDistanceClear.getText().toString()));
            }

            EditText maxDistanceClear = (EditText)findViewById(R.id.maxDistanceClear);
            if(!maxDistanceClear.getText().toString().isEmpty())
            {
                json.put("maxDistanceClear", Integer.parseInt(maxDistanceClear.getText().toString()));
            }

            EditText landingDistanceClear = (EditText)findViewById(R.id.landingDistanceClear);
            if(!landingDistanceClear.getText().toString().isEmpty())
            {
                json.put("landingDistanceClear", Integer.parseInt(landingDistanceClear.getText().toString()));
            }

            EditText takeoffRunwayLength = (EditText)findViewById(R.id.takeoffRunwayLength);
            if(!takeoffRunwayLength.getText().toString().isEmpty())
            {
                json.put("takeoffRunwayLength", Integer.parseInt(takeoffRunwayLength.getText().toString()));
            }

            EditText maxRunwayLength = (EditText)findViewById(R.id.maxRunwayLength);
            if(!maxRunwayLength.getText().toString().isEmpty())
            {
                json.put("maxRunwayLength", Integer.parseInt(maxRunwayLength.getText().toString()));
            }

            EditText landingRunwayLength = (EditText)findViewById(R.id.landingRunwayLength);
            if(!landingRunwayLength.getText().toString().isEmpty())
            {
                json.put("landingRunwayLength", Integer.parseInt(landingRunwayLength.getText().toString()));
            }

            intent.putExtra("PLANE_INFO", json.toString());

            if(getParent() == null)
            {
                setResult(Activity.RESULT_OK, intent);
            }
            else
            {
                getParent().setResult(Activity.RESULT_OK, intent);
            }

            finish();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
