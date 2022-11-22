package com.company;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import  static java.lang.Thread.sleep;

public class StationController {

     static Station Copenhagen = new Station();
     Station Odense = new Station();
     static Station Aarhus = new Station();


    private static Lock lock = new ReentrantLock();
    private static Semaphore AtracksOccupied;
    private static Semaphore CtracksOccupied;


     static int number = 0;


    public static void main(String[] args) throws InterruptedException {

        int trains = 80;
        Copenhagen.setPassenger(1000);
        Aarhus.setTracks(3);
        Copenhagen.setTracks(6);

        AtracksOccupied = new Semaphore(3);
        CtracksOccupied = new Semaphore(1);



        for (int i = 0; i < trains; i++) {
            Thread object = new Thread(new Trains());
            object.setName("Train " + i);
            object.start();
        }
    }


    public static void Station() throws InterruptedException {

        while(Aarhus.getPassenger() < 1000) {

            int Traveling_Passengers = (int) (Math.random() * 100);

            CtracksOccupied.acquire();
            System.out.println(Thread.currentThread().getName() + " Arrived at the Aarhus station");
            System.out.println(Traveling_Passengers+" passengers are entering" +Thread.currentThread().getName());
            try{
                lock.lock();

                System.out.println(Copenhagen.getPassenger());

                        if(Copenhagen.getPassenger() - Traveling_Passengers <= 0 ){
                            System.out.println("this should happen");
                            Traveling_Passengers = Copenhagen.getPassenger();

                        if(Traveling_Passengers == 0){
                           System.out.println(Thread.currentThread().getName() + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@never left Aarhus.");
                           break;

                        }
                    } else {
                        Copenhagen.setPassenger(Copenhagen.getPassenger() - Traveling_Passengers);
                    }



            } finally {
                lock.unlock();
            }
            CtracksOccupied.release();
            System.out.println(Thread.currentThread().getName() + " Leaving at the Aarhus station with " + Traveling_Passengers);

            // Train moving to Aarhus
            sleep(1500);


            AtracksOccupied.acquire();
            System.out.println(Thread.currentThread().getName() + " Arrived at the Copenhagen station");
            System.out.println(Traveling_Passengers+" passengers are leaving" +Thread.currentThread().getName());
            try{
                lock.lock();
                Aarhus.setPassenger(Aarhus.getPassenger() + Traveling_Passengers);
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " Leaving at the Copenhagen station");
            AtracksOccupied.release();

            // Train Moving to Copenhagen (this is ''empty'' as we are only interested in passengers going to Aarhus)
            sleep(1500);

        }
         number++;

        //System.out.println(number + " this amount of trains were used");

        System.out.println("There is "+Aarhus.getPassenger()+ " passengers that arrived at Aarhus");

    }
}

