package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder> {

    private final LayoutInflater mInflater;
    private List<Expense> mWords; // Cached copy of words

    ExpenseListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.expenseitem, parent, false);
        return new ExpenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        if (mWords != null) {
            Expense current = mWords.get(position);
//            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            String s = formatter.format(current.date);
            String s = "10/10/2010";
            holder.date.setText(s);
            holder.reason.setText(current.reason);
            holder.amount.setText(Float.toString(current.amount));
        }
    }

    void setWords(List<Expense> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
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
