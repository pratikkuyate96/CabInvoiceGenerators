package CabServices.service;

import CabServices.RideCategory;

public class Ride {

    public  double distance;
    public  int time;
    public RideCategory rideCategory;

    public Ride(double distance, int time, RideCategory rideCategory) {
        this.distance=distance;
        this.time=time;
        this.rideCategory = rideCategory;
    }

}
