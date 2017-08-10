/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import java.math.BigDecimal;

/**
 *
 * @author marvi
 */
public class Operand {
    // OPERAND 1
    private String negatedString;           // holds (-1), can flip
    private String zeroString;              // used for first 0 before .
    private String intString;               // integer values
    private String pointString;             // point string, decimal place
    private String fracString;              // integer values after .
    private String calculatedString;        // holds new calculated value
    private BigDecimal operandBigDecimal;   // holds calculated value, big decimal   
    

    public Operand()
    {
        negatedString = "";
        setZeroString(false);
        intString = "";
        pointString = "";
        fracString = "";
        setCalculatedString("");
        operandBigDecimal = new BigDecimal("0");       
    }
    
    
    public void clearOperand()
    {
        negatedString = "";
        setZeroString(false);
        intString = "";
        pointString = "";
        fracString = "";

        operandBigDecimal = new BigDecimal("0");
        setCalculatedString("");
    }
    
    public String getOperandString()
    {
        return getNegation() + getZeroString() + getIntString() + getPointString() +getFracString() + getCalculatedString();
    }
    
    public void updateValue()
    {
        operandBigDecimal = new BigDecimal(getOperandString());        
    }
    
    public void setBigDecimal(String num)
    {
        operandBigDecimal = new BigDecimal(num);
    }
    
    public BigDecimal getBigDecimal()
    {
        updateValue();
        return operandBigDecimal;
    }

    public String getNegation()
    {
        return negatedString;
    }    

    public void setNegation()
    {
        if (negatedString.equals(""))
            negatedString = "-";
        else
            negatedString = "";
    }
    

    public String getZeroString()
    {
        return zeroString;
    }
    
    public void setZeroString(boolean set)
    {
        if (set)
            zeroString = "0";
        else
            zeroString = "";
    }
    
    public String getIntString()
    {
        return intString;
    }
    
    public void setIntString(String str)
    {
        intString = str;
    }
    
    public void appendIntString(String str)
    {
        intString += str;
    }
    
    public String getPointString()
    {
        return pointString;
    }
    
    public void setPointString(boolean set)
    {
        if (set)
            pointString = ".";
        else
            pointString = "";
        
    }
    
    public String getFracString()
    {
        return fracString;
    }
    
    public void setFracString(String str)
    {
        fracString = str;
    }    
    
    public void appendFracString(String str)
    {
        fracString += str;
    }
    
    public void setCalculatedString(String str)
    {
        calculatedString = str;
    }
    
    public String getCalculatedString() 
    {
        return calculatedString;
    }
    
    // Big Decimal value formatted
    public String getOperandCommaTotalString()
    {
        
        
        return "";
    }
    
    // just the int part formatted
    public String getOperandCommaString()
    {
        
        
        return "";
    }    
    
}