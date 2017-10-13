package com.capstone.mike.a3_in_1flightmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChecklistMainActivity extends AppCompatActivity {
    private static final int SELECT_CHECKLIST_FIlE = 6453;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_main);
    }

    private void testFilePicker(View view)
    {
        String path = "/checklists";

        Intent intent = new Intent(this, FileSelectActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, FileSelectActivity.FILE_SELECT_TEST);
    }
    private void selectAndLaunchChecklist(View view)
    {
        // TODO Implement method
        // Ask file picker for path
        Intent intent = new Intent(this, FileSelectActivity.class);
        intent.putExtra("PATH", "/checklists");
        startActivityForResult(intent, SELECT_CHECKLIST_FIlE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == FileSelectActivity.FILE_SELECT_TEST)
        {
            if(resultCode == RESULT_OK)
            {
                String filePath = data.getStringExtra("FILEPATH");

                // TODO Replace the button text with something to prove you got something back
                Button button = (Button)findViewById(R.id.fileSelectAppTest);
                button.setText(filePath);
            }
        }
        else if(requestCode == SELECT_CHECKLIST_FIlE)
        {
            if(resultCode == RESULT_OK)
            {
                // TODO Use returned path to feed ChecklistRunthroughActivity
                // For now, I'm just using the testing path
                Intent newIntent = new Intent(this, ChecklistRunthroughActivity.class);

                newIntent.putExtra("TEST", true); // Either set this to false or comment out if not testing
                startActivity(newIntent);
            }
        }
    }
}
