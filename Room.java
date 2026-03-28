/**
 * Abstract base class representing a generic room.
 * Encapsulates common attributes shared by all room types.
 */
public abstract class Room {
    // Encapsulated attributes
    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    // Constructor
    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    // Getters for encapsulation
    public String getRoomType() { return roomType; }
    public int getNumberOfBeds() { return numberOfBeds; }
    public double getPricePerNight() { return pricePerNight; }

    // Common behavior for all rooms
    public void displayDetails() {
        System.out.println("- " + roomType + " | Beds: " + numberOfBeds + " | Price: $" + pricePerNight + "/night");
    }
}