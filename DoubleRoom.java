public class DoubleRoom extends Room {
    @Override
    public String getRoomType() { return "Double Room"; }
    
    @Override
    public double getPricePerNight() { return 150.0; }
    
    @Override
    public int getNumberOfBeds() { return 2; }
}
