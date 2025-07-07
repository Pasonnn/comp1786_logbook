package com.example.todolist;

public class Task {
    private String title;
    private String description;
    private boolean isDone;
    private String deadline;
    private String duration;
    private int id;

    public Task(int id, String title, String description, String deadline, String duration, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.duration = duration;
        this.isDone = isDone;
    }

    public Task(String title, String description, String deadline, String duration) {
        this(-1, title, description, deadline, duration, false);
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }
    public String getDeadline() { return deadline; }
    public String getDuration() { return duration; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
