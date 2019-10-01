/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package SubtractTestFunctionality;

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
public class SubtractTest {
    
    public SubtractTest() {
    }
    
    @Test
    public void subtractSimpleTest() {
        CalcSM.initialize();
        CalcSM.input("3");
        CalcSM.input("3");
        CalcSM.input("-");
        CalcSM.input("3");
        CalcSM.input("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("30");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("33 - 3 = " + CalcSM.getTotal());
        
        // reset machine
        CalcSM.clear();
    }
}
