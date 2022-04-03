package com.company;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread{

    public Worker(int id, CashRegister cashRegister) {
        customers = new ArrayList<>();
        this.idb = id;
        this.cashRegister = cashRegister;
        isActive = true;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    private List<Customer> customers;

    private CashRegister cashRegister;

    private int idb;

    private boolean isActive;

    public void addCustomer(Customer cust) {
        synchronized (customers) {
            customers.add(cust);
            customers.notify();
        }
    }

    public synchronized void workWithCustomer() throws InterruptedException {
        if (customers.size() > 0) {
            Customer customer = customers.get(0);
            System.out.println("Работник №" + idb + " начал работу с клиентом! Заполняем необходимые документы!\n\n");
            if (customer.getMoney() > 0) {
                cashRegister.workWithMoney(customer.getMoney());
                System.out.println("Работник №" + idb + " завыполняет операцию на сумму " + customer.getMoney() + " баланс банка: " + cashRegister.getMoney());
                customers.remove(customer);
                Thread.sleep(customer.getServiceTime());
            } else {
                if (customer.getMoney() > cashRegister.getMoney()) {
                    System.out.println("В банке не достаточно средств!\n\n");
                    customers.remove(customer);
                } else {
                    cashRegister.workWithMoney(customer.getMoney());
                    System.out.println("Работник №" + idb + " завыполняет операцию на сумму " + customer.getMoney() + " баланс банка: " + cashRegister.getMoney());
                    customers.remove(customer);
                    Thread.sleep(customer.getServiceTime());
                }
            }
        } else {
            synchronized (customers) {
                customers.wait();
            }
        }
    }



    @Override
    public void run() {
        while (isActive) {
            try {
                workWithCustomer();
            } catch (InterruptedException e) {
                System.out.println("error in working with customer: " + e);
            }
        }
    }
}
