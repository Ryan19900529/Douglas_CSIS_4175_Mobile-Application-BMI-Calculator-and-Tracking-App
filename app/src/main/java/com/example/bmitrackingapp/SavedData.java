package com.example.bmitrackingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class SavedData {

    private static final String PREF_NAME = "SavedDataPrefs";
    private static final String KEY_LIST_DATA = "list_data";
    private static final String KEY_WEIGHT_ARRAY_LIST = "weight_array_list";
    private static final String KEY_BMI_ARRAY_LIST = "bmi_array_list";
    private static final String KEY_DATES = "dates";

    // create an instance of WeightData for other class to call method without instantiate WeightData class
    private static SavedData instance;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private ArrayList<String> listData;
    private ArrayList<Float> weightArrayList;
    private ArrayList<Float> bmiArrayList;
    private ArrayList<String> dates;
    private String TAG = MainActivity.class.getSimpleName();

    LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd");
    String currentDay1 = currentDate.format(formatter1);
    String currentDay2 = currentDate.format(formatter2);

    private SavedData(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        loadSavedData();
    }

    private SavedData() {
        listData = new ArrayList<>();
        weightArrayList = new ArrayList<>();
        bmiArrayList = new ArrayList<>();
        dates =new ArrayList<>();
    }


    public static SavedData getInstance(Context context) {
        if (instance == null) {
            instance = new SavedData(context);
        }
        return instance;
    }

    public static SavedData getInstance() {
        if (instance == null) {
            instance = new SavedData();
        }
        return instance;
    }

    public List<String> getListData() {
        return listData;
    }

    public void addWeightArrayList(Float weight) {
        weightArrayList.add(weight);
        saveData();
    }

    public void addBmiArrayList(Float bmi) {
        bmiArrayList.add(bmi);
        saveData();
    }

    public ArrayList<Float> getWeightArrayList() {
        return weightArrayList;
    }

    public ArrayList<Float> getBmiArrayList() {
        return bmiArrayList;
    }

    public ArrayList<String> getDates() {
        Log.e(TAG, "Dates are: " + dates);
        return dates;
    }

    public void addData(String weight, String bmi) {
        listData.add("   " + currentDay1 + "                   " + weight + " kg" + "                     " + "BMI " + bmi);

        dates.add(currentDay2);
        saveData();
    }

    private void loadSavedData() {
        Type listType = new TypeToken<List<String>>() {}.getType();
        String listDataJson = sharedPreferences.getString(KEY_LIST_DATA, null);
        listData = gson.fromJson(listDataJson, listType);
        if (listData == null) {
            listData = new ArrayList<>();
        }

        Type floatListType = new TypeToken<List<Float>>() {}.getType();
        String weightArrayListJson = sharedPreferences.getString(KEY_WEIGHT_ARRAY_LIST, null);
        weightArrayList = gson.fromJson(weightArrayListJson, floatListType);
        if (weightArrayList == null) {
            weightArrayList = new ArrayList<>();
        }

        String bmiArrayListJson = sharedPreferences.getString(KEY_BMI_ARRAY_LIST, null);
        bmiArrayList = gson.fromJson(bmiArrayListJson, floatListType);
        if (bmiArrayList == null) {
            bmiArrayList = new ArrayList<>();
        }

        String datesJson = sharedPreferences.getString(KEY_DATES, null);
        dates = gson.fromJson(datesJson, listType);
        if (dates == null) {
            dates = new ArrayList<>();
        }
    }

    public void saveData() {
        if (sharedPreferences == null) {
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LIST_DATA, gson.toJson(listData));
        editor.putString(KEY_WEIGHT_ARRAY_LIST, gson.toJson(weightArrayList));
        editor.putString(KEY_BMI_ARRAY_LIST, gson.toJson(bmiArrayList));
        editor.putString(KEY_DATES, gson.toJson(dates));
        editor.apply();
    }
}
