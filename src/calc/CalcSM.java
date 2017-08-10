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
 * Calc Machine
 */
public class CalcSM {
    
    public static final Operand OP1 = new Operand();
    public static final Operand OP2 = new Operand();
    
    public static EnumOperationState currentState;
    public static EnumMathState mathState;
    
    public static BigDecimal total;
    public static BigDecimal memoryTotal;
    private static String userInput;
    public static boolean isPercentage;
    public static boolean isPercentageTop;
    public static boolean hasError;
    private static boolean printOutBool;
   
    
    public static void initialize()
    {
        debugPrint("INITIALIZE");
        setUserInput("...");
        printOutBool = true;                // set to true for console prinouts 
        currentState = ON;
        isPercentage = false;
        isPercentageTop = false;
        hasError = false;
        MemoryState.hasMemory = false;
        
        mathState = NONE;
        total = new BigDecimal("0");
        memoryTotal = new BigDecimal("0");
        clear();
        
    }
    
    public static void clear()
    {
        OP1.clearOperand();
        OP2.clearOperand();
        isPercentage = false;
        isPercentageTop = false;
        hasError = false;
        mathState = NONE;
        currentState = ON;
        total = new BigDecimal("0");
        //memoryTotal = new BigDecimal("0");
    }
    
    public static String getUserInput()
    {
        return userInput;
    }
    
    public static void setUserInput(String str)
    {
        CalcSM.userInput = str;
    }
    
    public static void input(String str)
    {
        setUserInput(str);
        debugPrint("UserInput = " + getUserInput());
        
        switch(getUserInput())
        {
            case "onInput":
                currentState = ON;
                break;
            case "offInput":
                currentState = OFF;
                break;
        }
        
        determineState();
    }
    
    
    public static void determineState()
    {
        debugPrint("#### Enter - DETERMINE STATE = " + currentState.toString() + " #############");
        
        switch(currentState)
        {
            case ON:
                on();
                break;
            case OFF:
                off();
                break;
            case MEMORY:
                MemoryState.memory();
                break;
            case BEGIN:
                CalcSMready.begin();
                break;
            case RESULT:
                CalcSMready.result();
                break;
            case OPERAND1:
                OperandOneState.operand1();
                break;
            case ZERO1:
                OperandOneState.zero1();
                break;
            case INT1:
                OperandOneState.int1();
                break;
            case FRAC1:
                OperandOneState.frac1();
                break;
            case OPENTERED:
                OpEnteredState.opEntered();
                break;
            case OPERAND2:
                OperandTwoState.operand2();
                break;
            case ZERO2:
                OperandTwoState.zero2();
                break;
            case INT2:
                OperandTwoState.int2();
                break;
            case FRAC2:
                OperandTwoState.frac2();
                break;
        }
        
        
        debugPrint("#### Exit - DETERMINE STATE ##########");
    }
    
    public static void on()
    {
        debugPrint("Enter - ON STATE");
        
        clear();
        currentState = BEGIN;
        CalcSMready.ready();
        
        
        
        debugPrint("Exit - ON STATE");        
    }
    
    public static void off()
    {
        debugPrint("Enter - OFF STATE");
        
        
        
        debugPrint("Exit - OFF STATE");        
    }
    
    // ######################################
  
    public static String operationOutput()
    {
        
        switch (currentState)
        {
            case ON:
                return "...";
            case OFF:
                return "**OFF**";
            case BEGIN:
                return "...";
            case MEMORY:
                return CalcSM.total.stripTrailingZeros().toPlainString();
            case OPERAND1:
            case ZERO1:
            case INT1:
            case FRAC1:
                //return OP1.getOperandString();
            case OPENTERED:
                //return OpEnteredState.opEnteredStringVal();
            case OPERAND2:
            case ZERO2:
            case INT2:
            case FRAC2:
                //return OP2.getOperandString();
                return Error(OP1.getOperandString() + " " +OpEnteredState.opEnteredStringVal() + " " + OP2.getOperandString());
                
            case RESULT:
                return Error(OP1.getOperandString() + isPercentTop() + " " +OpEnteredState.opEnteredStringVal() + " " + OP2.getOperandString());

            
        }
        
        
        return "###";
    }
    
    public static String resultOutput()
    {
        switch (currentState)
        {
            case ON:
                return "...";
            case OFF:
                return "...";
            case BEGIN:
                return "...";
            case OPERAND1:
            case ZERO1:
            case INT1:
            case FRAC1:

            case OPENTERED:

            case OPERAND2:
            case ZERO2:
            case INT2:
            case FRAC2:
                break;
            case RESULT:
                return Error(CalcSM.total.stripTrailingZeros().toPlainString() + isPercent());
            
        }
        return "";
    }
    
    public static String hasMemory()
    {
        if(MemoryState.hasMemory)
            return "MEMORY";
        else
            return "";
    }
    
    public static String Error(String input)
    {
        if(hasError)
            return "ERROR";
        else if (input.length() > 20)
            return "ERROR";
        else
            return input;
    }
    
    
    public static String isPercent()
    {
        if(isPercentage)
        {
            return "%";
        }
        else 
        {
            return "";
        }
    }
    
    public static String isPercentTop()
    {
        if (isPercentageTop)
        {
            return "%";
        }
        else
        {
            return "";
        }
    }
    
    
    // ***************************************
    public static void debugPrint(String str)
    {
        if(printOutBool)
            System.out.println(str);
    }
    
    public static String numWithCommas(String str)
    {
        if(isPercentage || isPercentageTop) {
            return str;
        }
        else
        {
            String temp = "";
            int j = 0;
            for (int i = str.length() - 1; i > -1; --i)
            {
                if (j < 2)
                {
                    ++j;
                    temp = str.charAt(i) + temp;
                }
                else
                {
                    j = 0;
                    if (i != 0)
                        temp = "," + str.charAt(i) + temp; 
                    else
                        temp = str.charAt(i) + temp;
                }
            }
            return temp;
        }
    }
}
