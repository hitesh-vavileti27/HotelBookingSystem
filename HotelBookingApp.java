/**
 * The main entry point for the Hotel Booking application.
 * Updated for Use Case 2: Demonstrating Inheritance, Abstraction, and State.
 */
public class HotelBookingApp {

    public static void main(String[] args) {
        
        System.out.println("****************************************");
        System.out.println("  Welcome to the Hotel Booking System!  ");
        System.out.println("****************************************\n");

        // 1. Initialize room objects using Polymorphism
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // 2. Store availability using simple variables
        int availableSingles = 5;
        int availableDoubles = 3;
        int availableSuites = 2;

        // 3. Display room details and availability to the console
        System.out.println("--- AVAILABLE ROOM TYPES ---");
        
        singleRoom.displayDetails();
        System.out.println("  Currently Available: " + availableSingles + "\n");
        
        doubleRoom.displayDetails();
        System.out.println("  Currently Available: " + availableDoubles + "\n");
        
        suiteRoom.displayDetails();
        System.out.println("  Currently Available: " + availableSuites + "\n");

        System.out.println("****************************************");
        System.out.println("Thank you for checking our inventory.");
    }
}