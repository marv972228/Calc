package ExponentTestFunctionality;

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
public class ExponentTest {
    
    public ExponentTest() {
    }
    
    @Test
    public void exponentTest() {
        CalcSM.initialize();
        CalcSM.input("5");
        CalcSM.input("^");
        CalcSM.input("2");
        CalcSM.input("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("25");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("5^2 = " + CalcSM.getTotal());

        // reset machine
        CalcSM.clear();
    }
}
