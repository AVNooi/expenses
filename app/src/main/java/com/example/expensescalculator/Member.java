package com.example.expensescalculator;

public class Member {
    private Integer month, year, budget, spending;
    public Member() {
    }

    public Member (Integer year, Integer month, Integer budget, Integer spending){
        this.year=year;
        this.month=month;
        this.budget=budget;
        this.spending=spending;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getSpending() {
        return spending;
    }

    public void setSpending(Integer spending) {
        this.spending = spending;
    }
}
