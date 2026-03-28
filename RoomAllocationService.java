import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomAllocationService {
    
    private Map<String, Set<String>> allocatedRooms;

    public RoomAllocationService() {
        this.allocatedRooms = new HashMap<>();
    }

    // CRITICAL SECTION: The entire check-then-act process is synchronized
    public synchronized void allocate(Reservation request, RoomInventory inventory) {
        String roomType = request.getRequestedRoomType();
        int availableCount = inventory.getAvailability(roomType);
        
        // Simulating a tiny processing delay that normally causes race conditions
        try { Thread.sleep(50); } catch (InterruptedException e) {}

        if (availableCount > 0) {
            allocatedRooms.putIfAbsent(roomType, new HashSet<>());
            Set<String> assignedIds = allocatedRooms.get(roomType);
            
            String newRoomId = roomType + "-" + (100 + assignedIds.size() + 1);
            
            if (assignedIds.add(newRoomId)) {
                request.setAssignedRoomId(newRoomId);
                inventory.updateAvailability(roomType, availableCount - 1);
                
                System.out.println("[Allocation] " + Thread.currentThread().getName() 
                    + " -> SUCCESS: " + request.getGuestName() + " got " + newRoomId);
            }
        } else {
            System.out.println("[Allocation] " + Thread.currentThread().getName() 
                + " -> FAILED: No " + roomType + "s available for " + request.getGuestName());
        }
    }
}