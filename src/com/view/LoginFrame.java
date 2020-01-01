package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.RecordDao;
import com.domain.User;
import com.service.RecordService;
import com.util.DatabaseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int count=0;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/com/favicon.ico")));
		setTitle("\u767B\u5F55\u7A97\u53E3");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-225, Toolkit.getDefaultToolkit().getScreenSize().height/2-150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(89, 58, 96, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(89, 122, 96, 15);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(218, 55, 127, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user=new User();
				user.setPassword(textField_1.getText());
				user.setUsername(textField.getText());
				if(new RecordService().login(user)) {
					new JOptionPane().showMessageDialog(null, textField.getText()+",ª∂”≠ƒ„£°");
					new MainFrame().setVisible(true);
					setVisible(false);
				}else {
					if(count++>2) {
						new JOptionPane().showMessageDialog(null, "‹≥ƒ·¥Û“Øµƒ£¨∂º ‰¥Ì3¥Œ¡À£¨∏œΩÙπˆ£°");
						System.exit(0);
					}
					new JOptionPane().showMessageDialog(null, "’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ£°");
				}
			}
		});
		btnNewButton.setBounds(92, 191, 253, 23);
		contentPane.add(btnNewButton);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(218, 119, 127, 21);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("\u62A5\u544A\u7BA1\u7406\u4FE1\u606F\u7CFB\u7EDF");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(89, 22, 256, 15);
		contentPane.add(label_2);
	}
}
