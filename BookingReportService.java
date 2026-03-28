import java.util.List;

/**
 * Generates summaries and reports from stored booking data.
 * Demonstrates separation of data storage from reporting logic.
 */
public class BookingReportService {

    public void generateSummaryReport(BookingHistory history) {
        List<Reservation> records = history.getHistory();
        
        System.out.println("\n========================================");
        System.out.println("      ADMIN: BOOKING HISTORY REPORT     ");
        System.out.println("========================================");
        
        if (records.isEmpty()) {
            System.out.println("No confirmed bookings found in history.");
        } else {
            int count = 1;
            for (Reservation res : records) {
                System.out.println(count + ". Guest: " + res.getGuestName() + 
                                   " | Room Type: " + res.getRequestedRoomType() + 
                                   " | Assigned ID: " + res.getAssignedRoomId());
                count++;
            }
            System.out.println("----------------------------------------");
            System.out.println("Total Confirmed Bookings: " + records.size());
        }
        System.out.println("========================================\n");
    }
}