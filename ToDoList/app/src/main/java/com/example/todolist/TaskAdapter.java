package com.example.todolist;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private TaskRepository taskRepository;

    public TaskAdapter(List<Task> taskList, TaskRepository taskRepository) {
        this.taskList = taskList;
        this.taskRepository = taskRepository;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.textViewTitle.setText(task.getTitle());
        holder.textViewDescription.setText(task.getDescription());
        holder.textViewDeadline.setText(task.getDeadline());
        holder.textViewDuration.setText(task.getDuration());

        // Mark as done
        if (task.isDone()) {
            holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // Delete button
        holder.buttonDelete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            Task t = taskList.get(pos);
            if (taskRepository != null && t.getId() != -1) {
                taskRepository.deleteTask(t.getId());
            }
            taskList.remove(pos);
            notifyItemRemoved(pos);
        });

        // Done button
        holder.buttonDone.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            Task t = taskList.get(pos);
            t.setDone(!t.isDone());
            if (taskRepository != null && t.getId() != -1) {
                taskRepository.updateTaskIsDone(t.getId(), t.isDone());
            }
            notifyItemChanged(pos);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
        notifyItemInserted(taskList.size() - 1);
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDescription, textViewDeadline, textViewDuration;
        Button buttonDelete, buttonDone;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDeadline = itemView.findViewById(R.id.textViewDeadline);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonDone = itemView.findViewById(R.id.buttonDone);
        }
    }
}
