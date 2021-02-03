package com.example.myatten;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class dashboard_fragment extends Fragment {

    TextView name_text;
    TextView date_text;
    TextView time_text;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private String date;
    private String time;
     DatabaseHandler db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        name_text = root.findViewById(R.id.name_text);
        date_text = root.findViewById(R.id.date);
        time_text = root.findViewById(R.id.time);
        db = new DatabaseHandler(getContext());
        Cursor cursor=db.alldata();
        if(cursor.getCount()==0){
            Toast.makeText(getContext(),"No data", Toast.LENGTH_SHORT).show();

        }
        else
        {
            while(cursor.moveToNext()){
                //Toast.makeText(getContext(),"")
                name_text.setText(cursor.getString(0));
            }
        }
            calendar = Calendar.getInstance();
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            timeFormat = new SimpleDateFormat("h:mm a");
            time = timeFormat.format(calendar.getTime());
            date = dateFormat.format(calendar.getTime());
            date_text.setText(date);
            time_text.setText(time);


          // name_text.setText(db.getname());
            return root;


        }
    }

