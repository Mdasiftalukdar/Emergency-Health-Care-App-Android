package com.example.EmergencyHealthcare.FindHospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.EmergencyHealthcare.R;
import com.example.EmergencyHealthcare.progressBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class FindNearestHospital extends AppCompatActivity {

    final com.example.EmergencyHealthcare.progressBar progressBar = new progressBar(FindNearestHospital.this);
    private RecyclerView hospital_recycler;
    private ArrayList<HospitalDataItems> hospitalDataItems = new ArrayList<>();
    private HospitalDataAdapter hospitalDataAdapter;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_nearest_hospital);
        progressBar.startLoadingDialog();
        hospital_recycler = findViewById(R.id.hospitalRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FindNearestHospital.this);
        hospital_recycler.setLayoutManager(linearLayoutManager);
        hospitalDataAdapter = new HospitalDataAdapter(FindNearestHospital.this,hospitalDataItems);

        reference = FirebaseDatabase.getInstance().getReference().child("Hospital");
        reference.keepSynced(true);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectFirebaseData((Map<String , Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void collectFirebaseData(Map<String, Object> value) {

        for(Map.Entry<String, Object> entry : value.entrySet()){
            Map SingleData = (Map) entry.getValue();
            hospitalDataItems.add(new HospitalDataItems(String.valueOf(SingleData.get("hospital_name")),String.valueOf(SingleData.get("address")),String.valueOf(SingleData.get("phone"))));



            //            Log.d("DEBUGLOG", String.valueOf(singleData.get("Name")));
            //            Log.d("DEBUGLOG", String.valueOf(singleData.get("phone")));
        }
        hospital_recycler.setAdapter(hospitalDataAdapter);
        progressBar.dismissDialog();
    }
}