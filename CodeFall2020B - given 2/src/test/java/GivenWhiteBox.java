import main.java.*;
import main.java.Stuffing.stuffing;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;


public class GivenWhiteBox {
    BearWorkshop oneBear;
    Customer customer;

    @Before
    public void setUp() throws Exception {
    }


    //the test case was wrong for it's expected, given the Bear() default constructor,
    //the price of the bear before tax is $30 then with a 7% tax it will end up $32.10 as calculated, the getCost was fine
    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(32.1, ans, 0.005);
    }
    
    @Test
    public void checkoutOneBearNY() {
        // One Student
        oneBear = new BearWorkshop("NY");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(32.7, ans, 0.005);
    }
    
    @Test
    public void checkoutOneBearDC() {
        // One Student
        oneBear = new BearWorkshop("DC");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(33.15, ans, 0.005);
    }
    
    @Test
    public void checkoutOneBearCA() {
        // One Student
        oneBear = new BearWorkshop("CA");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(33.0, ans, 0.005);
    }
    
    @Test
    public void checkoutOneBearDefault() {
        // One Student
        oneBear = new BearWorkshop("ZZ");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        assertEquals(31.5, ans, 0.005);
    }
    
    @Test
    public void checkoutOneBearUnderageButWithGaurdian() {
        // One Student
        oneBear = new BearWorkshop("ZZ");
        oneBear.addBear(new Bear());
        Customer cst = new Customer(9, "Az", this.customer);
        Double ans = oneBear.checkout();
        assertEquals(31.5, ans, 0.005);
    }
    

    
    @Test
    public void checkoutThreeBears() {
    	BearWorkshop threeBear = new BearWorkshop("VA");
    	Bear alpha = new Bear();
    	alpha.setPrice(alpha.price);
    	Bear beta = new Bear();
    	Bear charlie = new Bear();
    	
    	threeBear.addBear(alpha);
    	threeBear.addBear(beta);
    	threeBear.addBear(charlie);
        Double ans = threeBear.checkout();
        assertEquals(63.0, ans, 0.005);//$90 minus free bear = $60 times Arizona tax of 7% = $64.20
    }
    
    @Test
    public void checkoutOneBearTwoNoise() {
    	BearWorkshop oneBearTwoNoise = new BearWorkshop();
    	Bear alpha = new Bear();
    	NoiseMaker squeesh = new NoiseMaker();
    	NoiseMaker skibidi_bop_mm_dada = new NoiseMaker();
    	alpha.addNoise(squeesh);
    	alpha.addNoise(skibidi_bop_mm_dada);
    	
    	oneBearTwoNoise.addBear(alpha);
    	
    	Double ans = oneBearTwoNoise.checkout();
    	assertEquals(42.80, ans, 0.005);
    	
    	
    }
    
    @Test
    public void calcOneBearTwoNoise() {
    	BearWorkshop oneBearTwoNoise = new BearWorkshop();
    	Bear alpha = new Bear();
    	NoiseMaker squeesh = new NoiseMaker();
    	NoiseMaker skibidi_bop_mm_dada = new NoiseMaker();
    	alpha.addNoise(squeesh);
    	alpha.addNoise(skibidi_bop_mm_dada);
    	
    	oneBearTwoNoise.addBear(alpha);
    	
    	Double ans = oneBearTwoNoise.calculateSavings();
    	assertEquals(0.00, ans, 0.005);
    	
    	
    }
    
    @Test
    public void RemoveTrueCheese() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        Bear alpha = new Bear();
        oneBear.addBear(alpha);
        oneBear.removeBear(alpha);
        Double ans = oneBear.checkout();
        assertEquals(0.00, ans, 0.005);
    }
    
    @Test
    public void RemoveFalseCheese() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        Bear alpha = new Bear();
        oneBear.addBear(alpha);
        Bear beta = new Bear();
        oneBear.removeBear(beta);
        Double ans = oneBear.checkout();
        assertEquals(32.1, ans, 0.005);
    }
    
    @Test
    public void oneBearWithHat() {
    	oneBear = new BearWorkshop("AZ");
    	Bear alpha = new Bear();
    	int i = 0;
    	while(i < 15) {
    	alpha.clothing.add(new Clothing(4, "Hat"));
    	i++;
    	}
    	oneBear.addBear(alpha);
    	Double ans = oneBear.checkout();
    	assertEquals(74.9, ans,0.005);
    }
    
    @Test
    public void oneBearWithHatSavings() {
    	oneBear = new BearWorkshop("AZ");
    	Bear alpha = new Bear();
    	int i = 0;
    	while(i < 15) {
    	alpha.clothing.add(new Clothing(4, "Hat"));
    	i++;
    	}
    	oneBear.addBear(alpha);
    	Double ans = oneBear.calculateSavings();
    	assertEquals(15.1, ans,0.005);
    }
    
    @Test
    public void checkoutSixBears() {
    	BearWorkshop threeBear = new BearWorkshop("VA");
    	Bear alpha = new Bear();
    	Bear beta = new Bear();
    	Bear charlie = new Bear();
    	Bear delta = new Bear();
    	Bear echo = new Bear();
    	Bear foxtrot = new Bear();
    	
    	threeBear.addBear(alpha);
    	threeBear.addBear(beta);
    	threeBear.addBear(charlie);
    	threeBear.addBear(delta);
    	threeBear.addBear(echo);
    	threeBear.addBear(foxtrot);
        Double ans = threeBear.checkout();
        assertEquals(157.5, ans, 0.005);
    }
    
    @Test
    public void calcSavingsSixBears() {
    	BearWorkshop threeBear = new BearWorkshop("VA");
    	Bear alpha = new Bear();
    	Bear beta = new Bear();
    	Bear charlie = new Bear();
    	Bear delta = new Bear();
    	Bear echo = new Bear();
    	Bear foxtrot = new Bear();
    	
    	threeBear.addBear(alpha);
    	threeBear.addBear(beta);
    	threeBear.addBear(charlie);
    	threeBear.addBear(delta);
    	threeBear.addBear(echo);
    	threeBear.addBear(foxtrot);
        Double ans = threeBear.calculateSavings();
        assertEquals(60.0, ans, 0.005);
    }
    
    @Test
    public void checkoutOneBearStuffing() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear(stuffing.DOWN));
        Double ans = oneBear.checkout();
        assertEquals(42.8, ans, 0.005);
    }
    
}
