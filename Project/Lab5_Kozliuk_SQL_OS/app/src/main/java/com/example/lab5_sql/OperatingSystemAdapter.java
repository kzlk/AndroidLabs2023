package com.example.lab5_sql;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class OperatingSystemAdapter extends RecyclerView.Adapter<OperatingSystemAdapter.SubjectViewHolder> {
    private List<OperatingSystem> operatingSystemList;
    private int selectedItem = -1;

    private boolean doubleClick = false;

    private long lastClickTime = 0;

    public int getSelectedItemPosition() {
        return selectedItem;
    }

    public void clearSelectedItem() {
        selectedItem = -1;
        notifyDataSetChanged();
    }

    Button deleteButton;
    public OperatingSystemAdapter(List<OperatingSystem> operatingSystemList) {
        this.operatingSystemList = operatingSystemList;
    }
    public void removeItem(int position) {
        operatingSystemList.remove(position);
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
        OperatingSystem operatingSystem = operatingSystemList.get(position);
        holder.subjectNameTextView.setText(operatingSystem.getName());
        //holder.scopeTextView.setText("Обсяг: " + operatingSystem.getCompany());
        //holder.schedule.setText("Розклад: \n" + operatingSystem.getRating());
        //holder.rate.setText("Успішність " + operatingSystem.getArchitecture());
        //older.teacher.setText("Викладач: " + operatingSystem.getVersion());
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
                selectedItem = position; long clickTime = System.currentTimeMillis();
                notifyDataSetChanged(); // Refresh the RecyclerView to apply highlighting

                    // Double-click detected
                    doubleClick = true;
                    onDoubleClick(view, operatingSystemList.get(selectedItem));


            }
        });

    }

    private void onDoubleClick(View view, OperatingSystem system) {
        // Handle double-click, switch to a new window (activity) here
        // Example: Start a new activity
        Intent intent = new Intent(view.getContext(), OSDetailsActivity.class);
        intent.putExtra("osName", system.getName());
        intent.putExtra("ownerCompany", system.getCompany());
        intent.putExtra("version", system.getVersion());
        intent.putExtra("features", system.getArchitecture());
        intent.putExtra("marketStatus", system.getRating());
        view.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return operatingSystemList.size();
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


        }


    }
}
