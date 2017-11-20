package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.capstone.mike.a3_in_1flightmanager.R;

public class LogbookQuerySelectionActivity extends AppCompatActivity {
    public static final String[] presetQueries = new String[] {
            "Get all flights within 30 days",
            "Get all flights within 90 days",
            "Get all night flights within 30 days",
            "Get all night flights within 90 days",
            "Get all simulated instrument flights within 30 days",
            "Get all actual instrument flights within 30 days",
            "Get all simulator flights within 30 days",
            "Get all cross country flights within 30 days",
            "Get all flights as a flight instructor within 30 days",
            "Get all flights receiving instruction within 30 days",
            "Get all flights with Pilot-In-Command time within 30 days"
    };

    public static final int FILTER_NONE                  = -1;
    public static final int FILTER_30_DAYS               =  0;
    public static final int FILTER_90_DAYS               =  1;
    public static final int FILTER_30_DAYS_NIGHT         =  2;
    public static final int FILTER_90_DAYS_NIGHT         =  3;
    public static final int FILTER_30_DAYS_SIM_INST      =  4;
    public static final int FILTER_30_DAYS_ACT_INST      =  6;
    public static final int FILTER_30_DAYS_FLGT_SIM      =  7;
    public static final int FILTER_30_DAYS_XCOUNTRY      =  8;
    public static final int FILTER_30_DAYS_FLGT_INSTR    =  9;
    public static final int FILTER_30_DAYS_DUAL_RECEIVED = 10;
    public static final int FILTER_30_DAYS_PIC           = 11;

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_query_selection);

        list = (ListView)findViewById(R.id.querySelectionList);
        QuerySelectionAdapter adapter = new QuerySelectionAdapter(this, presetQueries);

        list.setAdapter(adapter);

        final Activity activity = this;

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent data = activity.getIntent();
                data.putExtra("QUERY", i);

                if(getParent() == null)
                {
                    setResult(Activity.RESULT_OK, data);
                }
                else
                {
                    getParent().setResult(Activity.RESULT_OK, data);
                }

                activity.finish();
            }
        });
    }
}
