public abstract class InsurancePolicy {

    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    public abstract double calcPayment();

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "policyHolderName='" + policyHolderName + '\'' +
                ", id=" + id +
                ", car=" + car +
                ", numberOfClaims=" + numberOfClaims +
                '}';
    }
}
