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
public class CalcSMerror {
    
    public static void error()
    {
        CalcSM.debugPrint("Enter ERROR state");
        CalcSM.setUserInput("error");
        
        switch(CalcSM.getUserInput())
        {
            case "error":
                break;
            default:
                CalcSM.clear();
                CalcSM.currentState = BEGIN;
                CalcSMready.begin();
                break;
        }
        
        CalcSM.debugPrint("Exit Error state");
    }
    
}
