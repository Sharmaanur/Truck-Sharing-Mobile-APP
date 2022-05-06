package com.suman.trucksharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewOrderActivity extends AppCompatActivity {
    Calendar calendar;
    int year, month, day;
    private int mHour,mMinute;
    String orderDate;
    EditText name, location;
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        name = findViewById(R.id.receiver_name);
        location = findViewById(R.id.ed_location);
        calendarView = findViewById(R.id.calender_view);
        orderDate = day+"/"+month+"/"+year;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                orderDate = day+"/"+month+"/"+year;
            }
        });
        findViewById(R.id.title_pickup_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(666);
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewOrderActivity.this, CreateOrderActivity.class);
                intent.putExtra("name", name.getText().toString().trim());
                intent.putExtra("date", orderDate);
                intent.putExtra("time", mHour+":"+mMinute );
                intent.putExtra("location", location.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if (id == 666) {
            return new TimePickerDialog(this, mopenSetListener, 12, 00, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int year, int month, int day) {
                    showDate(year, month+1, day);
                }
            };
    private TimePickerDialog.OnTimeSetListener mopenSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
        }
    };
    private void showDate(int year, int month, int day) {
        StringBuilder date = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);
        TextView txtDate = findViewById(R.id.title_pickup_date);
                txtDate.setText(new StringBuilder().append("Pickup date : ").append(date));
        orderDate = date.toString();
    }
}