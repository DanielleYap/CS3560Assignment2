/* ******************************************************************************************************
 * VISITOR
 * Used to count the total number of tweets/messages
 * ******************************************************************************************************/

public class LastUpdatedVisitor implements Visitor{
    private long mostRecent = 0;
    private String mostRecentID;

    @Override
    public double visit(Group group) {
        return 0;
    }

    @Override
    public double visit(User user) {
        if (user.getLastUpdateTime() > mostRecent) {
            mostRecent = user.getLastUpdateTime();
            return 1;
        }
        return 0;
    }
}
