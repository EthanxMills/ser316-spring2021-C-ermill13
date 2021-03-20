package main.java;

public class Stuffing {
    public enum Stuff { //SER316 TASK 2 SPOTBUGS FIX
        BASE,
        DOWN,
        FOAM
    }

    
    int price;

    public Stuffing (Stuff interiorStuffing) {

    	Stuff polyStuffing; //SER316 TASK 2 SPOTBUGS FIX
        switch (interiorStuffing) {
            case BASE:
                polyStuffing = Stuff.BASE;
                this.price = 30;
                break;
            case DOWN:
                polyStuffing = Stuff.DOWN;
                price = 40;
                break;
            case FOAM:
                polyStuffing = Stuff.FOAM;
                this.price = 50;
                break;
        }
    }
}
