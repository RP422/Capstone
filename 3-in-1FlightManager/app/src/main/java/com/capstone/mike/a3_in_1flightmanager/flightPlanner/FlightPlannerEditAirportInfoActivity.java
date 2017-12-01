package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FlightPlannerEditAirportInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_edit_airport_info);

        Intent data = getIntent();

        if(data.hasExtra("AIRPORT_INFO"))
        {
            try
            {
                JSONObject json = new JSONObject(data.getStringExtra("AIRPORT_INFO"));

                if(json.has("departureAirport"))
                {
                    EditText departureAirport = (EditText) findViewById(R.id.departureAirport);
                    departureAirport.setText(json.getString("departureAirport"));
                }
                if(json.has("departureATIS"))
                {
                    EditText departureATIS = (EditText) findViewById(R.id.departureATIS);
                    departureATIS.setText(json.getString("departureATIS"));
                }
                if(json.has("departureWind"))
                {
                    EditText departureWind = (EditText) findViewById(R.id.departureWind);
                    departureWind.setText(json.getString("departureWind"));
                }
                if(json.has("departureVisibility"))
                {
                    EditText departureVisibility = (EditText) findViewById(R.id.departureVisibility);
                    departureVisibility.setText(json.getString("departureVisibility"));
                }
                if(json.has("departureCeiling"))
                {
                    EditText departureCeiling = (EditText) findViewById(R.id.departureCeiling);
                    departureCeiling.setText(json.getString("departureCeiling"));
                }
                if(json.has("departureAltimeter"))
                {
                    EditText departureAltimeter = (EditText) findViewById(R.id.departureAltimeter);
                    departureAltimeter.setText(json.getString("departureAltimeter"));
                }
                if(json.has("departureApproach"))
                {
                    EditText departureApproach = (EditText) findViewById(R.id.departureApproach);
                    departureApproach.setText(json.getString("departureApproach"));
                }
                if(json.has("departureRunway"))
                {
                    EditText departureRunway = (EditText) findViewById(R.id.departureRunway);
                    departureRunway.setText(json.getString("departureRunway"));
                }
                if(json.has("departureGrnd"))
                {
                    EditText departureGrnd = (EditText) findViewById(R.id.departureGrnd);
                    departureGrnd.setText(json.getString("departureGrnd"));
                }
                if(json.has("departureFSS"))
                {
                    EditText departureFSS = (EditText) findViewById(R.id.departureFSS);
                    departureFSS.setText(json.getString("departureFSS"));
                }
                if(json.has("departureTower"))
                {
                    EditText departureTower = (EditText) findViewById(R.id.departureTower);
                    departureTower.setText(json.getString("departureTower"));
                }
                if(json.has("departureDep"))
                {
                    EditText departureDep = (EditText) findViewById(R.id.departureDep);
                    departureDep.setText(json.getString("departureDep"));
                }
                if(json.has("departureCTAF"))
                {
                    EditText departureCTAF = (EditText) findViewById(R.id.departureCTAF);
                    departureCTAF.setText(json.getString("departureCTAF"));
                }
                if(json.has("departureTPA"))
                {
                    EditText departureTPA = (EditText) findViewById(R.id.departureTPA);
                    departureTPA.setText(json.getString("departureTPA"));
                }
                if(json.has("departureFieldElev"))
                {
                    EditText departureFieldElev = (EditText) findViewById(R.id.departureFieldElev);
                    departureFieldElev.setText(json.getString("departureFieldElev"));
                }

                if(json.has("destinationAirport"))
                {
                    EditText destinationAirport = (EditText) findViewById(R.id.destinationAirport);
                    destinationAirport.setText(json.getString("destinationAirport"));
                }
                if(json.has("destinationATIS"))
                {
                    EditText destinationATIS = (EditText) findViewById(R.id.destinationATIS);
                    destinationATIS.setText(json.getString("destinationATIS"));
                }
                if(json.has("destinationWind"))
                {
                    EditText destinationWind = (EditText) findViewById(R.id.destinationWind);
                    destinationWind.setText(json.getString("destinationWind"));
                }
                if(json.has("destinationVisibility"))
                {
                    EditText destinationVisibility = (EditText) findViewById(R.id.destinationVisibility);
                    destinationVisibility.setText(json.getString("destinationVisibility"));
                }
                if(json.has("destinationCeiling"))
                {
                    EditText destinationCeiling = (EditText) findViewById(R.id.destinationCeiling);
                    destinationCeiling.setText(json.getString("destinationCeiling"));
                }
                if(json.has("destinationAltimeter"))
                {
                    EditText destinationAltimeter = (EditText) findViewById(R.id.destinationAltemeter);
                    destinationAltimeter.setText(json.getString("destinationAltimeter"));
                }
                if(json.has("destinationApproach"))
                {
                    EditText destinationApproach = (EditText) findViewById(R.id.destinationApproach);
                    destinationApproach.setText(json.getString("destinationApproach"));
                }
                if(json.has("destinationRunway"))
                {
                    EditText destinationRunway = (EditText) findViewById(R.id.destinationRunway);
                    destinationRunway.setText(json.getString("destinationRunway"));
                }
                if(json.has("destinationGrnd"))
                {
                    EditText destinationGrnd = (EditText) findViewById(R.id.destinationGrnd);
                    destinationGrnd.setText(json.getString("destinationGrnd"));
                }
                if(json.has("destinationRunway"))
                {
                    EditText destinationRunway = (EditText) findViewById(R.id.destinationRunway);
                    destinationRunway.setText(json.getString("destinationRunway"));
                }
                if(json.has("destinationFSS"))
                {
                    EditText destinationFSS = (EditText) findViewById(R.id.destinationFSS);
                    destinationFSS.setText(json.getString("destinationFSS"));
                }
                if(json.has("destinationAPP"))
                {
                    EditText destinationAPP = (EditText) findViewById(R.id.destinationAPP);
                    destinationAPP.setText(json.getString("destinationAPP"));
            }
                if(json.has("destinationCTAF"))
                {
                    EditText destinationCTAF = (EditText) findViewById(R.id.destinationCTAF);
                    destinationCTAF.setText(json.getString("destinationCTAF"));
                }
                if(json.has("destinationTPA"))
                {
                    EditText destinationTPA = (EditText) findViewById(R.id.destinationTPA);
                    destinationTPA.setText(json.getString("destinationTPA"));
                }
                if(json.has("destinationFieldElev"))
                {
                    EditText destinationFieldElev = (EditText) findViewById(R.id.destinationFieldElev);
                    destinationFieldElev.setText(json.getString("destinationFieldElev"));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(this, "Whoops. Something went wrong.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void saveAirportInfo(View view)
    {
        JSONObject json = new JSONObject();
        Intent intent = getIntent();

        try
        {
            EditText departureAirport = (EditText) findViewById(R.id.departureAirport);
            if(!departureAirport.getText().toString().isEmpty())
            {
                json.put("departureAirport", departureAirport.getText().toString());
            }

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

            EditText departureGrnd = (EditText) findViewById(R.id.departureGrnd);
            if(!departureGrnd.getText().toString().isEmpty())
            {
                json.put("departureGrnd", departureGrnd.getText().toString());
            }

            EditText departureFSS = (EditText) findViewById(R.id.departureFSS);
            if(!departureFSS.getText().toString().isEmpty())
                {
                json.put("departureFSS", departureFSS.getText().toString());
            }

            EditText departureTower = (EditText) findViewById(R.id.departureTower);
            if(!departureTower.getText().toString().isEmpty())
            {
                json.put("departureTower", departureTower.getText().toString());
            }

            EditText departureDep = (EditText) findViewById(R.id.departureDep);
            if(!departureDep.getText().toString().isEmpty())
            {
                json.put("departureDep", departureDep.getText().toString());
            }

            EditText departureCTAF = (EditText) findViewById(R.id.departureCTAF);
            if(!departureCTAF.getText().toString().isEmpty())
            {
                json.put("departureCTAF", departureCTAF.getText().toString());
            }

            EditText departureTPA = (EditText) findViewById(R.id.departureTPA);
            if(!departureTPA.getText().toString().isEmpty())
            {
                json.put("departureTPA", departureTPA.getText().toString());
            }

            EditText departureFieldElev = (EditText) findViewById(R.id.departureFieldElev);
            if(!departureFieldElev.getText().toString().isEmpty())
            {
                json.put("departureFieldElev", departureFieldElev.getText().toString());
            }


            EditText destinationAirport = (EditText) findViewById(R.id.destinationAirport);
            if(!destinationAirport.getText().toString().isEmpty())
            {
                json.put("destinationAirport", destinationAirport.getText().toString());
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

            EditText destinationAltimeter = (EditText) findViewById(R.id.destinationAltemeter);
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

            EditText destinationGrnd = (EditText) findViewById(R.id.destinationGrnd);
            if(!destinationGrnd.getText().toString().isEmpty())
            {
                json.put("destinationGrnd", destinationGrnd.getText().toString());
            }

            EditText destinationFSS = (EditText) findViewById(R.id.destinationFSS);
            if(!destinationFSS.getText().toString().isEmpty())
            {
                json.put("destinationFSS", destinationFSS.getText().toString());
            }

            EditText destinationTower = (EditText) findViewById(R.id.destinationTower);
            if(!destinationTower.getText().toString().isEmpty())
            {
                json.put("destinationTower", destinationTower.getText().toString());
            }

            EditText destinationAPP = (EditText) findViewById(R.id.destinationAPP);
            if(!destinationAPP.getText().toString().isEmpty())
            {
                json.put("destinationAPP", destinationAPP.getText().toString());
            }

            EditText destinationCTAF = (EditText) findViewById(R.id.destinationCTAF);
            if(!destinationCTAF.getText().toString().isEmpty())
            {
                json.put("destinationCTAF", destinationCTAF.getText().toString());
            }

            EditText destinationTPA = (EditText) findViewById(R.id.destinationTPA);
            if(!destinationTPA.getText().toString().isEmpty())
            {
                json.put("destinationTPA", destinationTPA.getText().toString());
            }

            EditText destinationFieldElev = (EditText) findViewById(R.id.destinationFieldElev);
            if(!destinationFieldElev.getText().toString().isEmpty())
            {
                json.put("destinationFieldElev", destinationFieldElev.getText().toString());
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
            Toast.makeText(this, "Save Successful", Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Save Failed", Toast.LENGTH_LONG).show();
        }
    }
}