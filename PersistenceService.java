import java.io.*;
import java.util.List;

public class PersistenceService {

    private static final String DATA_FILE = "hotel_data.ser";

    // SAVE: Serialize objects to a file
    public void saveSystemState(RoomInventory inventory, List<Reservation> history) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(inventory);
            oos.writeObject(history);
            System.out.println("[Persistence] System state saved successfully to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("[Error] Failed to save state: " + e.getMessage());
        }
    }

    // LOAD: Deserialize objects from a file
    public Object[] loadSystemState() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("[Persistence] No saved state found. Starting fresh.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            RoomInventory inventory = (RoomInventory) ois.readObject();
            List<Reservation> history = (List<Reservation>) ois.readObject();
            System.out.println("[Persistence] System state restored from " + DATA_FILE);
            return new Object[]{inventory, history};
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[Error] Failed to load state: " + e.getMessage());
            return null;
        }
    }
}