package com.example.expensetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

@Entity(tableName = "expenses")
@TypeConverters({Converters.class})
public class Expense {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "date")
    public Date date;

    @NonNull
    @ColumnInfo(name = "reason")
    public String reason;

    @NonNull
    @ColumnInfo(name = "amount")
    public float amount;

    public Expense(Date date, String reason, float amount, Context ctx){
        this.date = date;
        this.reason = reason;
        this.amount = amount;

        String ID_KEY = "IDKEY";
        String sharedPrefFile = "com.example.expensetracker.savedidx";
        SharedPreferences mPreferences = ctx.getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        id = mPreferences.getInt(ID_KEY, 0);
        id = id + 1;
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(ID_KEY, id);
        preferencesEditor.apply();

        String LOG_TAG=Expense.class.getSimpleName();
        Log.d(LOG_TAG, ""+id+" "+date.toString()+" "+reason+" "+amount+" ");

    }

    public Expense(Date date, String reason, float amount){}

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", amount=" + amount +
                '}';
    }
}
