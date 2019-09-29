/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package statemachine;

import static enums.EnumMathState.*;
import static enums.EnumOperationState.*;
import java.math.BigDecimal;

/**
 *
 * @author marvi
 */
public class ReadyState 
{
    public static void ready()
    {
        CalcSM.debugPrint("Enter - READY STATE");
        
        switch(CalcSM.currentState)
        {
            case BEGIN:
                begin();
                break;
            case RESULT:
                break;
        }        
        
        CalcSM.debugPrint("Exit - READY STATE");             
    }
    
    public static void begin()
    {
        CalcSM.debugPrint("Enter - BEGIN STATE");
        
        switch(CalcSM.getUserInput())
        {
            case "MRC":
                MemoryState.memory();
                break;
            case "negateInput":
                CalcSM.debugPrint("Go to NEGATED1");
                CalcSM.currentState = NEGATED1;
               
                break;
            case "0":
                CalcSM.debugPrint("Go to ZERO1");
                CalcSM.currentState = ZERO1;
                OperandOneState.zero1();
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
                CalcSM.debugPrint("Go to INT1");
                CalcSM.currentState = INT1;
                OperandOneState.int1();
                break;
                
            case "decimalPointInput":
                CalcSM.debugPrint("Go to FRAC1");
                break;
        }

        
        
        CalcSM.debugPrint("Exit - BEGIN STATE");        
    }
    
    public static void result()
    { 
        CalcSM.debugPrint("Enter - RESULT STATE");  
        CalcSM.isPercentage = false;
        
        switch(CalcSM.getUserInput())
        {
            case "+":
            case "-":
            case "/":
            case "*":
            case "^":
                CalcSM.isPercentageTop = false;
                CalcSM.OP1.clearOperand();
                CalcSM.OP2.clearOperand();
                CalcSM.OP1.setCalculatedString(CalcSM.getTotal().stripTrailingZeros().toPlainString());
                CalcSM.setTotal(new BigDecimal("0"));
                CalcSM.currentState = OPENTERED;
                OpEnteredState.opEntered();
                break;
            case "M-":
            case "M+":
                MemoryState.memory();
                break;
            case "(-)":
                CalcSM.setTotal(MathOperationsState.negate(CalcSM.getTotal()));
                break;
            case "%":
                MathOperationsState.percentOperation();
                break;
            case "=":
                CalcSM.setTotal(new BigDecimal(MathOperationsState.calculate()));
                break;
            default:
                CalcSM.mathState = NONE;
                CalcSM.clear();
                CalcSM.currentState = BEGIN;
                ready();
                
        }
        
        CalcSM.debugPrint("Exit - RESULT STATE");          
    } 
}
