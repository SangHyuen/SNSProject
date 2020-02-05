package com.codepresso.repository.post;

import java.awt.List;

import org.springframework.stereotype.Component;

@Component
public class PostVO {
	private int id;
	private int user_Id;
	private String content;
	private String title;
	private String created_At;
	private Object user;
	
	public Object getUser() {
		return user;
	}
	public void setUser(Object userSet) {
		this.user = userSet;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreatedAt() {
		return created_At;
	}
	public void setCreatedAt(String createdat) {
		this.created_At = createdat;
	}
	@Override
	public String toString() {
		return "PostVO [id=" + id + ", user_Id=" + user_Id + ", content=" + content + ", title=" + title
				+ ", created_At=" + created_At + ", user=" + user + "]";
	}
	

}
