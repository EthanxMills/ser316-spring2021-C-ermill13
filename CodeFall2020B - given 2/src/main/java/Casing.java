package main.java;

public class Casing {
    double priceModifier;

    //SER316 TASK 2 SPOTBUGS FIX
    
    public Casing() {
        this(1.00, "Default outer shell");
    }

    public Casing(double price, String descr) {
        this.priceModifier = price;
    }
}
