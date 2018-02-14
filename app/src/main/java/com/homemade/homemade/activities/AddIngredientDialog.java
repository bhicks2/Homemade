package com.homemade.homemade.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.homemade.homemade.R;
import com.homemade.homemade.model.food.FoodInformation;
import com.homemade.homemade.model.measurement.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brianhicks on 2/13/18.
 */
public class AddIngredientDialog extends DialogFragment {

    private FoodInformation food;
    private AddIngredientDialogListener listener;
    private Spinner unitSelector;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Bundle dataBundle = getArguments();
        String title = dataBundle.getString("FOOD_NAME");

        builder.setTitle("Add " + title + "...");

        View inflatedView = inflater.inflate(R.layout.add_ingredient_dialog, null);
        builder.setView(inflatedView);

        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogPositiveClick(AddIngredientDialog.this);
            }
        });

        Dialog dialog = builder.create();

        unitSelector = (Spinner) inflatedView.findViewById(R.id.unit_selector);

        List<Unit> unitList = new ArrayList<>();

        unitList.addAll(Arrays.asList(Unit.values()));
        SpinnerAdapter adapter = new ArrayAdapter<Unit>(getContext(), android.R.layout.simple_spinner_dropdown_item, unitList);
        unitSelector.setAdapter(adapter);

        return dialog;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof AddIngredientDialogListener){
            listener = (AddIngredientDialogListener) context;
        }
    }

    public interface AddIngredientDialogListener {
        public void onDialogPositiveClick(DialogFragment fragment);
        public void onDialogNegativeClick(DialogFragment fragment);
    }

    public FoodInformation getFood() {
        return food;
    }

    public void setFood(FoodInformation food) {
        this.food = food;
    }
}
