import java.util.HashMap;

/* ******************************************************************************************************
 * VISITOR
 * Used to count the total number of tweets/messages
 * ******************************************************************************************************/
public class MessageTotalVisitor implements Visitor {
    @Override
    public double visit(Group group) {
        HashMap<String, User> userList = AdminControlPanel.getInstance().getUsers();
        double messageTotal = 0;

        // Counts sent messages
        for (User user : userList.values()) {
            messageTotal += user.getTweets().size();
        }

        return messageTotal;
    }

    @Override
    public double visit(User user) {
        return 0;
    }
}
