package main.java;

import java.util.*;

// This class provides functionality for a BearWorkshop class.
public class BearWorkshop implements BearWorkshopInterface{
    // Workshop has a collection of bears
    // Workshop has a customer
    Customer customer;
    List<Bear> BearCart;
    
    /**
     * Default constructor for the Bear Workshop
     */
    public BearWorkshop() {
        this("AZ");
    }

    /**
     * This is a parameterized ctor for a BearWorkshop
     * @param state customer is in
     */
    public BearWorkshop(String state) {
        BearCart = new LinkedList<>();
        customer = new Customer(state);
    }

    /**
     * This is a convenience method to calculate the cost of bears in the
     * shopping cart for a customer in the BearFactory. This methods calculates
     * the overall price of ONE bear, with all its add-ons and should include the discounts. 
     * @param bear to get cost of
     * @return double representation of bear cost
     * TODO: test me and fix me in assignment 3
     */
    @Override
    public double getCost(Bear bear) {
        Collections.sort(bear.clothing);
        int numFree = 0;//�SER316 TASK 2 SPOTBUGS FIX
        numFree = bear.clothing.size() / 3;//�SER316 TASK 2 SPOTBUGS FIX
        ArrayList<Clothing> freeClothes = new ArrayList<>();

        for (int i = 0; i < bear.clothing.size(); i++) {
            Clothing clothes = bear.clothing.get(i);
            if (i % 3 != 0) {
            	bear.price += clothes.price;
            } else {
            	freeClothes.add(clothes);;
            }
        }

        for (NoiseMaker noise: bear.noisemakers) {
            bear.price += noise.price;
        }

        if (bear.ink != null) {
            bear.price += bear.ink.price;
        }

        bear.price += bear.stuff.price;
        bear.price *= bear.casing.priceModifier;
        
        return bear.price;
    }

    // Function to get the raw cost of a bear without any discounts
   // TODO: test me and fix me in assignment 3
    public double getRawCost(Bear bear) {
        for (int i = 0; i < bear.clothing.size(); i++) {
            Clothing clothes = bear.clothing.get(i);
            bear.price += clothes.price;

        }

        for (NoiseMaker noise: bear.noisemakers) {
            bear.price += noise.price;
        }

        if (bear.ink != null) {
            bear.price += bear.ink.price;
        }

        bear.price += bear.stuff.price;
        bear.price *= bear.casing.priceModifier;

        double bearPrice = bear.price;
        bear.price = 0;
        return bearPrice;
    }

    /**
     * Utility method to calculate tax for purchases by customers in different
     * states.
     * You can assume these taxes are what we want, so they are not wrong.
     * The taxes are meant to be used in this way: costInShoppingCart * tax
     * @return
     */
    public double calculateTax() {
        double tax;
        switch (customer.state) {
            case "AZ":
                tax = 1.07;
                break;
            case "NY":
                tax = 1.09;
                break;
            case "DC":
                tax = 1.105;
                break;
            case "CA":
                tax = 1.1;
                break;
            default:
                tax = 1.05;
                break;
        }
        return tax;
    }
    
    

    /**
     * Utility method to add a bear to the BearFactory. So to the shopping cart.
     * @param bear to add
     * @return true if successful, false otherwise
     * TODO: test me and fix me in assignment 3
     */
    @Override
    public boolean addBear(Bear bear)       {
        if (this.BearCart.add(bear))        {
            return true;
                                            }
        else                                {
            return false;
                                            }
    }

    // WE ARE REMOVING A BEAR FROM THE SHOPPING CART
    @Override
    public boolean removeBear(Bear bear)    {
        if (this.BearCart.remove(bear))     {
            return true;
                                            }
        else                                {
            return false;
                                            }
    }

    /**
     * This is a function to allow customers to checkout their shopping cart
     * This method is supposed to calculate the overall costs of the bears in the cart
     * and apply all discounts
     * TODO: Test me and fix me in assignment 3
     * @return
     * adjusted final calculation, it had cost = temp but it should be cost -= temp, I added the qualifier of this being whenever temp is less than cost because then that means there's a valid discount
     */
    @Override
    public double checkout() {
        if (this.customer.age <= 13) {
            if (this.customer.parent.age < 18)
                System.out.println("Guardian is too young");
                return -1;
        }
        double temp = 0;
        Double Cost = Double.valueOf(0.00);
        for (Bear bear: BearCart) {
            Cost += getRawCost(bear);
        }
        for (Bear bear: this.BearCart) {
            temp += getCost(bear);
        }


        double savings = 0;
        // calculate total cost
        double rawCost = 0;
        for (Bear bear: BearCart) {
            rawCost += this.getRawCost(bear);
        }

        // calculate adjusted cost
        double cost = 0;
        for (Bear bear: this.BearCart) {
            cost += this.getCost(bear);
        }
        savings += rawCost - cost; // calc delta between raw and prorated cost

        List<Bear> nonFreeBears = new LinkedList<Bear>();//SER316 TASK 2 SPOTBUGS FIX
        int counter = 0;
        int numberOfFreeBearsInBearCart = BearCart.size() / 3;
        double discountedCost = 0;
        Bear freeBear = new Bear();

        System.out.println("Temp Before: " + temp);
        for (int count = 0; count <= numberOfFreeBearsInBearCart; ++count) {
            for (Bear bear : BearCart) {
                if (freeBear != null && bear.price < freeBear.price)
                    freeBear = bear;
                    temp += temp - temp * 2 + bear.price;

            }
        }
        System.out.println("Temp After: " + temp);
        System.out.println("Cost: " + cost);
        
        if(temp < cost)
        		cost -= temp;

        return cost * calculateTax();
    }


    /** 
     * What this method does: 
     * - Bear costs are calculated based on all the accessories, nose makers, material and so forth
     * - Bears are Buy 2 bears, get a third one free. It is always the cheapest bear that is free. If I buy 3 then I pay only for the 2 most expensive ones, if I buy 6 I pay for the 4 most expensive ones. The cheapest is determined after all the individiual discounts are applied to a bear. So the total bear cost is always the bear with all accessories and all bear discounts.   
     * - It is 10% off the total cost of a bear when a single bear has 10 or more accessories (clothes and noise makers and embroidery) that the customer pays for (so if clothes are free these do not count). 
     * - Clothes are buy 2, get one free on each bear. Always the cheapest clothes are free. Basically same principle as for the Bears buy 2 get one free. 
     *  TIP: the implemented savings method in the BearWorkshop1-5 do not use the getCost method implemented in this base class. They implement their own savings calculation
     *  		 All of them do however use the getRawCost method implemented in this base class. You should test all the constraints that you can come up with, for noise makers, clothes etc. 
     * @return the savings if the customer would check out as double
     * Note: I used a lot of the above checkout for naming conventions so sorry if that gets confusing
     */
    public double calculateSavings() {
        
    	//took the variable names from checkout since one of the requirements was consistency
    	double cost = 0.0;
    	double rawCost = 0.0;
    	double savings = 0.0;
    	Bear bear;
    	
    	//get the rawCost
    	Iterator<Bear> alpha = BearCart.iterator();
    	while(alpha.hasNext()) {
    		bear = (Bear)alpha.next();
    		if(bear != null) {
    			rawCost += getRawCost(bear);
    		}
    	}
    	
    	try {//when testing my version I kept getting Index out of Bounds traced to here so I added a try catch for it https://stackoverflow.com/questions/58818517/continue-executing-loop-after-catching-an-exception-in-try-catch-block
            for(int i = 0; i < BearCart.size(); ++i) {
                bear = (Bear)BearCart.get(i);
                Collections.sort(bear.clothing);
                int numFree = bear.clothing.size() / 3;
                ArrayList<Clothing> freeClothes = new ArrayList<>();

                for (int j = 0; j < bear.clothing.size(); j++) {
                    Clothing clothes = bear.clothing.get(j);
                    if (j % 3 != 0) {
                    	bear.price += clothes.price;
                    } else {
                    	freeClothes.add(clothes);;
                    }
                }

                for (NoiseMaker noise: bear.noisemakers) {
                    bear.price += noise.price;
                }

                if (bear.ink != null) {
                    bear.price += bear.ink.price;
                }

                bear.price += bear.stuff.price;
                bear.price *= bear.casing.priceModifier;
                
                cost += bear.price;
            }
        } catch (IndexOutOfBoundsException beta) {System.out.println("index out of bounds exception");} //I kept getting these????
    	
    	System.out.println("raw cost: " + rawCost);
    	System.out.println("cost: " + cost);
    	savings = (savings + (rawCost - cost));//update but don't reset savings
    	System.out.println("savings: " + savings);
    	
    	
    	//BEGIN SECTION FOR BUY TWO GET ONE OFF CALCULATIONS
    	List<Bear> allBears = new LinkedList<>();//make a new list to work with
    	
    	//fill new list with valid bears, this will be pulled from to separate the free and non-free bears
    	int i = 0;
    	Iterator<Bear> sigma = BearCart.iterator();
    	while(sigma.hasNext()) {
    		Bear temp = (Bear)sigma.next();
    		allBears.add(temp);
    		i++;
    	}
    	
    	Collections.sort(allBears);//sort the listing I just made, idea from Geek's for Geek's to order ascending https://www.geeksforgeeks.org/collections-sort-java-examples/
    	List<Bear> nonFreeBears = new LinkedList<>();//will need this later in the accessory tracking
    	double discountTracker = 0.0;
    	
    	Iterator<Bear> echo = allBears.iterator();
    	int bearNumber = 0;
    	while(echo.hasNext()) {
    		bearNumber++;
    		Bear temp = (Bear)echo.next();
    		if((bearNumber % 3) != 0) {
    			nonFreeBears.add(temp);
    		}
    		else{
    			Bear freeBear = (Bear)((LinkedList<Bear>)nonFreeBears).remove();//This Bear WILL be free so take it off the other list
    			nonFreeBears.add(temp);
                discountTracker -= this.getCost(freeBear);//take it away from old so it doesn't double count
                discountTracker += this.getCost(freeBear);//originally had this before but was getting negative answers 
                
    		}
    	}
    	
    	System.out.println("cost again: " + cost);
    	System.out.println("discountTracker: " + discountTracker);
    	savings = (savings + discountTracker);
    	
    	//START SECTION OF ACCESSORIES DISCOUNT CALCULATIONS
    	
    	double AS = 0;//the accessory savings variable to be added to total savings
    	Iterator<Bear> foxtrot = nonFreeBears.iterator();//Iterators are kick ass, I haven't used them before but my buddy suggested I do so for this because I was having issues tracking
    	while(foxtrot.hasNext()) {
    		Bear teemo = (Bear)foxtrot.next();
    		int freeClothing =  teemo.clothing.size() / 3;//will round down by forcing the math, size should grant the number of clothes assigned
    		int numAcc = teemo.clothing.size() - freeClothing;//clothing is included in the accessories but only what we pay for with clothes x noise makers
    		numAcc += teemo.noisemakers.size();
    		System.out.println("NumAcc: " + numAcc);
    		if(numAcc > 9) {
    			Collections.sort(teemo.clothing);
    	        int numFree =teemo.clothing.size() / 3;
    	        ArrayList<Clothing> freeClothes = new ArrayList<>();

    	        for (int j = 0; j < teemo.clothing.size(); j++) {
    	            Clothing clothes = teemo.clothing.get(j);
    	            if (j % 3 != 0) {
    	            	teemo.price += clothes.price;
    	            } else {
    	            	freeClothes.add(clothes);;
    	            }
    	        }

    	        for (NoiseMaker noise: teemo.noisemakers) {
    	        	teemo.price += noise.price;
    	        }

    	        if (teemo.ink != null) {
    	        	teemo.price += teemo.ink.price;
    	        }

    	        teemo.price += teemo.stuff.price;
    	        teemo.price *= teemo.casing.priceModifier;
    	        
    			AS += teemo.price*.1 ;//It is 10% off the total cost of a bear when a single bear has 10 or more accessories requirement
    		}
    	}
    	
    	System.out.println("AS: " + AS);
    	System.out.println("Savings: " + savings);
    	System.out.println("----------------");
    	savings += AS;
    	return savings;
    }
}