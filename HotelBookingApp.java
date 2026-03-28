/**
 * The main entry point for the Hotel Booking application.
 * Updated for Use Case 3: Centralized Inventory Management (HashMap).
 */
public class HotelBookingApp {

    public static void main(String[] args) {
        
        System.out.println("****************************************");
        System.out.println("  Welcome to the Hotel Booking System!  ");
        System.out.println("****************************************\n");

        // 1. Initialize Domain Objects 
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // 2. Initialize Centralized Inventory
        RoomInventory inventory = new RoomInventory();
        
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 3);
        inventory.registerRoom(suiteRoom.getRoomType(), 2);

        // 3. Display Domain Details
        System.out.println("--- ROOM DETAILS OVERVIEW ---");
        singleRoom.displayDetails();
        doubleRoom.displayDetails();
        suiteRoom.displayDetails();

        // 4. Display Initial Inventory State
        inventory.displayInventory();

        // 5. Simulate a booking
        System.out.println("\n[System] Simulating a booking for 1 Single Room...");
        int currentSingleCount = inventory.getAvailability(singleRoom.getRoomType());
        inventory.updateAvailability(singleRoom.getRoomType(), currentSingleCount - 1);

        System.out.println("[System] Inventory updated successfully.");
        
        // Display new inventory state
        inventory.displayInventory();
    }
}