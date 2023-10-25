package com.example.lab5_sql;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InsertData extends AppCompatActivity {

    private EditText subjectNameEditText;
    private EditText scopeEditText;
    private EditText teacherEditText;
    private EditText scheduleEditText;
    private EditText successRateEditText;
    private Button saveButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);

        subjectNameEditText = findViewById(R.id.editTextSubjectName);
        scopeEditText = findViewById(R.id.editTextScope);
        teacherEditText = findViewById(R.id.editTextTeacher);
        scheduleEditText = findViewById(R.id.editTextSchedule);
        successRateEditText = findViewById(R.id.editTextSuccessRate);
        saveButton = findViewById(R.id.buttonSave);
        dbHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(insertData())
                {
                    Intent intent = new Intent(InsertData.this, Lab5.class);
                    startActivity(intent);
                }

            }
        });
    }

    private boolean insertData() {
        String subjectName = subjectNameEditText.getText().toString();
        String scope = scopeEditText.getText().toString();
        String teacher = teacherEditText.getText().toString();
        String schedule = scheduleEditText.getText().toString();
        String successRate = successRateEditText.getText().toString();

        if (subjectName.isEmpty() || scope.isEmpty() || teacher.isEmpty() || schedule.isEmpty() || successRate.isEmpty()) {
            // Show an error message or toast indicating that all fields must be filled.
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // All fields are filled, so proceed with the insertion.
            return dbHelper.insertData(subjectName, scope, teacher, schedule, successRate);
        }
    }

}
