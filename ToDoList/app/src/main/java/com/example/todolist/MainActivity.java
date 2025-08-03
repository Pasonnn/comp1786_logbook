package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd;
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    ArrayList<Task> taskList;
    public static final int ADD_TASK_REQUEST = 1;
    TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerView = findViewById(R.id.recyclerViewTasks);

        taskRepository = new TaskRepository(this);
        taskList = new ArrayList<>(taskRepository.getAllTasks());
        taskAdapter = new TaskAdapter(taskList, taskRepository);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("task_title");
            String desc = data.getStringExtra("task_desc");
            String deadline = data.getStringExtra("task_deadline");
            String duration = data.getStringExtra("task_duration");

            Task task = new Task(title, desc, deadline, duration);
            long id = taskRepository.insertTask(task);
            task.setId((int) id);
            taskAdapter.addTask(task);
        }
    }
}
