package com.example.expensescalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UProperty;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateSpending extends AppCompatActivity implements SpendingAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    DatabaseReference ref;
    ValueEventListener listener;
    private View rView;
    List<Member> memberList;
    SpendingAdapter spendingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_spending);

        recyclerView = findViewById(R.id.recyclerSpending);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        memberList = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("Expenses");
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    Member m = child.getValue(Member.class);
                    memberList.add(m);
                }
                spendingAdapter = new SpendingAdapter(UpdateSpending.this, memberList);
                recyclerView.setAdapter(spendingAdapter);
                spendingAdapter.setOnItemClickListener(UpdateSpending.this);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(listener);
    }

    @Override
    public void Onclick(int position) {
        Intent intent = new Intent(this, FirebaseSpending.class);
        intent.putExtra("year", memberList.get(position).getYear().toString());
        intent.putExtra("month", memberList.get(position).getMonth().toString());
        if (memberList.get(position).getSpending()!=null)
            intent.putExtra("spending", memberList.get(position).getSpending().toString());
        startActivity(intent);
    }
}
