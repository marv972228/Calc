/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import static calc.EnumOperationState.*;

/**
 *
 * @author marvi
 */
public class CalcSMoperandOne {
    
    public static void operand1()
    {
        CalcSM.debugPrint("Enter - OPERAND1 STATE");
        switch(CalcSM.getUserInput())
        {
            case "ceInput":
                CalcSM.clear();
                CalcSM.currentState = BEGIN;
                CalcSMready.ready();
                break;
            case "MRC":
                CalcSMmemory.memory();
                break;
            case "+":
            case "-":
            case "/":
            case "%":
            case "*":
            case "^":
                CalcSM.currentState = OPENTERED;
                CalcSMopEntered.opEntered();
                break;
        }
        
        CalcSM.debugPrint("Exit - OPERAND1 STATE");   
    }
    
    public static void zero1()
    {
        CalcSM.debugPrint("Enter - ZERO1 STATE");
        
        switch(CalcSM.getUserInput())
        {
            case "(-)":
                CalcSM.OP1.setNegation();
                break;
            case "0":
                CalcSM.debugPrint("-- Set Zero True");
                CalcSM.OP1.setZeroString(true);
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
                CalcSM.OP1.setZeroString(false); // 0 front not needed in this case
                CalcSM.currentState = INT1;
                int1();
                break;
            case ".":
                CalcSM.OP1.setZeroString(true);
                CalcSM.OP1.setPointString(true);
                CalcSM.currentState = FRAC1;
                frac1();
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                CalcSM.currentState = OPENTERED;
                CalcSMopEntered.opEntered();
                break;
            case "MRC":
            case "M+":
            case "M-":
                CalcSMmemory.memory();
                break;
            default:
                CalcSM.currentState = OPERAND1;
                operand1();
                break;
        }
        
        
        CalcSM.debugPrint("Exit - ZERO1 STATE");         
    }
    
    public static void int1()
    {
        CalcSM.debugPrint("Enter - INT1 STATE");
        switch(CalcSM.getUserInput())
        {
            case "(-)":
                CalcSM.OP1.setNegation();
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
                CalcSM.debugPrint("+++ Append INTEGER1 +++");
                CalcSM.OP1.appendIntString(CalcSM.getUserInput());
                break;
            case ".":
                CalcSM.OP1.setZeroString(false);
                CalcSM.OP1.setPointString(true);
                CalcSM.currentState = FRAC1;
                frac1();
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                CalcSM.currentState = OPENTERED;
                CalcSMopEntered.opEntered();
                break;
            case "^2":
                break;
            case "MRC":
            case "M+":
            case "M-":
                CalcSMmemory.memory();
                break;
            default:
                CalcSM.currentState = OPERAND1;
                operand1();
                break;
        }        
        
        CalcSM.debugPrint("OPERAND1 value string = " + CalcSM.OP1.getOperandString());
        CalcSM.debugPrint("Exit - INT1 STATE");         
    }
    
    public static void frac1()
    {
        CalcSM.debugPrint("Enter - FRAC1 STATE");
        switch(CalcSM.getUserInput())
        {
            case "(-)":
                CalcSM.OP1.setNegation();
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
                CalcSM.debugPrint("+++ Append FRAC1 +++");
                CalcSM.OP1.appendFracString(CalcSM.getUserInput());
                break;
            case ".":
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                CalcSM.currentState = OPENTERED;
                CalcSMopEntered.opEntered();
                break;
            case "MRC":
            case "M+":
            case "M-":
                CalcSMmemory.memory();
                break;
            default:
                CalcSM.currentState = OPERAND1;
                operand1();
                break;
        }              
        CalcSM.debugPrint("OPERAND1 value string = " + CalcSM.OP1.getOperandString());       
        CalcSM.debugPrint("Exit - FRAC1 STATE");         
    }
}
