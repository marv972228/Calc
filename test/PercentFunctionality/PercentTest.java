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
        CalcSM.input("2");
        CalcSM.input("5");
        CalcSM.input("%");
        CalcSM.input("1");
        CalcSM.input("0");
        CalcSM.input("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("2.5");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("25 % 10 = " + CalcSM.getTotal());

        // reset machine
        CalcSM.clear();
    }
}
