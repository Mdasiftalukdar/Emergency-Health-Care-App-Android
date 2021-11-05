package com.example.EmergencyHealthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.EmergencyHealthcare.AppointmentSet.AppointmentSet;
import com.example.EmergencyHealthcare.FindHospital.FindNearestHospital;

import com.example.EmergencyHealthcare.VolunteerDocSignup.VolunteerDocSignupActivity;
import com.example.EmergencyHealthcare.auth.LoginActivity;
import com.example.EmergencyHealthcare.auth.Profile;
import com.example.EmergencyHealthcare.auth.SignupActivity;
import com.example.EmergencyHealthcare.gov_hospital.AppointmentCheck;
import com.example.EmergencyHealthcare.Facts.Facts;
import com.example.EmergencyHealthcare.gov_hospital.FindAmbulance;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class startHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    RelativeLayout btn_NearHospital , btn_emAmbulance, btn_pandamicInfo, btn_support, btn_who, btn_faq, btn_set_appointment;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    String fName,email,phone;
    TextView ProfileName ;
    TextView ProfileEmail ;
    TextView ProfilePhone;

    String UserId;





    private static final int OK_MENU_ITEM = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_home_page);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        ProfileName = (TextView) findViewById(R.id.UserName);
        ProfileEmail = (TextView) findViewById(R.id.UserEmail);
        ProfilePhone = (TextView) findViewById(R.id.UserPhone);


        UserId = fAuth.getCurrentUser().getUid();


        fStore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {




                for (DocumentChange documentChange : documentSnapshots.getDocumentChanges()) {
                    String user = documentChange.getDocument().getId().toString();

                    if (user.equals(UserId)) {
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









        //--------------------------- Hooks ---------------------------

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar3);
//        logout = findViewById(R.id.nav_logout);

        //---------------------------- ToolBar -------------------------
        setSupportActionBar(toolbar);
        // Navigation drawer menu

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);




        btn_NearHospital = findViewById(R.id.btn_near_hospital);
        btn_emAmbulance = findViewById(R.id.btn_ambulance);
        btn_pandamicInfo = findViewById(R.id.btn_pandemic_info);
        btn_support = findViewById(R.id.btn_support_volunteer);
        btn_who = findViewById(R.id.btn_who_update);
        btn_faq = findViewById(R.id.btn_faq);
        btn_set_appointment = findViewById(R.id.btn_set_appointment);





        btn_emAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, FindAmbulance.class);
                startActivity(intent);

            }
        });

        btn_set_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, AppointmentSet.class);
                startActivity(intent);

            }
        });



        btn_pandamicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, homepage.class);
                startActivity(intent);

            }
        });

        btn_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, VolunteerDocSignupActivity.class);
                startActivity(intent);

            }
        });

        btn_NearHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, FindNearestHospital.class);
                startActivity(intent);
            }
        });
        btn_who.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.who.int/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btn_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(startHomePage.this, Facts.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.nav_logout){


            Intent intent = new Intent(startHomePage.this, LoginActivity.class);
            startActivity(intent);
            FirebaseAuth.getInstance().signOut();
            finish();
  }



        else if(id == R.id.nav_SignUp){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(startHomePage.this, SignupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();


        }

        else if(id == R.id.nav_about){


            Intent intent = new Intent(startHomePage.this, AboutActivity.class);

            startActivity(intent);


        }

        else if(id == R.id.nav_appointment_history){


            Intent intent = new Intent(startHomePage.this, AppointmentCheck.class);

            startActivity(intent);


        }

        else if(id == R.id.nav_profile){


            Intent intent = new Intent(startHomePage.this, Profile.class);

            startActivity(intent);


        }

        else if(id == R.id.nav_home){


            Intent intent = new Intent(startHomePage.this, startHomePage.class);
            startActivity(intent);
            finish();


        }


        return true;

    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(startHomePage.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }




}