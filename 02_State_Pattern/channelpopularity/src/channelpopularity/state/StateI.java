package channelpopularity.state;
import channelpopularity.util.Metrics;

public interface StateI {
   public boolean AdvertisementRequest(String videoName, int length); 
   public void AddVideo(String videoName);
   public void RemoveVideo(String videoName);
   public void MetricsOfVideo(String videoName, Metrics metrics);
   public float PopularityScoreOfChannel();
   public String GetStateName();
}
