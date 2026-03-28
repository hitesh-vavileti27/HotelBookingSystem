import java.util.HashMap;
import java.util.Map;

/**
 * Manages and exposes room availability across the system.
 * Demonstrates centralized state management using a HashMap.
 */
public class RoomInventory {
    
    // The centralized data structure mapping Room Type (String) to Available Count (Integer)
    private Map<String, Integer> inventory;

    // Constructor initializes the HashMap
    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    /**
     * Registers a room type with its initial available count.
     */
    public void registerRoomType(String roomType, int initialCount) {
        inventory.put(roomType, initialCount);
    }

    /**
     * Retrieves the current availability for a specific room type.
     * Utilizes HashMap's O(1) lookup time.
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Controlled update to room availability.
     */
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Error: Room type '" + roomType + "' not found in inventory.");
        }
    }

    /**
     * Displays the current inventory state.
     */
    public void displayInventory() {
        System.out.println("--- CURRENT ROOM INVENTORY ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + "s Available: " + entry.getValue());
        }
        System.out.println("------------------------------");
    }
}