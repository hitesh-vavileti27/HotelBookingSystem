public class HotelBookingApp {

    public static void main(String[] args) {
        
        System.out.println("****************************************");
        System.out.println("  Welcome to the Hotel Booking System!  ");
        System.out.println("****************************************\n");

        Room singleRoom = new SingleRoom();
        Room suiteRoom = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(suiteRoom.getRoomType(), 1);

        BookingQueueService queueService = new BookingQueueService();
        RoomAllocationService allocationService = new RoomAllocationService();

        // 1. Intake
        System.out.println("--- INTAKE ---");
        Reservation bobRequest = new Reservation("Bob", suiteRoom.getRoomType());
        queueService.addBookingRequest(bobRequest);
        
        // 2. Process Allocation
        allocationService.processQueue(queueService, inventory);

        // 3. Add-On Services Phase
        // We only allow add-ons if Bob actually got a room assigned!
        if (bobRequest.getAssignedRoomId() != null) {
            System.out.println("\n[Guest] Bob is adding optional services to his reservation...");
            
            AddOnManager addOnManager = new AddOnManager();
            String bobsRoomId = bobRequest.getAssignedRoomId();

            // Bob selects his add-ons
            addOnManager.addServiceToReservation(bobsRoomId, new BreakfastAddOn());
            addOnManager.addServiceToReservation(bobsRoomId, new SpaAddOn());

            // Display his final add-on bill
            addOnManager.displayAddOns(bobsRoomId);
        }
    }
}