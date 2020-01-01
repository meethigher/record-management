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

public class ModFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private DateSelector textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModFrame frame = new ModFrame();
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
	public ModFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModFrame.class.getResource("/com/favicon.ico")));
		setResizable(false);
		setTitle("\u4FEE\u6539\u4FE1\u606F");
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
		
		JLabel lblId = new JLabel("id\u641C\u7D22");
		lblId.setBounds(44, 10, 71, 15);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(113, 7, 99, 21);
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
						new JOptionPane().showMessageDialog(null, "未查询到内容！");
					}else {
						textField_4.setText(record.getName());
						textField_3.setText(record.getTitle());
						textField_2.setText(record.getDatetime());
						textField_1.setText(record.getPlace());
					}
				} catch (Exception e2) {
					new JOptionPane().showMessageDialog(null, "请输入数字！");
				}
			}
		});
		button.setBounds(222, 6, 93, 23);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 35, 434, 226);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u62A5\u544A\u5730\u70B9");
		label.setBounds(39, 111, 77, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u62A5\u544A\u65F6\u95F4");
		label_1.setBounds(39, 86, 77, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u62A5\u544A\u9898\u76EE");
		label_2.setBounds(39, 61, 77, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u62A5\u544A\u4EBA\u540D\u79F0");
		label_3.setBounds(39, 36, 77, 15);
		panel.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(114, 111, 202, 21);
		panel.add(textField_1);
		
		textField_2 = new DateSelector();
		textField_2.setBounds(114, 86, 202, 21);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(114, 61, 202, 21);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(114, 33, 202, 21);
		panel.add(textField_4);
		
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Record record=new Record();
					String name=textField_4.getText().trim();
					String title=textField_3.getText().trim();
					String datetime=textField_2.getText().trim();
					String place=textField_1.getText().trim();
					int id=Integer.parseInt(textField.getText().trim());
					String empty="";
					
					if(name.equals(empty)||title.equals(empty)||datetime.equals(empty)||place.equals(empty)) {
						new JOptionPane().showMessageDialog(null, "数据不能为空！");
					}else {
						record.setName(name);
						record.setTitle(title);
						record.setDatetime(datetime);
						record.setPlace(place);
						record.setId(id);
						if(new RecordService().updateMessage(record)) {
							new JOptionPane().showMessageDialog(null, "修改成功");
							new MainFrame().setVisible(true);
							setVisible(false);
						}else {
							new JOptionPane().showMessageDialog(null, "修改失败");
						}
					}
				} catch (Exception e2) {
					new JOptionPane().showMessageDialog(null, "bug");
				}
			}
		});
		btnNewButton.setBounds(37, 158, 279, 23);
		panel.add(btnNewButton);
	}
}
