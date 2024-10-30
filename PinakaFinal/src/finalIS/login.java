package finalIS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;


public class login {

	private JFrame frmJrmsukcDormitory;
	private JTextField lbluser;
	private JPasswordField lblpass;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJrmsukcDormitory = new JFrame();
		frmJrmsukcDormitory.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmJrmsukcDormitory.setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/image/jrmsu.png")));
		frmJrmsukcDormitory.setTitle("JRMSU-KC DORMITORY");
		frmJrmsukcDormitory.setBounds(100, 100, 642, 421);
		frmJrmsukcDormitory.setLocationRelativeTo(null);
		frmJrmsukcDormitory.setResizable(false);
		frmJrmsukcDormitory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJrmsukcDormitory.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/img/icons8-user-64.png")));
		lblNewLabel.setBounds(10, 57, 76, 77);
		frmJrmsukcDormitory.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/img/icons8-password-80.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 159, 76, 80);
		frmJrmsukcDormitory.getContentPane().add(lblNewLabel_1);
		
		lbluser = new JTextField();
		lbluser.setBackground(new Color(250, 250, 210));
		lbluser.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		lbluser.setHorizontalAlignment(SwingConstants.CENTER);
		lbluser.setBorder(new TitledBorder(null, "USERNAME", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lbluser.setBounds(93, 76, 181, 47);
		frmJrmsukcDormitory.getContentPane().add(lbluser);
		lbluser.setColumns(10);
		
		lblpass = new JPasswordField();
		lblpass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpass.setForeground(new Color(255, 0, 0));
		lblpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblpass.setBackground(new Color(250, 250, 210));
		lblpass.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "PASSWORD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "PASSWORD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblpass.setBounds(93, 174, 181, 47);
		frmJrmsukcDormitory.getContentPane().add(lblpass);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBackground(new Color(0, 255, 0));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String username = lbluser.getText();
				String password = String.valueOf(lblpass.getPassword());
				
				username = username.equals("Username") ? "" : username;
				password = password.equals("Password") ? "" : password;
				
				if(username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please complete all requirements", "Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(username.equals("larry11312") && password.equals("garena123")) {
					JOptionPane.showMessageDialog(null, "Login Success", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Failed", "Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnNewButton.setBounds(98, 283, 154, 47);
		btnNewButton.setFont(new Font("Agency FB", Font.BOLD, 21));
		btnNewButton.setBounds(98, 283, 154, 47);
		btnNewButton.setFocusable(false);
		frmJrmsukcDormitory.getContentPane().add(btnNewButton);
		
		panel = new JPanel();
		panel.setBackground(new Color(147, 112, 219));
		panel.setBounds(315, 0, 313, 384);
		frmJrmsukcDormitory.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("JRMSU KATIPUNAN");
		lblNewLabel_2.setFont(new Font("Agency FB", Font.BOLD, 40));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 57, 282, 64);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("DORMITORY");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 40));
		lblNewLabel_3.setBounds(10, 269, 282, 64);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(login.class.getResource("/img/jrmsu-removebg-preview (1).png")));
		lblNewLabel_4.setBounds(66, 107, 170, 170);
		panel.add(lblNewLabel_4);
		
	}
}
