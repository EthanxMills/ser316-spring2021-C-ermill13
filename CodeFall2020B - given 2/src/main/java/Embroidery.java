package main.java;

public class Embroidery {
    final static double pricePerLetter = 1.00; //SER316 TASK 2 SPOTBUGS FIX
    double price;
    //String embroidText; SER316 TASK 2 SPOTBUGS FIX

    public Embroidery (String embroidery) {
        //this.embroidText = embroidery; SER316 TASK 2 SPOTBUGS FIX
        this.price = embroidery.length() * pricePerLetter;
    }
}
