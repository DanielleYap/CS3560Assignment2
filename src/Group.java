import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* ******************************************************************************************************
 * COMPOSITE
 * Class that is able to hold users and other groups
 * ******************************************************************************************************/
public class Group implements SysEntry{

    private String groupID;
    private long creationTime;

    DateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
    Date date;

    public Group(String ID) {
        groupID = ID;
        creationTime = System.currentTimeMillis();

        date = new Date(creationTime);

        System.out.println("Group " + groupID + " created at: " + format.format(date));
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
