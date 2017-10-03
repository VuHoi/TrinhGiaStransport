package com.example.vukhachoi.testhorizontal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.UpdateableFragment;
import model.Contract;
import sql.Databasehelper;

/**
 * Created by Vu Khac Hoi on 9/30/2017.
 */

public class DayFragment extends Fragment implements UpdateableFragment
         {
             Databasehelper myDatabase;
             SQLiteDatabase database;
LinearLayout lncontainer1;
             List<Contract>contracts;

    public  DayFragment()
    {

    }

    public Date getA() {
        return A;
    }

    Date A=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myDatabase = new Databasehelper(getActivity());
        myDatabase.Khoitai();
        database = myDatabase.getMyDatabase();
        View view = inflater.inflate(R.layout.dayfragment, container, false);
        contracts = new ArrayList<>();
        final NestedScrollView scrollView = view.findViewById(R.id.nestscrollview);
        scrollView.setFillViewport(true);
        scrollView.setNestedScrollingEnabled(true);
        lncontainer1 = view.findViewById(R.id.lncontainer1);






        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d");

        Cursor cursor =database.rawQuery("Select * from Car a LEFT JOIN  Contract b on a.IDContract=b.id where Time=?",new String[]{format.format(A)});
        cursor.moveToFirst();

       while (!cursor.isAfterLast())
       {
           try {


          Contract contract=new Contract(cursor.getString(7),cursor.getString(9),cursor.getString(8),cursor.getString(10),cursor.getString(0),cursor.getString(2),cursor.getString(1),cursor.getString(5));
          contracts.add(contract);
           }catch (Exception e){}
           Log.d("abc",cursor.getString(1));
           cursor.moveToNext();
       }

       for(int i=0;i<contracts.size();i++)
       { int h=0;
           View tabOne =  LayoutInflater.from(getActivity()).inflate(R.layout.itemcar, null);
           LinearLayout lnitem =tabOne.findViewById(R.id.lnitem);
           TextView txt=tabOne.findViewById(R.id.txtcar);

           for(int j=i;j<contracts.size();j++) {
               if (contracts.get(i).getIdcar().toString().equals(contracts.get(j).getIdcar().toString())) {

                   for(int k=0;k<i;k++)
                   {
                       if (contracts.get(k).getIdcar().toString().equals(contracts.get(i).getIdcar().toString())) {
                       h=1;
                       break;
                       }
                   }
                   if(h==1)break;
                   View item =  LayoutInflater.from(getActivity()).inflate(R.layout.item_contract, null);
                       final TextView txtName, txtchair, txtroute, txtime;
                       txtName = item.findViewById(R.id.txtName);
                       txtchair = item.findViewById(R.id.txtchair);
                       txtroute = item.findViewById(R.id.txtroute);
                       txtime = item.findViewById(R.id.txtime);

                       txtchair.setText(contracts.get(j).getNumberChair());
                       txtName.setText(contracts.get(j).getClientName());
                       txtroute.setText(contracts.get(j).getRoute());
                       txtime.setText(contracts.get(j).getTime());
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), txtName.getText().toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                       lnitem.addView(item, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                   txt.setText(contracts.get(i).getDriver()+" "+contracts.get(i).getTelephone());

               }
           }

if(h==0)
               lncontainer1.addView(tabOne ,0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));




       }
//adapter.notifyDataSetChanged();

        return view;
    }
    public  void para(Date a)
    {
        A=a;
    }

             @Override
             public void update(Date date) {
                 A=date;


             }
         }
