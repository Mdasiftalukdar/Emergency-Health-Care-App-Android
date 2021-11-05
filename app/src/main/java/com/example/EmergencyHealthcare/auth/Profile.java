package com.example.EmergencyHealthcare.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.EmergencyHealthcare.R;
import com.example.EmergencyHealthcare.startHomePage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {


    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    String fName,email,phone;
    TextView ProfileName ;
    TextView ProfileEmail ;
    TextView ProfilePhone;

    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        UserId = fAuth.getCurrentUser().getUid();


        Button edit = (Button) findViewById(R.id.edit);



        ProfileName =(TextView) findViewById(R.id.profileName);
        ProfileEmail =(TextView) findViewById(R.id.profileEmail);
        ProfilePhone=(TextView) findViewById(R.id.profilePhone);



        Log.d("TAG", "onSuccess: user current UID is " + UserId);


        fStore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e !=null)
                {

                }


                for (DocumentChange documentChange : documentSnapshots.getDocumentChanges())
                {
                    String user= documentChange.getDocument().getId().toString();

                    if(user.equals(UserId)) {
                        fName = documentChange.getDocument().getData().get("fName").toString();
                        email = documentChange.getDocument().getData().get("email").toString();
                        phone = documentChange.getDocument().getData().get("phone").toString();


                        Log.d("TAG", "onSuccess: user current UID is " + UserId);
                        Log.d("TAG", "onSuccess: all user IDs  " + user);
                        Log.d("TAG", "onSuccess: user profile is " + fName);
                        Log.d("TAG", "onSuccess: user email is " + email);
                        Log.d("TAG", "onSuccess: user phone is " + phone);


                        ProfileName.setText(fName);
                        ProfileEmail.setText(email);
                        ProfilePhone.setText(phone);
                    }
                }
            }
        });






        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               EditText Name = (EditText) findViewById(R.id.Name);
               EditText Phone = (EditText) findViewById(R.id.Phone);

               phone = Phone.getText().toString().trim();
                 fName = Name.getText().toString().trim();

                DocumentReference documentReference = fStore.collection("users").document(UserId);
                Map<String, Object> user = new HashMap<>();
                user.put("email", email);
                user.put("fName", fName);
                user.put("phone", phone);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "onSuccess: user profile is created for " + UserId);
                    }
                });
                startActivity(new Intent(Profile.this, startHomePage.class));




            }
        });







    }
}