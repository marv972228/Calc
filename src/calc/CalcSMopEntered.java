/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package calc;

import static calc.EnumMathState.*;
import static calc.EnumOperationState.*;

/**
 *
 * @author marvi
 */
public class CalcSMopEntered 
{
    public static void opEntered()
    {
        CalcSM.debugPrint("Enter OP ENTERED STATE");
        switch(CalcSM.getUserInput())
        {
            case "MRC":
                CalcSMmemory.memory();
                break;
            case "+":
                CalcSM.mathState = ADD;
                break;
            case "-":
                CalcSM.mathState = SUBTRACT;
                break;
            case "/":
                CalcSM.mathState = DIVIDE;
                break;
            case "*":
                CalcSM.mathState = MULTIPLY;
                break;
            case "^":
                CalcSM.mathState = EXPONENT;
                break;
            case "(-)":
                CalcSM.OP2.setNegation();
                break;
            case "0":
                CalcSM.currentState = ZERO2;
                CalcSMoperandTwo.zero2();
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
                CalcSM.OP2.setZeroString(false);
                CalcSM.OP2.setPointString(false);
                CalcSM.currentState = INT2;
                CalcSMoperandTwo.int2();
                break;
            case ".":
                CalcSM.OP2.setZeroString(true);
                CalcSM.OP2.setPointString(true);
                CalcSM.currentState = FRAC2;
                CalcSMoperandTwo.frac2();
                break;
            default:
                break;
                
                    
        }
        
        CalcSM.debugPrint("Exit OP ENTERED STATE - mathstate = " + CalcSM.mathState.toString());
    }
    
    public static String opEnteredStringVal() 
    {
        switch (CalcSM.mathState)
        {
            case ADD:
                return "+";
            case SUBTRACT:
                return "-";
            case MULTIPLY:
                if(CalcSM.isPercentageTop)
                    return "of";
                else
                    return "*";
            case DIVIDE:
                return "/";
            case EXPONENT:
                return "^";
            default:
                return "";
        }
    }
}
