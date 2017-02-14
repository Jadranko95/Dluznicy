package com.example.adrian.dluznicy;

/**
 * Created by Adrian on 07.02.2017.
 */

public class Debtor {
    private int id;
    private String debtName;
    private String debtSurname;
    private int debt;

    public Debtor() {}
    public Debtor(int id, String debtName, String debtSurname, int debt){
        this.id = id;
        this.debtName = debtName;
        this.debtSurname = debtSurname;
        this.debt = debt;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDebtName(){
        return debtName;
    }

    public String getDebtSurname(){
        return debtSurname;
    }

    public void setDebtName(String debtName){
        this.debtName = debtName;
    }

    public void setDebtSurname(String debtSurname){
        this.debtSurname = debtSurname;
    }
}
