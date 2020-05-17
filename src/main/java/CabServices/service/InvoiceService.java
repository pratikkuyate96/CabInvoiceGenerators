package CabServices.service;

import CabServices.RideCategory;
import CabServices.repository.RideRepository;

public class InvoiceService {

    public int COST_PER_MINUTE =1;
    public int MINIMUM_COST_PER_KILOMETER =10;
    public double MINIMUM_FARE = 5;
    private RideRepository rideRepository;

    public void setValue(RideCategory rideCategory) {
        COST_PER_MINUTE = rideCategory.minCostPerKM;
        MINIMUM_COST_PER_KILOMETER = rideCategory.costPerMin;
        MINIMUM_FARE = rideCategory.minimumFare;
    }

    public InvoiceService(){
    this.rideRepository = new RideRepository();
    }
    public double calculateFare(double distance, int time, RideCategory rideCategory){
        setValue(rideCategory);
        double totalFare =  distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare += this.calculateFare(ride.distance, ride.time, ride.rideCategory);
        }
        return new InvoiceSummary(rides.length,(int) totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }

}