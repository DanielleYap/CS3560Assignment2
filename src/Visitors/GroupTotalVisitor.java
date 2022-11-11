package Visitors;

public class GroupTotalVisitor implements Visitable{

    private int groupTotal = 0;

    public int getGroupTotal() {
        return groupTotal;
    }

    @Override
    public void accept(Visitor visitor) {
        groupTotal+=1;
        visitor.visit(this);
    }
}
