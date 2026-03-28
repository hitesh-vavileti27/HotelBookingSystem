public class HotelBookingApp {

    public static void main(String[] args) {
        
        System.out.println("****************************************");
        System.out.println("  Welcome to the Hotel Booking System!  ");
        System.out.println("****************************************\n");

        Room singleRoom = new SingleRoom();
        RoomInventory inventory = new RoomInventory();
        
        // DANGER: Only 2 rooms available!
        inventory.registerRoom(singleRoom.getRoomType(), 2);

        BookingQueueService queueService = new BookingQueueService();
        RoomAllocationService allocationService = new RoomAllocationService();

        // 1. 5 Guests all click "Book" at the same time
        System.out.println("--- MASSIVE USER SURGE (5 Guests, 2 Rooms) ---");
        queueService.addBookingRequest(new Reservation("Alice", singleRoom.getRoomType()));
        queueService.addBookingRequest(new Reservation("Bob", singleRoom.getRoomType()));
        queueService.addBookingRequest(new Reservation("Charlie", singleRoom.getRoomType()));
        queueService.addBookingRequest(new Reservation("Dave", singleRoom.getRoomType()));
        queueService.addBookingRequest(new Reservation("Eve", singleRoom.getRoomType()));

        System.out.println("\n--- STARTING CONCURRENT PROCESSORS ---");
        
        // 2. Spin up 3 independent threads to process the queue simultaneously
        Thread t1 = new Thread(new ConcurrentBookingProcessor(queueService, allocationService, inventory), "Thread-1");
        Thread t2 = new Thread(new ConcurrentBookingProcessor(queueService, allocationService, inventory), "Thread-2");
        Thread t3 = new Thread(new ConcurrentBookingProcessor(queueService, allocationService, inventory), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        // 3. Wait for all threads to finish their work before printing the final inventory
        try {
            t1.join(); t2.join(); t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- FINAL SYSTEM STATE ---");
        inventory.displayInventory();
    }
}