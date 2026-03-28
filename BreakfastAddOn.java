public class BreakfastAddOn implements AddOnService {
    @Override
    public String getServiceName() { return "Premium Continental Breakfast"; }
    
    @Override
    public double getCost() { return 25.0; }
}
