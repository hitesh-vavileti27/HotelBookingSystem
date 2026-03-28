/**
 * The main entry point for the Hotel Booking application.
 * Updated for Use Case 4: Read-Only Search Service and Validation.
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
        
        // Group rooms into an array
        Room[] hotelRooms = { singleRoom, doubleRoom, suiteRoom };

        // 2. Initialize Centralized Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 3);
        inventory.registerRoom(suiteRoom.getRoomType(), 1); // Only 1 Suite available

        // 3. Initialize Search Service
        RoomSearchService searchService = new RoomSearchService();

        // 4. Guest initiates a search
        System.out.println("[Guest] Searching for available rooms...");
        searchService.searchAvailableRooms(hotelRooms, inventory);

        // 5. Simulate booking the LAST available Suite
        System.out.println("\n[System] Booking 1 Suite Room...");
        int currentSuiteCount = inventory.getAvailability(suiteRoom.getRoomType());
        inventory.updateAvailability(suiteRoom.getRoomType(), currentSuiteCount - 1);
        System.out.println("[System] Booking confirmed. Suite inventory updated.");

        // 6. Guest initiates another search
        System.out.println("\n[Guest] Searching for available rooms again...");
        // The search service should now automatically filter out the Suite Room!
        searchService.searchAvailableRooms(hotelRooms, inventory);
    }
}