package channelpopularity.state;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.AbstractState;
import channelpopularity.util.InputParser;
import channelpopularity.util.ParserResult;
import channelpopularity.operation.Operation;
import channelpopularity.util.Metrics;
import java.util.HashMap;
import java.util.Map;

/*The MildlyPopular state method extends the Abstract state class */
public class MildlyPopular extends AbstractState{

    /*The AdvertisementRequest method calculates If the input is valid, 
    *report if the request is approved or rejected based on whether the length of the advertisement falls within 
    *the current state's acceptable ad length range.
    *@param String,int
    *@return boolean
    */
    public boolean AdvertisementRequest(String videoName, int length)
    {
        CheckIfVideoExist(videoName);
        return (length <=20 && length >1);
    }

    /* GetStateName is getter method that gets the current state enum from StataName*/
    public String GetStateName()
    {
        return StateName.MILDLY_POPULAR.toString();
    }
}