/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import static calc.EnumMathState.*;
import static calc.EnumOperationState.*;
import java.math.BigDecimal;

/**
 *
 * @author marvi
 */
public class CalcSMready 
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
                CalcSMmemory.memory();
                break;
            case "negateInput":
                CalcSM.debugPrint("Go to NEGATED1");
                CalcSM.currentState = NEGATED1;
               
                break;
            case "0":
                CalcSM.debugPrint("Go to ZERO1");
                CalcSM.currentState = ZERO1;
                CalcSMoperandOne.zero1();
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
                CalcSMoperandOne.int1();
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
                CalcSM.OP1.setCalculatedString(CalcSM.total.stripTrailingZeros().toPlainString());
                CalcSM.total = new BigDecimal("0");
                CalcSM.currentState = OPENTERED;
                CalcSMopEntered.opEntered();
                break;
            case "M-":
            case "M+":
                CalcSMmemory.memory();
                break;
            case "(-)":
                CalcSM.total = mathBigDecimal.negate(CalcSM.total);
                break;
            case "%":
                mathBigDecimal.percentOperation();
                break;
            case "=":
                CalcSM.total = new BigDecimal(mathBigDecimal.calculate());
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
