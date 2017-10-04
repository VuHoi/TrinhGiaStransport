package com.example.vukhachoi.testhorizontal;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    Calendar calendarStart;
int date=0,month=0,year=0;
    int size=5;
List<DayFragment> dayFragments;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarStart = Calendar.getInstance();
date=calendarStart.get(Calendar.DATE);
        month=calendarStart.get(Calendar.MONTH);
        year=calendarStart.get(Calendar.YEAR);
        dayFragments=new ArrayList<>();
        viewPager =  findViewById(R.id.viewpager);
viewPager.setOffscreenPageLimit(5);
        tabLayout =  findViewById(R.id.tabs);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(),size,calendarStart);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons( size,calendarStart);



    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createTabIcons(int  Size,Calendar CalendarStart) {

        Date date1 = null;


adapter.remove();
        calendarStart.set(year,month,date);
        for(int i=0;i<Size;i++)
        {
             date1 = CalendarStart.getTime();
            View tabOne =  LayoutInflater.from(this).inflate(R.layout.custom_title_calendar, null);
            TextView txtday=tabOne.findViewById(R.id.txtday);
            TextView txtmonth=tabOne.findViewById(R.id.txtMonth);
            TextView txtthu=tabOne.findViewById(R.id.txtThu);
            txtday.setText(date1.getDate()+"");
            SimpleDateFormat month_date = new SimpleDateFormat("MMM");
            txtmonth.setText(month_date.format(date1));
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.US);

            txtthu.setText(dayFormat.format(date1));
            tabLayout.getTabAt(i).setCustomView(tabOne);
            DayFragment dayFragment=new DayFragment();
            dayFragment.para(calendarStart.getTime());

            adapter.AddFrag(dayFragment,calendarStart.getTime());
            CalendarStart.add(Calendar.DATE,1);
        }


        calendarStart.set(year,month,date);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnDate) {
            ShowDialogDate();
        }
        if (id == R.id.btnContract) {
            ShowDialogMa();
        }

        return super.onOptionsItemSelected(item);
    }
int k=0;

    void ShowDialogDate()
    {

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.filter_date);

        Button btnOK=dialog.findViewById(R.id.btnOK);
        Button btnCancel=dialog.findViewById(R.id.btnCancel);
        dialog.show();
       final DatePicker datePicker2=dialog.findViewById(R.id.datePicker2);
         final DatePicker datePicker3=dialog.findViewById(R.id.datePicker3);



    calendarStart.set(year, month, date);
    Calendar cal2 = calendarStart;

    cal2.add(Calendar.DATE, size);

    int year1 = cal2.get(Calendar.YEAR);
    int month1 = cal2.get(Calendar.MONTH);
    int day1 = cal2.get(Calendar.DAY_OF_MONTH);
    datePicker2.updateDate(year, month, date);
    datePicker3.updateDate(year1, month1, day1);
    cal2.clone();




        btnOK.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

Calendar b =  Calendar.getInstance();
                getDatePart(calendarStart,datePicker2);
                getDatePart(b,datePicker3);
                date=datePicker2.getDayOfMonth();
                month=datePicker2.getMonth();
                year=datePicker2.getYear();
                calendarStart.set(year,month,date);
                long diff = b.getTimeInMillis() - calendarStart.getTimeInMillis();
                size = (int) (diff / (24 * 60 * 60 * 1000));

                adapter = new ViewPagerAdapter(getSupportFragmentManager(),size,calendarStart);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);

                createTabIcons( size,calendarStart);

dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.dismiss();
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void ShowDialogMa()
    {

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.filter_contract);
        AutoCompleteTextView edtMa=dialog.findViewById(R.id.edtMa);
        String[] countries = getResources().getStringArray(R.array.auto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        edtMa.setAdapter(adapter);
        Button btnOK=dialog.findViewById(R.id.btnOK_Ma);

        Button btnCancel=dialog.findViewById(R.id.btnCancel_Ma);
        dialog.show();






        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.dismiss();
            }
        });



    }
     void getDatePart(Calendar cal,DatePicker datePicker){
        cal.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second

                                    // return the date part
    }

    public  Calendar getDateFromDatePicket(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }
}
