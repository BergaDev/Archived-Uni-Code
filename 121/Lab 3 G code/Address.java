// Address class
class Address {
    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    // Constructor
    public Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }

    // Accessors (get methods)
    public int getStreetNum() {
        return streetNum;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    // Mutators (set methods)
    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Override the toString method to convert the address to a string
    @Override
    public String toString() {
        return streetNum + " " + street + ", " + suburb + ", " + city;
    }
}
