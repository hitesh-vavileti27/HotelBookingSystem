/**
 * A custom exception used to indicate that a booking request 
 * violates business rules (e.g., invalid room type or insufficient inventory).
 */
public class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message); // Passes our custom error message up to the standard Java Exception class
    }
}