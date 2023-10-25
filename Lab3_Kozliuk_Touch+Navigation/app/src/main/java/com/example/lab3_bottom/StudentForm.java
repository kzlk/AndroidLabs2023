package com.example.lab3_bottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

public class StudentForm extends Fragment
{
    View rootView ;
    Student student = new Student();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout XML file
        // Inflate the fragment's layout XML file
       rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        Button save= rootView.findViewById(R.id.save);

        save.setOnClickListener(onSave);

        return rootView;
    }

    private View.OnClickListener onSave=new View.OnClickListener() {
        public void onClick(View v) {
            EditText name = (EditText) rootView.findViewById(R.id.name);
            EditText address = (EditText) rootView.findViewById(R.id.addr);

            student.setName(name.getText().toString());
            student.setAddress(address.getText().toString());


            RadioGroup types = (RadioGroup) rootView.findViewById(R.id.types);
            int checkedRadioButtonId = types.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.sit_down) {
                student.setType("Bachelor");
            } else if (checkedRadioButtonId == R.id.take_out) {
                student.setType("Master");
            } else if (checkedRadioButtonId == R.id.delivery) {
                student.setType("Graduate");
            } else {
                throw new IllegalStateException("Unexpected value: " + checkedRadioButtonId);
            }

        }

        ;
    };
}
