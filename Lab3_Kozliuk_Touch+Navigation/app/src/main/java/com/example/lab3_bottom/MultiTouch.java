package com.example.lab3_bottom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MultiTouch extends Fragment implements View.OnTouchListener {
    StringBuilder builder = new StringBuilder();
    TextView textView;
    float[] x = new float[10];
    float[] y = new float[10];
    boolean[] touched = new boolean[10];
    private void updateTextView() {
        builder.setLength(0);
        for(int i = 0; i < 10; i++) {
            builder.append(touched[i]);
            builder.append(", ");
            builder.append(x[i]);
            builder.append(", ");
            builder.append(y[i]);
            builder.append("\n");
        }
        textView.setText(builder.toString());
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout XML file
        // Inflate the fragment's layout XML file
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Find the TextView in the fragment's layout
        textView = rootView.findViewById(R.id.text_dashboard);

        // Set the text and set the fragment as the touch listener
        textView.setText(new StringBuilder("Touch and drag (one finger only)!"));

        textView.setText("Touch and drag (multiple fingers supported)!");
        textView.setOnTouchListener(this);

        return rootView;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >>
                MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerId = event.getPointerId(pointerIndex);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touched[pointerId] = true;
                x[pointerId] = (int)event.getX(pointerIndex);
                y[pointerId] = (int)event.getY(pointerIndex);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                touched[pointerId] = false;
                x[pointerId] = (int)event.getX(pointerIndex);
                y[pointerId] = (int)event.getY(pointerIndex);
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; i++) {
                    pointerIndex = i;
                    pointerId = event.getPointerId(pointerIndex);
                    x[pointerId] = (int)event.getX(pointerIndex);
                    y[pointerId] = (int)event.getY(pointerIndex);
                }
                break;
        }

        updateTextView();
        return true;
    }
}
