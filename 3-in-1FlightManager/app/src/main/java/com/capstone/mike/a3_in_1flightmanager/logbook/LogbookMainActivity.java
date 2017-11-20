package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.capstone.mike.a3_in_1flightmanager.R;

public class LogbookMainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_QUERY = 422;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_main);
    }

    public void filterFlights(View view)
    {
        Intent intent = new Intent(this, LogbookQuerySelectionActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUERY);
    }

    public void showAll(View view)
    {
        Intent intent = new Intent(this, LogbookDataViewActivity.class);
        intent.putExtra("QUERY", LogbookQuerySelectionActivity.FILTER_NONE);
        startActivity(intent);
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
                int queryCode = data.getIntExtra("QUERY", -1);

//                // START DEBUG LINES
//                Button btn = (Button)findViewById(R.id.quickSearchButton);
//                btn.setText("Returned Query Code: " + queryCode);
//                // END DEBUG LINES

                Intent intent = new Intent(this, LogbookDataViewActivity.class);
                intent.putExtra("QUERY", queryCode);

                startActivity(intent);
            }
        }
    }
}
