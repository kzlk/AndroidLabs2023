package com.example.lab5_sql;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private List<Subject> subjectList;
    private int selectedItem = -1;

    public int getSelectedItemPosition() {
        return selectedItem;
    }

    public void clearSelectedItem() {
        selectedItem = -1;
        notifyDataSetChanged();
    }

    Button deleteButton;
    public SubjectAdapter(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
    public void removeItem(int position) {
        subjectList.remove(position);
        notifyItemRemoved(position);
    }
    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new SubjectViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Subject subject = subjectList.get(position);
        holder.subjectNameTextView.setText(subject.getName());
        holder.scopeTextView.setText("Обсяг: " + subject.getStudyHours());
        holder.schedule.setText("Розклад: \n" + subject.getSchedule());
        holder.rate.setText("Успішність " + subject.getSuccessRate());
        holder.teacher.setText("Викладач: " + subject.getTeacher());
        // Populate other fields as needed


        // Highlight the clicked item
        if (position == selectedItem) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.highlightColor));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Reset background
        }
        // Handle item click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItem = position;
                notifyDataSetChanged(); // Refresh the RecyclerView to apply highlighting
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView subjectNameTextView;
        TextView scopeTextView;

        TextView teacher;

        TextView rate;

        TextView schedule;
        // Define other TextViews for teacher, schedule, success rate

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectNameTextView = itemView.findViewById(R.id.textViewSubjectName);
            scopeTextView = itemView.findViewById(R.id.textViewScope);
            teacher = itemView.findViewById(R.id.textViewTeacher);
            rate = itemView.findViewById(R.id.textViewSuccessRate);
            schedule = itemView.findViewById(R.id.textViewSchedule);

        }


    }
}
