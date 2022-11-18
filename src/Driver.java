/* ******************************************************************************************************
 * Opens the admin control panel upon running
 * ******************************************************************************************************/
public class Driver {
    public static void main(String[] args) {
        AdminControlPanel.getInstance().setVisible(true);
        System.out.println("Admin Panel Opened Successfully");
    }
}