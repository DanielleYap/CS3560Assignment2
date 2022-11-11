import java.util.HashMap;

public class Group implements SysEntry{

    private String groupID;

    public Group(String ID) {
        groupID = ID;
    }

    @Override
    public String getID() {
        return groupID;
    }

}
