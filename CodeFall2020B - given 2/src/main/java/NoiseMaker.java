package main.java;

public class NoiseMaker {
    public double price;
    //String label; SER316 TASK 2 SPOTBUGS FIX
   // String recording; SER316 TASK 2 SPOTBUGS FIX

    Location spot;

    public NoiseMaker() {
        this("Default Noise", "I wuv you", Location.CENTERBODY);
    }

    public NoiseMaker(String label, String recording,
                      Location location) {
        //this.label = label; SER316 TASK 2 SPOTBUGS FIX
        //this.recording = recording; SER316 TASK 2 SPOTBUGS FIX
        this.spot = location;
        switch (location) {
            case CENTERBODY:
                this.price = 10;
                break;
            default:
                this.price = 5;
                break;
        }
    }


    public enum Location {
        RIGHT_HAND, LEFT_HAND, RIGHT_FOOT, LEFT_FOOT, CENTERBODY
    }
}


