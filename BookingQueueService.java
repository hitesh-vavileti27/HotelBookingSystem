import java.util.LinkedList;
import java.util.Queue;

public class BookingQueueService {
    
    private Queue<Reservation> requestQueue;

    public BookingQueueService() {
        this.requestQueue = new LinkedList<>();
    }

    public void addBookingRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("[Intake] Received request from " + reservation.getGuestName() + " for a " + reservation.getRequestedRoomType());
    }

    public void displayQueue() {
        System.out.println("\n--- CURRENT BOOKING QUEUE (FIFO ORDER) ---");
        if (requestQueue.isEmpty()) {
            System.out.println("The queue is currently empty.");
        } else {
            int position = 1;
            for (Reservation req : requestQueue) {
                System.out.println(position + ". " + req.getGuestName() + " -> " + req.getRequestedRoomType());
                position++;
            }
        }
        System.out.println("------------------------------------------\n");
    }

    // Retrieves and removes the next request from the front of the queue
    public Reservation getNextRequest() {
        return requestQueue.poll(); 
    }

    // Checks if there is anyone left in the queue
    public boolean hasMoreRequests() {
        return !requestQueue.isEmpty();
    }
}