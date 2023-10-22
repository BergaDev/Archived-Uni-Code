class ThirdPartyPolicy extends InsurancePolicy {
    private String comments;

    // Constructor
    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments, MyDate expiryDate) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    // Getters and setters
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Override the calcPayment method for ThirdPartyPolicy
    @Override
    public double calcPayment(int flatRate) {
        double thirdPartyPremium = getCar().getPrice() / 100 + getNumberOfClaims() * 200 + flatRate;
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

    // Override the clone method to create a deep copy
    //Put in the exception to stop the error on super.clone 
    @Override
    public ThirdPartyPolicy clone() throws CloneNotSupportedException {
        ThirdPartyPolicy clonedPolicy = (ThirdPartyPolicy) super.clone();
        clonedPolicy.setExpiryDate(new MyDate(this.getExpiryDate().getDay(), this.getExpiryDate().getMonth(), this.getExpiryDate().getYear()));
        clonedPolicy.setCar(new Car(this.getCar().getModel(), this.getCar().getType(), this.getCar().getManufacturingYear(), this.getCar().getPrice()));
        return clonedPolicy;
    }
}
