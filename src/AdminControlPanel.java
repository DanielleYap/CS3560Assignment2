import javax.swing.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

/* ******************************************************************************************************
 * Main controller of the program
 * ******************************************************************************************************/
public class AdminControlPanel extends JFrame {

    // SINGLETON - Static instantiation
    private static AdminControlPanel adminControl = null;

    private HashMap<String, User> users = new HashMap<>();
    private HashMap<String, Group> groups = new HashMap<>();
    private ArrayList<SysEntry> usersAndGroups = new ArrayList<>();
    private User newUser;
    private Group group = new Group("group");

    UserTotalVisitor userTotalVisitor;
    GroupTotalVisitor groupTotalVisitor;
    MessageTotalVisitor messageTotalVisitor;
    PositivePercentVisitor positivePercentVisitor;
    int userTotal;
    int groupTotal;
    int messageTotal;
    double positivePercent;

    private JPanel adminPanel;
    private JTree adminTreeView;
    private TreeModel adminTreeModel;
    private DefaultMutableTreeNode rootGroup,
                                   selection;
    private JTextField userTextField,
                       groupTextField;
    private JButton addUserButton,
                    addGroupButton,
                    openUserViewButton,
                    showUserTotalButton,
                    showGroupTotalButton,
                    showMessagesTotalButton,
                    showPositivePercentButton;

    /* ******************************************************************************************************
     * SINGLETON
     * Private constructor and static getter
     * ******************************************************************************************************/
    private AdminControlPanel() {
        generateAdminControlPanel();
    }
    public static AdminControlPanel getInstance() {
        if (adminControl == null) {
            synchronized (AdminControlPanel.class) {
                if (adminControl == null) {
                    adminControl = new AdminControlPanel();
                }
            }
        }
        return adminControl;
    }

    /* ******************************************************************************************************
     * User and group getters
     * ******************************************************************************************************/
    public HashMap<String, User> getUsers() {
        return users;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

    /* ******************************************************************************************************
     * Generates admin panel UI
     * ******************************************************************************************************/
    private void generateAdminControlPanel() {
        // Set initials of panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        setTitle("Mini Twitter: Admin Control");
        adminPanel = new JPanel();
        adminPanel.setLayout(null);
        setContentPane(adminPanel);

        // make tree
        rootGroup = new DefaultMutableTreeNode("Root", true);
        adminTreeView = new JTree(rootGroup);
        adminTreeView.setBounds(10, 9, 300, 542);
        adminTreeModel = adminTreeView.getModel();
        adminPanel.add(adminTreeView);

        // make text areas
        userTextField = new JTextField();
        userTextField.setBounds(320,10,200,25);
        adminPanel.add(userTextField);

        groupTextField = new JTextField();
        groupTextField.setBounds(320,45,200,25);
        adminPanel.add(groupTextField);

        // make buttons
        addUserButton = new JButton("Add User");
        addUserButton.setBounds(527,10,147,24);
        addUserButton.addActionListener(new AddUserButton());
        adminPanel.add(addUserButton);

        addGroupButton = new JButton("Add Group");
        addGroupButton.setBounds(527,45,147,24);
        addGroupButton.addActionListener(new AddGroupButton());
        adminPanel.add(addGroupButton);

        openUserViewButton = new JButton("Open User View");
        openUserViewButton.setBounds(320,85,355,40);
        openUserViewButton.addActionListener(new OpenUserViewButton());
        adminPanel.add(openUserViewButton);

        showUserTotalButton = new JButton("User Total");
        showUserTotalButton.setBounds(320,460,170,40);
        showUserTotalButton.addActionListener(new ShowUserTotalButton());
        adminPanel.add(showUserTotalButton);

        showGroupTotalButton = new JButton("Group Total");
        showGroupTotalButton.setBounds(500,460,170,40);
        showGroupTotalButton.addActionListener(new ShowGroupTotalButton());
        adminPanel.add(showGroupTotalButton);

        showMessagesTotalButton = new JButton("Messages Total");
        showMessagesTotalButton.setBounds(320,510,170,40);
        showMessagesTotalButton.addActionListener(new ShowMessagesTotalButton());
        adminPanel.add(showMessagesTotalButton);

        showPositivePercentButton = new JButton("Positive Percentage");
        showPositivePercentButton.setBounds(500,510,170,40);
        showPositivePercentButton.addActionListener(new ShowPositivePercentButton());
        adminPanel.add(showPositivePercentButton);
    }

    /* ******************************************************************************************************
     * Button functionalities
     * ******************************************************************************************************/
    private class AddUserButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userID = userTextField.getText();
            userTextField.setText("");
            selection = (DefaultMutableTreeNode)adminTreeView.getLastSelectedPathComponent();
            if (selection == null) {
                selection = rootGroup;
            }
            selection.add(new DefaultMutableTreeNode(userID, false));

            newUser = new User(userID);
            users.put(userID, newUser);
            usersAndGroups.add(newUser);

            ((DefaultTreeModel) adminTreeModel).reload();
        }
    }

    private class AddGroupButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String groupID = groupTextField.getText();
            groupTextField.setText("");
            selection = (DefaultMutableTreeNode)adminTreeView.getLastSelectedPathComponent();
            if (selection == null) {
                selection = rootGroup;
            }
            selection.add(new DefaultMutableTreeNode(groupID, true));

            Group newGroup = new Group(groupID);
            groups.put(groupID, newGroup);
            usersAndGroups.add(newGroup);

            ((DefaultTreeModel) adminTreeModel).reload();
        }
    }

    private class OpenUserViewButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String ID = adminTreeView.getLastSelectedPathComponent().toString();
            if (!(groups.containsKey(adminTreeView.getLastSelectedPathComponent().toString()))) {
                User user = users.get(ID);
                UserView view = new UserView(user);
                view.setVisible(true);
            }
        }
    }

    /* *****************************************************************************************************
     * VISITOR
     * Button functionalities for buttons using the visitor pattern
     * ******************************************************************************************************/
    private class ShowUserTotalButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userTotalVisitor = new UserTotalVisitor();
            userTotal = (int)(group.accept(userTotalVisitor));
            JOptionPane.showMessageDialog(null, "Total Users:\n" + userTotal);
        }
    }

    private class ShowGroupTotalButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            groupTotalVisitor = new GroupTotalVisitor();
            groupTotal = (int)(group.accept(groupTotalVisitor));
            JOptionPane.showMessageDialog(null, "Total Groups:\n" + groupTotal);
        }
    }

    private class ShowMessagesTotalButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageTotalVisitor = new MessageTotalVisitor();
            messageTotal = (int)(group.accept(messageTotalVisitor));
            JOptionPane.showMessageDialog(null, "Total Messages:\n" + messageTotal);
        }
    }

    private class ShowPositivePercentButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            positivePercentVisitor = new PositivePercentVisitor();
            positivePercent = group.accept(positivePercentVisitor);
            JOptionPane.showMessageDialog(null, "Positive Percent:\n" + positivePercent);

        }
    }

}
