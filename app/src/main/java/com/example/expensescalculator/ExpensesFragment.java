package com.example.expensescalculator;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ExpensesFragment extends Fragment implements View.OnClickListener {
    private Button btnBudget, btnSpending;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_expenses, container, false);

        btnBudget=(Button)mView.findViewById(R.id.budgetBtn);
        btnSpending=(Button)mView.findViewById(R.id.spendingBtn);

        btnBudget.setOnClickListener(this);
        btnSpending.setOnClickListener(this);

        return mView;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.spendingBtn){
            Intent intent = new Intent(getActivity(), UpdateSpending.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.budgetBtn){
            Intent intent = new Intent(getActivity(), AllocateBudget.class);
            startActivity(intent);
        }
    }
}
