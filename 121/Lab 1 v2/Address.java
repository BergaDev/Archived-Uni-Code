public class Address {
    static int streetNum;
    static String street;
    static String suburb;
    static String city;


    public Address(int streetNum, String street, String suburb, String city) {
        //super(city);
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
       
    }

    static void print(){
        System.out.println("This is the address object " + streetNum + street + suburb + city);
        
    }
    
}
