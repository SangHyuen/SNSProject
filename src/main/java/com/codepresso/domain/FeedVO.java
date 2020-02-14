package com.codepresso.domain;

public class FeedVO {
	
	int userId;
	int followeeId;
	int postId;
	String createdAt;
	
	
	
	public int getUserid() {
		return userId;
	}
	public void setUserid(int userid) {
		this.userId = userid;
	}
	public int getFolloweeid() {
		return followeeId;
	}
	public void setFolloweeid(int followeeid) {
		this.followeeId = followeeid;
	}
	public int getPostid() {
		return postId;
	}
	public void setPostid(int postid) {
		this.postId = postid;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "FeedVO [userid=" + userId + ", followeeid=" + followeeId + ", postid=" + postId + ", createdAt="
				+ createdAt + "]";
	}

}
