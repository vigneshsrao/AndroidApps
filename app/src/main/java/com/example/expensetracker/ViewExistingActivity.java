package com.example.expensetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ViewExistingActivity extends AppCompatActivity {

    private ExpenseViewModal mExpenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_existing);


        mExpenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModal.class);

        RecyclerView recyclerView = findViewById(R.id.dispRecyclerView);
        final ExpenseListAdapter adapter = new ExpenseListAdapter(mExpenseViewModel.getAllExpenses().getValue());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
