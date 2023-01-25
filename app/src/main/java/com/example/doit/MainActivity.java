package com.example.doit;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doit.Adapter.ToDoAdapter;
import com.example.doit.Model.ToDoModel;
import com.example.doit.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListner {

    private  RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;

    private List<ToDoModel> taskList;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(this);
        tasksRecyclerView.setAdapter(tasksAdapter);


        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

    }
    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

}