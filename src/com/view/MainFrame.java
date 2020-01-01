package com.view;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mkk.swing.JCalendarChooser;
import com.mkk.swing.JTimeChooser;
import com.service.RecordService;
import com.util.DatabaseManager;
import com.util.DateSelector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel defaultModel = null;
	private Vector insertRow;
	private JMenuBar menuBar;
	private JTextField textField;
	private ArrayList<String[]> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/favicon.ico")));
		setTitle("\u4E3B\u7A97\u53E3");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 225,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150, 450, 300);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 225,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 52, 434, 209);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		defaultModel = new DefaultTableModel(new Object[][] {}, new String[] { "编号", "报告人名称", "报告题目", "报告时间", "报告地点" });
		table.setModel(defaultModel);
		scrollPane.setViewportView(table);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 23);
		contentPane.add(menuBar);

		JMenu menu = new JMenu("\u6570\u636E");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("\u6DFB\u52A0");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFrame().setVisible(true);
				setVisible(false);
			}
		});
		menu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("\u4FEE\u6539");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModFrame().setVisible(true);
				setVisible(false);
			}
		});
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("\u5220\u9664");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DelFrame().setVisible(true);
				setVisible(false);
			}
		});
		menu.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("\u5BFC\u51FA\u5168\u90E8\u6570\u636E");
		// 导出
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
		menu.add(menuItem_3);

		JMenu menu_1 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_1);

		JMenuItem menuItem_4 = new JMenuItem("\u4E0B\u8F7D\u6E90\u7801");
		// 下载源码
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				downloadSource();
			}
		});
		menu_1.add(menuItem_4);

		JMenuItem menuItem_5 = new JMenuItem("\u5728\u7EBF\u6587\u6863");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpDoc();
			}
		});
		menu_1.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("\u5173\u4E8E\u4F5C\u8005");
		// 关于作者
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutAuthor();
			}
		});
		menu_1.add(menuItem_6);

		JLabel label = new JLabel("\u5173\u952E\u5B57\u68C0\u7D22");
		label.setBounds(10, 30, 115, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(124, 27, 192, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton button = new JButton("\u67E5\u8BE2");
		button.setBounds(331, 26, 93, 23);
		contentPane.add(button);
		list = new RecordService().searchAll();
		initContent(list);

		// 关键字检索
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keywords = textField.getText().trim();
				searchByKeywords(keywords);
			}
		});
	}

	/**
	 * 通过传递过来的数据，渲染表格
	 * 
	 * @param list
	 */
	public void initContent(ArrayList<String[]> list) {
		defaultModel.setRowCount(0);
		try {
			for (int i = 0; i < list.size(); i++) {
				Vector<String> insertRow = new Vector<String>();
				insertRow.addElement(list.get(i)[0]);
				insertRow.addElement(list.get(i)[1]);
				insertRow.addElement(list.get(i)[2]);
				insertRow.addElement(list.get(i)[3]);
				insertRow.addElement(list.get(i)[4]);
				defaultModel.addRow(insertRow);
			}
			table.revalidate();
			table.getColumn("编号").setPreferredWidth(25);
			table.getColumn("报告人名称").setPreferredWidth(75);
			table.getColumn("报告题目").setPreferredWidth(90);
			table.getColumn("报告时间").setPreferredWidth(125);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关于作者--按钮事件
	 */
	public void aboutAuthor() {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection("https://meethigher.top/");
		clip.setContents(tText, null);
		new JOptionPane().showMessageDialog(null, "地址已经复制到剪贴板，浏览器访问作者博客！");
	}

	/**
	 * 关键字检索--按钮事件
	 * 
	 * @param keywords
	 */
	public void searchByKeywords(String keywords) {
		if (keywords.equals("")) {
			initContent(list);
		} else {
			initContent(new RecordService().searchByKeywords(keywords));
		}
	}

	/**
	 * 下载源码--按钮事件
	 */
	public void downloadSource() {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection("https://github.com/meethigher/record-management");
		clip.setContents(tText, null);
		new JOptionPane().showMessageDialog(null, "地址已经复制到剪贴板，请打开浏览器访问！");
	}

	/**
	 * 导出--按钮事件
	 */
	public void export() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.showDialog(new JLabel(), "导出");
		File path = chooser.getSelectedFile();
		if (path != null) {
			try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
				ArrayList<String[]> list = new RecordService().searchAll();
				for (String[] arr : list) {
					for (int i = 0; i < arr.length; i++) {
						fw.write(arr[i]);
						if (i < arr.length - 1)
							fw.write(" | ");
					}
					fw.write(System.getProperty("line.separator"));

				}
				new JOptionPane().showMessageDialog(null, "导出成功！");
			} catch (IOException e2) {
				new JOptionPane().showMessageDialog(null, "导出失败！");
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 在线文档
	 */
	public void helpDoc() {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection("https://meethigher.github.io/record-management/");
		clip.setContents(tText, null);
		new JOptionPane().showMessageDialog(null, "地址已经复制到剪贴板，请打开浏览器访问！");
	}
}
