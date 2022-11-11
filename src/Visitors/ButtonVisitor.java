package Visitors;

public class ButtonVisitor implements Visitor{

    @Override
    public void visit(GroupTotalVisitor groupTotal) {
        groupTotal.getGroupTotal();
    }

    @Override
    public void visit(MessageTotalVisitor messageTotal) {
        messageTotal.getMessageTotal();
    }

    @Override
    public void visit(PositivePercentVisitor positivePercent) {
        positivePercent.getPostitivePercent();
    }

    @Override
    public void visit(UserTotalVisitor userTotal) {
        userTotal.getUserTotal();
    }
}
