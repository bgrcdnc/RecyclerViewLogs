package com.bugracdnc.sqlitelearning;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bugracdnc.sqlitelearning.databinding.RecyclerRowBinding;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.LogsViewHolder> {

    private final Logs logs;

    public LogsAdapter(Logs logs) {
        this.logs = logs;
    }

    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LogsViewHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsViewHolder holder, int position) {
        holder.binding.recyclerViewText.setText(logs.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public static class LogsViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerRowBinding binding;

        public LogsViewHolder(@NonNull RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
