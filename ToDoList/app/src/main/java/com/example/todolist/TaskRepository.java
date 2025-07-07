package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private TaskDbHelper dbHelper;

    public TaskRepository(Context context) {
        dbHelper = new TaskDbHelper(context);
    }

    public long insertTask(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskDbHelper.COLUMN_TITLE, task.getTitle());
        values.put(TaskDbHelper.COLUMN_DESCRIPTION, task.getDescription());
        values.put(TaskDbHelper.COLUMN_DEADLINE, task.getDeadline());
        values.put(TaskDbHelper.COLUMN_DURATION, task.getDuration());
        values.put(TaskDbHelper.COLUMN_IS_DONE, task.isDone() ? 1 : 0);
        long id = db.insert(TaskDbHelper.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void updateTaskIsDone(int id, boolean isDone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskDbHelper.COLUMN_IS_DONE, isDone ? 1 : 0);
        db.update(TaskDbHelper.TABLE_NAME, values, TaskDbHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TaskDbHelper.TABLE_NAME, TaskDbHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(TaskDbHelper.COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskDbHelper.COLUMN_TITLE));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(TaskDbHelper.COLUMN_DESCRIPTION));
                String deadline = cursor.getString(cursor.getColumnIndexOrThrow(TaskDbHelper.COLUMN_DEADLINE));
                String duration = cursor.getString(cursor.getColumnIndexOrThrow(TaskDbHelper.COLUMN_DURATION));
                boolean isDone = cursor.getInt(cursor.getColumnIndexOrThrow(TaskDbHelper.COLUMN_IS_DONE)) == 1;
                tasks.add(new Task(id, title, desc, deadline, duration, isDone));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }
} 