package com.company;

public class CashRegister {

    private double money = 1000;

    public void workWithMoney(double tempMoney) {
        money += tempMoney;
    }

    public double getMoney() {
        return money;
    }
}
