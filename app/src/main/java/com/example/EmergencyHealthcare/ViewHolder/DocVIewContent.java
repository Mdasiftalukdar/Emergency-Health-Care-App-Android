package com.example.EmergencyHealthcare.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EmergencyHealthcare.R;

public class DocVIewContent extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView contactNumber;

    public DocVIewContent(@NonNull View itemView) {
        super(itemView);

        name = (TextView)itemView.findViewById(R.id.docName);
        contactNumber = (TextView)itemView.findViewById(R.id.contactNumber);

    }
}
