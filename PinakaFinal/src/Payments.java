import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.MatteBorder;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;





public class Payments extends JFrame {

	private JPanel contentPane;
	private JTextField lblsid;
	private JTextField lblfname;
	private JTextField lbllname;
	private JTextField lblroom;
	private JLabel lblNewLabel_1;
	private JTextField lblamount;
	private JLabel lblNewLabel_2;
	private JTextField lblbalance;
	private JDateChooser dateChooser;
	private JLabel lblNewLabel_4;
	private JTextField lblrid;
	private JLabel lblNewLabel_5;
	private JLabel lbltotal;
	private JLabel totalsum = new JLabel("0.0");
	private static final String FILENAME = "paymentdata.txt";

	/**
	 * Launch the application.
	 */
	
	Connection con1;
	PreparedStatement insert;
	ResultSet rs;
	DefaultTableModel Df;
	private JLabel lblPaymentHistory;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_7;
	private JTextField txtsearch;
	private JButton btnNewButton_1;
	

	

	

	private void table_update() {
        int c;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory", "root", "");
            insert = con1.prepareStatement("select * from paymentdata");
            ResultSet rs = insert.executeQuery();
            ResultSetMetaData Rss = rs.getMetaData();
            c = Rss.getColumnCount();
            DefaultTableModel Df = (DefaultTableModel) table.getModel();
            Df.setRowCount(0);
            
            List<Vector<String>> rows = new ArrayList<>();

            while (rs.next()) {
                Vector<String> v2 = new Vector<String>();

                for (int i = 1; i <= c; i++) {
                	 v2.add(rs.getString(i));
                   
                }
                rows.add(0, v2);

            }
            for (Vector<String> row : rows) {
                Df.addRow(row);
                
            }
            
            
            
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	 public void searchData(String str) {
		 
		Df = (DefaultTableModel)table.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(Df);
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter("(?i)"+str));	 
	 }
	
	

	 public void display() {
	      
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	contentPane.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	
	
	 private class UpperCaseCellRenderer extends DefaultTableCellRenderer {
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        
		       
		        if (value instanceof String) {
		            String text = (String) value;
		            setText(text.toUpperCase());
		        }
		        
		        return cellComponent;
		    }
		}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payments frame = new Payments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Payments() {
		
		
		
		
		
		loadTotalSum();
	    
		setTitle("PAYMENT");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Payments.class.getResource("/img/icons8-dollar-48.png")));
		setResizable(false);
		setBounds(100, 100, 1011, 594);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(0, 0, 997, 220);
		contentPane.add(panel);
		panel.setLayout(null);
		

		
		
		JLabel lblNewLabel = new JLabel("PAYMENT");
		lblNewLabel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 204, 0)));
		lblNewLabel.setFont(new Font("Agency FB", Font.BOLD, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(383, 4, 215, 37);
		panel.add(lblNewLabel);
		
		lblsid = new JTextField();
		lblsid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
						String sid = lblsid.getText();
					
					try{
							Class.forName("com.mysql.cj.jdbc.Driver");
							con1 = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory", "root", "");
							insert = con1.prepareStatement("select * from studentdata where Student_ID = ?");
							insert.setString(1, sid);
							rs = insert.executeQuery();
							
							if(rs.next() == false) {
								JOptionPane.showMessageDialog(lblNewLabel, "Student ID not Found!");
								
							}
							else {
								
								String fname = rs.getString("Fname");
								String Lname = rs.getString("Lname");
								String Room = rs.getString("Room_Number");
								
								lblfname.setText(fname);
								lbllname.setText(Lname);
								lblroom.setText(Room);
								
							}
							
							
							
							
							
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		         
					
					
				}
			}
		});
		lblsid.setHorizontalAlignment(SwingConstants.CENTER);
		lblsid.setFont(new Font("Agency FB", Font.BOLD, 15));
		lblsid.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Student ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblsid.setBounds(10, 43, 205, 48);
		panel.add(lblsid);
		lblsid.setColumns(10);
		
		lblfname = new JTextField();
		lblfname.setHorizontalAlignment(SwingConstants.CENTER);
		lblfname.setFont(new Font("Agency FB", Font.BOLD, 15));
		lblfname.setColumns(10);
		lblfname.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "First Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblfname.setBounds(274, 43, 205, 48);
		panel.add(lblfname);
		
		lbllname = new JTextField();
		lbllname.setHorizontalAlignment(SwingConstants.CENTER);
		lbllname.setFont(new Font("Agency FB", Font.BOLD, 15));
		lbllname.setColumns(10);
		lbllname.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Last Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lbllname.setBounds(528, 43, 205, 48);
		panel.add(lbllname);
		
		lblroom = new JTextField();
		lblroom.setHorizontalAlignment(SwingConstants.CENTER);
		lblroom.setFont(new Font("Agency FB", Font.BOLD, 15));
		lblroom.setColumns(10);
		lblroom.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Room", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblroom.setBounds(782, 43, 205, 48);
		panel.add(lblroom);
		
		lblNewLabel_1 = new JLabel("Amount:");
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 110, 59, 37);
		panel.add(lblNewLabel_1);
		
		lblamount = new JTextField();
		lblamount.setHorizontalAlignment(SwingConstants.CENTER);
		lblamount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblamount.setBounds(69, 110, 101, 37);
		panel.add(lblamount);
		lblamount.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Balance:");
		lblNewLabel_2.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblNewLabel_2.setBounds(180, 110, 59, 37);
		panel.add(lblNewLabel_2);
		
		lblbalance = new JTextField();
		lblbalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblbalance.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblbalance.setColumns(10);
		lblbalance.setBounds(239, 110, 101, 37);
		panel.add(lblbalance);
		
		lblNewLabel_4 = new JLabel("Receipt No:");
		lblNewLabel_4.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblNewLabel_4.setBounds(526, 110, 72, 37);
		panel.add(lblNewLabel_4);
		
		lblrid = new JTextField();
		lblrid.setHorizontalAlignment(SwingConstants.CENTER);
		lblrid.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblrid.setColumns(10);
		lblrid.setBounds(593, 110, 132, 37);
		panel.add(lblrid);
		
		lblNewLabel_5 = new JLabel("Payment Status:");
		lblNewLabel_5.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblNewLabel_5.setBounds(735, 109, 106, 37);
		panel.add(lblNewLabel_5);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(844, 111, 143, 37);
		panel.add(comboBox);

		comboBox.addItem("PAID");
		comboBox.addItem("PARTIAL");
		comboBox.addItem("ADVANCED");
		
		JButton btnNewButton = new JButton("SAVE");
		
		
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				
			    String Sid = lblsid.getText();
			    String name = lblfname.getText();
			    String lname = lbllname.getText();
			    String room = lblroom.getText();
			    String amount = lblamount.getText();
			    String balance = lblbalance.getText();
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String date = sdf.format(dateChooser.getDate());
			    String rid = lblrid.getText();
//			    String ps = lblpaymentstatus.getText();
			    String paymentStatus = (String) comboBox.getSelectedItem();
				
				
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con1 = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory","root","");
		            insert = con1.prepareStatement("INSERT INTO paymentdata(Student_ID, Fname, Lname, Room, Amount, Balance, DateOfPayment, Receipt_ID, Payment_Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		            insert.setString(1, Sid);
		            insert.setString(2, name);
		            insert.setString(3, lname);
		            insert.setString(4, room);
		            insert.setString(5, amount);
		            insert.setString(6, balance);
		            insert.setString(7, date);
		            insert.setString(8, rid);
		            insert.setString(9, paymentStatus); 
		    
		            insert.executeUpdate();
		            
		            JOptionPane.showMessageDialog(btnNewButton,"Record Added!");

		            table_update();
		            
		            // Clear the fields after adding the record
		            lblsid.setText("");
		            lblfname.setText("");
		            lbllname.setText("");
		            lblroom.setText("");
		            lblamount.setText("");
		            lblbalance.setText("");
		            lblrid.setText("");
		            lblsid.requestFocus();  
		            
		            double total = calculateTotalFromTable();
		            saveTotalSum(total);
		            updateTotalSumLabel(total);
		    
		        } catch (ClassNotFoundException e1) {
		            e1.printStackTrace();    
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
        totalsum.setFont(new Font("Arial Black", Font.PLAIN, 22));
        totalsum.setBounds(192, 229, 138, 37);
        contentPane.add(totalsum);
		
		
		
		btnNewButton.setBackground(new Color(153, 204, 51));
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 24));
		btnNewButton.setBounds(425, 167, 118, 43);
		btnNewButton.setFocusable(false);
		panel.add(btnNewButton);

		 dateChooser = new JDateChooser();
	     dateChooser.setBounds(403, 110, 113, 37);
	     panel.add(dateChooser);
		
		JLabel lblNewLabel_2_1 = new JLabel("Date:");
		lblNewLabel_2_1.setFont(new Font("Agency FB", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(365, 110, 42, 37);
		panel.add(lblNewLabel_2_1);
		
	
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 264, 977, 283);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(153, 204, 204));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STUDENT ID", "FIRST NAME", "LAST NAME", "ROOM NUMBER", "AMOUNT", "BALANCE", "DATE", "RECEIPT ID", "PAYMENT STATUS"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
		
		
		lblPaymentHistory = new JLabel("PAYMENT HISTORY");
		lblPaymentHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentHistory.setFont(new Font("Agency FB", Font.BOLD, 32));
		lblPaymentHistory.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 51, 0)));
		lblPaymentHistory.setBounds(378, 225, 215, 37);
		contentPane.add(lblPaymentHistory);
		
		JLabel lblNewLabel_6 = new JLabel("TOTAL INCOME:");
		lblNewLabel_6.setBounds(0, 229, 182, 37);
		contentPane.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		lblNewLabel_7 = new JLabel("Search:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_7.setBounds(726, 230, 72, 28);
		contentPane.add(lblNewLabel_7);
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String searchString = txtsearch.getText();
				searchData(searchString);
			}
		});
		txtsearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtsearch.setFont(new Font("Agency FB", Font.BOLD, 19));
		txtsearch.setBounds(797, 230, 190, 28);
		contentPane.add(txtsearch);
		txtsearch.setColumns(10);
		

		 table_update();
		 
		 for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		        table.getColumnModel().getColumn(i).setCellRenderer(new UpperCaseCellRenderer());
		    }
	
	}
	@SuppressWarnings("unused")
	 private void updateTotalSumLabel(double total) {
        totalsum.setText(String.valueOf(total)); // Update the label with the total amount
    }
	
    private double calculateTotalFromTable() {
        double total = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            double amount1 = Double.parseDouble((String) table.getValueAt(i, 4));
            total += amount1;
        }
        return total;
    }

    private void saveTotalSum(double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            writer.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTotalSum() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line = reader.readLine();
            if (line != null) {
                double total = Double.parseDouble(line);
                totalsum.setText(String.valueOf(total));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
