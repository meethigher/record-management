package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.RecordDao;
import com.domain.Record;
import com.service.RecordService;
import com.util.DateSelector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private DateSelector textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFrame frame = new AddFrame();
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
	public AddFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddFrame.class.getResource("/com/favicon.ico")));
		setTitle("\u6DFB\u52A0\u4FE1\u606F");
		setResizable(false);
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
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0\u4FE1\u606F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Record record=new Record();
				String name=textField.getText().trim();
				String title=textField_1.getText().trim();
				String datetime=textField_2.getText().trim();
				String place=textField_3.getText().trim();
				String empty="";
				
				if(name.equals(empty)||title.equals(empty)||datetime.equals(empty)||place.equals(empty)) {
					new JOptionPane().showMessageDialog(null, "数据不能为空！");
				}else {
					record.setName(name);
					record.setTitle(title);
					record.setDatetime(datetime);
					record.setPlace(place);
					if(new RecordService().addMessage(record)) {
						new JOptionPane().showMessageDialog(null, "添加成功！");
						new MainFrame().setVisible(true);
						setVisible(false);
					}else {
						new JOptionPane().showMessageDialog(null, "添加失败！");
					}
				}			
				
			}
		});
		btnNewButton.setBounds(63, 195, 275, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(52, 24, 310, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u62A5\u544A\u5730\u70B9");
		lblNewLabel_2.setBounds(24, 104, 99, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u62A5\u544A\u65F6\u95F4");
		lblNewLabel_1.setBounds(24, 73, 99, 15);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u62A5\u544A\u9898\u76EE");
		label.setBounds(24, 42, 99, 15);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\u62A5\u544A\u4EBA\u540D\u79F0");
		lblNewLabel.setBounds(24, 13, 99, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(154, 10, 146, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(154, 39, 146, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new DateSelector();
		textField_2.setLocation(154, 66);
		textField_2.setSize(146, 21);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(154, 101, 146, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
	}
}
