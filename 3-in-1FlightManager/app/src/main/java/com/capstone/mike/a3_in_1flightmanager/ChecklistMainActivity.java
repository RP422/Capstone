package com.capstone.mike.a3_in_1flightmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ChecklistMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void testFilePicker(View view)
    {
        String path = "/checklists";

        Intent intent = new Intent(this, FileSelectActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, FileSelectActivity.FILE_SELECT);
    }
    private void selectChecklist(View view)
    {
        // TODO link the file picker here and use the returned path to feed the ChecklistRunthroughActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == FileSelectActivity.FILE_SELECT)
        {
            if(resultCode == RESULT_OK)
            {
                String filePath = data.getStringExtra("FILEPATH");

                // TODO Replace the button text with something to prove you got something back
                Button button = (Button)findViewById(R.id.fileSelectAppTest);
                button.setText(filePath);
            }
        }
    }
}
