public class Reservation {
    private String guestName;
    private String requestedRoomType;
    private String assignedRoomId; // New field to hold the confirmed room

    public Reservation(String guestName, String requestedRoomType) {
        this.guestName = guestName;
        this.requestedRoomType = requestedRoomType;
    }

    public String getGuestName() { return guestName; }
    public String getRequestedRoomType() { return requestedRoomType; }
    
    public String getAssignedRoomId() { return assignedRoomId; }
    public void setAssignedRoomId(String assignedRoomId) { this.assignedRoomId = assignedRoomId; }

    @Override
    public String toString() {
        String status = (assignedRoomId != null) ? "Confirmed (Room: " + assignedRoomId + ")" : "Pending";
        return "Reservation [Guest: " + guestName + ", Room: " + requestedRoomType + " | Status: " + status + "]";
    }
}