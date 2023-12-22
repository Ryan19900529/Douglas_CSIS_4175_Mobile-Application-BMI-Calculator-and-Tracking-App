package com.example.bmitrackingapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ChartFragment extends Fragment {

    LineChart lineChart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chart, container, false);

        lineChart = v.findViewById(R.id.line_chart);

        ArrayList<Entry> lineEntries = new ArrayList<>();
        if(!SavedData.getInstance().getBmiArrayList().isEmpty()){
            for(int i = 0; i < SavedData.getInstance().getBmiArrayList().size(); i++){
                lineEntries.add(new Entry(i,SavedData.getInstance().getBmiArrayList().get(i)));
            }
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "BMIs");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setValueFormatter(new WholeNumberValueFormatter());

        //modify axis
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new MyXAxisValueFormatter());

        //modify legend
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);

        lineChart.setData(new LineData(lineDataSet));
        lineChart.getDescription().setText(" ");

        return v;
    }
}