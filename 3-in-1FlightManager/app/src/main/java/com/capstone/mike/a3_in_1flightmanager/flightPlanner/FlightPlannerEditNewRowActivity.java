package com.capstone.mike.a3_in_1flightmanager.flightPlanner;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;import android.widget.EditText;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FlightPlannerEditNewRowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_planner_edit_new_row);

        Intent data = getIntent();
        if(data.hasExtra("EDIT_DATA"))
        {
            try
            {
                JSONObject json = new JSONObject(data.getStringExtra("EDIT_DATA"));

                if(json.has("checkpointName"))
                {
                    EditText checkpointName = (EditText)findViewById(R.id.newRowCheckpointName);
                    checkpointName.setText(json.getString("checkpointName"));
                }
                if(json.has("course"))
                {
                    EditText course = (EditText)findViewById(R.id.newRowCourse);
                    course.setText(json.getString("course"));
                }
                if(json.has("legDistance"))
                {
                    EditText legDistance = (EditText)findViewById(R.id.newRowLegDistance);
                    legDistance.setText("" + json.getInt("legDistance"));
                }
                if(json.has("altitude"))
                {
                    EditText altitude = (EditText)findViewById(R.id.newRowAltitude);
                    altitude.setText("" + json.getInt("altitude"));
                }
                if(json.has("tas"))
                {
                    EditText tas = (EditText)findViewById(R.id.newRowTAS);
                    tas.setText("" + json.getInt("tas"));
                }
                if(json.has("windDir"))
                {
                    EditText windDir = (EditText)findViewById(R.id.newRowWindDir);
                    windDir.setText("" + json.getInt("windDir"));
                }
                if(json.has("windSpeed"))
                {
                    EditText windSpeed = (EditText)findViewById(R.id.newRowWindSpeed);
                    windSpeed.setText("" + json.getInt("windSpeed"));
                }
                if(json.has("headAdjust"))
                {
                    EditText headAdjust = (EditText)findViewById(R.id.newRowHeadAdjust);
                    headAdjust.setText("" + json.getInt("headAdjust"));
                }
                if(json.has("magHeadAdjust"))
                {
                    EditText magHeadAdjust = (EditText)findViewById(R.id.newRowMagHeadAdjust);
                    magHeadAdjust.setText("" + json.getInt("magHeadAdjust"));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Toast.makeText(this, "Whoops. Something went wrong.", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void saveRow(View view)
    {
        JSONObject json = new JSONObject();

        boolean allReqsFilled = true;

        try
        {
            EditText checkpointName = (EditText)findViewById(R.id.newRowCheckpointName);
            if(!checkpointName.getText().toString().isEmpty())
            {
                json.put("checkpointName", checkpointName.getText().toString());
            }
            else
            {
                allReqsFilled = false;
            }

            EditText freq = (EditText)findViewById(R.id.newRowFreq);
            if(!freq.getText().toString().isEmpty())
            {
                json.put("freq", Integer.parseInt(freq.getText().toString()));
            }

            EditText ident = (EditText)findViewById(R.id.newRowIdent);
            if(!ident.getText().toString().isEmpty())
            {
                json.put("ident", Integer.parseInt(ident.getText().toString()));
            }

            EditText course = (EditText)findViewById(R.id.newRowCourse);
            if(!course.getText().toString().isEmpty())
            {
                json.put("course", Integer.parseInt(course.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }

            EditText legDistance = (EditText)findViewById(R.id.newRowLegDistance);
            if(!legDistance.getText().toString().isEmpty())
            {
                json.put("legDistance", Integer.parseInt(legDistance.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }

            EditText altitude = (EditText)findViewById(R.id.newRowAltitude);
            if(!altitude.getText().toString().isEmpty())
            {
                json.put("altitude", Integer.parseInt(altitude.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }

            EditText tas = (EditText)findViewById(R.id.newRowTAS);
            if(!tas.getText().toString().isEmpty())
            {
                json.put("tas", Integer.parseInt(tas.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }

            EditText windDir = (EditText)findViewById(R.id.newRowWindDir);
            if(!windDir.getText().toString().isEmpty())
            {
                json.put("windDir", Integer.parseInt(windDir.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }

            EditText windSpeed = (EditText)findViewById(R.id.newRowWindSpeed);
            if(!windSpeed.getText().toString().isEmpty())
            {
                json.put("windSpeed", Integer.parseInt(windSpeed.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }

            EditText headAdjust = (EditText)findViewById(R.id.newRowHeadAdjust);
            if(!headAdjust.getText().toString().isEmpty())
            {
                json.put("headAdjust", Integer.parseInt(headAdjust.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }

            EditText magHeadAdjust = (EditText)findViewById(R.id.newRowMagHeadAdjust);
            if(!magHeadAdjust.getText().toString().isEmpty())
            {
                json.put("magHeadAdjust", Integer.parseInt(magHeadAdjust.getText().toString()));
            }
            else
            {
                allReqsFilled = false;
            }


            if(allReqsFilled)
            {
                Intent data = getIntent();
                data.putExtra("CHECKPOINT_INFO", json.toString());

                if(getParent() == null)
                {
                    setResult(Activity.RESULT_OK, data);
                }
                else
                {
                    getParent().setResult(Activity.RESULT_OK, data);
                }

                finish();
            }
            else
            {
                Toast.makeText(this, "Please fill in all the required fields", Toast.LENGTH_LONG).show();
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Save Failed", Toast.LENGTH_LONG).show();
        }
    }
}
