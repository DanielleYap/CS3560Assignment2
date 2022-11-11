package Visitors;

public class MessageTotalVisitor implements Visitable{

    private int messageTotal;

    public int getMessageTotal() {
        return messageTotal;
    }

    @Override
    public void accept(Visitor visitor) {
        messageTotal+=1;
        visitor.visit(this);
    }
}
