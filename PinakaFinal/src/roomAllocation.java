import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Window.Type;
import java.awt.Toolkit;





public class roomAllocation implements DataRetriever {

    public JFrame frmRoomOccupants;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    roomAllocation window = new roomAllocation();
                    window.frmRoomOccupants.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public roomAllocation() {
        initialize();
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	
    	   frmRoomOccupants = new JFrame();
    	   frmRoomOccupants.setResizable(false);
    	   frmRoomOccupants.setIconImage(Toolkit.getDefaultToolkit().getImage(roomAllocation.class.getResource("/img/icons8-rooms-64.png")));
    	   frmRoomOccupants.setTitle("ROOM OCCUPANTS");
    	   frmRoomOccupants.getContentPane().setBackground(new Color(102, 153, 153));
           frmRoomOccupants.setBounds(100, 100, 1111, 578);
           frmRoomOccupants.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frmRoomOccupants.setLocationRelativeTo(null);
           frmRoomOccupants.getContentPane().setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(119, 136, 153));
        panel_1.setBounds(0, 0, 1236, 52);
        frmRoomOccupants.getContentPane().add(panel_1);

        JLabel lblRoomChecking = new JLabel("***| ROOM OCCUPANTS |***");
        lblRoomChecking.setHorizontalAlignment(SwingConstants.CENTER);
        lblRoomChecking.setForeground(Color.WHITE);
        lblRoomChecking.setFont(new Font("Agency FB", Font.BOLD, 26));
        lblRoomChecking.setBounds(382, 0, 278, 51);
        panel_1.add(lblRoomChecking);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setFont(new Font("Tahoma", Font.BOLD, 14));
        scrollPane.setBackground(new Color(0, 206, 209));
        scrollPane.setBounds(10, 130, 1077, 401);
        frmRoomOccupants.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"StudentID", "First_Name", "Last_Name", "Gender", "Room_Number"}
        ) {
            boolean[] columnEditables = new boolean[]{false, false, false, true, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Agency FB", Font.BOLD, 24));
        comboBox.setBounds(421, 76, 66, 44);
        frmRoomOccupants.getContentPane().add(comboBox);

        for (int i = 1; i <= 10; i++) {
            comboBox.addItem(Integer.toString(i));
        }

        JLabel lblSelectRoom = new JLabel("| SELECT ROOM |");
        lblSelectRoom.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectRoom.setForeground(new Color(255, 255, 0));
        lblSelectRoom.setFont(new Font("Agency FB", Font.BOLD, 26));
        lblSelectRoom.setBounds(219, 69, 233, 51);
        frmRoomOccupants.getContentPane().add(lblSelectRoom);

        JLabel lblSelectGender = new JLabel("| SELECT GENDER |");
        lblSelectGender.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectGender.setForeground(new Color(255, 255, 0));
        lblSelectGender.setFont(new Font("Agency FB", Font.BOLD, 26));
        lblSelectGender.setBounds(497, 69, 233, 51);
        frmRoomOccupants.getContentPane().add(lblSelectGender);

        JComboBox<String> CGender = new JComboBox<>();
        CGender.setFont(new Font("Agency FB", Font.BOLD, 24));
        CGender.setBounds(705, 76, 82, 44);
        frmRoomOccupants.getContentPane().add(CGender);

        CGender.addItem("MALE");
        CGender.addItem("FEMALE");

        // Add action listeners to combo boxes
        comboBox.addActionListener(e -> {
            String selectedRoom = comboBox.getSelectedItem().toString();
            String selectedGender = CGender.getSelectedItem().toString();
            retrieveData(selectedRoom, selectedGender);
        });

        CGender.addActionListener(e -> {
            String selectedRoom = comboBox.getSelectedItem().toString();
            String selectedGender = CGender.getSelectedItem().toString();
            retrieveData(selectedRoom, selectedGender);
        });

    }

    public void retrieveData(String roomNumber, String gender) {
        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory", "root", "");

            // Construct the SELECT query
            String query = "SELECT Student_ID, Fname, Lname, Gender, Room_Number FROM studentdata WHERE Room_Number = ? AND Gender = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roomNumber);
            statement.setString(2, gender);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Populate the table with the retrieved data
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            while (resultSet.next()) {
                String studentID = resultSet.getString("Student_ID");
                String firstName = resultSet.getString("Fname");
                String lastName = resultSet.getString("Lname");
                String studentGender = resultSet.getString("Gender");
                String roomNum = resultSet.getString("Room_Number");

                // Add the retrieved data to the table
                model.addRow(new Object[]{studentID, firstName, lastName, studentGender, roomNum});
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
