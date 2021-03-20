package main.java;

import main.java.Stuffing.Stuff;
import java.util.LinkedList;

public class Bear implements Comparable<Bear>{
    public Casing casing;
    public Stuffing stuff;
    public Embroidery ink; 
    public LinkedList<NoiseMaker> noisemakers; // accessory
    public LinkedList<Clothing> clothing; // accessory
    public double price;
    // bear has a shell (requ)
    // bear has stuffing (req)
    // bear has a tattoo/emroider or not (opt)
    // bear has a noisemaker (opt)


    public Bear() {
        this.casing = new Casing();
        this.stuff = new Stuffing(Stuff.BASE);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }

    public Bear(Stuff stuff) {
        this.casing = new Casing();
        this.stuff = new Stuffing(stuff);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }

    public void setPrice(double incomingPrice) {
        this.price = incomingPrice;
    }

    public boolean addNoise(NoiseMaker noise) {
        if (this.noisemakers.size() >= 5) {
            return false;
        } 
        else {
            for (NoiseMaker noisemaker: noisemakers) {
                if (noise.spot == noisemaker.spot) {
                    return false;
                }
            }
            noisemakers.add(noise);
            return true;
        }
    }

	@Override
    public int compareTo(Bear bear) {
        return Double.compare(this.price, bear.price); //’SER316 TASK 2 SPOTBUGS FIX
        
       
    }

	//SER316 TASK 2 SPOTBUGS FIX
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casing == null) ? 0 : casing.hashCode());
		result = prime * result + ((clothing == null) ? 0 : clothing.hashCode());
		result = prime * result + ((ink == null) ? 0 : ink.hashCode());
		result = prime * result + ((noisemakers == null) ? 0 : noisemakers.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stuff == null) ? 0 : stuff.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bear other = (Bear) obj;
		if (casing == null) {
			if (other.casing != null)
				return false;
		} else if (!casing.equals(other.casing))
			return false;
		if (clothing == null) {
			if (other.clothing != null)
				return false;
		} else if (!clothing.equals(other.clothing))
			return false;
		if (ink == null) {
			if (other.ink != null)
				return false;
		} else if (!ink.equals(other.ink))
			return false;
		if (noisemakers == null) {
			if (other.noisemakers != null)
				return false;
		} else if (!noisemakers.equals(other.noisemakers))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (stuff == null) {
			if (other.stuff != null)
				return false;
		} else if (!stuff.equals(other.stuff))
			return false;
		return true;
	}
}
	
	