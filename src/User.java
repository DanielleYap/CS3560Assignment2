import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/* ******************************************************************************************************
 * COMPOSITE
 * The user
 * ******************************************************************************************************/
public class User extends Subject implements Observer, SysEntry{

    private String userID;
    private HashMap<String, User> userFollowers;
    private HashMap<String, User> userFollowing;
    private JList newsFeedList;
    private DefaultListModel newsFeedListModel;
    private HashMap<String, User> users = AdminControlPanel.getInstance().getUsers();
    private ArrayList<String> tweets = new ArrayList<>();
    private long creationTime;
    private long lastUpdateTime;
    DateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
    Date date;

    public User(String ID) {
        userID = ID;
        userFollowers = new HashMap<>();
        userFollowing = new HashMap<>();
        newsFeedListModel = new DefaultListModel();
        newsFeedList = new JList<>(newsFeedListModel);
        creationTime = System.currentTimeMillis();

        date = new Date(creationTime);

        System.out.println("User " + userID + " created at: " + format.format(date));

        lastUpdateTime = 0;
    }

    @Override
    public String getID() {
        return userID;
    }

    public ArrayList<String> getTweets() {
        return tweets;
    }

    public DefaultListModel getNewsFeedListModel() {
        return newsFeedListModel;
    }

    public JList getNewsFeedList() {
        return newsFeedList;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void addTweet(String tweet) {
        tweets.add(tweet);
    }

    @Override
    public void update(String tweet) {
        newsFeedListModel.addElement(tweet);
        newsFeedList.setModel(newsFeedListModel);
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
