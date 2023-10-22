public class ThirdPartyPolicy extends InsurancePolicy {

    private String comments;

    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments) {
        super(policyHolderName, id, car, numberOfClaims);
        this.comments = comments;
    }

    @Override
    public double calcPayment() {
        return car.getPrice() / 100 + numberOfClaims * 200 + flatRate;
    }

    @Override
    public String toString() {
        return "ThirdPartyPolicy{" +
                "comments='" + comments + '\'' +
                "} " + super.toString();
    }
}