package com.example.expensetracker;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense expense);

    @Query("DELETE FROM expenses")
    void deleteAll();

    @Query("SELECT * from expenses ORDER BY id ASC")
    LiveData<List<Expense>> getAllExpenses();
}

