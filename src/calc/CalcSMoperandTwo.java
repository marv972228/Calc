/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package calc;

import static calc.EnumMathState.*;
import static calc.EnumOperationState.*;
import java.math.BigDecimal;

/**
 *
 * @author marvi
 */
public class CalcSMoperandTwo 
{
    public static void operand2()
    {
        CalcSM.debugPrint("Enter - OPERAND2 STATE");
        switch(CalcSM.getUserInput())
        {
            case "MRC":
                CalcSMmemory.memory();
                break;
            case "ceInput":
                CalcSM.OP2.clearOperand();
                CalcSM.currentState = OPENTERED;
                //CalcSM.mathState = NONE;
                CalcSMopEntered.opEntered();
                break;
            case "+":
            case "-":
            case "/":
            case "*":
            case "^":
                CalcSM.total = new BigDecimal(mathBigDecimal.calculate());
                CalcSM.OP1.clearOperand();
                CalcSM.OP2.clearOperand();
                CalcSM.OP1.setCalculatedString(CalcSM.total.stripTrailingZeros().toPlainString());
                CalcSM.total = new BigDecimal("0");
                CalcSM.currentState = OPENTERED;
                CalcSMopEntered.opEntered();
                
                break;
            case "%":
            case "=":
                CalcSM.debugPrint("RESULT = ..");
                CalcSM.currentState = RESULT;
                CalcSMready.result();

                break;
        }
        
        CalcSM.debugPrint("Exit - OPERAND2 STATE");   
    }
    
    public static void zero2()
    {
        CalcSM.debugPrint("Enter - ZERO2 STATE");
        
        switch(CalcSM.getUserInput())
        {
            case "(-)":
                CalcSM.OP2.setNegation();
                break;
            case "0":
                CalcSM.debugPrint("-- Set Zero2 True");
                CalcSM.OP2.setZeroString(true);
                break;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                CalcSM.OP2.setZeroString(false); // 0 front not needed in this case
                CalcSM.currentState = INT2;
                int2();
                break;
            case ".":
                CalcSM.OP2.setZeroString(true);
                CalcSM.OP2.setPointString(true);
                CalcSM.currentState = FRAC2;
                frac2();
                break;
            case "MRC":
            case "M+":
            case "M-":
                CalcSMmemory.memory();
                break;
            default:
                CalcSM.currentState = OPERAND2;
                operand2();
                break;
        }
        
        
        CalcSM.debugPrint("Exit - ZERO2 STATE");         
    }
    
    public static void int2()
    {
        CalcSM.debugPrint("Enter - INT2 STATE");
        switch(CalcSM.getUserInput())
        {
            case "(-)":
                CalcSM.OP2.setNegation();
                break;
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                CalcSM.debugPrint("+++ Append INTEGER2 +++");
                CalcSM.OP2.appendIntString(CalcSM.getUserInput());
                break;
            case ".":
                CalcSM.OP2.setZeroString(false);
                CalcSM.OP2.setPointString(true);
                CalcSM.currentState = FRAC2;
                frac2();
                break;
            case "MRC":
            case "M+":
            case "M-":
                CalcSMmemory.memory();
                break;
            default:
                CalcSM.currentState = OPERAND2;
                operand2();
                break;
        }        
        
        CalcSM.debugPrint("OPERAND2 value string = " + CalcSM.OP2.getOperandString());
        CalcSM.debugPrint("Exit - INT2 STATE");         
    }
    
    public static void frac2()
    {
        CalcSM.debugPrint("Enter - FRAC2 STATE");
        switch(CalcSM.getUserInput())
        {
            case "(-)":
                CalcSM.OP2.setNegation();
                break;
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                CalcSM.debugPrint("+++ Append FRAC2 +++");
                CalcSM.OP2.appendFracString(CalcSM.getUserInput());
                break;
            case ".":
                break;
            case "MRC":
            case "M+":
            case "M-":
                CalcSMmemory.memory();
                break;
            default:
                CalcSM.currentState = OPERAND2;
                operand2();
                break;
        }              
        CalcSM.debugPrint("OPERAND2 value string = " + CalcSM.OP2.getOperandString());       
        CalcSM.debugPrint("Exit - FRAC2 STATE");         
    }    
}
