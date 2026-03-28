import java.util.LinkedList;
import java.util.Queue;

public class BookingQueueService {
    
    private Queue<Reservation> requestQueue;

    public BookingQueueService() {
        this.requestQueue = new LinkedList<>();
    }

    // "synchronized" ensures only one thread can add to the queue at a time
    public synchronized void addBookingRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("[Intake] Received request from " + reservation.getGuestName() + " for a " + reservation.getRequestedRoomType());
    }

    // "synchronized" ensures two threads don't accidentally grab the exact same reservation
    public synchronized Reservation getNextRequest() {
        return requestQueue.poll(); 
    }

    public synchronized boolean hasMoreRequests() {
        return !requestQueue.isEmpty();
    }
    
    public void displayQueue() {
        // Omitting for brevity in this multithreaded test
    }
}