public class HotelBookingApp {

    public static void main(String[] args) {
        
        System.out.println("****************************************");
        System.out.println("  Welcome to the Hotel Booking System!  ");
        System.out.println("****************************************\n");

        Room singleRoom = new SingleRoom();
        Room suiteRoom = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(suiteRoom.getRoomType(), 1); // Only 1 suite!

        BookingQueueService queueService = new BookingQueueService();
        RoomAllocationService allocationService = new RoomAllocationService();
        
        // Initialize our new historical and reporting services
        BookingHistory bookingHistory = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // 1. Create incoming requests
        Reservation req1 = new Reservation("Alice", singleRoom.getRoomType());
        Reservation req2 = new Reservation("Bob", suiteRoom.getRoomType());
        Reservation req3 = new Reservation("Charlie", suiteRoom.getRoomType()); // Charlie will fail
        
        Reservation[] allRequests = { req1, req2, req3 };

        System.out.println("--- SIMULATING INTAKE ---");
        for (Reservation req : allRequests) {
            queueService.addBookingRequest(req);
        }

        // 2. Process the queue and allocate rooms
        allocationService.processQueue(queueService, inventory);

        // 3. Archive only the SUCCESSFUL bookings into history
        System.out.println("--- ARCHIVING CONFIRMED BOOKINGS ---");
        for (Reservation req : allRequests) {
            if (req.getAssignedRoomId() != null) { // If it has a Room ID, it was confirmed
                bookingHistory.addRecord(req);
            }
        }

        // 4. Admin requests the end-of-day report
        reportService.generateSummaryReport(bookingHistory);
    }
}