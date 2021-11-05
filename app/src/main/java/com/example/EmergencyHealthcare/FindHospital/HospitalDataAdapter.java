package com.example.EmergencyHealthcare.FindHospital;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EmergencyHealthcare.R;

import java.util.ArrayList;

public class HospitalDataAdapter extends RecyclerView.Adapter<HospitalDataAdapter.ViewHolder>
{
    public  Context mContext;
    public  ArrayList<HospitalDataItems>hostpitalDataItems;


    public HospitalDataAdapter(Context mContext, ArrayList<HospitalDataItems> hostpitalDataItems) {
        this.mContext = mContext;
        this.hostpitalDataItems = hostpitalDataItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_nearest_hospital, parent, false);
        return new HospitalDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HospitalDataItems hospitalDataItems = hostpitalDataItems.get(position);

        TextView HospitalName= holder.HospitalName;
        TextView address = holder.address;
        TextView NtlcontactNumber = holder.NtlcontactNumber;
        RelativeLayout nationalCallBtn =  holder.nationalCallBtn;

        HospitalName.setText(hospitalDataItems.getHospitalName());
        address.setText(hospitalDataItems.getHospitalAdress());
        NtlcontactNumber.setText(hospitalDataItems.getHospitalContactNumber());

        nationalCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+hospitalDataItems.getHospitalContactNumber()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hostpitalDataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address,NtlcontactNumber,HospitalName;
        RelativeLayout nationalCallBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.ner_hospitaAddress);
            HospitalName = itemView.findViewById(R.id.ner_hospitalName);
            NtlcontactNumber = itemView.findViewById(R.id.ner_hospitalContact);
            nationalCallBtn = itemView.findViewById(R.id.ner_call_hospital_list_btn);
        }
    }
}