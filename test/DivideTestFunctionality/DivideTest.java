/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package DivideTestFunctionality;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;
import statemachine.CalcSM;

/**
 *
 * @author marvi
 */
public class DivideTest {
    
    public DivideTest() {
    }
    
    @Test
    public void divideSimple() {
        CalcSM.initialize();
        CalcSM.input("5");
        CalcSM.input("5");
        CalcSM.input("/");
        CalcSM.input("5");
        CalcSM.input("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("11");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("55 / 5 = " + CalcSM.getTotal());
        
        // reset machine
        CalcSM.clear();

    }

}
