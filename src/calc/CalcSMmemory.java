/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import static calc.EnumMathState.NONE;
import static calc.EnumOperationState.*;

/**
 *
 * @author marvi
 */
public class CalcSMmemory 
{
    
    public static boolean hasMemory;
    
    public static void memory() 
    {
        CalcSM.debugPrint("Enter MEMORY STATE");
        switch(CalcSM.getUserInput())
        {
            case "MRC":
                if(hasMemory)
                {
                    MemoryRecall();
                }
                break;
            case "M+":
                MemoryAdd();
                hasMemory = true;
                break;
            case "M-":
                MemorySubtract();
                hasMemory = true;
                break;
        }
                
        CalcSM.debugPrint("Exit MEMORY STATE");
    }
    
    public static void MemoryRecall()
    {
        // if in begin state, replace operand1 with memory recall go to opEntered
        switch (CalcSM.currentState)
        {
            case OPERAND1:
                CalcSM.memoryTotal = CalcSM.memoryTotal.ZERO;
                hasMemory = false;
                CalcSM.clear();
                CalcSM.setUserInput("");
                CalcSM.currentState = BEGIN;
                CalcSMready.begin();
                break;
            case BEGIN: 
            case ZERO1:
            case INT1:
            case FRAC1:
                CalcSM.OP1.clearOperand();
                CalcSM.OP1.setCalculatedString(CalcSM.memoryTotal.toPlainString());
                CalcSM.currentState = OPERAND1;
                CalcSM.setUserInput("");
                CalcSMoperandOne.operand1();
                break;
            case RESULT:
                break;
            case OPENTERED:
                if (CalcSM.mathState != NONE)
                {
                    CalcSM.OP2.clearOperand();
                    CalcSM.OP2.setCalculatedString(CalcSM.memoryTotal.toPlainString());
                    CalcSM.currentState = RESULT;
                    CalcSM.setUserInput("=");
                    CalcSMready.result();
                }
                break;            
            case OPERAND2:
            case ZERO2:
            case INT2:
            case FRAC2:
                CalcSM.OP2.clearOperand();
                CalcSM.OP2.setCalculatedString(CalcSM.memoryTotal.toPlainString());
                CalcSM.currentState = RESULT;
                CalcSM.setUserInput("=");
                CalcSMready.result();
                break;
        }
        // if in opEntered, replace Operand2 with memory recall and go to result
    }
    
    public static void MemoryAdd()
    {
        CalcSM.debugPrint("Enter MEMORY ADD STATE");
        
        switch (CalcSM.currentState)
        {

            case INT1:
            case ZERO1:
            case FRAC1:
                CalcSM.memoryTotal =  CalcSM.memoryTotal.add(CalcSM.OP1.getBigDecimal());
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.memoryTotal.toPlainString());
                break;
            case INT2:
            case ZERO2:
            case FRAC2:
                CalcSM.memoryTotal =  CalcSM.memoryTotal.add(CalcSM.OP2.getBigDecimal());
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.memoryTotal.toPlainString());
                break;
            case RESULT:
                CalcSM.memoryTotal = CalcSM.memoryTotal.add(CalcSM.total);
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.memoryTotal.toPlainString());
                break;
                
        }

        
        CalcSM.debugPrint("Exit MEMORY ADD STATE");        
    }
    
    public static void MemorySubtract()
    {
        CalcSM.debugPrint("Enter MEMORY SUBTRACT STATE");
        switch (CalcSM.currentState)
        {
            case INT1:
            case ZERO1:
            case FRAC1:
                CalcSM.memoryTotal =  CalcSM.memoryTotal.subtract(CalcSM.OP1.getBigDecimal());        
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.memoryTotal.toPlainString());
                break;
            case OPERAND2:
                break;
            case INT2:
            case ZERO2:
            case FRAC2:
                CalcSM.memoryTotal =  CalcSM.memoryTotal.subtract(CalcSM.OP2.getBigDecimal());        
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.memoryTotal.toPlainString());
                break;
            case RESULT:
                CalcSM.memoryTotal = CalcSM.memoryTotal.subtract(CalcSM.total);
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.memoryTotal.toPlainString());
                break;               
        }
        

        
        CalcSM.debugPrint("Exit MEMORY SUBTRACT STATE");           
    }
    
    
    
}
