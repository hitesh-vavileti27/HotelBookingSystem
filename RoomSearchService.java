/**
 * Handles read-only access to inventory and room information for guests.
 * Demonstrates separation of concerns and defensive programming.
 */
public class RoomSearchService {

    /**
     * Displays available rooms by cross-referencing domain objects with inventory state.
     * * @param allRooms Array of all room types in the system.
     * @param inventory The centralized inventory to check for availability.
     */
    public void searchAvailableRooms(Room[] allRooms, RoomInventory inventory) {
        System.out.println("\n--- GUEST ROOM SEARCH RESULTS ---");
        boolean foundAvailable = false;

        for (Room room : allRooms) {
            // 1. Retrieve availability (Read-Only Access)
            int availableCount = inventory.getAvailability(room.getRoomType());

            // 2. Validation Logic (Defensive Programming to ensure count > 0)
            if (availableCount > 0) {
                foundAvailable = true;
                
                // 3. Show room details and pricing using domain objects
                System.out.println("Available: " + availableCount + " | " 
                    + room.getRoomType() 
                    + " - $" + room.getPricePerNight() + "/night "
                    + "(Beds: " + room.getNumberOfBeds() + ")");
            }
        }

        if (!foundAvailable) {
            System.out.println("Sorry, no rooms are currently available for your search.");
        }
        System.out.println("---------------------------------");
    }
}