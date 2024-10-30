import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.Icon;


public class DataEntry {

	private JFrame anotherWindow;
	private JTextField StudentID;
	private JTextField Fname;
	private JTextField Lname;
	private JTextField Address;
	private JTextField ContactN;
	private JTextField Program;
	private JTable table;
	private JTextField Room;
	private Payments paymentFrame;
	

	DefaultTableModel Df;
	Connection con1;
	PreparedStatement insert;
	private JTextField Gender;
	private JTextField paymentstatus;
	private JTextField lblbalance;
	private JTextField txtsearch;
	
	private void table_update() {
        int c;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory", "root", "");
            insert = con1.prepareStatement("select * from studentdata");
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
		trs.setRowFilter(RowFilter.regexFilter("(?i)" +str));	 
	 }
	
	
	 public void display() {
	      
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    anotherWindow.setVisible(true);
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
					DataEntry window = new DataEntry();
					 window.display();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DataEntry() {
			initialize();
			table_update();
			paymentFrame = new Payments();
			
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		anotherWindow = new JFrame();
		anotherWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(DataEntry.class.getResource("/img/jrmsu-removebg-preview (1).png")));
		
		anotherWindow.setTitle("JRMSU-KC DORMITORY");
		anotherWindow.setBounds(100, 100, 1260, 693);
		anotherWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		anotherWindow.getContentPane().setLayout(null);
		anotherWindow.setResizable(false);
		anotherWindow.setLocationRelativeTo(null);
	        
	        
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(85, 107, 47));
		panel.setBounds(0, 0, 89, 683);
		anotherWindow.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel dentry = new JLabel("");
		dentry.setIcon(new ImageIcon(DataEntry.class.getResource("/img/profile.png")));
		dentry.setBounds(10, 73, 69, 53);
		panel.add(dentry);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Logout", "Confirmation", JOptionPane.WARNING_MESSAGE);
				
				if(confirmation == 0) {
					
					
					Logins login = new Logins();
					login.display();
					anotherWindow.dispose();
							
				}

			}
		});
		btnNewButton.setIcon(new ImageIcon(DataEntry.class.getResource("/img/icons8-logout-64.png")));
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 18));
		btnNewButton.setBounds(10, 392, 69, 60);
		panel.add(btnNewButton);
		
		JButton Payment = new JButton(new ImageIcon(DataEntry.class.getResource("/img/icons8-payment-48.png")));
		Payment.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        paymentFrame.setVisible(true);
		        paymentFrame.setLocationRelativeTo(null);
		        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    }
		});
		Payment.setBounds(10, 179, 69, 70);
		Payment.setFocusable(false);
		Payment.setContentAreaFilled(false); // Makes the button's background transparent
		Payment.setBorderPainted(false); // Removes the border around the button
		Payment.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Changes the cursor to a hand when hovered over the button

		// Adding mouse hover effects
		Payment.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // Optional: You can change the icon to a slightly different one on hover to give feedback
		        // Payment.setIcon(new ImageIcon(DataEntry.class.getResource("/img/icons8-payment-hover-48.png")));
		        Payment.setBorderPainted(true); // Show border on hover
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        // Payment.setIcon(new ImageIcon(DataEntry.class.getResource("/img/icons8-payment-48.png"))); // Change back to the original icon
		        Payment.setBorderPainted(false); // Hide border when not hovered over
		    }
		});

		panel.add(Payment);
		
		// Create and configure the roomallo button
		JButton roomallo = new JButton(new ImageIcon(DataEntry.class.getResource("/img/icons8-rooms-64.png")));
		roomallo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomAllocation roomAllo = new roomAllocation();
		        roomAllo.frmRoomOccupants.setVisible(true);
		        roomAllo.frmRoomOccupants.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		roomallo.setFocusable(false);
		roomallo.setContentAreaFilled(false); // Makes the button's background transparent
		roomallo.setBorderPainted(false); // Removes the border around the button
		roomallo.setBounds(10, 280, 69, 60);

		// Adding mouse hover effects
		roomallo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // Optional: You can change the icon to a slightly different one on hover to give feedback
		        // roomallo.setIcon(new ImageIcon(DataEntry.class.getResource("/img/icons8-rooms-hover-64.png")));
		        roomallo.setBorderPainted(true); // Show border on hover
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        // roomallo.setIcon(new ImageIcon(DataEntry.class.getResource("/img/icons8-rooms-64.png"))); // Change back to the original icon
		        roomallo.setBorderPainted(false); // Hide border when not hovered over
		    }
		});

		// Add the roomallo button to the panel
		panel.add(roomallo);

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(119, 136, 153));
		panel_1.setBounds(88, 0, 1158, 40);
		anotherWindow.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("| STUDENT REGISTRATION |");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Agency FB", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 0, 278, 40);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Search StudentID");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblNewLabel_2.setBounds(837, 7, 110, 30);
		panel_1.add(lblNewLabel_2);
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String searchString = txtsearch.getText();
				searchData(searchString);
				
			}
		});
		txtsearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtsearch.setFont(new Font("Agency FB", Font.BOLD, 21));
		txtsearch.setBounds(944, 4, 204, 30);
		panel_1.add(txtsearch);
		txtsearch.setColumns(10);
		
		JPanel DataEntry = new JPanel();
		DataEntry.setBackground(new Color(192, 192, 192));
		DataEntry.setBorder(new EmptyBorder(2, 2, 2, 2));
		DataEntry.setBounds(88, 39, 298, 617);
		
		anotherWindow.getContentPane().add(DataEntry);
		DataEntry.setLayout(null);
		
		StudentID = new JTextField();
		StudentID.setBounds(11, 4, 277, 50);
		StudentID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(192, 192, 192)), "STUDENT ID", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14), Color.GRAY));
		StudentID.setHorizontalAlignment(SwingConstants.CENTER);
		StudentID.setFont(new Font("SansSerif", Font.PLAIN, 16));
		DataEntry.add(StudentID);
		StudentID.setColumns(10);
		StudentID.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        StudentID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(100, 149, 237)), "STUDENT ID", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14), new Color(100, 149, 237)));
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        StudentID.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(192, 192, 192)), "STUDENT ID", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14), Color.GRAY));
		    }
		});
		StudentID.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(192, 192, 192)), "STUDENT ID", TitledBorder.LEADING, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 14), Color.GRAY),
			    BorderFactory.createEmptyBorder(5, 5, 5, 5)));


		
		Fname = new JTextField();
		Fname.setBounds(11, 57, 277, 50);
		Fname.setBorder(new TitledBorder(null, "FIRST NAME", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Fname.setHorizontalAlignment(SwingConstants.CENTER);
		Fname.setFont(new Font("Agency FB", Font.BOLD, 18));
		Fname.setColumns(10);
		DataEntry.add(Fname);
		
		Lname = new JTextField();
		Lname.setBounds(11, 112, 277, 55);
		Lname.setBorder(new TitledBorder(null, "LAST NAME", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Lname.setHorizontalAlignment(SwingConstants.CENTER);
		Lname.setFont(new Font("Agency FB", Font.BOLD, 18));
		Lname.setColumns(10);
		DataEntry.add(Lname);
		
		Address = new JTextField();
		Address.setBounds(11, 171, 277, 50);
		Address.setBorder(new TitledBorder(null, "ADDRESS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Address.setHorizontalAlignment(SwingConstants.CENTER);
		Address.setFont(new Font("Agency FB", Font.BOLD, 18));
		Address.setColumns(10);
		DataEntry.add(Address);
		
		ContactN = new JTextField();
		ContactN.setBounds(11, 225, 277, 50);
		ContactN.setBorder(new TitledBorder(null, "PHONE NUMBER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ContactN.setHorizontalAlignment(SwingConstants.CENTER);
		ContactN.setFont(new Font("Agency FB", Font.BOLD, 18));
		ContactN.setColumns(10);
		DataEntry.add(ContactN);
		
		Program = new JTextField();
		Program.setBounds(11, 279, 277, 50);
		Program.setBorder(new TitledBorder(null, "PROGRAM & YR.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Program.setHorizontalAlignment(SwingConstants.CENTER);
		Program.setFont(new Font("Agency FB", Font.BOLD, 18));
		Program.setColumns(10);
		DataEntry.add(Program);
		
		JButton Save = new JButton("ADD");
		Save.setBounds(11, 556, 85, 36);

		// Setting up the button appearance
			Save.setBackground(new Color(51, 204, 51)); 
			Save.setForeground(Color.WHITE); 
			Save.setFont(new Font("SansSerif", Font.BOLD, 22));
			Save.setFocusable(false); 
			Save.setBorder(new RoundedBorder(10));

		// Hover effect
			Save.addMouseListener(new MouseAdapter() {
		   	 @Override
		  	  public void mouseEntered(MouseEvent e) {
		    	    Save.setBackground(new Color(0, 153, 0));
		  	  }

		  	  @Override
		   	 public void mouseExited(MouseEvent e) {
		      	  Save.setBackground(new Color(51, 204, 51)); 
		 	   }
			});
				
			
				
				Save.addActionListener(new ActionListener() {
					

					
					
					public void actionPerformed(ActionEvent e) {
						
						
						
						String Sid = StudentID.getText();
						String name = Fname.getText();
						String lname = Lname.getText();
						String add = Address.getText();
						String number = ContactN.getText();
						String program = Program.getText();
						String room = Room.getText();
						String payment = paymentstatus.getText();
						String balance = lblbalance.getText();
						String gender = Gender.getText();
					

						try {
						    Class.forName("com.mysql.cj.jdbc.Driver");
						    con1 = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory","root","");
						    insert = con1.prepareStatement("INSERT INTO studentdata(Student_ID, Fname, Lname, Address, Contact_Number, Program, Room_Number, PaymentStatus, Balance, Gender) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?)");

						    insert.setString(1, Sid);
						    insert.setString(2, name);
						    insert.setString(3, lname);
						    insert.setString(4, add);
						    insert.setString(5, number);
						    insert.setString(6, program);
						    insert.setString(7, room);
						    insert.setString(8, payment); 
						    insert.setString(9, balance); // Set the balance here
						    insert.setString(10, gender);

						    insert.executeUpdate();

						    JOptionPane.showMessageDialog(Save,"Record Added!");
						    table_update();

						    // Clearing fields
						    StudentID.setText("");
						    Fname.setText("");
						    Lname.setText("");
						    Address.setText("");
						    ContactN.setText("");
						    Program.setText("");
						    Room.setText("");
						    paymentstatus.setText("");
						    Gender.setText("");

						    StudentID.requestFocus();

						} catch (ClassNotFoundException e1) {
						    e1.printStackTrace();
						} catch (SQLException ex) {
						    ex.printStackTrace();
						}

						
						
						
					}
				});
				
				
				DataEntry.add(Save);

		
		Save.setBackground(new Color(51, 204, 51));
		Save.setFont(new Font("Agency FB", Font.BOLD, 22));
		Save.setFocusable(false);
		DataEntry.add(Save);
		
		JButton UPDATE = new JButton("UPDATE");
		UPDATE.setBounds(106, 557, 85, 36);
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Df = (DefaultTableModel)table.getModel();	
				int selectedIndex = table.getSelectedRow();
				
				try {
					
					String Sid = StudentID.getText();
					String name = Fname.getText();
					String lname = Lname.getText();
					String add = Address.getText();
					String number = ContactN.getText();
					String program = Program.getText();
					String room = Room.getText();
					String payment = paymentstatus.getText();
					String balance = lblbalance.getText();
					String gender = Gender.getText();
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					
					con1 = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory","root","");
					insert = con1.prepareStatement("update studentdata set Student_ID = ?, Fname = ?, Lname = ?, Address = ?, Contact_Number = ?, Program = ?, Room_Number = ?, PaymentStatus = ?, Balance = ?, Gender = ? where Student_ID = ?");
					
					
					insert.setString(1, Sid);
					insert.setString(2, name);
					insert.setString(3, lname);
					insert.setString(4, add);
					insert.setString(5, number);
					insert.setString(6, program);
					insert.setString(7, room);
					insert.setString(8, payment);
					insert.setString(9, balance);
					insert.setString(10, gender);
					insert.setString(11, Sid);
				
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(Save,"Record Updated!");
					table_update();
					
					StudentID.setText("");
					Fname.setText("");
					Lname.setText("");
					Address.setText("");
					ContactN.setText("");
					Program.setText("");
					Room.setText("");
					paymentstatus.setText("");
					lblbalance.setText("");
					Gender.setText("");
					StudentID.requestFocus();
					
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				
				
				
			}
		});
		// Setting up the button appearance
		UPDATE.setBackground(new Color(0, 204, 255));
		UPDATE.setForeground(Color.WHITE);
		UPDATE.setFont(new Font("SansSerif", Font.BOLD, 16));
		UPDATE.setFocusable(false);
		UPDATE.setBorder(new RoundedBorder(10));

		// Hover effect
		UPDATE.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        UPDATE.setBackground(new Color(0, 153, 255));
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        UPDATE.setBackground(new Color(0, 204, 255));
		    }
		});

		// Adding the UPDATE button to the container
		DataEntry.add(UPDATE);
		
		JButton delete = new JButton("DELETE");
		delete.setBounds(203, 557, 85, 36);
		delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        DefaultTableModel Df = (DefaultTableModel) table.getModel();
		        int selectedIndex = table.getSelectedRow();

		        try {
		            int dialogResult = JOptionPane.showConfirmDialog(null, "ARE YOU SURE TO DELETE THE RECORD?!",
		                    "WARNING!", JOptionPane.YES_NO_OPTION);

		            if (dialogResult == JOptionPane.YES_OPTION && selectedIndex != -1) {
		                Object Sid = Df.getValueAt(selectedIndex, 0); 
		                Class.forName("com.mysql.cj.jdbc.Driver");

		                con1 = DriverManager.getConnection("jdbc:mysql://localhost/jrmsukcdormitory", "root", "");
		                PreparedStatement insert = con1.prepareStatement("DELETE FROM studentdata WHERE Student_ID = ? LIMIT 1");
		                insert.setObject(1, Sid);

		                insert.executeUpdate();

		                JOptionPane.showMessageDialog(Save, "Record Deleted!");
		                table_update();
		            } else {
		                JOptionPane.showMessageDialog(null, "Please select a row to delete.");
		            }

		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		delete.setBackground(new Color(255, 51, 51));
		delete.setForeground(Color.WHITE);
		delete.setFont(new Font("SansSerif", Font.BOLD, 16));
		delete.setFocusable(false);
		delete.setBorder(new RoundedBorder(10));

		// Hover effect
		delete.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        delete.setBackground(new Color(255, 0, 0));
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        delete.setBackground(new Color(255, 51, 51));
		    }
		});

		// Adding the DELETE button to the container
		DataEntry.add(delete);
		
		Room = new JTextField();
		Room.setBounds(11, 333, 277, 50);
		Room.setBorder(new TitledBorder(null, "ROOM NUMBER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Room.setHorizontalAlignment(SwingConstants.CENTER);
		Room.setFont(new Font("Agency FB", Font.BOLD, 18));
		Room.setColumns(10);
		DataEntry.add(Room);
		
		Gender = new JTextField();
		Gender.setBounds(11, 443, 277, 50);
		Gender.setBorder(new TitledBorder(null, "GENDER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Gender.setHorizontalAlignment(SwingConstants.CENTER);
		Gender.setFont(new Font("Agency FB", Font.BOLD, 18));
		Gender.setColumns(10);
		DataEntry.add(Gender);
		
		paymentstatus = new JTextField();
		paymentstatus.setBounds(11, 388, 277, 50);
		paymentstatus.setBorder(new TitledBorder(null, "PAYMENT STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paymentstatus.setHorizontalAlignment(SwingConstants.CENTER);
		paymentstatus.setFont(new Font("Agency FB", Font.BOLD, 18));
		paymentstatus.setColumns(10);
		DataEntry.add(paymentstatus);
		
		lblbalance = new JTextField();
		lblbalance.setBounds(11, 497, 277, 50);
		lblbalance.setBorder(new TitledBorder(null, "BALANCE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblbalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblbalance.setFont(new Font("Agency FB", Font.BOLD, 18));
		lblbalance.setColumns(10);
		DataEntry.add(lblbalance);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(388, 39, 858, 617);
		anotherWindow.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(102, 255, 255));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			DefaultTableModel Df = (DefaultTableModel)table.getModel();	
			int selectedIndex = table.getSelectedRow();
			
			StudentID.setText(Df.getValueAt(selectedIndex, 0).toString());
			Fname.setText(Df.getValueAt(selectedIndex, 1).toString());
			Lname.setText(Df.getValueAt(selectedIndex, 2).toString());
			Address.setText(Df.getValueAt(selectedIndex, 3).toString());
			ContactN.setText(Df.getValueAt(selectedIndex, 4).toString());
			Program.setText(Df.getValueAt(selectedIndex, 5).toString());
			Room.setText(Df.getValueAt(selectedIndex, 6).toString());
			paymentstatus.setText(Df.getValueAt(selectedIndex, 7).toString());
			lblbalance.setText(Df.getValueAt(selectedIndex, 8).toString());
			Gender.setText(Df.getValueAt(selectedIndex, 9).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student ID", "First Name", "Last Name", "Address", "Phone Number", "Program", "Room Number", "Status", "Total Balance", "Gender"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		 for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
		        table.getColumnModel().getColumn(i).setCellRenderer(new UpperCaseCellRenderer());
		    }

	}
	 class RoundedBorder implements Border {
		    private int radius;

		    RoundedBorder(int radius) {
		        this.radius = radius;
		    }

		    public Insets getBorderInsets(Component c) {
		        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
		    }

		    public boolean isBorderOpaque() {
		        return false;
		    }

		    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		    }
		}
}
