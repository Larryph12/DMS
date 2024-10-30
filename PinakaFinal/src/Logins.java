import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import finalIS.login;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Logins {

	private JFrame frmJrmsukcDormitory;
	private JTextField lbluser;
	private JPasswordField lblpass;
	private DataEntry anotherWindow;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel3;
	private JLabel lblNewLabel_5;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logins window = new Logins();
					window.frmJrmsukcDormitory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Logins() {
		initialize();
		anotherWindow = new DataEntry();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJrmsukcDormitory = new JFrame();
		frmJrmsukcDormitory.setIconImage(Toolkit.getDefaultToolkit().getImage(Logins.class.getResource("/img/jrmsu-removebg-preview (1).png")));
		frmJrmsukcDormitory.setTitle("JRMSU-KC DORMITORY");
		frmJrmsukcDormitory.setBounds(100, 100, 642, 421);
		frmJrmsukcDormitory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJrmsukcDormitory.setLocationRelativeTo(null);
		frmJrmsukcDormitory.getContentPane().setLayout(null);
		frmJrmsukcDormitory.setResizable(false);
		
		lbluser = new JTextField();
		lbluser.setBounds(93, 83, 181, 47);
		lbluser.setBackground(new Color(250, 250, 210));
		lbluser.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		lbluser.setHorizontalAlignment(SwingConstants.CENTER);
		lbluser.setBorder(new TitledBorder(null, "USERNAME", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmJrmsukcDormitory.getContentPane().add(lbluser);
		lbluser.setColumns(10);
		
        JButton btnNewButton = new JButton("LOGIN");
        btnNewButton.setBackground(new Color(0, 136, 255)); 
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = lbluser.getText();
                String password = new String(lblpass.getPassword());

                username = username.equals("Username") ? "" : username;
                password = password.equals("Password") ? "" : password;

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please complete all requirements", "Message", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (username.equals("admin") && password.equals("123admin")) {
                    JOptionPane.showMessageDialog(null, "Login Success", "Message", JOptionPane.INFORMATION_MESSAGE);
                    anotherWindow.display();
                    frmJrmsukcDormitory.dispose();
                } else {
                    JOptionPane optionPane = new JOptionPane("Login Failed", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Message");
                    dialog.setFocusable(false);
                    dialog.setVisible(true);
                }
            }
        });

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setBackground(new Color(0, 156, 255)); // Lighter blue when hovered
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setBackground(new Color(0, 136, 255)); // Back to original
            }
        });

        btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        btnNewButton.setBounds(98, 283, 154, 47);
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 21));
        btnNewButton.setFocusPainted(false); // Removes border around text when clicked
        frmJrmsukcDormitory.getContentPane().add(btnNewButton);

        frmJrmsukcDormitory.setVisible(true);
    

		
		lblpass = new JPasswordField();
		lblpass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpass.setForeground(new Color(255, 0, 0));
		lblpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblpass.setBackground(new Color(250, 250, 210));
		lblpass.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "PASSWORD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "PASSWORD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblpass.setBounds(93, 186, 181, 47);
		frmJrmsukcDormitory.getContentPane().add(lblpass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/img/icons8-user-64.png")));
		lblNewLabel.setBounds(10, 64, 76, 77);
		frmJrmsukcDormitory.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/img/icons8-password-80.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 171, 76, 80);
		frmJrmsukcDormitory.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(147, 112, 219));
		panel_1.setBounds(315, 0, 313, 384);
		frmJrmsukcDormitory.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_3 = new JLabel("JRMSU KATIPUNAN");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 40));
		lblNewLabel_3.setBounds(10, 36, 282, 64);
		panel_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(login.class.getResource("/img/jrmsu-removebg-preview (1).png")));
		lblNewLabel_4.setBounds(66, 107, 170, 170);
		panel_1.add(lblNewLabel_4);
		
		lblNewLabel3 = new JLabel("DORMITORY");
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setFont(new Font("Agency FB", Font.BOLD, 40));
		lblNewLabel3.setBounds(10, 286, 282, 64);
		panel_1.add(lblNewLabel3);
		
		lblNewLabel_5 = new JLabel("GOOD DAY!");
		lblNewLabel_5.setForeground(new Color(0, 153, 0));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_5.setBounds(10, 10, 296, 41);
		frmJrmsukcDormitory.getContentPane().add(lblNewLabel_5);

		

	}

	public void display() {
	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	frmJrmsukcDormitory.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
	}
}
