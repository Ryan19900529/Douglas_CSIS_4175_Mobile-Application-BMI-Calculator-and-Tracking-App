package com.example.bmitrackingapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeightFragment extends Fragment {

    BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weight, container, false);

        barChart = v.findViewById(R.id.bar_chart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        if(!SavedData.getInstance().getWeightArrayList().isEmpty()){
            for(int i = 0; i < SavedData.getInstance().getWeightArrayList().size(); i++){
                barEntries.add(new BarEntry(i,SavedData.getInstance().getWeightArrayList().get(i)));
            }
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Weights");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextSize(12f);
        barDataSet.setValueFormatter(new WholeNumberValueFormatter());

        //modify axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new MyXAxisValueFormatter());

        //modify legend
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        barChart.setData(new BarData(barDataSet));
        barChart.getDescription().setText(" ");

        return v;
    }


}