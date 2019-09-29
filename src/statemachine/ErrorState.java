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
public class ErrorState {
    
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
                ReadyState.begin();
                break;
        }
        
        CalcSM.debugPrint("Exit Error state");
    }
    
}
