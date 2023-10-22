class Address extends InsurancePolicy{
    protected int streetNum;
    String street;
    String suburb;
    String city;

    public Address(int streetNum){
        //code
        super(streetNum);
        this.streetNum = streetNum;
        
    }
    public String toString() {
    return " Street Num follows: " + streetNum;
  }

  
}
