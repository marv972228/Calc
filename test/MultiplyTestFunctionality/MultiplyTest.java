/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package MultiplyTestFunctionality;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;
import statemachine.CalcSM;

/**
 *
 * @author marvi
 */
public class MultiplyTest {
    
    public MultiplyTest() {
    }
    
    @Test 
    public void multiplySimple() {
        CalcSM.initialize();
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("*");
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("25");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("5 * 5 = " + CalcSM.getTotal());
        
        // reset machine
        CalcSM.clear();
    }
}
