package channelpopularity.context;
import channelpopularity.state.StateName;
import channelpopularity.state.StateI;

public interface ContextI {
    public StateI setCurrentState(StateName nextState);
    public StateI getCurrentState();
}
