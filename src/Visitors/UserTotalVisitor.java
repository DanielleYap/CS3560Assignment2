package Visitors;

public class UserTotalVisitor implements Visitable{

    private int userTotal = 0;

    public int getUserTotal() {
        return userTotal;
    }


    @Override
    public void accept(Visitor visitor) {
        userTotal+=1;
        visitor.visit(this);
    }

}
