package com.homemade.homemade.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.homemade.homemade.R;

import java.util.List;

/**
 * Created by brianhicks on 2/13/18.
 */

public class InstructionListAdapter  extends ArrayAdapter<String> {

    public InstructionListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public InstructionListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public InstructionListAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }

    public InstructionListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public InstructionListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    public InstructionListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        if(v == null){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.instruction_list_element, null);
        }

        String instruction = getItem(position);

        if(instruction != null){
            TextView instructionText = (TextView) v.findViewById(R.id.instruction_list_text);
            String instructionString = (position + 1) + ". " + instruction;
            instructionText.setText(instructionString);
        }

        return v;
    }
}
