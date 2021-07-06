package com.example.parkapp.util;

public class ParkingSpots {

    private static volatile int availableSpotsNR;
    private static volatile boolean[] spotAvailabilityArray;
    public ParkingSpots(int lenght) {
        this.availableSpotsNR = lenght;
        this.spotAvailabilityArray = new boolean[lenght] ;
        for(int i = 0; i < lenght; i++){
            spotAvailabilityArray[i] = false;
        }
    }


    public ParkingSpots() {
        this.availableSpotsNR = 0;
    }

    public int getAvailableSpotsNR() {
        return availableSpotsNR;
    }

    public void setAvailableSpotsNR(int availableSpotsNR) {
        this.availableSpotsNR = availableSpotsNR;
    }

    public void increaseAvailableSpots(){
        this.availableSpotsNR++;
    }

    public boolean getSpotAvailabilityArray(int idx) {
        return spotAvailabilityArray[idx];
    }

    public void setSpotAvailabilityArray(int idx, boolean status) {
        this.spotAvailabilityArray[idx] = status;
    }

    public void decreaseAvailableSpots(){
        this.availableSpotsNR--;
    }

}
