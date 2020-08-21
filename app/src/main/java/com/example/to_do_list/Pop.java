package com.example.to_do_list;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class Pop extends Activity {

    //Creating private constants to connect to the SQL database
    //Fields that are empty are for the user to fill in and kept empty for security reasons.
    private static String ip = "";
    private static String port = "";
    private static String classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "toDoList";
    private static String username = "";
    private static String password = "";
    private static String url = "jdbc:jtds:sqlserver://" + ip +":"+ port + "/" + database;

    private Date retrievedDate;
    private Connection connection = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                PackageManager.PERMISSION_GRANTED);

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


        //Cancel Button Initialization

        Button cancel_button = (Button) findViewById(R.id.button_to_cancel);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Pop.this,MainActivity.class));
            }
        });

        //Calendar Button Initialization

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
                                retrievedDate = java.sql.Date.valueOf(((year)
                                        + "-" + (monthOfYear +1) + "-" + (dayOfMonth)));
                                calendar_popupText.setText
                                        (dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);

                picker.show();

            }
        });

        //Title EditText box Initiation

        final EditText title = (EditText) findViewById(R.id.titleEditText);


        //Confirm Button Initialization

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            Class.forName(classes);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error here");
        }

        Button confirm_button = (Button) findViewById(R.id.button_to_confirm);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task newTask = new Task(retrievedDate,
                        (String) tags.getSelectedItem().toString(),
                        (String) title.getText().toString(),
                        (String) courses.getSelectedItem().toString());

                if(connection != null){
                    Statement statement = null;

                    try{
                        statement = connection.createStatement();
                        statement.executeQuery("INSERT INTO VALUES_TABLE " +
                                "(dates, tag, course, title) VALUES ('" +
                                newTask.getDate().toString() + "', '" +
                                newTask.getTag() + "', '" + newTask.getCourse() +"', '" +
                                newTask.getTitle() + "');");

                        } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(Pop.this,MainActivity.class));
            }
        });
    }
}
