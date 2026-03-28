public class HotelBookingApp {

    public static void main(String[] args) {
        
        System.out.println("****************************************");
        System.out.println("  Welcome to the Hotel Booking System!  ");
        System.out.println("****************************************\n");

        Room singleRoom = new SingleRoom();
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getRoomType(), 5);

        BookingQueueService queueService = new BookingQueueService();
        RoomAllocationService allocationService = new RoomAllocationService();
        CancellationService cancellationService = new CancellationService();

        // 1. Book a room
        System.out.println("--- BOOKING PHASE ---");
        Reservation aliceReq = new Reservation("Alice", singleRoom.getRoomType());
        queueService.addBookingRequest(aliceReq);
        allocationService.processQueue(queueService, inventory);
        
        inventory.displayInventory(); // Should be 4 Singles left

        // 2. Cancel the room (Happy Path)
        System.out.println("\n--- CANCELLATION PHASE ---");
        try {
            cancellationService.cancelReservation(aliceReq, inventory);
        } catch (InvalidBookingException e) {
            System.out.println("[Error] " + e.getMessage());
        }

        inventory.displayInventory(); // Should be back to 5 Singles!
        cancellationService.displayReleasedRooms(); // Should show Alice's released room ID

        // 3. Attempt to double-cancel the room (Error Handling)
        System.out.println("\n--- ATTEMPTING DOUBLE CANCELLATION ---");
        try {
            cancellationService.cancelReservation(aliceReq, inventory);
        } catch (InvalidBookingException e) {
            System.out.println("[Gracefully Caught Error] " + e.getMessage());
        }
    }
}