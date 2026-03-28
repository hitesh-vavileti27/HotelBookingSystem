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

        InvalidBookingValidator validator = new InvalidBookingValidator();

        System.out.println("--- PROCESSING BOOKINGS WITH VALIDATION ---\n");

        // Scenario 1: A Perfectly Valid Booking (Happy Path)
        Reservation aliceReq = new Reservation("Alice", singleRoom.getRoomType());
        try {
            validator.validateRequest(aliceReq, inventory);
            System.out.println("[Success] " + aliceReq.getGuestName() + "'s request for a " + aliceReq.getRequestedRoomType() + " is valid!");
        } catch (InvalidBookingException e) {
            System.out.println("[Error] " + e.getMessage());
        }

        // Scenario 2: Invalid Booking - Guest asks for a room that doesn't exist
        Reservation eveReq = new Reservation("Eve", "Penthouse Suite");
        try {
            validator.validateRequest(eveReq, inventory);
            System.out.println("[Success] " + eveReq.getGuestName() + "'s request is valid!");
        } catch (InvalidBookingException e) {
            System.out.println("[Gracefully Caught Error] " + e.getMessage());
        }

        // Scenario 3: Invalid Booking - Guest asks for a sold-out room
        Reservation daveReq = new Reservation("Dave", suiteRoom.getRoomType());
        try {
            // Let's manually drain the suite inventory to simulate it being sold out
            inventory.updateAvailability(suiteRoom.getRoomType(), 0);
            
            validator.validateRequest(daveReq, inventory);
            System.out.println("[Success] " + daveReq.getGuestName() + "'s request is valid!");
        } catch (InvalidBookingException e) {
            System.out.println("[Gracefully Caught Error] " + e.getMessage());
        }

        System.out.println("\n[System] Application finishes running smoothly because all errors were handled gracefully!");
    }
}