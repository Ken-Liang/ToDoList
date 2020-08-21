package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.to_do_list.Task;

public class MainActivity extends AppCompatActivity {

    //Creating private constants to connect to the SQL database
    //Fields that are empty are for the user to fill in and kept empty for security reasons.
    private static String ip = "";
    private static String port = "";
    private static String classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "toDoList";
    private static String username = "";
    private static String password = "";
    private static String url = "jdbc:jtds:sqlserver://" + ip +":"+ port + "/" + database;

    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                PackageManager.PERMISSION_GRANTED);


        //Initiliazing and defining the button used to add a Task.

        Button pop_button = (Button) findViewById(R.id.button_to_pop);

        pop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Pop.class));
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            Class.forName(classes);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        //Initializing the list of tasks will hold the results of the SQL Database

        List<Task> results = new ArrayList<>();

        if(connection != null){
            Statement statement = null;

            try{
                statement = connection.createStatement();

                //Getting the SQL database to execute a Query that will retrieve the values back in
                //an object of ResultSet.

                ResultSet resultSet = statement.executeQuery(
                        "Select * from VALUES_TABLE ORDER BY dates ASC;");

                while(resultSet.next()){

                    //Creating a temporary task for each row of the database. Adding the task to
                    //the initialized task list.

                    Task temp = new Task(resultSet.getDate("dates"),
                            resultSet.getString("tag"), resultSet.getString("title"),
                            resultSet.getString("course"));
                    results.add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //Initializing the size of the arrays to be sent into the ItemAdapter
        //Adding the values into the arrays from the list of tasks.

        int arraysize = results.size();

        Date[] listDates = new Date[arraysize];
        String[] listTags = new String[arraysize];
        String[] listCourses = new String[arraysize];
        String[] listTitles = new String[arraysize];

        for(int j = 0; j < results.size(); j++){
            listDates[j] = (Date) results.get(j).getDate();
            listTags[j] = results.get(j).getTag();
            listCourses[j] = results.get(j).getCourse();
            listTitles[j] = results.get(j).getTitle();
        }

        //Initializing the ListView, using the ItemAdapter to extrapolate the items in the arrays
        //into the components of the listview.

        ListView listView = (ListView) findViewById(R.id.list);

        ItemAdapter itemAdapter = new ItemAdapter(this,listDates,listTags,listCourses,listTitles);
        listView.setAdapter(itemAdapter);
    }
}