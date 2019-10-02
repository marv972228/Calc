/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
 */
package statemachine;

import static enums.EnumOperationState.*;

/**
 *
 * @author marvi
 */
public class OperandOneState {

    public static void operand1() {
        CalcSM.debugPrint("Enter - OPERAND1 STATE");
        switch (CalcSM.getButtonActionValue()) {
            case "ceInput":
                CalcSM.clear();
                CalcSM.setCurrentState(BEGIN);
                ReadyState.ready();
                break;
            case "MRC":
                MemoryState.memory();
                break;
            case "+":
            case "-":
            case "/":
            case "%":
            case "*":
            case "^":
                CalcSM.setCurrentState(OPENTERED);
                OpEnteredState.opEntered();
                break;
        }

        CalcSM.debugPrint("Exit - OPERAND1 STATE");
    }

    public static void zero1() {
        CalcSM.debugPrint("Enter - ZERO1 STATE");

        switch (CalcSM.getButtonActionValue()) {
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
                CalcSM.setCurrentState(INT1);
                int1();
                break;
            case ".":
                CalcSM.OP1.setZeroString(true);
                CalcSM.OP1.setPointString(true);
                CalcSM.setCurrentState(FRAC1);
                frac1();
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                CalcSM.setCurrentState(OPENTERED);
                OpEnteredState.opEntered();
                break;
            case "MRC":
            case "M+":
            case "M-":
                MemoryState.memory();
                break;
            default:
                CalcSM.setCurrentState(OPERAND1);
                operand1();
                break;
        }

        CalcSM.debugPrint("Exit - ZERO1 STATE");
    }

    public static void int1() {
        CalcSM.debugPrint("Enter - INT1 STATE");
        switch (CalcSM.getButtonActionValue()) {
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
                CalcSM.OP1.appendIntString(CalcSM.getButtonActionValue());
                break;
            case ".":
                CalcSM.OP1.setZeroString(false);
                CalcSM.OP1.setPointString(true);
                CalcSM.setCurrentState(FRAC1);
                frac1();
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                CalcSM.setCurrentState(OPENTERED);
                OpEnteredState.opEntered();
                break;
            case "^2":
                break;
            case "MRC":
            case "M+":
            case "M-":
                MemoryState.memory();
                break;
            default:
                CalcSM.setCurrentState(OPERAND1);
                operand1();
                break;
        }

        CalcSM.debugPrint("OPERAND1 value string = " + CalcSM.OP1.getOperandString());
        CalcSM.debugPrint("Exit - INT1 STATE");
    }

    public static void frac1() {
        CalcSM.debugPrint("Enter - FRAC1 STATE");
        switch (CalcSM.getButtonActionValue()) {
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
                CalcSM.OP1.appendFracString(CalcSM.getButtonActionValue());
                break;
            case ".":
                break;
            case "+":
            case "-":
            case "/":
            case "*":
                CalcSM.setCurrentState(OPENTERED);
                OpEnteredState.opEntered();
                break;
            case "MRC":
            case "M+":
            case "M-":
                MemoryState.memory();
                break;
            default:
                CalcSM.setCurrentState(OPERAND1);
                operand1();
                break;
        }
        CalcSM.debugPrint("OPERAND1 value string = " + CalcSM.OP1.getOperandString());
        CalcSM.debugPrint("Exit - FRAC1 STATE");
    }
}
