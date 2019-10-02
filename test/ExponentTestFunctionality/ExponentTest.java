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
        CalcSM.buttonAction("5");
        CalcSM.buttonAction("^");
        CalcSM.buttonAction("2");
        CalcSM.buttonAction("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("25");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("5^2 = " + CalcSM.getTotal());

        // reset machine
        CalcSM.clear();
    }

    @Test
    public void exponentTest2() {
        CalcSM.initialize();
        CalcSM.buttonAction("2");
        CalcSM.buttonAction("^");
        CalcSM.buttonAction("4");
        CalcSM.buttonAction("=");
        BigDecimal val = CalcSM.getTotal();
        BigDecimal expected = new BigDecimal("16");
        assertEquals(expected.intValue(), val.intValue());
        System.out.println("2^4 = " + CalcSM.getTotal());

        // reset machine
        CalcSM.clear();
    }
}
