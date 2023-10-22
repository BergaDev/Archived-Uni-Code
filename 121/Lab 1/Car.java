public class Car extends InsurancePolicy{
    //vars
    String model;
    //enum here
    int makeYear;
    protected double carPrice;
    public static void main(String[] args){
        //code
        Car car = new Car();
        car.carPrice = 2000;
        InsurancePolicy insurancePolicy = new insurancePolicy();
    }

    public double getPrice(){
        return carPrice;
    }
}