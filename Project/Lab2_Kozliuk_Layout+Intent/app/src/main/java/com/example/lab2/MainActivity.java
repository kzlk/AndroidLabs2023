package com.example.lab2;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    StringBuilder builder = new StringBuilder();
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        textView1.setText(builder.append("Життєвий цикл:\n\n"));
        setTitle("Kozliuk_KI44_Lab2_2023");
        log("onCreate");
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);
    }

    private void log(String text) {
        Log.d("LifeCycleTestActivity1", text);
        builder.append(text);
        builder.append('\n');
        textView1.setText(builder.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log("onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause");
        if (isFinishing()) {
            log("finishing");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
    }
}
