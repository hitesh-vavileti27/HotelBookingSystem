import java.util.ArrayList;
import java.util.List;

/**
 * Maintains a historical record of all successfully confirmed reservations.
 */
public class BookingHistory {
    
    // A List preserves the exact insertion order (chronological timeline)
    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        this.confirmedBookings = new ArrayList<>();
    }

    public void addRecord(Reservation reservation) {
        confirmedBookings.add(reservation);
        System.out.println("[History] Record archived for Room: " + reservation.getAssignedRoomId());
    }

    public List<Reservation> getHistory() {
        // Return a COPY of the list to prevent external modification (Defensive Programming)
        return new ArrayList<>(confirmedBookings);
    }
}