package com.example.EmergencyHealthcare.AppointmentSet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.EmergencyHealthcare.R;
import com.example.EmergencyHealthcare.VolunteerDocSignup.VolunteerDocSignupActivity;
import com.example.EmergencyHealthcare.VolunteerDocSignup.VolunteerHelperClass;
import com.example.EmergencyHealthcare.progressBar;
import com.example.EmergencyHealthcare.startHomePage;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppointmentSet extends AppCompatActivity {
    TextInputLayout Docname,workPlace,Date,Time;
    Button regBtn;
    FirebaseDatabase database;
    DatabaseReference reference;
    String UserId;
    FirebaseAuth fAuth;
    AppointmentSetHelper X;


    com.example.EmergencyHealthcare.progressBar progressBar = new progressBar(AppointmentSet.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_set);
        fAuth = FirebaseAuth.getInstance();
        UserId = fAuth.getCurrentUser().getUid();


        Docname = findViewById(R.id.doctorName);
        workPlace = findViewById(R.id.workplace);
        Date = findViewById(R.id.Date);
        Time = findViewById(R.id.Time);

        regBtn = findViewById(R.id.docReg);



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                database = FirebaseDatabase.getInstance();
                reference = database.getReference("AppointmentSet");

                String name = Docname.getEditText().getText().toString();
                String workplace = workPlace.getEditText().getText().toString() ;
                String date = Date.getEditText().getText().toString();
                String time = Time.getEditText().getText().toString();

                 X= new AppointmentSetHelper(name,workplace,date,time);



                if(TextUtils.isEmpty(name)){

                    Toast.makeText(getApplicationContext(),"Name is empty",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(workplace)){
                    Toast.makeText(getApplicationContext(),"Hospital Name is Empty",Toast.LENGTH_SHORT).show();

                }
                else if(TextUtils.isEmpty(date)){

                    Toast.makeText(getApplicationContext(),"Date field is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(time)){

                    Toast.makeText(getApplicationContext(),"Time field is Empty",Toast.LENGTH_SHORT).show();
                }
                else{



                    reference.child(UserId)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // get total available quest
                                  int  size = (int) dataSnapshot.getChildrenCount();

                                  size++;

                                    reference.child(UserId).child("0"+size).setValue(X);



                                    Toast.makeText(getApplicationContext(),"Informations are registered!! number of appointments "+size, Toast.LENGTH_LONG).show();
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });





                            //Toast.makeText(getApplicationContext(),"Informations are registered", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(getApplicationContext(), startHomePage.class);
                    startActivity(intent);
                    finish();




                }


            }


        });

    }

}