package com.example.myatten;

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

public class signup_fragment extends Fragment {
    Button signup;
    EditText name;
    EditText reg_no;
    EditText password;
    EditText contact;
    EditText confirmpass;
    DatabaseHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.signup_fragment,container,false);
        signup=root.findViewById(R.id.signup);
        name=root.findViewById(R.id.name);
        reg_no=root.findViewById(R.id.register_number);
        password=root.findViewById(R.id.password_signup);
        contact=root.findViewById(R.id.contact);
        confirmpass=root.findViewById(R.id.password_conf);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Intent intent= new Intent(getActivity(),LoginActivity.class);
                //startActivity(intent);
                String edname = name.getText().toString();
                String edreg_no = reg_no.getText().toString();
                String edContact = contact.getText().toString();
                String edpass = password.getText().toString();
                String edConf = confirmpass.getText().toString();

                if(edname.equals("") && edreg_no.equals("") && edContact.equals("") && edpass.equals("") && edConf.equals("")){
                    Toast.makeText(getContext(),"Enter details",Toast.LENGTH_LONG).show();



                } else
                {

                    if(edConf.equals(edpass) ){


                        db = new DatabaseHandler(getContext());
                        RegisterData reg = new RegisterData();

                        reg.setName(edname);
                        reg.setReg_no(edreg_no);
                        reg.setContact(edContact);

                        reg.setPassword(edpass);
                        db.addregister(reg);

                        Toast.makeText(getContext(), "Registered", Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }
                    else{

                        Toast.makeText(getContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
                        password.setText("");
                        confirmpass.setText("");
                    }
                }
            }
        });
        return root;
    }
}
