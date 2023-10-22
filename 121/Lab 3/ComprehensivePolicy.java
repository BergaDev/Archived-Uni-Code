public class ComprehensivePolicy extends InsurancePolicy {

    private int driverAge;
    private int level;

    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level) {
        super(policyHolderName, id, car, numberOfClaims);
        this.driverAge = driverAge;
        this.level = level;
    }

    @Override
    public double calcPayment() {
        double premium = car.getPrice() / 50 + numberOfClaims * 200 + flatRate;
        if (driverAge < 30) {
            premium += (30 - driverAge) * 50;
        }
        return premium;
    }

    @Override
    public String toString() {
        return "ComprehensivePolicy{" +
                "driverAge=" + driverAge +
                ", level=" + level +
                "} " + super.toString();
    }
}