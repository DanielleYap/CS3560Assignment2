import Visitors.ButtonVisitor;
import Visitors.MessageTotalVisitor;
import Visitors.PositivePercentVisitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    // GUI elements
    private User currentUser;
    private JPanel userPanel;
    private JTextField userTextField,
                       tweetTextField;
    private JList currentFollowingList,
                  newsFeedList;
    private JButton followUserButton,
                    postTweetButton;
    private ListModel<String> currentFollowingListModel,
                              newsFeedListModel1;
    private AdminControlPanel admin = AdminControlPanel.getInstance();
    private MessageTotalVisitor totalMessages = new MessageTotalVisitor();
    private int tm;

    public UserView(User user) {
        currentUser = user;
        generateUserViewPanel(user);
        for (int i=0; i<user.getNewsFeedList().getModel().getSize(); i++) {

        }
    }

    private void generateUserViewPanel(User user) {
        // Set initials of panel
        setBounds(900, 100, 600, 500);
        setTitle("User View: " + user.getID());
        userPanel = new JPanel();
        userPanel.setLayout(null);
        setContentPane(userPanel);

        // make text fields
        userTextField = new JTextField();
        userTextField.setBounds(10,10,405,25);
        userPanel.add(userTextField);

        tweetTextField = new JTextField();
        tweetTextField.setBounds(10, 200, 405, 25);
        userPanel.add(tweetTextField);

        // make lists
        currentFollowingListModel = new DefaultListModel<>();
        currentFollowingList = new JList<>(currentFollowingListModel);
        currentFollowingList.setBounds(10,40,565,150);
        userPanel.add(currentFollowingList);


        //newsFeedListModel1 = new DefaultListModel<>();
        newsFeedList = new JList<>(currentUser.getNewsFeedListModel());
        newsFeedList.setBounds(10,235,565,215);
        userPanel.add(newsFeedList);

        // make buttons
        followUserButton = new JButton("Follow User");
        followUserButton.setBounds(420, 10, 155, 25);
        followUserButton.addActionListener(new FollowUserButton());
        userPanel.add(followUserButton);

        postTweetButton = new JButton("Post Tweet");
        postTweetButton.setBounds(420, 200, 155, 25);
        postTweetButton.addActionListener(new PostTweetButton());
        userPanel.add(postTweetButton);

    }

    private class FollowUserButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String followingID = userTextField.getText();
            User followingUser = admin.getUsers().get(followingID);

            if (admin.getUsers().containsKey(followingID)){
                followingUser.attach(currentUser);
                ((DefaultListModel)currentFollowingListModel).addElement(followingID);
            }

            userTextField.setText("");
        }
    }

    private class PostTweetButton extends Subject implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String recentMessage = ""+ currentUser.getID() + ": " + tweetTextField.getText();

            currentUser.notifyUsers(recentMessage);
            System.out.println("TWEET BUTTON");
            currentUser.getNewsFeedListModel().addElement(recentMessage);
            tweetTextField.setText("");

            totalMessages = new MessageTotalVisitor();
            totalMessages.accept(new ButtonVisitor());
            tm++;


            PositivePercentVisitor positivePercent = new PositivePercentVisitor();
            String[] positiveWords = {"yes", "good", "great", "amazing"};
            for (String word : positiveWords) {
                if (recentMessage.contains(word)) {
                    positivePercent.accept(new ButtonVisitor());
                }
            }

            admin.setTotalMessages(tm);
        }
    }
}
