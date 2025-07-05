package com.example.unitconverter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText editTextValue, editTextResult;
    Spinner spinnerFrom, spinnerTo;
    Button buttonConvert;

    HashMap<String, Double> unitFactors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link views
        editTextValue = findViewById(R.id.editTextValue);
        editTextResult = findViewById(R.id.editTextResult);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        buttonConvert = findViewById(R.id.buttonConvert);

        // Initialize unit factors
        unitFactors = new HashMap<>();
        unitFactors.put("Meter", 1.0);
        unitFactors.put("Kilometer", 1000.0);
        unitFactors.put("Centimeter", 0.01);
        unitFactors.put("Millimeter", 0.001);
        unitFactors.put("Inch", 0.0254);
        unitFactors.put("Foot", 0.3048);

        // Set spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.length_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Convert button logic
        buttonConvert.setOnClickListener(view -> {
            String inputStr = editTextValue.getText().toString();
            if (!inputStr.isEmpty()) {
                double inputValue = Double.parseDouble(inputStr);
                String fromUnit = spinnerFrom.getSelectedItem().toString();
                String toUnit = spinnerTo.getSelectedItem().toString();

                double result = convertLength(inputValue, fromUnit, toUnit);
                editTextResult.setText(String.valueOf(result));
            } else {
                Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double convertLength(double value, String from, String to) {
        double baseValue = value * unitFactors.get(from);
        return baseValue / unitFactors.get(to);
    }
}
