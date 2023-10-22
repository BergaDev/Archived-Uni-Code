// ComprehensivePolicy class
class ComprehensivePolicy extends InsurancePolicy {
    int driverAge;
    int level;

    // Constructor
    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level, MyDate expiryDate) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.driverAge = driverAge;
        this.level = level;
    }

    // Override the calcPayment method for ComprehensivePolicy
    @Override
    public double calcPayment(int flatRate) {
        double comprehensivePremium = car.price / 50 + numberOfClaims * 200 + flatRate;
        if (driverAge < 30) {
            comprehensivePremium += (30 - driverAge) * 50;
        }
        return comprehensivePremium;
    }

    // Override the print method to include additional attributes
    @Override
    public void print() {
        super.print();
        System.out.println("Driver Age: " + driverAge);
        System.out.println("Level: " + level);
    }

    // Override the toString method to include additional attributes
    @Override
    public String toString() {
        return super.toString() + "\nDriver Age: " + driverAge + "\nLevel: " + level;
    }

    // Add getters and setters if needed
}
