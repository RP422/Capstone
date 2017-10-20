package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

/**
 * Created by Mike on 10/18/2017.
 */

public class LogbookDataViewAdapter extends ArrayAdapter<LogbookEntry>
{
    LogbookEntry[] logbookEntries;
    Context context;

    public LogbookDataViewAdapter(Context context, LogbookEntry[] listItems)
    {
        super(context, R.layout.checklist_item, listItems);
        this.context = context;
        this.logbookEntries = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.logbook_data_view_row, parent, false);

        // fill in the view for each box. If value is 0 or empty, just leave blank

        return convertView;
    }
}
