package com.codepresso.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class MemberVO {
	private int id;
	private int user_Id;
	private String password;
	private String username;
	private String createdAt;
	private String token;
	
	public MemberVO() {	
	}

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		Date now = new Date();
		SimpleDateFormat B = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateform = B.format(now);
		this.createdAt = dateform;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public void setToken() {
		String token;
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 20; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		token = temp.toString();
		this.token = token;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", user_Id=" + user_Id + ", password=" + password
				+ ", username=" + username + ", createdAt=" + createdAt + ", token=" + token + "]";
	}

}