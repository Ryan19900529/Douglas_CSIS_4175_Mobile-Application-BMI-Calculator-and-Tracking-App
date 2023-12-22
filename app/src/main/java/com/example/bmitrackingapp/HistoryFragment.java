package com.example.bmitrackingapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class HistoryFragment extends Fragment {

//    List<String> weightData;
    ArrayAdapter<String> adapter;
    ListView listView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_history, container, false);

//        weightData = new ArrayList<>();
//        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, weightData);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, SavedData.getInstance().getListData());
        listView = v.findViewById(R.id.weightList);
        listView.setAdapter(adapter);
        setUpListViewListener();

        return v;
    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getActivity().getApplicationContext();
                Toast.makeText(context, "Record Removed", Toast.LENGTH_SHORT).show();
                SavedData.getInstance().getListData().remove(position);
                SavedData.getInstance().getWeightArrayList().remove(position);
                SavedData.getInstance().getBmiArrayList().remove(position);
                SavedData.getInstance().getDates().remove(position);
                SavedData.getInstance().saveData();
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void addWeightToList(String weight) {
//        if(WeightData.getInstance().getWeightList() != null){
//            WeightData.getInstance().addWeight(weight);
//            adapter.notifyDataSetChanged();
//        }
//    }
}