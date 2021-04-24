import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import main.java.BearWorkshop;

import static org.junit.Assert.*;

/***
 * This class provides a framework to implement black box tests for various
 * implementations of the BearWorkshop Class. The BearWorkshop is having a
 * blowout sale and is offering the following savings.
 *
 * Bears are Buy 2 bears, get 1 free. It is 10% off the cost of a bear when a
 * single bear has 10 or more accessories (Note that embroidery, stuffing, and
 * the material used for the bear casing is not considered an accessory).
 * Additionally, clothes are buy 2, get one free on each bear. Only non free clothes count 
 * towards the 10 accessories or more savings part. 
 */
@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<BearWorkshop> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
        this.classUnderTest = (Class<BearWorkshop>) classUnderTest;
    }

    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = {
                {BearWorkshop1.class},
                {BearWorkshop2.class},
                {BearWorkshop3.class},
                {BearWorkshop4.class},
                {BearWorkshop5.class}

        };
        return Arrays.asList(classes);
    }

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        Constructor<BearWorkshop> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }

    BearWorkshop oneBear;
    Double oneBearExpected;

    BearWorkshop threeBears;
    Double threeBearsExpected;

    BearWorkshop twoBears;
    Double twoBearsExpected;

    @Before
    public void setUp() throws Exception {

        // One Bear base stuffing, no saving expected
        oneBear = createBearWorkshop("NY");
        oneBear.addBear(new Bear(Stuffing.stuffing.BASE));
        oneBearExpected = 0.00; // no savings
        
        // Three Bears expected to not pay for cheapest one
        threeBears = createBearWorkshop("AZ");
        threeBears.addBear(new Bear(Stuffing.stuffing.BASE));
        threeBears.addBear(new Bear(Stuffing.stuffing.DOWN));
        threeBears.addBear(new Bear(Stuffing.stuffing.FOAM));
        threeBearsExpected = 30.00;
        
        
    }

    @After
    public void tearDown() throws Exception {
    }

    // sample test
    /**
     * Test examines a BearFactory with 1 simple bear in it. The bear costs $30
     * and there are no savings.
     */
    @Test
    public void oneBearNoSavings() {
        Double ans = oneBear.calculateSavings();
        assertEquals(oneBearExpected, ans);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //BUY 2 GET 1 FREE BEARS TESTING SECTION
    
    @Test
    public void negativeBearsTest() {
    	BearWorkshop alpha = null;
    	try {
    		alpha = createBearWorkshop("DC");
    	} catch (Exception e) {	
    	}
    	Bear beta = new Bear(Stuffing.stuffing.BASE);
    	alpha.removeBear(beta);
    	
    	Double ans = alpha.calculateSavings();
    	Double exp = 0.0;
    	assertEquals(exp, ans, 0.005);
    }
    
    @Test
    public void zeroBearsTest() {
    	BearWorkshop alpha = null;
    	try {
    		alpha = createBearWorkshop("DC");
    	} catch (Exception e) {	
    	}
    	
    	Double ans = alpha.calculateSavings();
    	Double exp = 0.0;
    	assertEquals(exp, ans, 0.005);
    }
    
    @Test
    public void TwoBearsTest() {
    	BearWorkshop alpha = null;
    	try {
    		alpha = createBearWorkshop("DC");
    	} catch (Exception e) {	
    	}
    	Bear beta = new Bear(Stuffing.stuffing.BASE);
    	Bear charlie = new Bear(Stuffing.stuffing.BASE);
    	alpha.addBear(beta);
    	alpha.addBear(charlie);
    	
    	Double ans = alpha.calculateSavings();
    	Double exp = 0.0;
    	assertEquals(exp, ans, 0.005);
    }
    
    @Test
    public void threeBearsSaveOnCheapest() {
        Double ans = threeBears.calculateSavings();
        assertEquals(threeBearsExpected, ans);
    }
    
    @Test
    public void FiveBearsTest() {
    	BearWorkshop alpha = null;
    	try {
    		alpha = createBearWorkshop("DC");
    	} catch (Exception e) {	
    	}
    	Bear beta = new Bear(Stuffing.stuffing.BASE);
    	Bear charlie = new Bear(Stuffing.stuffing.BASE);
    	Bear delta = new Bear(Stuffing.stuffing.BASE);
    	Bear echo = new Bear(Stuffing.stuffing.BASE);
    	Bear foxtrot = new Bear(Stuffing.stuffing.BASE);
    	alpha.addBear(beta);
    	alpha.addBear(charlie);
    	alpha.addBear(delta);
    	alpha.addBear(echo);
    	alpha.addBear(foxtrot);
    	
    	Double ans = alpha.calculateSavings();
    	Double exp = 30.00;
    	assertEquals(exp, ans, 0.005);
    }
    
    @Test
    public void sixBearsSaveOnCheapest() {
    	BearWorkshop alpha = null;
    	try {
    		alpha = createBearWorkshop("DC");
    	} catch (Exception e) {	
    	}
    	Bear beta = new Bear(Stuffing.stuffing.BASE);
    	Bear charlie = new Bear(Stuffing.stuffing.BASE);
    	Bear delta = new Bear(Stuffing.stuffing.BASE);
    	Bear echo = new Bear(Stuffing.stuffing.BASE);
    	Bear foxtrot = new Bear(Stuffing.stuffing.BASE);
    	Bear gamma = new Bear(Stuffing.stuffing.BASE);
    	alpha.addBear(beta);
    	alpha.addBear(charlie);
    	alpha.addBear(delta);
    	alpha.addBear(echo);
    	alpha.addBear(foxtrot);
    	alpha.addBear(gamma);
    	
    	Double ans = alpha.calculateSavings();
    	Double exp = 60.00;
    	assertEquals(exp, ans, 0.005);
    }
    
    @Test
    public void ThreeHundredBearsSaveOnCheapestHundred() {
    	BearWorkshop alpha = null;
    	try {
    		alpha = createBearWorkshop("DC");
    	} catch (Exception e) {	
    	}
    	int i = 0;
    	while(i < 300) {
    		Bear beta = new Bear(Stuffing.stuffing.BASE);
    		alpha.addBear(beta);
    		i++;
    	}
    	Double ans = alpha.calculateSavings();
    	Double exp = 3000.00;
    	assertEquals(exp, ans, 0.005);
    }
    

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
     //BUY 2 GET 1 FREE CLOTHING TESTING SECTION
    
    @Test
    public void oneBearNegativeClothing() {
    	BearWorkshop alpha = null;
        try {
            alpha = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear beta = new Bear(Stuffing.stuffing.BASE);
        alpha.addBear(beta);

	    beta.clothing.remove(new Clothing(4, "Hat"));
	    
	    Double bearsExpected = 0.00;
        Double ans = alpha.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void oneBearZeroClothing() {
    	BearWorkshop alpha = null;
        try {
            alpha = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear beta = new Bear(Stuffing.stuffing.BASE);
        alpha.addBear(beta);
        
        Double bearsExpected = 0.0;
        Double ans = alpha.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);

    }
    
    @Test
    public void oneBearOneClothing() {
    	BearWorkshop alpha = null;
        try {
            alpha = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear beta = new Bear(Stuffing.stuffing.BASE);
        alpha.addBear(beta);
        beta.clothing.add(new Clothing(4, "Hat"));
        
        Double bearsExpected = 0.0;
        Double ans = alpha.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);

    }
    
    @Test
    public void oneBearTest3clothingsExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(4, "Sunglasses"));
	    customBear.clothing.add(new Clothing(4, "Shoes"));
	    
        Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void twoBearTest3clothingsExpectSaving() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);
        Bear alpha = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(alpha);
        
	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(4, "Sunglasses"));
	    customBear.clothing.add(new Clothing(4, "Shoes"));
	    
	    alpha.clothing.add(new Clothing(4, "Hat"));
	    alpha.clothing.add(new Clothing(4, "Sunglasses"));
	    alpha.clothing.add(new Clothing(4, "Shoes"));
	    
        Double bearsExpected = 8.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void oneBear5clothes() {
    	BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear alpha = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(alpha);
        
        alpha.clothing.add(new Clothing(4, "Hat"));
	    alpha.clothing.add(new Clothing(4, "Sunglasses"));
	    alpha.clothing.add(new Clothing(4, "Shoes"));
	    alpha.clothing.add(new Clothing(4, "Sunglasses"));
	    alpha.clothing.add(new Clothing(4, "Shoes"));
	    
	    Double bearsExpected = 4.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void oneBear6clothes() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(4, "Sunglasses"));
	    customBear.clothing.add(new Clothing(4, "Shoes"));
	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(4, "Sunglasses"));
	    customBear.clothing.add(new Clothing(4, "Shoes"));
	    
        Double bearsExpected = 8.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void twoBear6clotheseach() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear customBear = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(customBear);

	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(4, "Sunglasses"));
	    customBear.clothing.add(new Clothing(4, "Shoes"));
	    customBear.clothing.add(new Clothing(4, "Hat"));
	    customBear.clothing.add(new Clothing(4, "Sunglasses"));
	    customBear.clothing.add(new Clothing(4, "Shoes"));
	    
	    Bear alpha = new Bear(Stuffing.stuffing.BASE);
        bears.addBear(alpha);

	    alpha.clothing.add(new Clothing(4, "Hat"));
	    alpha.clothing.add(new Clothing(4, "Sunglasses"));
	    alpha.clothing.add(new Clothing(4, "Shoes"));
	    alpha.clothing.add(new Clothing(4, "Hat"));
	    alpha.clothing.add(new Clothing(4, "Sunglasses"));
	    alpha.clothing.add(new Clothing(4, "Shoes"));
	    
        Double bearsExpected = 16.0;
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void oneBear15clothes() {
    	BearWorkshop alpha = null;
        try {
            alpha = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear beta = new Bear(Stuffing.stuffing.BASE);
        alpha.addBear(beta);
        
        int i = 0;
        
        while(i < 15) {
        	beta.clothing.add(new Clothing(4, "Hat"));
        	i++;
        }
        
        Double bearsExpected = 27.00;
        Double ans = alpha.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void twoBear15clotheseach() {
    	BearWorkshop alpha = null;
        try {
            alpha = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear beta = new Bear(Stuffing.stuffing.BASE);
        alpha.addBear(beta);
        Bear charlie = new Bear(Stuffing.stuffing.BASE);
        alpha.addBear(charlie);
        
        int i = 0;
        
        while(i < 15) {
        	beta.clothing.add(new Clothing(4, "Hat"));
        	i++;
        }
        
        int j = 0;
        while(j < 15) {
        	charlie.clothing.add(new Clothing(4, "Hat"));
        	j++;
        }
        
        Double bearsExpected = 54.00;
        Double ans = alpha.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }
    
    @Test
    public void oneBear300clothes() {
    	BearWorkshop alpha = null;
        try {
            alpha = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        Bear beta = new Bear(Stuffing.stuffing.BASE);
        alpha.addBear(beta);
        
        int i = 0;
        
        while(i < 300) {
        	beta.clothing.add(new Clothing(4, "Hat"));
        	i++;
        }
        
        Double bearsExpected = 483.00;//200(2:1 ratio for paid) * $4 = $800 + $30 = $830(sub total) - 10% = $747.00 --> 1230 sub total - 747 = 483.00 in savings
        Double ans = alpha.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
