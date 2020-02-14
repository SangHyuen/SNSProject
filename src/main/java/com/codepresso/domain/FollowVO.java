package com.codepresso.domain;

public class FollowVO {
	//테이블 별 클래스 분리, 변수명 '_'
	
	int followerId;
	int followeeId;
	String createdAt;
	
	
	public int getFolloweeId() {
		return followeeId;
	}
	public void setFolloweeId(int followeeId) {
		this.followeeId = followeeId;
	}
	
	public int getFollowerId() {
		return followerId;
	}
	public void setFollowerId(int follower_id) {
		this.followerId = follower_id;
	}

	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "FollowVO [  followee_id=" + followeeId + ", follower_id=" + followerId
				+  ", createdAt=" + createdAt + "]";
	}

	
}
