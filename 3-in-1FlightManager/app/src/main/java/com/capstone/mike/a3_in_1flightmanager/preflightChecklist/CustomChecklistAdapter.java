package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

/**
 * Created by Mike on 10/13/2017.
 */

public class CustomChecklistAdapter extends ArrayAdapter<String>
{
    String[] listItems = null;
    Context context;

    public CustomChecklistAdapter(Context context, String[] listItems)
    {
        super(context, R.layout.checklist_item, listItems);
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.checklist_item, parent, false);

        TextView lineItem = (TextView) convertView.findViewById(R.id.listItemTextView);
        lineItem.setText(listItems[position]);

        return convertView;
    }
}