/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package calc;

import static calc.EnumMathState.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author marvi
 */
public class MathOperationsState 
{
    public static String calculate()
    {
        BigDecimal total = new BigDecimal("0");
        
        switch (CalcSM.mathState) 
        {
            case ADD:
                total = add(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case SUBTRACT:
                total =  subtract(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case MULTIPLY:
                total = multiply(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case DIVIDE:
                if (CalcSM.OP2.getBigDecimal().intValue() == 0)
                {
                    CalcSM.hasError = true;
                    break;
                }
                total = divide(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case EXPONENT:
                total = exponent(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;

        }
        
        return total.stripTrailingZeros().toPlainString();
    }
    
    
    private static BigDecimal add (BigDecimal operand1, BigDecimal operand2)
    {
        return operand1.add(operand2);
    }
    
    private static BigDecimal subtract(BigDecimal operand1, BigDecimal operand2)
    {
        return operand1.subtract(operand2);
    }
    
    private static BigDecimal multiply(BigDecimal operand1, BigDecimal operand2)
    {
        return operand1.multiply(operand2).setScale(8, RoundingMode.HALF_UP);
    }
    
    private static BigDecimal divide(BigDecimal operand1, BigDecimal operand2)
    {
        return operand1.divide(operand2, 10, RoundingMode.HALF_UP);
    }
    
    public static BigDecimal square(BigDecimal operand)
    {
        return operand.multiply(operand);
    }
    
    public static BigDecimal exponent(BigDecimal operand1, BigDecimal operand2)
    {
        BigDecimal total = new BigDecimal(operand1.toPlainString());
        
        for (int i = 1; i < operand2.intValue(); ++i) 
        {
            total = total.multiply(operand1);
            CalcSM.debugPrint("---total = " + total.toPlainString());
        }
        
        return total;
    }
    
    public static BigDecimal negate(BigDecimal total) 
    {
        BigDecimal negative = new BigDecimal("-1");
        return total.multiply(negative);  
    }    
    
    public static void percentOperation()
    {
        if (CalcSM.mathState == DIVIDE)
        {
            CalcSM.total = new BigDecimal(MathOperationsState.calculate());
            CalcSM.total = CalcSM.total.multiply(BigDecimal.TEN);
            CalcSM.total = CalcSM.total.multiply(BigDecimal.TEN);
            CalcSM.isPercentage = true;
        }
        else if (CalcSM.mathState == MULTIPLY)
        {
            CalcSM.total = new BigDecimal(MathOperationsState.calculate());
            CalcSM.total = CalcSM.total.divide(BigDecimal.TEN);
            CalcSM.total = CalcSM.total.divide(BigDecimal.TEN);
            CalcSM.isPercentageTop = true;
        }
    }
    
}
