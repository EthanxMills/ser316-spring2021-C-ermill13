package main.java;

public class Clothing implements Comparable<Clothing> {
    public double price;
    public String description; //SER316 TASK 2 SPOTBUGS FIX

    public Clothing() {
        this(4.00, "Generic Offtrack Separate");

    }

    public Clothing(double price, String descr) {
        this.price = price;
        this.description = descr;
    }

    public int compareTo(Clothing clothes) {
        if (Double.compare(clothes.price, this.price) > 0) { //SER316 TASK 2 SPOTBUGS FIX
            return 1;
        } else if (Double.compare(clothes.price, this.price) < 0) { //SER316 TASK 2 SPOTBUGS FIX
            return -1;
        } else {
            return 0;
        }
    }

    
    //SER316 TASK 2 SPOTBUGS FIX
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Clothing other = (Clothing) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
}
