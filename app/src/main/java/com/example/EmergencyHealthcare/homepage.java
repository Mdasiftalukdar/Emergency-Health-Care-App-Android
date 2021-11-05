package com.example.EmergencyHealthcare;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.EmergencyHealthcare.CountryData.CountryDataActivity;

import com.example.EmergencyHealthcare.auth.LoginActivity;
import com.example.EmergencyHealthcare.auth.Profile;
import com.example.EmergencyHealthcare.auth.SignupActivity;
import com.example.EmergencyHealthcare.gov_hospital.AppointmentCheck;
import com.example.EmergencyHealthcare.myths.MythsActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


import org.json.JSONException;
import org.json.JSONObject;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    Context mContext;
//=============================== Menu bar==================
    DrawerLayout drawerLayout ;
    NavigationView navigationView;
    Toolbar toolbar;



    //global
    private TextView tvTotalConfirmed,tvTotalDeaths,tvTotalRecovered,tvTodayDeaths,tvTodaycases,tvActive,tvCritical,coronabd;

    //bd
    private TextView  tvTotalCOnfirmedbd,tvTotalDeathsbd,recoveredbd,tvTodaybdDeaths,tvTodaybdCases,tvBdActive,tvBdCritical;

    private ImageView about ;
    //progressDialog

 //   final progressBar progressBar = new progressBar(homepage.this);

    ProgressDialog progressDialog;



    RelativeLayout btn_1;
    RelativeLayout btn_2;
    RelativeLayout btn_3;
    RelativeLayout btn_4;
    RelativeLayout btn_5;
    RelativeLayout btn_6;
    RelativeLayout btn_7;
    RelativeLayout btn_8;
    Button em_call_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        //===================== creating overlap menu ==================

        //--------------------------- Hooks ---------------------------

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar3);

        //---------------------------- ToolBar -------------------------
            setSupportActionBar(toolbar);
          // Navigation drawer menu

       navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);






       //Clickable activity transaction ...
       btn_1 = (RelativeLayout)findViewById(R.id.btn1);
       btn_2 = (RelativeLayout)findViewById(R.id.btn2);
       btn_3 = (RelativeLayout)findViewById(R.id.btn3);
       btn_5 = (RelativeLayout)findViewById(R.id.btn5);
       btn_6 = (RelativeLayout)findViewById(R.id.btn6);
       btn_4 = (RelativeLayout)findViewById(R.id.donation_btn);
       btn_7 = (RelativeLayout)findViewById(R.id.btn7);
       btn_8 =(RelativeLayout)findViewById(R.id.btn8);
     //  em_call_btn = (Button)findViewById(R.id.emg_call_btn);
        //about = (ImageView)findViewById(R.id.about);
        //coronabd = (TextView) findViewById(R.id.coronabd);
       //OnCLick

//        about.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(homepage.this,AboutActicity.class);
//                startActivity(intent);
//            }
//        });

//        coronabd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://corona.gov.bd/"));
//                startActivity(intent);
//            }
//        });

       btn_1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               Intent intent = new Intent(homepage.this,covid_btn.class);

               startActivity(intent);
           }
       });
       btn_2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(homepage.this,doc_em_btn.class);
               startActivity(intent);
           }
       });


       btn_3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(homepage.this,sym_prv_btn.class);
               startActivity(intent);
           }
       });

       btn_4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(homepage.this,Donation.class);
               startActivity(intent);
           }
       });

       btn_5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(homepage.this,news_activity.class);
               startActivity(intent);
           }
       });

      btn_6.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(homepage.this, CountryDataActivity.class);
              startActivity(intent);
          }
      });

      btn_7.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(homepage.this, MythsActivity.class);
              startActivity(intent);
          }
      });

      btn_8.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.prothomalo.com/live"));
              startActivity(intent );
          }
      });


//      em_call_btn.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:+10655"));
//              startActivity(intent);
//          }
//      });

        //global
        tvTotalConfirmed = findViewById(R.id.tvTotalConfirmed);
        tvTotalDeaths = findViewById(R.id.tvTotalDead);
        tvTotalRecovered = findViewById(R.id.tvTotalRecoverd);
        tvTodaycases = findViewById(R.id.tvTodayCases);
        tvTodayDeaths = findViewById(R.id.todayDeaths);
        tvActive = findViewById(R.id.tvActive);
        tvCritical = findViewById(R.id.global_critical);

        //initialaizing bd

        tvTotalCOnfirmedbd = findViewById(R.id.tvTotalConfirmedbd);
        tvTotalDeathsbd = findViewById(R.id.tvTotalDeadbd);
        recoveredbd  = findViewById(R.id.tvTotalRecoverdbd);

        tvTodaybdCases = findViewById(R.id.tvTodayBDCases);
        tvTodaybdDeaths = findViewById(R.id.tvTodayBdDeaths);
        tvBdActive = findViewById(R.id.tvBdactive);
        tvBdCritical = findViewById(R.id.tvBdCritical);

        getBdData();

        getData();
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

    private void getData(){
        progressDialog = new ProgressDialog(homepage.this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.progress_dialog);
       progressDialog.getWindow().setBackgroundDrawableResource(
              android.R.color.transparent
       );
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvTotalConfirmed.setText(jsonObject.getString("cases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTotalRecovered.setText(jsonObject.getString("recovered"));
                    tvTodaycases.setText(jsonObject.getString("todayCases"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvCritical.setText(jsonObject.getString("critical"));
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }
        });
        queue.add(stringRequest);


    }

    private void getBdData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url =" https://corona.lmao.ninja/v2/countries/Bangladesh";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvTotalCOnfirmedbd.setText(jsonObject.getString("cases"));
                    tvTotalDeathsbd.setText(jsonObject.getString("deaths"));
                    recoveredbd.setText(jsonObject.getString("recovered"));
                    tvTodaybdCases.setText(jsonObject.getString("todayCases"));
                    tvTodaybdDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvBdActive.setText(jsonObject.getString("active"));
                    tvBdCritical.setText(jsonObject.getString("critical"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       progressDialog.dismiss();
                    }
                },4000);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());

            }
  });

        queue.add(stringRequest);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.nav_logout){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();


        }

        if(id == R.id.nav_about){


            Intent intent = new Intent(homepage.this, AboutActivity.class);

            startActivity(intent);


        }

        if(id == R.id.nav_SignUp){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
            finish();


        }
        if(id == R.id.nav_profile){


            Intent intent = new Intent(homepage.this, Profile.class);

            startActivity(intent);


        }

        if(id == R.id.nav_appointment_history){


            Intent intent = new Intent(homepage.this, AppointmentCheck.class);

            startActivity(intent);


        }

        if(id == R.id.nav_home){


            Intent intent = new Intent(this, startHomePage.class);
            startActivity(intent);
            finish();


        }


        return true;

    }
}
