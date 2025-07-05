package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    EditText editTextTaskTitle, editTextTaskDescription;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle);
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTaskTitle.getText().toString().trim();
                String desc = editTextTaskDescription.getText().toString().trim();

                if (title.isEmpty()) {
                    Toast.makeText(AddTaskActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("task_title", title);
                resultIntent.putExtra("task_desc", desc);

                setResult(RESULT_OK, resultIntent);
                finish(); // close the activity and return to MainActivity
            }
        });
    }
}
