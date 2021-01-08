
package channelpopularity.state;

import channelpopularity.util.Metrics;
import java.util.HashMap;
import java.util.Map;

/* The abstract class implements logic for calculating the popularity score for videos, and in turn of the channel*/

public abstract class AbstractState implements StateI {
       
    protected static HashMap<String, Float> popularityScoreVideo = new HashMap<String,Float>();


    /*The AdvertisementRequest method calls CheckIfVideoExist to calculates If the video does not exist, 
    *throw an appropriate exception reporting a meaningful error message and terminate.
    *@param String,int
    *@return boolean
    */ 
    protected void CheckIfVideoExist(String videoName)
    {
        if(!popularityScoreVideo.containsKey(videoName))
        {
            throw new RuntimeException("Video associated with an advertisement request does not exist");
        }
    }

    /*The CalculatePopularityScoreOfVideo method calculates popularity score of a video
    * @param Metrics
    * return float
    */ 
    private float CalculatePopularityScoreOfVideo(Metrics metrics)
    {
        float deltaLikeDisLike = metrics.getLikes() - metrics.getDislikes();
        if(deltaLikeDisLike < 0)
        {
            //deltaLikeDisLike = 0;
        }
        float popularityScoreOfVideo = (metrics.getViews() + 2*(deltaLikeDisLike));
        
        return popularityScoreOfVideo;
    }


    /*The AddVideo method adds a video to the channel i.e Hashmap named popularityScoreVideo
    * @param String
    * return nothing
    */ 
    public void AddVideo(String videoName)
    {
        if(popularityScoreVideo.containsKey(videoName))
        {
            throw new RuntimeException("Video already exists with name - " + videoName);
        }
        // When we insert video for first time, metrics should be empty.
        popularityScoreVideo.put(videoName, 0.0f);
    }

    /*The RemoveVideo method removes a video from the channel i.e Hashmap named popularityScoreVideo
    * @param String
    * return nothing
    */ 
    public void RemoveVideo(String videoName)
    {
        if(!popularityScoreVideo.containsKey(videoName))
        {
            throw new RuntimeException("Cannot remove as video does not exists -  " + videoName); 
        }
        // Remove video as well as popularity score for the video.
        popularityScoreVideo.remove(videoName);
    }


    /*The MetricsOfVideo method stores the log of popularity score of a video present in the channel
    * @param String, Metrics
    * return nothing
    */
    public void MetricsOfVideo(String videoName, Metrics metrics)
    {
        if(!popularityScoreVideo.containsKey(videoName))
        {
            throw new RuntimeException("Video does not exists! :: " + videoName); 
        }

        // Calculate new popularity score
        float newPopularityScore = CalculatePopularityScoreOfVideo(metrics);
        //System.out.println("newPopularityScore" + newPopularityScore);

        // Get existing popularity score
        float existingPopularityScore = popularityScoreVideo.get(videoName);
        //System.out.println("existingPopularityScore" + existingPopularityScore);

        // Get updated popularity score
        float updatePopularityScore = newPopularityScore + existingPopularityScore;

        // store score.
        popularityScoreVideo.put(videoName, updatePopularityScore);
    }


    /*The  PopularityScoreOfChannel method calculates avg popularity score of a channel
    * @param nothing
    * return float
    */ 
    public float PopularityScoreOfChannel()
    {
        // channel has no videos, empty score.
        if(popularityScoreVideo.size() == 0)
        {
            return 0.0f;
        }

        // Calcuate average score of video.
        float sum = 0.0f;
        for(float value : popularityScoreVideo.values())
        {
            sum += value;
        }
        return (sum) / popularityScoreVideo.size();
    }
}



 
 