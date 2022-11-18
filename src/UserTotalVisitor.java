/* ******************************************************************************************************
 * VISITOR
 * Used to count the total number of created users
 * ******************************************************************************************************/
public class UserTotalVisitor implements Visitor {

    @Override
    public double visit(Group group) {
        double userTotal = 0;

        for (int i=0; i<AdminControlPanel.getInstance().getUsers().size(); i++) {
            userTotal++;
        }

        return userTotal;
    }


}
