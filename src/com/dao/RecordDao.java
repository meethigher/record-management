package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.domain.Record;
import com.domain.User;
import com.util.DatabaseManager;

public class RecordDao {
	/**
	 * 判断账户密码决定用户是否登录
	 * @param message
	 * @return
	 */
	public boolean login(User message) {
		DatabaseManager dbm = DatabaseManager.getInstance();
		String sql = "select * from record_user where username=? and password=?";
		ResultSet rs = dbm.executeQueryByParam(sql, message.getUsername(), message.getPassword());

		try {
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close();
		}
		return false;
	}
	/**
	 * 添加数据
	 * @param record
	 * @return
	 */
	public boolean addMessage(Record record) {
		DatabaseManager dbm=DatabaseManager.getInstance();
		String sql="INSERT INTO record_message (NAME,title,TIME,place) VALUES (?,?,?,?);";
		int rs=dbm.executeUpdate(sql, record.getName(),record.getTitle(),record.getDatetime(),record.getPlace());
		try {
			if(rs>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close();
		}
		return false;
	}
	/**
	 * 通过id搜索数据
	 * @param record
	 * @return
	 */
	public Record searchMessage(Record record) {
		DatabaseManager dbm=DatabaseManager.getInstance();
		String sql="select * from record_message where id=?";
		ResultSet rs=dbm.executeQueryByParam(sql, record.getId());
		try {
			if(rs.next()) {
				record.setName(rs.getString("name"));
				record.setTitle(rs.getString("title"));
				record.setDatetime(rs.getString("time"));
				record.setPlace(rs.getString("place"));
				return record;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close();
		}
		return null;
	}
	/**
	 * 修改数据
	 * @param record
	 * @return
	 */
	public boolean updateMessage(Record record) {
		DatabaseManager dbm=DatabaseManager.getInstance();
		String sql="UPDATE record_message SET NAME=?,title=?,TIME=?,place=? WHERE id=?;";
		int rs=dbm.executeUpdate(sql, record.getName(),record.getTitle(),record.getDatetime(),record.getPlace(),record.getId());
		try {
			if(rs>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close();
		}
		return false;
	}
	/**
	 * 删除数据
	 * @param record
	 * @return
	 */
	public boolean deleteMessage(Record record) {
		DatabaseManager dbm=DatabaseManager.getInstance();
		String sql="delete from record_message where id=?";
		int rs=dbm.executeUpdate(sql, record.getId());
		try {
			if(rs>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbm.close();
		}
		return false;
	}
	/**
	 * 检索所有数据
	 * @return
	 */
	public ArrayList<String[]> searchAll() {
		DatabaseManager dbm=DatabaseManager.getInstance();
		String sql="select * from record_message";
		ResultSet rs=dbm.executeQueryNoParam(sql);
		ArrayList<String[]> list=new ArrayList<String[]>();
		try {
			while(rs.next()) {
				String[] arr=new String[5];
				arr[0]=Integer.toString(rs.getInt("id"));
				arr[1]=rs.getString("name");
				arr[2]=rs.getString("title");
				arr[3]=rs.getString("time");
				arr[4]=rs.getString("place");
				list.add(arr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 关键字检索数据
	 * @param keywords
	 * @return
	 */
	public ArrayList<String[]> searchByKeywords(String keywords){
		keywords="%"+keywords+"%";
		DatabaseManager dbm=DatabaseManager.getInstance();
		String sql="SELECT * FROM record_message WHERE NAME LIKE ? OR title LIKE ? OR place LIKE ?";
		ResultSet rs=dbm.executeQueryByParam(sql, keywords,keywords,keywords);
		ArrayList<String[]> list=new ArrayList<String[]>();
		try {
			while(rs.next()) {
				String[] arr=new String[5];
				arr[0]=Integer.toString(rs.getInt("id"));
				arr[1]=rs.getString("name");
				arr[2]=rs.getString("title");
				arr[3]=rs.getString("time");
				arr[4]=rs.getString("place");
				list.add(arr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
