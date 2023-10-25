package com.example.lab5_sql;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Lab5 extends AppCompatActivity
{
    // Define variables for UI elements
    private Button insertButton;

    private DatabaseHelper dbHelper;
    private Button updateButton;
    private Button deleteButton;
    private List<OperatingSystem> operatingSystemList;
    private RecyclerView recyclerView;

    private OperatingSystemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        setTitle("Lab5_Kozliuk_KI44");

        // Initialize UI elements

        dbHelper = new DatabaseHelper(this);

        insertButton = findViewById(R.id.buttonInsert);
        updateButton = findViewById(R.id.buttonUpdate);
        deleteButton = findViewById(R.id.buttonDelete);

        recyclerView = findViewById(R.id.subjectRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appendToRecycle();


        // Set click listeners for the buttons
        insertButton.setOnClickListener(view -> {
            // Launch the InsertDataActivity when the Insert button is clicked
            Intent intent = new Intent(Lab5.this, InsertData.class);
            startActivity(intent);
        });

        updateButton.setOnClickListener(view -> {
            // Launch the UpdateDataActivity when the Update button is clicked
           // Intent intent = new Intent(MainActivity.this, UpdateDataActivity.class);
//                startActivity(intent);
        });

        deleteButton.setOnClickListener(view -> {
            // Launch the DeleteDataActivity when the Delete button is clicked
//                Intent intent = new Intent(MainActivity.this, DeleteDataActivity.class);
//                startActivity(intent);
            int selectedItemPosition = adapter.getSelectedItemPosition();
            if (selectedItemPosition != -1) {

                // Delete the item from the data source (e.g., subjectList)
                // Refresh the RecyclerView
                // For example, if using a List<Subject>:
                //dbHelper.deleteSubject(selectedItemPosition);


                boolean isDeleted = dbHelper.deleteSubjectById((int) operatingSystemList.get(selectedItemPosition).getId());
                if (isDeleted) {
                    Toast.makeText(getApplicationContext(), "Subject deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                     Toast.makeText(getApplicationContext(), "Failed to delete subject", Toast.LENGTH_SHORT).show();
                }
                operatingSystemList.remove(selectedItemPosition);
                adapter.clearSelectedItem(); // Clear the selected item
                adapter.notifyItemRemoved(selectedItemPosition);



            }

        });
    }

    void appendToRecycle()
    {
        operatingSystemList = dbHelper.fetchDataFromDatabase();
        adapter = new OperatingSystemAdapter(operatingSystemList);
        recyclerView.setAdapter(adapter);
    };



}

