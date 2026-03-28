import java.util.ArrayList;
import java.util.List;

public class HotelBookingApp {

    public static void main(String[] args) {
        System.out.println("--- DURABLE HOTEL SYSTEM ---\n");

        PersistenceService persistence = new PersistenceService();
        RoomInventory inventory;
        List<Reservation> history;

        // 1. ATTEMPT RECOVERY
        Object[] recoveredData = persistence.loadSystemState();

        if (recoveredData != null) {
            inventory = (RoomInventory) recoveredData[0];
            history = (List<Reservation>) recoveredData[1];
        } else {
            // 2. INITIALIZE FRESH (If no file exists)
            inventory = new RoomInventory();
            inventory.registerRoom("Single Room", 5);
            history = new ArrayList<>();
        }

        // 3. SHOW CURRENT STATE
        System.out.println("Current Inventory: " + inventory.getAvailability("Single Room"));
        System.out.println("History Count: " + history.size());

        // 4. PERFORM A NEW ACTION (If Alice isn't already there)
        if (history.isEmpty()) {
            System.out.println("\n[Action] First run: Booking for Alice...");
            Reservation alice = new Reservation("Alice", "Single Room");
            alice.setAssignedRoomId("SR-101");
            inventory.updateAvailability("Single Room", 4);
            history.add(alice);
            
            // 5. PERSIST DATA
            persistence.saveSystemState(inventory, history);
            System.out.println("[System] Alice is saved. Now STOP the app and run it again!");
        } else {
            System.out.println("\n[Success] DATA PERSISTED! Alice is still here from the previous run.");
            System.out.println("Last Guest: " + history.get(0).getGuestName());
        }
    }
}