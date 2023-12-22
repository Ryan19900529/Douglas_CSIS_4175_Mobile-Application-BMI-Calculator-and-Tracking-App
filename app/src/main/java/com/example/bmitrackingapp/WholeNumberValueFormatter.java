package com.example.bmitrackingapp;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class WholeNumberValueFormatter extends ValueFormatter {
    private final DecimalFormat decimalFormat;

    public WholeNumberValueFormatter() {
        decimalFormat = new DecimalFormat("###,###,##0");
    }

    @Override
    public String getFormattedValue(float value) {
        return decimalFormat.format(value);
    }
}
