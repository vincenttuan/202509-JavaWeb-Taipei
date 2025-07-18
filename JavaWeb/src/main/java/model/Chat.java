package model;

import java.util.Date;

/*
use web;
create table if not exists chat(
	id int auto_increment primary key,
    username varchar(50) not null,
    question text not null,
    answer text not null,
    create_time datetime default current_timestamp
);
*/
public class Chat {
	private Integer id; // 資料表會自動 +1 維護
	private String username; // 提問者姓名
	private String question; // 提問
	private String answer; // 回答
	private Date createTime; // 自動生成現在時間
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
