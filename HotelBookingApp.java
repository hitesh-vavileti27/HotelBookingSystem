public class HotelBookingApp {

    public static void main(String[] args) {
        
        System.out.println("****************************************");
        System.out.println("  Welcome to the Hotel Booking System!  ");
        System.out.println("****************************************\n");

        Room singleRoom = new SingleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize Inventory: 5 Singles, 1 Suite
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(singleRoom.getRoomType(), 5);
        inventory.registerRoom(suiteRoom.getRoomType(), 1);

        BookingQueueService queueService = new BookingQueueService();
        RoomAllocationService allocationService = new RoomAllocationService();

        System.out.println("--- SIMULATING PEAK DEMAND INTAKE ---");
        queueService.addBookingRequest(new Reservation("Alice", singleRoom.getRoomType()));
        queueService.addBookingRequest(new Reservation("Bob", suiteRoom.getRoomType()));
        
        // Charlie tries to book a suite, but Bob is ahead of him in the queue!
        queueService.addBookingRequest(new Reservation("Charlie", suiteRoom.getRoomType())); 

        queueService.displayQueue();

        // Process the queue and allocate rooms
        allocationService.processQueue(queueService, inventory);

        // Display final states
        inventory.displayInventory();
        allocationService.displayAllocations();
    }
}