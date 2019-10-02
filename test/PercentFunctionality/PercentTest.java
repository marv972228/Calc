/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package PercentFunctionality;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;
import statemachine.CalcSM;

/**
 *
 * @author marvi
 */
public class PercentTest {
    
    public PercentTest() {
    }
    
    @Test
    public void simplePercentTest() {
        CalcSM.initialize();
        CalcSM.buttonAction("2");
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("%");
        CalcSM.buttonAction("1");
        CalcSM.buttonAction("0");
        CalcSM.buttonAction("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("2.5");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("25 % 10 = " + CalcSM.getTotal());

        // reset machine
        CalcSM.clear();
    }
}
