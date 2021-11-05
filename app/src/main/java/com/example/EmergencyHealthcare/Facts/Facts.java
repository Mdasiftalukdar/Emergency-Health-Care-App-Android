package com.example.EmergencyHealthcare.Facts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.EmergencyHealthcare.R;
import com.example.EmergencyHealthcare.gov_hospital.GovHospitalAdapter;
import com.example.EmergencyHealthcare.gov_hospital.GovHospotalItems;
import com.example.EmergencyHealthcare.progressBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Facts extends AppCompatActivity {
    EditText hospitalSearchBox;

    private RecyclerView hospitalRecycler;
    private ArrayList<GovHospotalItems> govHospotalItems = new ArrayList<>();
    private GovHospitalAdapter hospitalAdapter;
    DatabaseReference reference;
    final progressBar progressBar = new progressBar(Facts.this);
    String UserId;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        fAuth = FirebaseAuth.getInstance();
        UserId = fAuth.getCurrentUser().getUid();

        hospitalSearchBox = findViewById(R.id.searchText_hospital);

        progressBar.startLoadingDialog();

        hospitalRecycler = findViewById(R.id.hospitalRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        hospitalRecycler.setLayoutManager(linearLayoutManager);
        hospitalAdapter = new GovHospitalAdapter(getApplicationContext(),govHospotalItems);


        hospitalSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("Facts");
        reference.keepSynced(true);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectFirebaseData((Map<String,Object>)dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void collectFirebaseData(Map<String,Object> value){
        for (Map.Entry<String ,Object>entry:value.entrySet()){
            Map SingleData = (Map) entry.getValue();
            govHospotalItems.add(new GovHospotalItems(String.valueOf(SingleData.get("Question")),"Fact details",String.valueOf(SingleData.get("Ans"))));
        }
        hospitalRecycler.setAdapter(hospitalAdapter);
        progressBar.dismissDialog();
    }
    private void filter(String keyword){
        ArrayList<GovHospotalItems> filterHospitalName = new ArrayList<>();

        for (GovHospotalItems item : govHospotalItems){
            if(item.getHospitalName().toLowerCase().contains(keyword.toLowerCase())){
                filterHospitalName.add(item);
            }
        }
        hospitalAdapter.filter(filterHospitalName);
    }
}
