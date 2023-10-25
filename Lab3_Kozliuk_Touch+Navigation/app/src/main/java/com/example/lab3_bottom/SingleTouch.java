package com.example.lab3_bottom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
public class SingleTouch extends Fragment implements View.OnTouchListener
{
    StringBuilder builder = new StringBuilder();
    TextView textView ;
    @SuppressLint("ClickableViewAccessibility")

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout XML file
        // Inflate the fragment's layout XML file
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the TextView in the fragment's layout
        textView = rootView.findViewById(R.id.text_home);

        textView.clearComposingText();
        // Set the text and set the fragment as the touch listener
       textView.setText(new StringBuilder("Touch and drag (one finger only)!"));


        textView.setOnTouchListener(this);


        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        builder.setLength(0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                builder.append("down, ");
                break;
            case MotionEvent.ACTION_MOVE:
                builder.append("move, ");
                break;
            case MotionEvent.ACTION_CANCEL:
                builder.append("cancle, ");
                break;
            case MotionEvent.ACTION_UP:
                builder.append("up, ");
                break;
        }
        builder.append(event.getX());
        builder.append(", ");
        builder.append(event.getY());
        String text = builder.toString();
        Log.d("TouchTest", text);
        textView.setText(text);
        return true;
    }
}