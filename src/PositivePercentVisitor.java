import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* ******************************************************************************************************
 * VISITOR
 * Used to see what percentages of tweets have positive words
 * ******************************************************************************************************/
public class PositivePercentVisitor implements Visitor {

    private Group group = new Group("group");
    private MessageTotalVisitor messageTotalVisitor = new MessageTotalVisitor();
    int messageTotal = (int)(group.accept(messageTotalVisitor));

    double positivePercent;

    ArrayList<String> positiveWords = new ArrayList<>(Arrays.asList("good", "great", "amazing",
                                                                    "fantastic", "ffxiv", "awesome"));

    @Override
    public double visit(Group group) {
        HashMap<String, User> userList = AdminControlPanel.getInstance().getUsers();

        for (User user : userList.values()) {
            for (String tweet : user.getTweets()) {
                for (String word : positiveWords) {
                    if (tweet.contains(word)) {
                        positivePercent++;
                        break;
                    }
                }

            }
        }

        positivePercent = (positivePercent/messageTotal)*100;

        return positivePercent;
    }


}
