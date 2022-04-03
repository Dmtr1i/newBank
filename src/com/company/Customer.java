package com.company;

public class Customer {

    public Customer() {
        this.money = Math.ceil((Math.random() * 500 - 250) * 100) / 100;
        this.serviceTime = (int)(Math.random() * 10 + 5);
    }

    private double money;

    private int serviceTime;

    public double getMoney() {
        return money;
    }

    public int getServiceTime() {
        return serviceTime;
    }
}
