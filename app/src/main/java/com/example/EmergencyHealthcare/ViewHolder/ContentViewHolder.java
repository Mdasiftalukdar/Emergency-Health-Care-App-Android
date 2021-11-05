package com.example.EmergencyHealthcare.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EmergencyHealthcare.Model.Content;
import com.example.EmergencyHealthcare.R;

import java.util.ArrayList;


public class ContentViewHolder extends RecyclerView.ViewHolder {

    ArrayList<Content> ques = new ArrayList<>();

    public TextView textQuestion;
    public TextView textAns;
    public TextView Textaddress;
    public TextView phoneOne,phoneTwo,phoneThree,phoneFour,phoneFive,phoneSix,phoneSeven;
    public Toolbar title;

    public ContentViewHolder(@NonNull View itemView) {
        super(itemView);
        textQuestion = (TextView)itemView.findViewById(R.id.textQuestion);
        textAns = (TextView)itemView.findViewById(R.id.textAns);
        Textaddress = (TextView)itemView.findViewById(R.id.address);
        title = (Toolbar) itemView.findViewById(R.id.toolbar);
    }


}
