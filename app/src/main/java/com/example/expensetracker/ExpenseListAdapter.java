package com.example.expensetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder> {

    private List<Expense> mWords; // Cached copy of words

    public ExpenseListAdapter(List<Expense> mWords) {
        this.mWords = mWords;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expenseitem, parent, false);
        return new ExpenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
            holder.date.setText(new SimpleDateFormat("yyyy-MM-dd").format(mWords.get(position).date));
            holder.reason.setText(mWords.get(position).reason);
            holder.amount.setText(Float.toString(mWords.get(position).amount));
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        return mWords.size();
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private final TextView date;
        private final TextView reason;
        private final TextView amount;

        private ExpenseViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.rvDate);
            reason = itemView.findViewById(R.id.rvReason);
            amount = itemView.findViewById(R.id.rvAmount);
        }
    }
}
