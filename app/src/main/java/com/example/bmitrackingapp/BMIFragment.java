package com.example.bmitrackingapp;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.InputMismatchException;


@RequiresApi(api = Build.VERSION_CODES.O)
public class BMIFragment extends Fragment {

    EditText edtHeight, edtWeight;
    TextView txvBMI, txvDesc,txvRecom;
    Button calBtn;
    float bmi;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmi, container, false);

        edtHeight = v.findViewById(R.id.edtHeight);
        edtWeight = v.findViewById(R.id.edtWeight);
        txvBMI = v.findViewById(R.id.txvBMI);
        txvDesc = v.findViewById(R.id.txvDesc);
        txvRecom = v.findViewById(R.id.txvRecom);
        calBtn = v.findViewById(R.id.calBtn);


        // calculate BMI
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Log.e(TAG, "Test2 are: " + edtWeight.getText().toString());
                if (edtHeight.getText().toString().equals("")) {
                    txvDesc.setTextColor(Color.parseColor("#BB2649"));
                    txvBMI.setText("");
                    txvDesc.setText("Please Enter Your Height");
                } else if (edtWeight.getText().toString().equals("")) {
                    txvDesc.setTextColor(Color.parseColor("#BB2649"));
                    txvBMI.setText("");
                    txvDesc.setText("Please Enter Your Weight");
                } else {
                    float height = Float.parseFloat(edtHeight.getText().toString()) / 100f;
                    float weight = Float.parseFloat(edtWeight.getText().toString());
                    float bmi = Math.round((weight / (height * height)) * 100.0f) / 100.0f;

                    setBmi(bmi);
                    setDescColor(bmi);
                    txvBMI.setText("Your BMI is: " + bmi);
                    txvDesc.setText("Considered: " + healthMessage(bmi));
                    txvRecom.setText(healthRecommendations(bmi));
                    txvRecom.setBackgroundResource(R.drawable.back);
                }
            }
        });

        return v;
    }


    public String getWeightInput() {return edtWeight.getText().toString();}
    public void setBmi(float bmi){this.bmi = bmi;}
    public String getBmiInput() {return String.valueOf(bmi);}
    public String getBmiTextView() {return txvBMI.getText().toString();}



    public void setDescColor(float bmi) {
        if (bmi < 18.5f) {
            txvDesc.setTextColor(Color.parseColor("#7fcdcd"));
        }else if (bmi <25.0f) {
            txvDesc.setTextColor(Color.parseColor("#88b04b"));
        }else if (bmi < 30.0f) {
            txvDesc.setTextColor(Color.parseColor("#FFCA4B"));
        }else if (bmi >= 30.0f) {
            txvDesc.setTextColor(Color.parseColor("#BB2649"));
        }
    }

    public String healthMessage(float bmi) {

        if (bmi < 18.5f) {
            return "Underweight";
        }else if (bmi <25.0f) {
            return "Healthy";
        }else if (bmi < 30.0f) {
            return "Overweight";
        }

        return "Obese";
    }

    public String healthRecommendations(float bmi) {

        if (bmi < 18.5f) {
            return  "* Eat a nutritious diet for healthy weight gain.\n\n" +
                    "* Include calorie-dense foods like nuts and avocados.\n\n" +
                    "* Do resistance training to build muscle mass.\n\n" +
                    "* Consult a healthcare professional for guidance.";
        }else if (bmi <25.0f) {
            return  "* Maintain a well-balanced diet.\n\n" +
                    "* Engage in regular physical activity.\n\n" +
                    "* Aim for at least 150 minutes aerobic exercise per week.\n\n" +
                    "* Consider strength training to improve muscle strength.";
        }else if (bmi < 30.0f) {
            return  "* Adopt healthy eating habits.\n\n" +
                    "* Increase consumption of fruits, vegetables and grains.\n\n" +
                    "* Aim for at least 150 minutes aerobic exercise per week.\n\n" +
                    "* Seek support from a healthcare professional.";
        }

            return  "* Follow a calorie-controlled diet for weight loss.\n\n" +
                    "* Incorporate regular physical activity.\n\n" +
                    "* Seek medical supervision for weight management.\n\n" +
                    "* Focus on sustainable lifestyle changes.";
    }

}