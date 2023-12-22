package com.example.bmitrackingapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MyXAxisValueFormatter extends ValueFormatter {

    @Override
    public String getAxisLabel(float value, AxisBase axis) {

        List<String> dates = SavedData.getInstance().getDates();
        if (!dates.isEmpty() && value >= 0 && value < dates.size()) {
            return dates.get((int) value);
        }
        return "";
    }
}
