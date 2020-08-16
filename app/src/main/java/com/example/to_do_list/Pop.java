package com.example.to_do_list;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.Calendar;

public class Pop extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_up_form);

        DisplayMetrics displaydimensions = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaydimensions);

        int width = displaydimensions.widthPixels;
        int height = displaydimensions.heightPixels;

        getWindow().setLayout((int)(width*0.9), (int)(height*0.8));

        //To Initialize the course spinner on the pop up.

        final Spinner courses = (Spinner) findViewById(R.id.courses_spinner);

        ArrayAdapter<String> spinner_course = new ArrayAdapter<String>(Pop.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.course));

        spinner_course.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courses.setAdapter(spinner_course);

        //To initialize the tag spinner on the pop up.

        final Spinner tags = (Spinner) findViewById(R.id.tag_spinner);

        ArrayAdapter<String> spinner_tag = new ArrayAdapter<String>(Pop.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tag));

        spinner_tag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tags.setAdapter(spinner_tag);


        //Cancel Button

        Button cancel_button = (Button) findViewById(R.id.button_to_cancel);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pop.this,MainActivity.class));
            }
        });

        //Calendar Button Initiation

        final TextView calendar_popupText = (TextView) findViewById(R.id.dateField);

        calendar_popupText.setInputType(InputType.TYPE_NULL);


        calendar_popupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar_popup = Calendar.getInstance();

                int day = calendar_popup.get(Calendar.DAY_OF_MONTH);
                int month = calendar_popup.get(Calendar.MONTH);
                int year = calendar_popup.get(Calendar.YEAR);

                DatePickerDialog picker = new DatePickerDialog(Pop.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calendar_popupText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);

                picker.show();

            }
        });

        //Title Initiation

        final EditText title = (EditText) findViewById(R.id.titleEditText);


        //Confirm Button Initialization

        Button confirm_button = (Button) findViewById(R.id.button_to_confirm);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task newTask = new Task((Date) calendar_popupText.getText(),
                        (String) tags.getSelectedItem().toString(),
                        (String) title.getText().toString(),
                        (String) courses.getSelectedItem().toString());
            }
        });

    }
}
