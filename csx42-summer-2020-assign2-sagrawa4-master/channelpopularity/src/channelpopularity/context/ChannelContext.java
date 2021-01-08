package channelpopularity.context;
import channelpopularity.util.FileProcessor;
import channelpopularity.state.StateName;
import channelpopularity.state.StateI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.state.Unpopular;

/* This class use the simple factory design pattern to fetch the required state*/

public class ChannelContext implements ContextI {
    private StateI curState;
    private HashMap<StateName, StateI> availableStates;
    
    /* The constructor of the Channelcontext accepts an instance of SimpleStateFactory.
    * @param SimpleStateFactoryI and List<StateName>
    */
    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames){
        availableStates = new HashMap<StateName, StateI>();
        for(int i=0; i< stateNames.size();i++)
        {
            StateName stateName = stateNames.get(i);
            StateI iState = stateFactoryIn.create(stateNames.get(i));
            availableStates.put(stateName, iState);
        }
        curState=availableStates.get(StateName.UNPOPULAR);
    }

    /*The setCurrentState accepts an enum representing the state to change to.
    * @param enum
    * return StateI
    */    
    
    public StateI setCurrentState(StateName nextState)
    {
        curState = availableStates.get(nextState);
        return curState;
    }
    
    public StateI getCurrentState()
    {
        return curState;
    }

    /*The ChangeState makes the change in state of a channel based on its popularityscore
    * @param none
    * return StateI
    */ 

    public StateI ChangeState()
    {
        if(curState.PopularityScoreOfChannel() >=0 && curState.PopularityScoreOfChannel() <=1000)
        {
            setCurrentState(StateName.UNPOPULAR);
        }
        else if(curState.PopularityScoreOfChannel() >1000 && curState.PopularityScoreOfChannel() <= 10000)
        {
            StateI test = setCurrentState(StateName.MILDLY_POPULAR);
            //System.out.println("StateName.MILDLY_POPULAR state change" + test);
        }
        else if(curState.PopularityScoreOfChannel() >10000 && curState.PopularityScoreOfChannel() <=100000)
        {
            setCurrentState(StateName.HIGHLY_POPULAR);
        }
        else if(curState.PopularityScoreOfChannel() >100000 && curState.PopularityScoreOfChannel() <= Integer.MAX_VALUE)
        {
            setCurrentState(StateName.ULTRA_POPULAR);
        }
        return null;
    }
}