/*
 * Author: Marvin Mallari
 * Email: marvinmallari@gmail.com
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
