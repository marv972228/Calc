/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package statemachine;

import static enums.EnumMathState.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author marvi
 */
public class MathOperationsState {

    /**
     * This is the main entry point to determine which mathematical operation to
     * do after user has entered operand1, opentered, operand2 then hits
     * equal...
     *
     * @return
     */
    public static String calculate() {
        BigDecimal total = new BigDecimal("0");

        switch (CalcSM.getMathState()) {
            case ADD:
                total = add(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case SUBTRACT:
                total = subtract(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case MULTIPLY:
                total = multiply(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case DIVIDE:
                if (CalcSM.OP2.getBigDecimal().floatValue() == 0) {
                    CalcSM.setError(true);
                    break;
                }
                total = divide(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case EXPONENT:
                total = exponent(CalcSM.OP1.getBigDecimal(), CalcSM.OP2.getBigDecimal());
                break;
            case PERCENT:

                total = divide(CalcSM.OP1.getBigDecimal(), new BigDecimal(100));
                total = multiply(total, CalcSM.OP2.getBigDecimal());
                break;

        }

        return total.stripTrailingZeros().toPlainString();
    }

    /**
     * This performs the add operation. Just add the two operands
     *
     * @param operand1
     * @param operand2
     * @return
     */
    private static BigDecimal add(BigDecimal operand1, BigDecimal operand2) {
        return operand1.add(operand2);
    }

    /**
     * This performs the subtraction operation. Just subtract the two operands
     *
     * @param operand1
     * @param operand2
     * @return
     */
    private static BigDecimal subtract(BigDecimal operand1, BigDecimal operand2) {
        return operand1.subtract(operand2);
    }

    /**
     * This multiplies the two operands and also takes an opinion of how many
     * decimal places to go as well as rounding up if reaching some repeating
     * value
     *
     * @param operand1
     * @param operand2
     * @return
     */
    private static BigDecimal multiply(BigDecimal operand1, BigDecimal operand2) {
        return operand1.multiply(operand2).setScale(8, RoundingMode.HALF_UP);
    }

    /**
     * This will perform the divide operation To the 10th place and round up
     * .999999999 = 1
     *
     * @param operand1
     * @param operand2
     * @return
     */
    private static BigDecimal divide(BigDecimal operand1, BigDecimal operand2) {
        return operand1.divide(operand2, 10, RoundingMode.HALF_UP);
    }

    /**
     * To square numbers together, all is needed is to multiply the operand to
     * itself and return that value
     *
     * @param operand
     * @return
     */
    public static BigDecimal square(BigDecimal operand) {
        return operand.multiply(operand);
    }

    /**
     * This basically loops and keeps multiplying itself till the loop reaches
     * the value of the power
     *
     * 2^4 = 2 * 2 * 2 * 2
     *
     * @param operand1
     * @param operand2
     * @return
     */
    public static BigDecimal exponent(BigDecimal operand1, BigDecimal operand2) {
        BigDecimal total = new BigDecimal(operand1.toPlainString());

        for (int i = 1; i < operand2.intValue(); ++i) {
            total = total.multiply(operand1);
            CalcSM.debugPrint("---total = " + total.toPlainString());
        }

        return total;
    }

    /**
     * Just negate the value of the total
     *
     * @param total
     * @return
     */
    public static BigDecimal negate(BigDecimal total) {
        BigDecimal negative = new BigDecimal("-1");
        return total.multiply(negative);
    }

    /**
     * An opinionated operation on how to use percent
     *
     * If divide the result of the following input 8 / 3 % is 266.667% This says
     * the value 8 is 266% bigger than 3.
     *
     * If multiply the result of the following 8 * 3 % is 0.24. This says what
     * is 8% of 3.00?
     */
    public static void percentOperation() {
        if (CalcSM.getMathState() == DIVIDE) {
            CalcSM.setTotal(new BigDecimal(MathOperationsState.calculate()));
            CalcSM.setTotal(CalcSM.getTotal().multiply(BigDecimal.TEN));
            CalcSM.setTotal(CalcSM.getTotal().multiply(BigDecimal.TEN));
            CalcSM.isPercentage = true;
        } else if (CalcSM.getMathState() == MULTIPLY) {
            CalcSM.setTotal(new BigDecimal(MathOperationsState.calculate()));
            CalcSM.setTotal(CalcSM.getTotal().divide(BigDecimal.TEN));
            CalcSM.setTotal(CalcSM.getTotal().divide(BigDecimal.TEN));
            CalcSM.isPercentageTop = true;
        }
    }

}
