/**
 * Validates input and system state before processing requests.
 * Demonstrates "Fail-Fast" design by throwing exceptions immediately upon invalid state.
 */
public class InvalidBookingValidator {

    // The "throws" keyword warns the rest of the system that this method might trigger an error!
    public void validateRequest(Reservation request, RoomInventory inventory) throws InvalidBookingException {
        String roomType = request.getRequestedRoomType();

        // Rule 1: Does this room type even exist in our hotel?
        if (!inventory.hasRoomType(roomType)) {
            throw new InvalidBookingException("Validation Failed: The room type '" + roomType + "' does not exist in our system.");
        }

        // Rule 2: Is there actual inventory available?
        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("Validation Failed: Insufficient inventory. No '" + roomType + "s' are currently available.");
        }
    }
}