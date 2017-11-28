package com.capstone.mike.a3_in_1flightmanager.logbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Mike on 10/26/2017.
 */

public class QuerySelectionAdapter extends ArrayAdapter<String>
{
    private String[] presetQueries;
    private Context context;

    public QuerySelectionAdapter(Context context, String[] listItems)
    {
        super(context, R.layout.query_selection_item, listItems);
        this.context = context;
        this.presetQueries = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.query_selection_item, parent, false);

        TextView tv = convertView.findViewById(R.id.querySelectionListItem);
        String s = presetQueries[position];
        tv.setText(s);

        return convertView;
    }
}
