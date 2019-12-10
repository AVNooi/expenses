package com.example.expensescalculator;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment {

    private Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, button10, buttonC, buttonEqual;
    private TextView tView;
    private View mView;

    float mValueOne, mValueTwo;

    boolean Addition, Subtraction, Multiplication, Division;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_calculator, container, false);

        button0 = mView.findViewById(R.id.button0);
        button1 = mView.findViewById(R.id.button1);
        button2 = mView.findViewById(R.id.button2);
        button3 = mView.findViewById(R.id.button3);
        button4 = mView.findViewById(R.id.button4);
        button5 = mView.findViewById(R.id.button5);
        button6 = mView.findViewById(R.id.button6);
        button7 = mView.findViewById(R.id.button7);
        button8 = mView.findViewById(R.id.button8);
        button9 = mView.findViewById(R.id.button9);
        button10 = mView.findViewById(R.id.button10);
        buttonAdd = mView.findViewById(R.id.buttonAdd);
        buttonSub = mView.findViewById(R.id.buttonSub);
        buttonMul = mView.findViewById(R.id.buttonMul);
        buttonDiv = mView.findViewById(R.id.buttonDiv);
        buttonC = mView.findViewById(R.id.buttonClear);
        buttonEqual = mView.findViewById(R.id.buttonEqual);
        tView = mView.findViewById(R.id.calculatorScreen);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + "0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tView == null) {
                    tView.setText("");
                } else {
                    mValueOne = Float.parseFloat(tView.getText() + "");
                    Addition = true;
                    tView.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(tView.getText() + "");
                Subtraction = true;
                tView.setText(null);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(tView.getText() + "");
                Multiplication = true;
                tView.setText(null);
            }
        });

        buttonDiv   .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(tView.getText() + "");
                Division = true;
                tView.setText(null);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(tView.getText() + "");

                if (Addition == true) {
                    tView.setText(mValueOne + mValueTwo + "");
                    Addition = false;
                }

                if (Subtraction == true) {
                    tView.setText(mValueOne - mValueTwo + "");
                    Subtraction = false;
                }

                if (Multiplication == true) {
                    tView.setText(mValueOne * mValueTwo + "");
                    Multiplication = false;
                }

                if (Division == true) {
                    tView.setText(mValueOne / mValueTwo + "");
                    Division = false;
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText("");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText() + ".");
            }
        });

        return mView;
    }
}
