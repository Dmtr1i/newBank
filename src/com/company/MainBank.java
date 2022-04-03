package com.company;

import java.util.ArrayList;
import java.util.List;

public class MainBank {

    public static final int numWorkers = 5;
    public static final int customersPeriod = 5;
    public static CashRegister cashRegister = new CashRegister();

    public static void main(String[] args) throws InterruptedException {
        List<Worker> workers = new ArrayList<>();
        for (int i = 1; i < numWorkers; i++) {
            workers.add(new Worker(i, cashRegister));
        }
        for(Worker employee:workers){
            employee.start();
        }
        while (true) {
            Customer cust = customerGenerator(customersPeriod);
            workers.get(getFreeWorker(workers)).addCustomer(cust);
        }
    }

    public static int getFreeWorker(List<Worker> workers) {
        int minId = 0;
        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).getCustomers().size() < workers.get(minId).getCustomers().size()) {
                minId = i;
            }
        }
        return minId;
    }

    public static Customer customerGenerator(int time) throws InterruptedException {
        Thread.sleep(time * 1000);
        return new Customer();
    }
}
