package AddTestFunctionality;

/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import statemachine.CalcSM;

/**
 *
 * @author marvi
 */
public class AddTest {
    
    public AddTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * 55 + 55 = 110
     */
    @Test
    public void addTest() {
        CalcSM.initialize();
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("+");
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("110");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("55 + 55 = " + CalcSM.getTotal());
        
        // reset machine
        CalcSM.clear();
    }
    
    /**
     * 1 + 1 != 3
     */
    @Test 
    public void addIncorrectValues() {
        System.out.println("This test is to make sure 1+1 != 3");
        CalcSM.initialize();
        CalcSM.buttonAction("1");
        CalcSM.buttonAction("+");
        CalcSM.buttonAction("1");
        CalcSM.buttonAction("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("3");
        System.out.println(CalcSM.OP1.getBigDecimal().intValue() + " + " + CalcSM.OP2.getBigDecimal().intValue() + " = " + CalcSM.getTotal());
        
        System.out.println(expected.intValue() + " != " + val.intValue());
        assertNotEquals(expected.intValue(), val.intValue());
        
        CalcSM.clear();
    }
}
