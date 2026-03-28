public class SuiteRoom extends Room {
    @Override
    public String getRoomType() { return "Suite Room"; }
    @Override
    public double getPricePerNight() { return 300.0; }
    @Override
    public int getNumberOfBeds() { return 3; }
}