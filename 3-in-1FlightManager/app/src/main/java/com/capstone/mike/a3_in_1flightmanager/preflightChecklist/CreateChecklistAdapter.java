package com.capstone.mike.a3_in_1flightmanager.preflightChecklist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capstone.mike.a3_in_1flightmanager.R;

import java.util.ArrayList;

/**
 * Created by Mike on 10/22/2017.
 */

public class CreateChecklistAdapter  extends ArrayAdapter<String>
{
    ArrayList<String> listItems;
    Context context;

    public CreateChecklistAdapter(Context context, ArrayList<String> listItems)
    {
        super(context, R.layout.checklist_new_row, listItems);
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.checklist_new_row, parent, false);

        TextView lineItem = (TextView) convertView.findViewById(R.id.newRowTextView);
        lineItem.setText(listItems.get(position));

        return convertView;
    }

    public void addNewItem(String s)
    {
        listItems.add(s);
    }
    public void editAtIndex(int index, String s)
    {
        if(index >= listItems.size()) { listItems.add(s); }
        else { listItems.set(index, s); }
    }
    public int getListLength()
    {
        return listItems.size();
    }
    // Am I gonna need a method to figure out which item in the list a string is?
}
