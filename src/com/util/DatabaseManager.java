package com.util;

import java.sql.*;


public class DatabaseManager {
	private Connection con;
	private PreparedStatement pre;
	private ResultSet rs;
	private static DatabaseManager db;
	// 静态代码块，执行唯一的一次
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DatabaseManager() {
	}

	public static DatabaseManager getInstance() {
		if (db == null) {
			db = new DatabaseManager();
		}
		return db;
	}

	private void buildConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/record-db?useSSL=false&serverTimezone=UTC",
					"root", "1050121804");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 使用Object可变参数
	public ResultSet executeQueryByParam(String sql, Object... o) {
		this.buildConnection();
		try {
			pre = con.prepareStatement(sql);
			for (int i = 0; i < o.length; i++) {
				pre.setObject(i + 1, o[i]);
			}
			rs = pre.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet executeQueryNoParam(String sql) {
		this.buildConnection();
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int executeUpdate(String sql, Object... o) {
		this.buildConnection();
		try {
			pre = con.prepareStatement(sql);
			for (int i = 0; i < o.length; i++) {
				pre.setObject(i + 1, o[i]);
			}
			int count = pre.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
