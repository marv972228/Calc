/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package statemachine;

import enums.EnumMathState;
import enums.EnumOperationState;
import static enums.EnumMathState.*;
import static enums.EnumOperationState.*;
import java.math.BigDecimal;

/**
 *
 * Calc Machine
 */
public class CalcSM {

    public static final Operand OP1 = new Operand();
    public static final Operand OP2 = new Operand();

    private static EnumOperationState currentState;
    private static EnumMathState mathState;

    private static BigDecimal total;
    private static BigDecimal memoryTotal;
    private static String buttonActionValue;
    public static boolean isPercentage;
    public static boolean isPercentageTop;
    private static boolean error;
    private static boolean printOutBool;

    /**
     * This method sets the default states
     */
    public static void initialize() {
        debugPrint("INITIALIZE");

        // set the default calculator value
        setButtonActionValue("...");

        // set to true for console printouts
        printOutBool = false;

        // set the initial state to ON
        currentState = ON;

        isPercentage = false;
        isPercentageTop = false;
        error = false;

        MemoryState.hasMemory = false;
        mathState = NONE;
        total = new BigDecimal("0");
        memoryTotal = new BigDecimal("0");
        clear();

    }

    /**
     * This method resets the calculator states
     */
    public static void clear() {
        OP1.clearOperand();
        OP2.clearOperand();
        isPercentage = false;
        isPercentageTop = false;
        error = false;
        mathState = NONE;
        currentState = ON;
        total = new BigDecimal("0");
        //memoryTotal = new BigDecimal("0");
    }

    /**
     * This method gets the buttonActionValue value
     *
     * @return
     */
    public static String getButtonActionValue() {
        return buttonActionValue;
    }

    /**
     * This method sets the buttonActionValue. Usually is only one character,
     * but maybe a predefined string value for some determined state to be acted
     * upon
     *
     * @param str
     */
    public static void setButtonActionValue(String str) {
        CalcSM.buttonActionValue = str;
    }

    /**
     * This is the method used via the CalcController to set the value of the
     * last user action on the calculator
     * 
     * This basically is listening for whatever value is triggered by 
     * touching some button on the calculator. Typically each button has
     * a string of length one, but a few buttons have more chars to 
     * better describe it's actions.
     * 
     * This method will see first check if a user has turned on or off 
     * the calculator. As the sequence of buttons are pushed, state changes 
     * and determines where in the state machine action is happening.
     * 
     *
     * @param str
     */
    public static void buttonAction(String str) {
        setButtonActionValue(str);
        debugPrint("UserInput = " + getButtonActionValue());

        switch (getButtonActionValue()) {
            case "onInput":
                currentState = ON;
                break;
            case "offInput":
                currentState = OFF;
                break;
        }

        determineState();
    }

    /**
     * This method determines one state the calculator is in. Depending on the
     * user actions, operand1 value and operand 2 value, and other state, state
     * is continually updated. Through the state machine process. This method is
     * used after each buttonAction to determine what the next set of operations
     * should be...
     */
    public static void determineState() {
        debugPrint("#### Enter - DETERMINE STATE = " + currentState.toString() + " #############");

        switch (currentState) {
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
                ReadyState.begin();
                break;
            case RESULT:
                ReadyState.result();
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

    public static void on() {
        debugPrint("Enter - ON STATE");

        clear();
        currentState = BEGIN;
        ReadyState.ready();

        debugPrint("Exit - ON STATE");
    }

    public static void off() {
        debugPrint("Enter - OFF STATE");

        debugPrint("Exit - OFF STATE");
    }

    // ######################################
    public static String operationOutput() {

        switch (currentState) {
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
                return errorOutputCheck(OP1.getOperandString() + " " + OpEnteredState.opEnteredStringVal() + " " + OP2.getOperandString());

            case RESULT:
                return errorOutputCheck(OP1.getOperandString() + isPercentTop() + " " + OpEnteredState.opEnteredStringVal() + " " + OP2.getOperandString());

        }

        return "###";
    }

    public static String resultOutput() {
        switch (currentState) {
            case ON:
                return "...";
            case OFF:
                return "...";
            case BEGIN:
                return "...";
                
            // for these cases, nothing will show in the result window until RESULT state is reached
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
                return errorOutputCheck(CalcSM.total.stripTrailingZeros().toPlainString() + isPercent());

        }
        return "";
    }

    public static String hasMemory() {
        if (MemoryState.hasMemory) {
            return "MEMORY";
        } else {
            return "";
        }
    }

    /**
     * When the result is finally calculated, check if the length of the string
     * goes beyond the window, if so just error out.
     * 
     * Future is to add some scientific notation to better display giant numbers
     * 
     * @param input
     * @return 
     */
    public static String errorOutputCheck(String input) {
        if (error) {
            return "ERROR";
        } else if (input.length() > 20) {
            return "ERROR";
        } else {
            return input;
        }
    }

    public static String isPercent() {
        if (isPercentage) {
            return "%";
        } else {
            return "";
        }
    }

    public static String isPercentTop() {
        if (isPercentageTop) {
            return "%";
        } else {
            return "";
        }
    }

    // ***************************************
    public static void debugPrint(String str) {
        if (printOutBool) {
            System.out.println(str);
        }
    }

    @Deprecated
    public static String numWithCommas(String str) {
        if (isPercentage || isPercentageTop) {
            return str;
        } else {
            String temp = "";
            int j = 0;
            for (int i = str.length() - 1; i > -1; --i) {
                if (j < 2) {
                    ++j;
                    temp = str.charAt(i) + temp;
                } else {
                    j = 0;
                    if (i != 0) {
                        temp = "," + str.charAt(i) + temp;
                    } else {
                        temp = str.charAt(i) + temp;
                    }
                }
            }
            return temp;
        }
    }

    public static boolean isError() {
        return error;
    }

    public static void setError(boolean error) {
        CalcSM.error = error;
    }

    public static BigDecimal getTotal() {
        return total;
    }

    public static void setTotal(BigDecimal total) {
        CalcSM.total = total;
    }

    public static BigDecimal getMemoryTotal() {
        return memoryTotal;
    }

    public static void setMemoryTotal(BigDecimal memoryTotal) {
        CalcSM.memoryTotal = memoryTotal;
    }

    public static EnumOperationState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(EnumOperationState currentState) {
        CalcSM.currentState = currentState;
    }

    public static EnumMathState getMathState() {
        return mathState;
    }

    public static void setMathState(EnumMathState mathState) {
        CalcSM.mathState = mathState;
    }

}
