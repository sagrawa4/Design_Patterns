package channelpopularity.state.factory;
import channelpopularity.state.StateName;
import channelpopularity.state.StateI;

public interface SimpleStateFactoryI {
    public StateI create(StateName currentState);
}
