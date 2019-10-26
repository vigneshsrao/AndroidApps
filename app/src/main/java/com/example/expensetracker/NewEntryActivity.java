package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewEntryActivity extends AppCompatActivity {

    private EditText reason;
    private EditText date;
    private EditText amount;
    private ExpenseViewModal mExpenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        mExpenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModal.class);

        date = findViewById(R.id.dateView);
        reason = findViewById(R.id.reasonView);
        amount = findViewById(R.id.amountView);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        date.setText(parseDate(year, month, day));
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {

        String dateMessage = parseDate(year, month, day);
        date.setText(parseDate(year, month, day));

    }

    public String parseDate(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string +
                "/" + month_string + "/" + year_string);
        return dateMessage;
    }

    public void saveExpense(View view) {

        String dateString = date.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date inpDate = new Date();
        try {
            inpDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String inpReason = reason.getText().toString();
        float inpAmount = Float.parseFloat(amount.getText().toString());

        Expense expense = new Expense(inpDate, inpReason, inpAmount, this);
        mExpenseViewModel.insert(expense);

    }
}
