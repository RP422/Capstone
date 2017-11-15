package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.capstone.mike.a3_in_1flightmanager.R;
import com.capstone.mike.a3_in_1flightmanager.common.DBHandler;

import org.json.JSONObject;

public class LogbookMainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_QUERY = 4221997;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_main);
    }

    public void selectQuery(View view)
    {
        Intent intent = new Intent(this, LogbookQuerySelectionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUERY);
    }

    public void showAll(View view)
    {
        Intent intent = new Intent(this, LogbookDataViewActivity.class);
        intent.putExtra("QUERY", LogbookQuerySelectionActivity.QUERY_ALL);
    }

    public void newEntry (View view)
    {
        Intent intent = new Intent(this, LogbookNewEntryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE_QUERY)
        {
            if(resultCode == RESULT_OK)
            {
                Intent intent = new Intent(this, LogbookDataViewActivity.class);
                intent.putExtra("QUERY", data.getIntExtra("QUERY", -1));

                startActivity(intent);
            }
        }
    }
}
