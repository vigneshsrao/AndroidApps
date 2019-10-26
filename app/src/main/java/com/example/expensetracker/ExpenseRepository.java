package com.example.expensetracker;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseRepository {
    private ExpenseDao mExpenseDao;
    private LiveData<List<Expense>> mAllExpenses;

    ExpenseRepository(Application application) {
        ExpenseRoomDatabase db = ExpenseRoomDatabase.getDatabase(application);
        mExpenseDao = db.expenseDao();
    }

    LiveData<List<Expense>> getAllExpenses() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Log.i("MUAHAHAHAHAHAH", mExpenseDao.getAllExpenses().getValue().toString());
                return null;
            }
        }.execute();
//        Log.i(ExpenseRepository.class.getSimpleName(), mAllExpenses.getValue().toString());
        return mAllExpenses;
    }

    public void insert (Expense expense) {
        new insertAsyncTask(mExpenseDao).execute(expense);
    }

    private class insertAsyncTask extends AsyncTask<Expense, Void, Void> {

        private ExpenseDao mAsyncTaskDao;

        insertAsyncTask(ExpenseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Expense... params) {
            String LOG_TAG = ExpenseRepository.class.getSimpleName();
            Log.d(LOG_TAG,params[0].toString());
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
