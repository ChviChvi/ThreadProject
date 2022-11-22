package com.company;

public class Station {

    int passenger;
    String name;
    int tracks;

    public int getPassenger() {
        return passenger;
    }

    public synchronized void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }
}
