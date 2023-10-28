package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "lab4.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveButton = findViewById(R.id.saveFileButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText();
            }
        });

        Button readButton = findViewById(R.id.readFileButton);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openText();
            }
        });

        Button deleteButton = findViewById(R.id.deleteFileButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textBox = findViewById(R.id.fileNameEditText);
                String text = textBox.getText().toString();
                delete(text);
            }
        });

        EditText editText = findViewById(R.id.selectedFolderTextView);
        editText.setText("/data/user/0/com.example.lab4/files/");

    }

    public void saveText(){

        FileOutputStream fos = null;
        File myFile = null;

        try {


            EditText file_name = findViewById(R.id.fileNameEditText);

            if (file_name.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Будь ласка введіть назву файла", Toast.LENGTH_SHORT).show();
                return;
            }

            EditText textBox = findViewById(R.id.readTextView);
            String text = textBox.getText().toString();


            myFile = new File(file_name.getText().toString());
            fos = openFileOutput(file_name.getText().toString(), MODE_PRIVATE);
            fos.write(text.getBytes());

            Toast.makeText(this, "Файл збережено", Toast.LENGTH_SHORT).show();
            textBox.setText("");
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openText(){

        EditText file_name = findViewById(R.id.fileNameEditText);
        if (file_name.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Будь ласка введуть назву файла", Toast.LENGTH_SHORT).show();
            return;
        }

        FileInputStream fin = null;
        TextView textView = findViewById(R.id.readTextView);
        try {
            fin = openFileInput(file_name.getText().toString());
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void delete(String fileName) {
        if (fileName.isEmpty()) {
            Toast.makeText(MainActivity.this, "Будь ласка введуть назву файла", Toast.LENGTH_SHORT).show();
            return;
        }

        if (getApplicationContext().deleteFile(fileName)) {
            Toast.makeText(MainActivity.this, "Файл видалено успішно", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Помилка видалення файлу", Toast.LENGTH_SHORT).show();
        }
    }

}
