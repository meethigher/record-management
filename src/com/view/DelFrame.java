package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.domain.Record;
import com.service.RecordService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelFrame frame = new DelFrame();
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
	public DelFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DelFrame.class.getResource("/com/favicon.ico")));
		setResizable(false);
		setTitle("\u5220\u9664\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				new MainFrame().setVisible(true);
			}
			
		});
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-225, Toolkit.getDefaultToolkit().getScreenSize().height/2-150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id\u641C\u7D22");
		lblNewLabel.setBounds(44, 21, 78, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(157, 21, 94, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Record record=new Record();
				try {
					record.setId(Integer.parseInt(textField.getText()));
					record=new RecordService().searchMessage(record);
					if(record==null) {
						new JOptionPane().showMessageDialog(null, "Œ¥≤È—ØµΩƒ⁄»›£°");
					}else {
						textField_1.setText(record.getName());
						textField_2.setText(record.getTitle());
						textField_3.setText(record.getDatetime());
						textField_4.setText(record.getPlace());
					}
				} catch (Exception e2) {
					new JOptionPane().showMessageDialog(null, "«Î ‰»Î ˝◊÷£°");
				}
			}
		});
		button.setBounds(261, 20, 93, 23);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 69, 434, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u62A5\u544A\u4EBA\u540D\u79F0");
		label.setBounds(42, 10, 115, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u62A5\u544A\u9898\u76EE");
		label_1.setBounds(42, 35, 115, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u62A5\u544A\u65F6\u95F4");
		label_2.setBounds(42, 60, 115, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u62A5\u544A\u5730\u70B9");
		label_3.setBounds(42, 85, 115, 15);
		panel.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(156, 7, 198, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(156, 32, 198, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(156, 57, 198, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(155, 82, 199, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A\u5220\u9664");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Record record=new Record();
				try {
					int id=Integer.parseInt(textField.getText().trim());
					record.setId(id);
					if(new RecordService().deleteMessage(record)) {
						new JOptionPane().showMessageDialog(null, "…æ≥˝≥…π¶");
						new MainFrame().setVisible(true);
						setVisible(false);
					}else {
						new JOptionPane().showMessageDialog(null, "…æ≥˝ ß∞‹");
					}
				} catch (Exception e2) {
					new JOptionPane().showMessageDialog(null, "«Î ‰»Î ˝◊÷£°");
				}
				
				
			}
		});
		btnNewButton.setBounds(42, 120, 312, 23);
		panel.add(btnNewButton);
	}
}
