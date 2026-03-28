import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages incoming booking requests using a FIFO Queue.
 * Decouples request intake from actual room allocation.
 */
public class BookingQueueService {
    
    // The Queue data structure to hold our reservations in arrival order
    private Queue<Reservation> requestQueue;

    public BookingQueueService() {
        this.requestQueue = new LinkedList<>();
    }

    /**
     * Accepts a booking request and adds it to the back of the queue.
     * Notice: NO inventory mutation happens here!
     */
    public void addBookingRequest(Reservation reservation) {
        // .offer() safely adds the element to the tail of the queue
        requestQueue.offer(reservation);
        System.out.println("[Intake] Received request from " + reservation.getGuestName() + " for a " + reservation.getRequestedRoomType());
    }

    /**
     * Displays the current state of the queue to verify FIFO ordering.
     */
    public void displayQueue() {
        System.out.println("\n--- CURRENT BOOKING QUEUE (FIFO ORDER) ---");
        if (requestQueue.isEmpty()) {
            System.out.println("The queue is currently empty.");
        } else {
            int position = 1;
            // Iterating through a queue displays elements from head (first) to tail (last)
            for (Reservation req : requestQueue) {
                System.out.println(position + ". " + req.getGuestName() + " -> " + req.getRequestedRoomType());
                position++;
            }
        }
        System.out.println("------------------------------------------\n");
    }
}