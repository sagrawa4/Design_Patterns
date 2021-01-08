package channelpopularity.state.factory;
import channelpopularity.state.StateName;
import channelpopularity.state.StateI;
import channelpopularity.state.Unpopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;

/* The class SimpleStateFactory accepts enum representing the state to be instantiated and
* implements SimpleStateFactoryInterface
*/

public class SimpleStateFactory implements SimpleStateFactoryI {
       
    private StateI state =null;

    /*The create method of SimpleStateFactory accepts an enum representing the state to be instantiated.
    * @param StateName
    * @return StateI
    */
    public StateI create(StateName currentState)
    {
        if(currentState.equals(StateName.UNPOPULAR))
        {                  
            state = new Unpopular();                                
        } 
        else if(currentState.equals(StateName.MILDLY_POPULAR))
        {
            state= new MildlyPopular();
        } 
        else if(currentState.equals(StateName.HIGHLY_POPULAR))
        {
            state = new HighlyPopular();
        } 
        else if(currentState.equals(StateName.ULTRA_POPULAR))
        {
            state = new UltraPopular();
        }
        return state;
    }
}
