package Visitors;

public class PositivePercentVisitor implements Visitable{

    private double postitivePercent = 0;
    int posCount=0;
    private MessageTotalVisitor totalMessages = new MessageTotalVisitor();

    public double getPositivePercent(double total) {
        //double totalMes = totalMessages.getMessageTotal();
        System.out.println("total " + total);
        double totalPos = posCount;
        System.out.println("positive "+ totalPos);
        return postitivePercent = (totalPos/total)*100.0;
    }

    @Override
    public void accept(Visitor visitor) {
        posCount++;
        visitor.visit(this);
    }

    public void getPostitivePercent() {
    }
}
