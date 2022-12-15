import java.util.ArrayList;
import java.util.HashSet;

/* ******************************************************************************************************
 * VISITOR
 * Used to count the total number of tweets/messages
 * ******************************************************************************************************/
public class VerifyIDVisitor implements Visitor{
    HashSet<String> IDs = new HashSet<>();

    @Override
    public double visit(Group group) {
        if (group.getID().contains(" ") || IDs.contains(group.getID())) {
            IDs.add(group.getID());
            return 0;
        }
        else {
            IDs.add(group.getID());
            return 1;
        }
    }


    @Override
    public double visit(User user) {
        if (user.getID().contains(" ") || IDs.contains(user.getID())) {
            IDs.add(user.getID());
            return 0;
        }
        else {
            IDs.add(user.getID());
            return 1;
        }
    }
}
