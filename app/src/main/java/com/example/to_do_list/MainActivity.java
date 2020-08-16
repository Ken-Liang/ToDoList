package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.example.to_do_list.Task;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiliazing and defining the button used to add a Task.

        Button pop_button = (Button) findViewById(R.id.button_to_pop);

        pop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Pop.class));
            }
        });

        //SETTING UP SOME TEST CASES TO SEE IF THE DISPLAY WORKS OR NOT.
        //Also, it is formatted in a way that may be repetitive because I wanted to imitate
        //how data may be grabbed from the future SQL database.

        long millis = System.currentTimeMillis();
        Date test = new java.sql.Date(millis);

        Task task1 = new Task(test , "Homework", "Assignment #1", "CPEN 221");
        Task task2 = new Task(test , "Midterm", "Midterm #1", "CPEN 211");
        Task task3 = new Task(test , "Final", "MATH 220 FINAL", "MATH 220");
        Task task4 = new Task(test , "Misc", "Finish Survey", "MATH 253");
        Task task5 = new Task(test , "Homework", "Assignment #4", "CPEN 281");
        Task task6 = new Task(test , "Homework", "Assingment #8", "CPEN 221");
        Task task7 = new Task(test , "Midterm", "Midterm #2", "CPEN 211");
        Task task8 = new Task(test , "Final", "MATH 220 FINAL", "MATH 220");
        Task task9 = new Task(test , "Misc", "Finish Survey", "MATH 253");
        Task task10 = new Task(test , "Homework", "Hand in Essay", "CPEN 281");


        List<Task> list = new ArrayList<Task>();

        list.add(task1);
        list.add(task2);
        list.add(task3);
        list.add(task4);
        list.add(task5);
        list.add(task6);
        list.add(task7);
        list.add(task8);
        list.add(task9);
        list.add(task10);

        Date[] listDates = new Date[10];
        String[] listTags = new String[10];
        String[] listCourses = new String[10];
        String[] listTitles = new String[10];


        for(int i = 0; i < 10; i++){
            listDates[i] = (Date) list.get(i).getDate(list.get(i));
            listTags[i] = list.get(i).getTag(list.get(i));
            listCourses[i] =  list.get(i).getCourse(list.get(i));
            listTitles[i] =  list.get(i).getTitle(list.get(i));
        }




        ListView listView = (ListView) findViewById(R.id.list);

        ItemAdapter itemAdapter = new ItemAdapter(this,listDates,listTags,listCourses,listTitles);
        listView.setAdapter(itemAdapter);
    }





}