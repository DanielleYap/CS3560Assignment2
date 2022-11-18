import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    public User(String ID) {
        userID = ID;
        userFollowers = new HashMap<>();
        userFollowing = new HashMap<>();
        newsFeedListModel = new DefaultListModel();
        newsFeedList = new JList<>(newsFeedListModel);
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
        return 0;
    }
}
