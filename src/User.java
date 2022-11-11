import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class User extends Subject implements Observer, SysEntry{

    private String userID;

    private HashMap<String, User> userFollowers;
    private HashMap<String, User> userFollowing;
    private ArrayList<String> userFeed;
    private JList newsFeedList;
    private DefaultListModel newsFeedListModel;
    private HashMap<String, User> users = AdminControlPanel.getInstance().getUsers();
    private String recentMessage;

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

    public DefaultListModel getNewsFeedListModel() {
        return newsFeedListModel;
    }

    public JList getNewsFeedList() {
        return newsFeedList;
    }

    public HashMap<String, User> getUserFollowers() {
        return userFollowers;
    }

    public HashMap<String, User> getUserFollowing() {
        return userFollowing;
    }

    public String getRecentMessage() {
        return recentMessage;
    }

    @Override
    public void update(String tweet) {
        newsFeedListModel.addElement(tweet);
        newsFeedList.setModel(newsFeedListModel);
        System.out.println(tweet);
        System.out.println("UPDATE CALLED");
    }
}
