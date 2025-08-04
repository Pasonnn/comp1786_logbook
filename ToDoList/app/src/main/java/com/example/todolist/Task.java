// app/src/main/java/com/example/todolist/Task.java

package com.example.todolist;

public class Task {
    private String title;
    private String description;
    private boolean isDone;
    private String deadline;
    private String duration;

    public Task(String title, String description, String deadline, String duration) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.duration = duration;
        this.isDone = false;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isDone() { return isDone; }
    public void setDone(boolean done) { isDone = done; }
    public String getDeadline() { return deadline; }
    public String getDuration() { return duration; }
}
