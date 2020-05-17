package CabServices;

import CabServices.repository.RideRepository;
import CabServices.service.InvoiceService;
import CabServices.service.InvoiceSummary;
import CabServices.service.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService;
    RideRepository rideRepository;

    @Before
    public void setUp(){
        invoiceService = new InvoiceService();
        rideRepository = new RideRepository();

    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double fare = invoiceService.calculateFare(2.0, 5, RideCategory.NORMAL);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        double fare = invoiceService.calculateFare(0.5, 3, RideCategory.NORMAL);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = { new Ride(2.0, 5,RideCategory.NORMAL),
                         new Ride(0.1, 1,RideCategory.NORMAL)};
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_shouldReturnInvoiceSummary() {
        String userId = "pratik@";
        Ride[] rides = { new Ride(2.0, 5,RideCategory.NORMAL),
                         new Ride(0.1, 1,RideCategory.NORMAL)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenDistanceAndTimeForMultipleRidesForPremium_ShouldReturnInvoiceSummary() {
        Ride[] ride = {
                new Ride(2.0, 5, RideCategory.PREMIUM),
                new Ride(0.1, 1, RideCategory.PREMIUM)};
        InvoiceSummary invoiceSummary = invoiceService.calculateFare(ride);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserIdAndNormalAndPremiumRides_ShouldReturnInvoiceSummary() {
        String userId = "pratik@";
        Ride[] rides = {new Ride(2.0, 5, RideCategory.NORMAL),
                new Ride(2.0, 5, RideCategory.PREMIUM),
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 65);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

}