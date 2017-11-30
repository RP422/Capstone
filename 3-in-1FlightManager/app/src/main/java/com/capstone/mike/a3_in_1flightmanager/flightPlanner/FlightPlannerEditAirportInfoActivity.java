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

public class FlightPlannerEditAirportInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_edit_airport_info);

        Intent data = getIntent();
        try
        {
            if(data.hasExtra("AIRPORT_INFO"))
            {
                JSONObject json = new JSONObject(data.getStringExtra("AIRPORT_INFO"));

//                departureATIS
//                departureWind
//                departureVisibility
//                departureCeiling
//                departureAltimeter
//                departureApproach
//                departureRunway

                if(json.has("departureATIS"))
                {
                    EditText departureATIS = (EditText) findViewById(R.id.departureATIS);
                    departureATIS.setText((String)json.get("departureATIS"));
                }
                if(json.has("departureWind"))
                {
                    EditText departureWind = (EditText) findViewById(R.id.departureWind);
                    departureWind.setText((String)json.get("departureWind"));
                }
                if(json.has("departureVisibility"))
                {
                    EditText departureVisibility = (EditText) findViewById(R.id.departureVisibility);
                    departureVisibility.setText((String)json.get("departureVisibility"));
                }
                if(json.has("departureCeiling"))
                {
                    EditText departureCeiling = (EditText) findViewById(R.id.departureCeiling);
                    departureCeiling.setText((String)json.get("departureCeiling"));
                }
                if(json.has("departureAltimeter"))
                {
                    EditText departureAltimeter = (EditText) findViewById(R.id.departureAltimeter);
                    departureAltimeter.setText((String)json.get("departureAltimeter"));
                }
                if(json.has("departureApproach"))
                {
                    EditText departureApproach = (EditText) findViewById(R.id.departureApproach);
                    departureApproach.setText((String)json.get("departureApproach"));
                }
                if(json.has("departureRunway"))
                {
                    EditText departureRunway = (EditText) findViewById(R.id.departureRunway);
                    departureRunway.setText((String)json.get("departureRunway"));
                }
                if(json.has("departureATIS"))
                {
                    EditText departureATIS = (EditText) findViewById(R.id.departureATIS);
                    departureATIS.setText((String)json.get("departureATIS"));
                }
                if(json.has("departureWind"))
                {
                    EditText departureWind = (EditText) findViewById(R.id.departureWind);
                    departureWind.setText((String)json.get("departureWind"));
                }
                if(json.has("departureVisibility"))
                {
                    EditText departureVisibility = (EditText) findViewById(R.id.departureVisibility);
                    departureVisibility.setText((String)json.get("departureVisibility"));
                }
                if(json.has("departureCeiling"))
                {
                    EditText departureCeiling = (EditText) findViewById(R.id.departureCeiling);
                    departureCeiling.setText((String)json.get("departureCeiling"));
                }
                if(json.has("departureAltimeter"))
                {
                    EditText departureAltimeter = (EditText) findViewById(R.id.departureAltimeter);
                    departureAltimeter.setText((String)json.get("departureAltimeter"));
                }
                if(json.has("departureApproach"))
                {
                    EditText departureApproach = (EditText) findViewById(R.id.departureApproach);
                    departureApproach.setText((String)json.get("departureApproach"));
                }
                if(json.has("departureRunway"))
                {
                    EditText departureRunway = (EditText) findViewById(R.id.departureRunway);
                    departureRunway.setText((String)json.get("departureRunway"));
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


    public void saveAirportInfo(View view)
    {
        JSONObject json = new JSONObject();
        Intent intent = getIntent();

        try
        {
            EditText departureATIS = (EditText) findViewById(R.id.departureATIS);
            if(!departureATIS.getText().toString().isEmpty())
            {
                json.put("departureATIS", departureATIS.getText().toString());
            }

            EditText departureWind = (EditText) findViewById(R.id.departureWind);
            if(!departureWind.getText().toString().isEmpty())
            {
                json.put("departureWind", departureWind.getText().toString());
            }

            EditText departureVisibility = (EditText) findViewById(R.id.departureVisibility);
            if(!departureVisibility.getText().toString().isEmpty())
            {
                json.put("departureVisibility", departureVisibility.getText().toString());
            }

            EditText departureCeiling = (EditText) findViewById(R.id.departureCeiling);
            if(!departureCeiling.getText().toString().isEmpty())
            {
                json.put("departureCeiling", departureCeiling.getText().toString());
            }

            EditText departureAltimeter = (EditText) findViewById(R.id.departureAltimeter);
            if(!departureAltimeter.getText().toString().isEmpty())
            {
                json.put("departureAltimeter", departureAltimeter.getText().toString());
            }

            EditText departureApproach = (EditText) findViewById(R.id.departureApproach);
            if(!departureApproach.getText().toString().isEmpty())
            {
                json.put("departureApproach", departureApproach.getText().toString());
            }

            EditText departureRunway = (EditText) findViewById(R.id.departureRunway);
            if(!departureRunway.getText().toString().isEmpty())
            {
                json.put("departureRunway", departureRunway.getText().toString());
            }

            EditText destinationATIS = (EditText) findViewById(R.id.destinationATIS);
            if(!destinationATIS.getText().toString().isEmpty())
            {
                json.put("destinationATIS", destinationATIS.getText().toString());
            }

            EditText destinationWind = (EditText) findViewById(R.id.destinationWind);
            if(!destinationWind.getText().toString().isEmpty())
            {
                json.put("destinationWind", destinationWind.getText().toString());
            }

            EditText destinationVisibility = (EditText) findViewById(R.id.destinationVisibility);
            if(!destinationVisibility.getText().toString().isEmpty())
            {
                json.put("destinationVisibility", destinationVisibility.getText().toString());
            }

            EditText destinationCeiling = (EditText) findViewById(R.id.destinationCeiling);
            if(!destinationCeiling.getText().toString().isEmpty())
            {
                json.put("destinationCeiling", destinationCeiling.getText().toString());
            }

            EditText destinationAltimeter = (EditText) findViewById(R.id.destinationAltemiter);
            if(!destinationAltimeter.getText().toString().isEmpty())
            {
                json.put("destinationAltimeter", destinationAltimeter.getText().toString());
            }

            EditText destinationApproach = (EditText) findViewById(R.id.destinationApproach);
            if(!destinationApproach.getText().toString().isEmpty())
            {
                json.put("destinationApproach", destinationApproach.getText().toString());
            }

            EditText destinationRunway = (EditText) findViewById(R.id.destinationRunway);
            if(!destinationRunway.getText().toString().isEmpty())
            {
                json.put("destinationRunway", destinationRunway.getText().toString());
            }

            intent.putExtra("AIRPORT_INFO", json.toString());

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
