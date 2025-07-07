package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    EditText editTextTaskTitle, editTextTaskDescription, editTextDuration;
    Button buttonSave, buttonPickDate;
    TextView textViewDeadlineDisplay;
    String selectedDeadline = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle);
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        editTextDuration = findViewById(R.id.editTextDuration);
        buttonSave = findViewById(R.id.buttonSave);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        textViewDeadlineDisplay = findViewById(R.id.textViewDeadlineDisplay);

        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddTaskActivity.this,
                        (view, year1, month1, dayOfMonth) -> {
                            selectedDeadline = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                            textViewDeadlineDisplay.setText(selectedDeadline);
                        },
                        year, month, day
                );
                datePickerDialog.show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTaskTitle.getText().toString().trim();
                String desc = editTextTaskDescription.getText().toString().trim();
                String duration = editTextDuration.getText().toString().trim();

                if (title.isEmpty()) {
                    Toast.makeText(AddTaskActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("task_title", title);
                resultIntent.putExtra("task_desc", desc);
                resultIntent.putExtra("task_deadline", selectedDeadline);
                resultIntent.putExtra("task_duration", duration);

                setResult(RESULT_OK, resultIntent);
                finish(); // close the activity and return to MainActivity
            }
        });
    }
}
