package CabServices;

public class InvoiceService {
    private static final int COST_PER_MINUTE =1;
    private static final int MINIMUM_COST_PER_KILOMETER =10;
    private static final double MINIMUM_FARE = 5;

    public double calculateFare(double distance, int time){
        double totalFare =  distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(totalFare, MINIMUM_FARE);
    }
}
