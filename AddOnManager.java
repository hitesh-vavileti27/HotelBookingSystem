import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOnManager {
    
    // Maps a Reservation ID to a List of selected Add-On Services
    private Map<String, List<AddOnService>> reservationAddOns;

    public AddOnManager() {
        this.reservationAddOns = new HashMap<>();
    }

    public void addServiceToReservation(String reservationId, AddOnService service) {
        // If this is the first add-on for this reservation, initialize the List
        reservationAddOns.putIfAbsent(reservationId, new ArrayList<>());
        
        // Add the service to the list
        reservationAddOns.get(reservationId).add(service);
        System.out.println("[Add-On] Added '" + service.getServiceName() + "' to Reservation " + reservationId);
    }

    public double calculateTotalAddOnCost(String reservationId) {
        List<AddOnService> services = reservationAddOns.getOrDefault(reservationId, new ArrayList<>());
        double total = 0.0;
        for (AddOnService service : services) {
            total += service.getCost();
        }
        return total;
    }

    public void displayAddOns(String reservationId) {
        List<AddOnService> services = reservationAddOns.getOrDefault(reservationId, new ArrayList<>());
        
        System.out.println("\n--- OPTIONAL SERVICES FOR RESERVATION: " + reservationId + " ---");
        if (services.isEmpty()) {
            System.out.println("No optional services selected.");
        } else {
            for (AddOnService service : services) {
                System.out.println("- " + service.getServiceName() + " ($" + service.getCost() + ")");
            }
            System.out.println("Total Add-On Cost: $" + calculateTotalAddOnCost(reservationId));
        }
        System.out.println("---------------------------------------------------------");
    }
}