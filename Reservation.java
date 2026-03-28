public class Reservation implements java.io.Serializable {
    private String guestName;
    private String requestedRoomType;
    private String assignedRoomId; 
    private boolean isCancelled; // New flag to track cancellation state

    public Reservation(String guestName, String requestedRoomType) {
        this.guestName = guestName;
        this.requestedRoomType = requestedRoomType;
        this.isCancelled = false; // Default to active
    }

    public String getGuestName() { return guestName; }
    public String getRequestedRoomType() { return requestedRoomType; }
    
    public String getAssignedRoomId() { return assignedRoomId; }
    public void setAssignedRoomId(String assignedRoomId) { this.assignedRoomId = assignedRoomId; }

    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean cancelled) { this.isCancelled = cancelled; }

    @Override
    public String toString() {
        String status;
        if (isCancelled) {
            status = "Cancelled";
        } else if (assignedRoomId != null) {
            status = "Confirmed (Room: " + assignedRoomId + ")";
        } else {
            status = "Pending";
        }
        return "Reservation [Guest: " + guestName + ", Room: " + requestedRoomType + " | Status: " + status + "]";
    }
}