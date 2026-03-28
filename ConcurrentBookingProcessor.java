/**
 * Processes booking requests in a multi-threaded environment.
 * Implements Runnable so it can be executed by a Java Thread.
 */
public class ConcurrentBookingProcessor implements Runnable {
    
    private BookingQueueService queueService;
    private RoomAllocationService allocationService;
    private RoomInventory inventory;

    public ConcurrentBookingProcessor(BookingQueueService queueService, RoomAllocationService allocationService, RoomInventory inventory) {
        this.queueService = queueService;
        this.allocationService = allocationService;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        // Keep processing as long as there are people in the queue
        while (queueService.hasMoreRequests()) {
            Reservation request = queueService.getNextRequest();
            if (request != null) {
                // Safely attempt allocation
                allocationService.allocate(request, inventory);
            }
        }
    }
}