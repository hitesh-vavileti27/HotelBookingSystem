import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomAllocationService {
    
    // Maps a Room Type to a Set of unique Room IDs (prevents double booking)
    private Map<String, Set<String>> allocatedRooms;

    public RoomAllocationService() {
        this.allocatedRooms = new HashMap<>();
    }

    public void processQueue(BookingQueueService queueService, RoomInventory inventory) {
        System.out.println("\n--- PROCESSING BOOKING QUEUE ---");
        
        while (queueService.hasMoreRequests()) {
            // 1. Dequeue the request in FIFO order
            Reservation request = queueService.getNextRequest();
            String roomType = request.getRequestedRoomType();

            System.out.println("[Processing] Request for " + request.getGuestName() + " (" + roomType + ")");

            // 2. Check availability
            int availableCount = inventory.getAvailability(roomType);
            
            if (availableCount > 0) {
                // Initialize the Set for this room type if it doesn't exist yet
                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                Set<String> assignedIds = allocatedRooms.get(roomType);
                
                // 3. Generate a unique room ID (e.g., "Single Room-101")
                String newRoomId = roomType + "-" + (100 + assignedIds.size() + 1);
                
                // 4. Record room ID using Set (enforces uniqueness to prevent double booking)
                if (assignedIds.add(newRoomId)) {
                    request.setAssignedRoomId(newRoomId);
                    
                    // 5. Decrement inventory immediately (Atomic operation)
                    inventory.updateAvailability(roomType, availableCount - 1);
                    System.out.println("  -> SUCCESS: Assigned Room " + newRoomId);
                }
            } else {
                System.out.println("  -> FAILED: No " + roomType + "s available. Waitlisting guest.");
            }
        }
        System.out.println("--------------------------------\n");
    }

    public void displayAllocations() {
        System.out.println("--- CURRENT ROOM ALLOCATIONS ---");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + "s Allocated: " + entry.getValue());
        }
        System.out.println("--------------------------------");
    }
}