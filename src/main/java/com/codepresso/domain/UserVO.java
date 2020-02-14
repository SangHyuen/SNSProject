package com.codepresso.domain;

import org.springframework.stereotype.Component;

@Component
public class UserVO {
	private int id;
	private String username;
	private String createdAt;
	private Boolean isFollow;
	
	public Boolean getisFollow() {
		return isFollow;
	}
	public void setisFollow(Boolean isFollow) {
		this.isFollow = isFollow;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", username=" + username + ", createdAt=" + createdAt + ", isfollow=" + isFollow
				+ "]";
	}

}
