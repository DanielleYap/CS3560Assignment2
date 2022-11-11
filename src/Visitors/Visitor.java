package Visitors;

public interface Visitor {
    void visit (GroupTotalVisitor groupTotal);
    void visit (MessageTotalVisitor messageTotal);
    void visit (PositivePercentVisitor positivePercent);
    void visit (UserTotalVisitor userTotal);
}
