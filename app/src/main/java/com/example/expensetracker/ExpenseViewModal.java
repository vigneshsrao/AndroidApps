package com.example.expensetracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseViewModal extends AndroidViewModel {

    private ExpenseRepository mRepository;

    private LiveData<List<Expense>> mAllExpenses;

    public ExpenseViewModal (Application application) {
        super(application);
        mRepository = new ExpenseRepository(application);
    }

    LiveData<List<Expense>> getAllExpenses() { return mRepository.getAllExpenses(); }

    public void insert(Expense expense) { mRepository.insert(expense); }
}
