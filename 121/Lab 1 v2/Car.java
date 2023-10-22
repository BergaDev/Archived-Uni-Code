//Author: Matthew Bergamini - MAIN IN programm
//Enum declaration
enum CarType {
  SUV, SED, LUX, HATCH, TRUCK
}

class Car {
    //Class specfic vars
  String model;
  CarType type;
  int manufacturingYear;
  static double price;

  
  public Car(String model, CarType type, int manufacturingYear, double price) {
    //Store vars
    this.model = model;
    this.type = type;
    this.manufacturingYear = manufacturingYear;
    this.price = price;
  }

  //Return and print
  @Override
  public String toString() {
    return " Car Details > " + " Model: " + model + " Type: " + type + " Manufacturing Year: " + manufacturingYear + " Car Price: $" + price;
  }

  void priceRise(double rise){
    price = price * rise;
  }
}