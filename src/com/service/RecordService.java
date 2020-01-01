package com.service;

import java.util.ArrayList;

import com.dao.RecordDao;
import com.domain.Record;
import com.domain.User;

public class RecordService {
	public boolean login(User message) {
		RecordDao userDao=new RecordDao();
		return userDao.login(message);
	}
	public boolean addMessage(Record record) {
		return new RecordDao().addMessage(record);
	}
	public boolean updateMessage(Record record) {
		return new RecordDao().updateMessage(record);
	}
	public Record searchMessage(Record record) {
		return new RecordDao().searchMessage(record);
	}
	public boolean deleteMessage(Record record) {
		return new RecordDao().deleteMessage(record);
	}
	public ArrayList<String[]> searchAll(){
		return new RecordDao().searchAll();
	}
	public ArrayList<String[]> searchByKeywords(String keywords){
		return new RecordDao().searchByKeywords(keywords);
	}
	public static Record generateRecord() {
		return new Record();
	}
	public static User generateUser() {
		return new User();
	}
	
}
