package channelpopularity.util;

import channelpopularity.operation.Operation;

/*The class ParserResult parse's the line and split into operationName,videoname and video's metrics*/

public class ParserResult{
    public Operation operationName;
    public String videoName;
    public Metrics metrics;

    public ParserResult(Operation operationName, String videoName, Metrics metrics){
        this.operationName = operationName;
        this.videoName = videoName;
        this.metrics = metrics;
    }

    public Metrics getMetrics()
    {
        return metrics;
    }

    public String getVideoName()
    {
        return videoName;
    }

    public Operation getOperation()
    {
        return operationName;
    }

    public String toString()
    {
        return operationName + " " + videoName + " " + metrics;
    }
}