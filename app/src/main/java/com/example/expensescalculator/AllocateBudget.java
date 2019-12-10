package com.example.expensescalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;

public class AllocateBudget extends AppCompatActivity {
    private EditText etYear, etMonth, etBudget;
    private Button cnfmButton;
    private DatabaseReference databaseRef;
    Member member;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_budget);

        etYear = (EditText)findViewById(R.id.etYear);
        etMonth = (EditText)findViewById(R.id.etMonth);
        etBudget = (EditText)findViewById(R.id.etBudget);
        cnfmButton = (Button) findViewById(R.id.confirmButton);
        member = new Member();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("Expenses");

        etYear.addTextChangedListener(textWatcher);
        etMonth.addTextChangedListener(textWatcher);
        etBudget.addTextChangedListener(textWatcher);

        cnfmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strYr = etYear.getText().toString();
                String strMth = etMonth.getText().toString();
                String strBdgt = etBudget.getText().toString();

                if (strYr.trim().equals("")) {
                    etYear.setError("Please fill in this field!");
                }

                else if (strMth.trim().equals("")) {
                    etMonth.setError("Please fill in this field!");
                }

                else if (strBdgt.trim().equals("")) {
                    etBudget.setError("Please fill in this field!");
                }

                else if (etYear.getText().length()>4){
                    etYear.setError("Invalid year!");
                }

                else{
                    final int year = Integer.parseInt((etYear.getText().toString().trim()));
                    final int month = Integer.parseInt((etMonth.getText().toString().trim()));
                    final int budget = Integer.parseInt((etBudget.getText().toString().trim()));
                    int curYear = calendar.get(Calendar.YEAR);
                    int curMonth = calendar.get(Calendar.MONTH);
                    if (year < curYear){
                        etYear.setError("Must be at least the current year " + curYear);
                    }

                    else if (month < 1 || month >12){
                        etMonth.setError("Invalid month!");
                    }

                    else if ((year == curYear) && (month < curMonth)){
                        etMonth.setError("Must be at least the current month ("+ 12 +") for " + curYear);
                    }

                    else if (budget<1){
                        etBudget.setError("Must be a positive number!");
                    }
                    else {
                        member.setYear(year);
                        member.setMonth(month);
                        member.setBudget(budget);
                        databaseRef.push().setValue(member);
                        Toast.makeText(AllocateBudget.this, "Allocated Budget added succesfully!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String yrInput = ((etYear.getText().toString().trim()));
            String mthInput = ((etYear.getText().toString().trim()));
            String bdgtInput = ((etYear.getText().toString().trim()));

            if (!TextUtils.isEmpty(yrInput) && !TextUtils.isEmpty(mthInput) && !TextUtils.isEmpty(bdgtInput)){
                cnfmButton.setEnabled(!TextUtils.isEmpty(yrInput) && !TextUtils.isEmpty(mthInput) && !TextUtils.isEmpty(bdgtInput));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
