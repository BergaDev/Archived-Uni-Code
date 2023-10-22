// ThirdPartyPolicy class
class ThirdPartyPolicy extends InsurancePolicy {
    private String comments;

    // Constructor
    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments, MyDate expiryDate) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    // Override the calcPayment method for ThirdPartyPolicy
    @Override
    public double calcPayment(int flatRate) {
        double thirdPartyPremium = car.getPrice() / 100 + getNumberOfClaims() * 200 + flatRate;
        return thirdPartyPremium;
    }

    // Override the print method to include additional attributes
    @Override
    public void print() {
        super.print();
        System.out.println("Comments: " + comments);
    }

    // Override the toString method to include additional attributes
    @Override
    public String toString() {
        return super.toString() + "\nComments: " + comments;
    }

    // Add getters and setters if needed
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
