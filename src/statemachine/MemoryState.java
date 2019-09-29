/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package statemachine;

import static enums.EnumMathState.NONE;
import static enums.EnumOperationState.*;

/**
 * This class adds the ability for the calculator to implement the memory feature
 * which exists on many standard calculators. 
 * 
 * @author marvi
 */
public class MemoryState 
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
        switch (CalcSM.getCurrentState())
        {
            case OPERAND1:
                CalcSM.setMemoryTotal(CalcSM.getMemoryTotal().ZERO);
                hasMemory = false;
                CalcSM.clear();
                CalcSM.setUserInput("");
                CalcSM.setCurrentState(BEGIN);
                ReadyState.begin();
                break;
            case BEGIN: 
            case ZERO1:
            case INT1:
            case FRAC1:
                CalcSM.OP1.clearOperand();
                CalcSM.OP1.setCalculatedString(CalcSM.getMemoryTotal().toPlainString());
                CalcSM.setCurrentState(OPERAND1);
                CalcSM.setUserInput("");
                OperandOneState.operand1();
                break;
            case RESULT:
                break;
            case OPENTERED:
                if (CalcSM.getMathState() != NONE)
                {
                    CalcSM.OP2.clearOperand();
                    CalcSM.OP2.setCalculatedString(CalcSM.getMemoryTotal().toPlainString());
                    CalcSM.setCurrentState(RESULT);
                    CalcSM.setUserInput("=");
                    ReadyState.result();
                }
                break;            
            case OPERAND2:
            case ZERO2:
            case INT2:
            case FRAC2:
                CalcSM.OP2.clearOperand();
                CalcSM.OP2.setCalculatedString(CalcSM.getMemoryTotal().toPlainString());
                CalcSM.setCurrentState(RESULT);
                CalcSM.setUserInput("=");
                ReadyState.result();
                break;
        }
        // if in opEntered, replace Operand2 with memory recall and go to result
    }
    
    public static void MemoryAdd()
    {
        CalcSM.debugPrint("Enter MEMORY ADD STATE");
        
        switch (CalcSM.getCurrentState())
        {

            case INT1:
            case ZERO1:
            case FRAC1:
                CalcSM.setMemoryTotal(CalcSM.getMemoryTotal().add(CalcSM.OP1.getBigDecimal()));
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.getMemoryTotal().toPlainString());
                break;
            case INT2:
            case ZERO2:
            case FRAC2:
                CalcSM.setMemoryTotal(CalcSM.getMemoryTotal().add(CalcSM.OP2.getBigDecimal()));
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.getMemoryTotal().toPlainString());
                break;
            case RESULT:
                CalcSM.setMemoryTotal(CalcSM.getMemoryTotal().add(CalcSM.getTotal()));
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.getMemoryTotal().toPlainString());
                break;
                
        }

        
        CalcSM.debugPrint("Exit MEMORY ADD STATE");        
    }
    
    public static void MemorySubtract()
    {
        CalcSM.debugPrint("Enter MEMORY SUBTRACT STATE");
        switch (CalcSM.getCurrentState())
        {
            case INT1:
            case ZERO1:
            case FRAC1:
                CalcSM.setMemoryTotal(CalcSM.getMemoryTotal().subtract(CalcSM.OP1.getBigDecimal()));        
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.getMemoryTotal().toPlainString());
                break;
            case OPERAND2:
                break;
            case INT2:
            case ZERO2:
            case FRAC2:
                CalcSM.setMemoryTotal(CalcSM.getMemoryTotal().subtract(CalcSM.OP2.getBigDecimal()));        
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.getMemoryTotal().toPlainString());
                break;
            case RESULT:
                CalcSM.setMemoryTotal(CalcSM.getMemoryTotal().subtract(CalcSM.getTotal()));
                CalcSM.debugPrint("MEMORY TOTAL = " + CalcSM.getMemoryTotal().toPlainString());
                break;               
        }
        

        
        CalcSM.debugPrint("Exit MEMORY SUBTRACT STATE");           
    }
    
    
    
}
