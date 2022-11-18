import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* ******************************************************************************************************
 * OBSERVER
 * Subject being observed
 * ******************************************************************************************************/
public abstract class Subject {
    private List<User> observerList = new ArrayList<>();
    private HashMap<String, User> users = AdminControlPanel.getInstance().getUsers();

    public void attach (User user) {
        observerList.add(user);
    }

    public void notifyUsers(String tweet) {
        int totalFollowers = observerList.size();
        for (int i=0; i<totalFollowers; i++) {
            observerList.get(i).update(tweet);
        }
    }
}
