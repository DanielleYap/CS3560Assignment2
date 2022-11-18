/* ******************************************************************************************************
 * COMPOSITE
 * Class that is able to hold users and other groups
 * ******************************************************************************************************/
public class Group implements SysEntry{

    private String groupID;

    public Group(String ID) {
        groupID = ID;
    }

    @Override
    public String getID() {
        return groupID;
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
