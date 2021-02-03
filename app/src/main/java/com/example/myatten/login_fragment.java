package com.example.myatten;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myatten.R;

public class login_fragment extends Fragment {
    EditText username;
    EditText password;
    Button login;
    float v = 0;
    DatabaseHandler db;
    public static String user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);
        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.pass);
        login = root.findViewById(R.id.login);

        username.setTranslationX(800);
        password.setTranslationX(800);
        login.setTranslationX(800);

        username.setAlpha(v);
        password.setAlpha(v);
        login.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View arg0) {

//                Intent intent=new Intent(getActivity(),Dashboard.class);
//                startActivity(intent);
                   // TODO Auto-generated method stub
                  db=new DatabaseHandler(getContext());
                    user=username.getText().toString();
                   String pass1= password.getText().toString();

                   String StoredPassword =db.getregister(user);
                   if(pass1.equals(StoredPassword)){

                       Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_LONG).show();
                       Intent intent=new Intent(getActivity(),Dashboard.class);
                       startActivity(intent);
                   }
                   else{
                       Toast.makeText(getContext(), "Username/Password Incorrect", Toast.LENGTH_LONG).show();
                       username.setText("");
                       password.setText("");
                   }



            }
      });

        return root;


    }


}