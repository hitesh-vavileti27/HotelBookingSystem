public abstract class Room {
    public abstract String getRoomType();
    public abstract double getPricePerNight();
    public abstract int getNumberOfBeds();

    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType() + 
                           " | Price: $" + getPricePerNight() + 
                           " | Beds: " + getNumberOfBeds());
    }
}