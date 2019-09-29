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
    
    /**
     * This method enters the error state, not much happens here but is handled
     * outside this state
     */
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
                CalcSM.setCurrentState(BEGIN);
                ReadyState.begin();
                break;
        }
        
        CalcSM.debugPrint("Exit Error state");
    }
    
}
