package com.capstone.mike.a3_in_1flightmanager.common;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.capstone.mike.a3_in_1flightmanager.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSelectActivity extends ListActivity {
    public static final int FILE_SELECT_TEST = 4221997;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_select);

        if(getIntent().hasExtra("PATH"))
        {
            path = getIntent().getStringExtra("PATH");
        }
        else
        {
            path = "/";
        }
        setTitle(path);

        // Read all files sorted into the values-array
        List values = new ArrayList();
        File dir = new File(path);

        if (!dir.canRead())
        {
            setTitle(getTitle() + " (inaccessible)");
        }

        String[] list = dir.list();
        if (list != null)
        {
            for (String file : list) {
                if (!file.startsWith(".") && // removes files that start with a "."
                    file.lastIndexOf(".json") == file.length() - 5)  // removes files that aren't .json
                {
                    values.add(file);
                }
            }
        }
        Collections.sort(values);

        // Put the data into the list
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String filename = (String) getListAdapter().getItem(position);
        if (path.endsWith(File.separator)) {
            filename = path + filename;
        } else {
            filename = path + File.separator + filename;
        }
        if (new File(filename).isDirectory()) {
            Intent intent = new Intent(this, FileSelectActivity.class);
            intent.putExtra("path", filename);
            startActivityForResult(intent, FILE_SELECT_TEST);
        } else {
            //// This Toast stuff looks like something I may want to look into later
            //Toast.makeText(this, filename + " is not a directory", Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.putExtra("FILEPATH", filename);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == FILE_SELECT_TEST)
        {
            if(resultCode == RESULT_OK)
            {
                setResult(RESULT_OK, data);
                finish();
            }
        }
    }
}
