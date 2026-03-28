import java.util.HashMap;
import java.util.Map;

/**
 * Manages room availability across the system.
 * Acts as the single source of truth using a centralized HashMap.
 */
public class RoomInventory {
    
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    public void registerRoom(String roomType, int initialCount) {
        inventory.put(roomType, initialCount);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Error: Room type '" + roomType + "' does not exist.");
        }
    }

    public void displayInventory() {
        System.out.println("\n--- CURRENT ROOM INVENTORY ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + "s Available: " + entry.getValue());
        }
        System.out.println("------------------------------");
    }
}