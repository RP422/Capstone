package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChecklistRunthroughActivity extends AppCompatActivity {

    String[] testItems = new String[] { "Check Wheels", "Check Wings", "Check Rudders" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_runthrough);

        Intent intent = this.getIntent();
        ListView lv = (ListView)findViewById(R.id.checklistListView);

        boolean useTestData = false;
        if(intent.hasExtra("TEST"))
        {
            useTestData = intent.getBooleanExtra("TEST", false);
        }

        if(useTestData)
        {
            lv.setAdapter(new CustomChecklistAdapter(this, testItems));
        }
        else
        {
            String file = intent.getStringExtra("FILE");

            DBHandler db = DBHandler.getInstance(this);
            JSONObject json = db.getJSONfromReferenceName(file);

            try
            {
                JSONArray jsonItems = json.getJSONArray("CHECKLIST");
                String [] items = new String[jsonItems.length()];

                for(int x = 0; x < items.length; x++)
                {
                    items[x] = (String)jsonItems.get(x);
                }

                lv.setAdapter(new CustomChecklistAdapter(this, items));
            }
            catch (JSONException e)
            {
                Toast.makeText(this, "Sorry, there was a problem with the file.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
