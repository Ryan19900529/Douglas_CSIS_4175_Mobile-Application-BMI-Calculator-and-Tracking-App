package com.example.bmitrackingapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity{


    private SavedData savedData;

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;
    int currentId = R.id.bmi;
    private String TAG = MainActivity.class.getSimpleName();

    BMIFragment bmiFragment = new BMIFragment();
    WeightFragment weightFragment = new WeightFragment();
    ChartFragment chartFragment = new ChartFragment();
    HistoryFragment historyFragment = new HistoryFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SavedData with the application context
        savedData = SavedData.getInstance(getApplicationContext());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, bmiFragment).commit();


//        bottomNavigationView.setSelectedItemId(R.id.bmi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bmi){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, bmiFragment).commit();
                    currentId = item.getItemId();

                    return true;
                } else if (item.getItemId() == R.id.weight){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, weightFragment).commit();
                    currentId = item.getItemId();

                    return true;
                } else if (item.getItemId() == R.id.chart){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, chartFragment).commit();
                    currentId = item.getItemId();

                    return true;
                } else if (item.getItemId() == R.id.history){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, historyFragment).commit();
                    currentId = item.getItemId();

                    return true;
                }
                return false;
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String weight = bmiFragment.getWeightInput();;
                String bmi = bmiFragment.getBmiInput();;
                String bmiTxv = bmiFragment.getBmiTextView();
                Float weightFloat;
                Float bmiFloat;
//                Log.e(TAG, "currentId: " + currentId);
                if (currentId == R.id.bmi && !weight.equals("") && !bmiTxv.equals("")) {
                    weightFloat = Float.valueOf(weight);
                    bmiFloat = Float.valueOf(bmi);
                    SavedData.getInstance().addData(weight, bmi);
                    SavedData.getInstance().addWeightArrayList(weightFloat);
                    SavedData.getInstance().addBmiArrayList(bmiFloat);
                    Toast.makeText(MainActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
                } else if(currentId == R.id.bmi && weight.equals("")) {
                    Toast.makeText(MainActivity.this, "Please input your weight", Toast.LENGTH_SHORT).show();
                } else if(currentId == R.id.bmi && bmiTxv.equals("")){
                    Toast.makeText(MainActivity.this, "Please calculate your BMI", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}