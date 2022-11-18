/* ******************************************************************************************************
 * VISITOR
 * Used to count the number of total groups (including root)
 * ******************************************************************************************************/
public class GroupTotalVisitor implements Visitor {
    @Override
    public double visit(Group group) {
        double groupTotal = 1;

        // Counts all created groups
        for (int i=0; i<AdminControlPanel.getInstance().getGroups().size(); i++) {
            groupTotal++;
        }

        return groupTotal;
    }
}
