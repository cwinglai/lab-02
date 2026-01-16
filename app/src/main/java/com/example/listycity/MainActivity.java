package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1; // Added this to track which city is clicked

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Find all views
        EditText myEditText = findViewById(R.id.my_edit_text);
        Button addButton = findViewById(R.id.button_add);
        Button deleteButton = findViewById(R.id.button_delete);
        cityList = findViewById(R.id.city_list);

        // 2. Initialize data and adapter
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // 3. Track selection: When a city is clicked, remember its position
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            String selectedCity = dataList.get(position);
            Toast.makeText(this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
        });

        // 4. Logic for Adding a city
        addButton.setOnClickListener(v -> {
            String newCity = myEditText.getText().toString();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                myEditText.setText("");
                Toast.makeText(this, "City Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Enter a city name!", Toast.LENGTH_SHORT).show();
            }
        });

        // 5. Logic for Deleting a city
        deleteButton.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1; // Reset selection after deleting
                Toast.makeText(this, "City Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Select a city first!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}