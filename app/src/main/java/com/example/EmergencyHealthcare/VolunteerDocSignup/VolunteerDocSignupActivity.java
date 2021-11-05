package com.example.EmergencyHealthcare.VolunteerDocSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.EmergencyHealthcare.Donation;
import com.example.EmergencyHealthcare.R;
import com.example.EmergencyHealthcare.progressBar;
import com.example.EmergencyHealthcare.startHomePage;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VolunteerDocSignupActivity extends AppCompatActivity {

    TextInputLayout Docname,workPlace,docPhone,docEmail;
    Button  regBtn;
    FirebaseDatabase database;
    DatabaseReference reference;

    progressBar progressBar = new progressBar(VolunteerDocSignupActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_doc_signup);
        Docname = findViewById(R.id.doctorName);
        workPlace = findViewById(R.id.workplace);
        docPhone = findViewById(R.id.doctorPhone);
        docEmail = findViewById(R.id.docEmail);

        regBtn = findViewById(R.id.docReg);



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                database = FirebaseDatabase.getInstance();
                reference = database.getReference("docsignup");

                String name = Docname.getEditText().getText().toString();
                String workplace = workPlace.getEditText().getText().toString() ;
                String phone = docPhone.getEditText().getText().toString();
                String email = docEmail.getEditText().getText().toString();

                VolunteerHelperClass volunteerHelperClass = new VolunteerHelperClass(name,workplace,phone,email);



                if(TextUtils.isEmpty(name)){

                    Toast.makeText(VolunteerDocSignupActivity.this,"Name is empty",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(workplace)){
                    Toast.makeText(getApplicationContext(),"Hospital Name is Empty",Toast.LENGTH_SHORT).show();

                }
                else if(TextUtils.isEmpty(phone)){

                    Toast.makeText(getApplicationContext(),"Phone is Empty",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email)){

                    Toast.makeText(getApplicationContext(),"Email is Empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    reference.child(phone).setValue(volunteerHelperClass);
                    Toast.makeText(getApplicationContext(),"Informations are registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(VolunteerDocSignupActivity.this, startHomePage.class);
                    startActivity(intent);
                    finish();


                }


            }


        });

    }


}
