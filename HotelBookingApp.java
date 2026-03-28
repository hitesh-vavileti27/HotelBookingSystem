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

        System.out.println("--- SIMULATING PEAK DEMAND INTAKE ---");
        
        Reservation req1 = new Reservation("Alice", singleRoom.getRoomType());
        queueService.addBookingRequest(req1);

        Reservation req2 = new Reservation("Bob", suiteRoom.getRoomType());
        queueService.addBookingRequest(req2);

        Reservation req3 = new Reservation("Charlie", suiteRoom.getRoomType());
        queueService.addBookingRequest(req3);

        queueService.displayQueue();

        System.out.println("[System] Intake complete. Requests are waiting for allocation processing.");
    }
}