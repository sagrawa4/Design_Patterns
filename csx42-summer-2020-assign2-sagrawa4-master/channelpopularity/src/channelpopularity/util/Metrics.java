package channelpopularity.util;

/*This class stores metrics detail of a given video i.e likes,dislikes,view and ad length*/
public class Metrics{
    private int views;
    private int likes;
    private int dislikes;
    private int adLength;

    public Metrics(int views, int likes, int dislikes, int adLength)
    {
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.adLength = adLength;
    }

    public int getViews()
    {
        return views;
    }

    public int getLikes()
    {
        return likes;
    }

    public int getDislikes()
    {
        return dislikes;
    }

    public int getAdLength()
    {
        return adLength;
    }
    
    public String toString()
    {
        return views + " " + likes + " " + dislikes + " " + adLength;
    }
}