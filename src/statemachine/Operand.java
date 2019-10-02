package statemachine;

import java.math.BigDecimal;

/**
 * Author: Marvin Mallari
 *
 * About: This class holds the values of the two operands. This class can be
 * used if more than one operand is designed into the calculator for various
 * operations Note this is called dynamically in the program. The idea for the
 * dynamic design is to allow future versions of the calculator to handle long
 * strings of input that will need the requirement of some recursive parser to
 * handle the calculations
 *
 */
public class Operand {

    // OPERAND 1
    private String negatedString;           // holds (-1), can flip based on user input
    private String zeroString;              // used for first 0 before .
    private String intString;               // integer values
    private String pointString;             // point string, decimal place
    private String fracString;              // integer values after .
    private String calculatedString;        // holds new calculated value
    private BigDecimal operandBigDecimal;   // holds calculated value, big decimal   

    // constructor
    public Operand() {
        // initialize the different strings that constructs the user value to null 
        negatedString = "";
        setZeroString(false);
        intString = "";
        pointString = "";
        fracString = "";
        setCalculatedString("");
        operandBigDecimal = new BigDecimal("0");  // might not have to do this     
    }

    // this restarts the operand
    public void clearOperand() {
        negatedString = "";
        setZeroString(false);
        intString = "";
        pointString = "";
        fracString = "";
        operandBigDecimal = new BigDecimal("0");
        setCalculatedString("");
    }

    // returns the string of the user input, used before the number is turned into a bigdecimal
    public String getOperandString() {
        return getNegation() + getZeroString() + getIntString() + getPointString() + getFracString() + getCalculatedString();
    }

    // turn string into bigdecimal value
    public void updateValue() {
        operandBigDecimal = new BigDecimal(getOperandString());
    }

    public void setBigDecimal(String num) {
        operandBigDecimal = new BigDecimal(num);
    }

    public BigDecimal getBigDecimal() {
        updateValue();
        return operandBigDecimal;
    }

    public String getNegation() {
        return negatedString;
    }

    public void setNegation() {
        if (negatedString.equals("")) {
            negatedString = "-";
        } else {
            negatedString = "";
        }
    }

    public String getZeroString() {
        return zeroString;
    }

    public void setZeroString(boolean set) {
        if (set) {
            zeroString = "0";
        } else {
            zeroString = "";
        }
    }

    public String getIntString() {
        return intString;
    }

    public void setIntString(String str) {
        intString = str;
    }

    public void appendIntString(String str) {
        intString += str;
    }

    public String getPointString() {
        return pointString;
    }

    public void setPointString(boolean set) {
        if (set) {
            pointString = ".";
        } else {
            pointString = "";
        }

    }

    public String getFracString() {
        return fracString;
    }

    public void setFracString(String str) {
        fracString = str;
    }

    public void appendFracString(String str) {
        fracString += str;
    }

    public void setCalculatedString(String str) {
        calculatedString = str;
    }

    public String getCalculatedString() {
        return calculatedString;
    }

    // Big Decimal value formatted
    public String getOperandCommaTotalString() {

        return "";
    }

    // just the int part formatted
    public String getOperandCommaString() {

        return "";
    }

}
