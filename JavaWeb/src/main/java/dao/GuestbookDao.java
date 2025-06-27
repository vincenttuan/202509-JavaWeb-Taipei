package dao;

import java.util.List;

import model.Guestbook;

public interface GuestbookDao {
	public List<Guestbook> findAll(); // 查詢所有訪客留言資料
	public void add(String name, String message); // 新增訪客留言資料
	public void delete(Integer id); // 刪除指定留言
	public Guestbook get(Integer id); // 取得單筆資料
	public void update(Integer id, String name, String message); // 修改資料
}
