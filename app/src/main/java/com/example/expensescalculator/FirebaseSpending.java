package com.example.expensescalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FirebaseSpending extends AppCompatActivity {
    private EditText etSpend;
    private Button confirmBtn;
    private TextView tvYearSpend, tvMonthSpend;
    private DatabaseReference databaseRef;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_spending);

        etSpend = (EditText)findViewById(R.id.etSpend);
        confirmBtn = (Button)findViewById(R.id.cnfmButton);
        final String year = getIntent().getStringExtra("year");
        final String month = getIntent().getStringExtra("month");
        tvYearSpend = (TextView) findViewById(R.id.tvYearSpend);
        tvYearSpend.setText("Year: " + getIntent().getStringExtra("year"));
        tvMonthSpend = (TextView) findViewById(R.id.tvMonthSpend);
        tvMonthSpend.setText("Month: " + getIntent().getStringExtra("month"));
        String spendInput = ((etSpend.getText().toString().trim()));

        DatabaseReference databaseReference;
        databaseReference= FirebaseDatabase.getInstance().getReference("Expenses");
        ValueEventListener listener;

        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren())
                {
                    Member member = child.getValue(Member.class);

                    if(member.getYear() == Integer.parseInt(year) && member.getMonth() == Integer.parseInt(month))
                    {
                        key = child.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        databaseReference.addValueEventListener(listener);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferenceSpend;
                String strSpend = etSpend.getText().toString();
                if (strSpend.trim().equals(""))
                    etSpend.setError("Please fill in this field!");

                else {
                    int Spend, spend = Integer.parseInt((etSpend.getText().toString().trim()));
                    if (spend<1){
                        etSpend.setError("Must be a positive number!");
                    }
                    else if (getIntent().getStringExtra("spending") != null) {
                        Spend = Integer.parseInt(getIntent().getStringExtra("spending"));

                        databaseReferenceSpend = FirebaseDatabase.getInstance().getReference("Expenses").child(key).child("spending");

                        databaseReferenceSpend.setValue(spend + Spend);
                        Toast.makeText(FirebaseSpending.this, "Spending amount has been updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        databaseReferenceSpend = FirebaseDatabase.getInstance().getReference("Expenses").child(key).child("spending");

                        databaseReferenceSpend.setValue(spend);

                        Toast.makeText(FirebaseSpending.this, "Spending amount has been updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}
