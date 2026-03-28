public class SingleRoom extends Room {
    @Override
    public String getRoomType() { return "Single Room"; }
    
    @Override
    public double getPricePerNight() { return 100.0; }
    
    @Override
    public int getNumberOfBeds() { return 1; }
}