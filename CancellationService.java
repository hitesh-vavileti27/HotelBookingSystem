import java.util.Stack;

/**
 * Handles safe cancellation of confirmed bookings by reversing system state.
 * Uses a Stack (LIFO) to track recently released room IDs.
 */
public class CancellationService {
    
    // Stack structure naturally models rollback operations
    private Stack<String> releasedRoomIds;

    public CancellationService() {
        this.releasedRoomIds = new Stack<>();
    }

    public void cancelReservation(Reservation reservation, RoomInventory inventory) throws InvalidBookingException {
        System.out.println("[Cancellation Request] Processing for " + reservation.getGuestName() + "...");

        // 1. Validation: Does the reservation actually have a room to cancel?
        if (reservation.getAssignedRoomId() == null) {
            throw new InvalidBookingException("Validation Failed: Cannot cancel a pending or waitlisted reservation.");
        }

        // 2. Validation: Is it already cancelled?
        if (reservation.isCancelled()) {
            throw new InvalidBookingException("Validation Failed: This reservation has already been cancelled.");
        }

        String roomId = reservation.getAssignedRoomId();
        String roomType = reservation.getRequestedRoomType();

        // 3. Mark as cancelled
        reservation.setCancelled(true);

        // 4. Record the released room in our LIFO stack
        releasedRoomIds.push(roomId);

        // 5. Restore inventory safely
        int currentCount = inventory.getAvailability(roomType);
        inventory.updateAvailability(roomType, currentCount + 1);

        System.out.println("  -> SUCCESS: Reservation cancelled. Room " + roomId + " released to inventory.");
    }

    public void displayReleasedRooms() {
        System.out.println("\n--- RECENTLY RELEASED ROOMS (LIFO) ---");
        if (releasedRoomIds.isEmpty()) {
            System.out.println("No rooms have been released recently.");
        } else {
            // Display stack from top to bottom (LIFO order)
            for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
                System.out.println("- " + releasedRoomIds.get(i));
            }
        }
        System.out.println("--------------------------------------");
    }
}