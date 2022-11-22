package com.company;

public class Trains implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println( Thread.currentThread().getName() + " is on the tracks!");

            StationController.Station();
        }
        catch (Exception e) {
            System.out.println("Exception is caught" + e);
        }
    }
}
