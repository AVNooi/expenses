package com.example.expensescalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<Member> expensesList;

    public MyAdapter(Context c, List<Member> e){
        context = c;
        expensesList = e;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Member curMember = expensesList.get(position);
        holder.year.setText(curMember.getYear().toString());
        if (curMember.getMonth()==1)
            holder.month.setText("January");
        else if (curMember.getMonth().equals(2))
            holder.month.setText("February");
        else if (curMember.getMonth()==3)
            holder.month.setText("March");
        else if (curMember.getMonth()==4)
            holder.month.setText("April");
        else if (curMember.getMonth()==5)
            holder.month.setText("May");
        else if (curMember.getMonth()==6)
            holder.month.setText("June");
        else if (curMember.getMonth()==7)
            holder.month.setText("July");
        else if (curMember.getMonth()==8)
            holder.month.setText("August");
        else if (curMember.getMonth()==9)
            holder.month.setText("September");
        else if (curMember.getMonth()==10)
            holder.month.setText("October");
        else if (curMember.getMonth().equals(11))
            holder.month.setText("November");
        else if (curMember.getMonth().equals(12))
            holder.month.setText("December");
        holder.budget.setText("RM"+curMember.getBudget().toString());
        if (curMember.getSpending()!=null)
            holder.spending.append("RM"+curMember.getSpending()+"");
        if (curMember.getSpending()!=null)
            holder.saving.append("RM"+(curMember.getBudget()-curMember.getSpending())+"");
        else
            holder.saving.append("RM"+curMember.getBudget().toString());
    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView year, month, saving, spending, budget;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            year = itemView.findViewById(R.id.tvYear);
            month = itemView.findViewById(R.id.tvMonth);
            budget = itemView.findViewById(R.id.tvBudget);
            saving = itemView.findViewById(R.id.tvSaving);
            spending = itemView.findViewById(R.id.tvSpending);
        }
    }
}
