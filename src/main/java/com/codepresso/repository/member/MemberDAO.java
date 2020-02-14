package com.codepresso.repository.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.codepresso.domain.FeedVO;
import com.codepresso.domain.FollowVO;
import com.codepresso.domain.MemberVO;
import com.codepresso.domain.UserVO;

public interface MemberDAO {
	
	 public List selectAllMemberList() throws DataAccessException;
	 public int insertMember(MemberVO memberVO) throws DataAccessException ;
	 public int deleteMember(String id) throws DataAccessException;
	 public MemberVO loginById(String id) throws DataAccessException;
	List selectByID() throws DataAccessException;
	MemberVO selectByID(int id) throws DataAccessException;
	MemberVO selectByPassWord(String passWord) throws DataAccessException;
	MemberVO selectByToken(int id) throws DataAccessException;
	UserVO selectByUserID(int id) throws DataAccessException;
	MemberVO selectByUserCheck(MemberVO memberVO) throws DataAccessException;
	MemberVO selectByUserName(String userName) throws DataAccessException;
	int insertToken(MemberVO memberVO) throws DataAccessException;
	MemberVO selectIdByToken(String memberToken) throws DataAccessException;
	int insertFollow(FollowVO insertFollow) throws DataAccessException;
	FollowVO selectByFollowId(FollowVO selectFollowVO) throws DataAccessException;
	int insertFeed(FeedVO insertFollow) throws DataAccessException;
	int deleteFollow(FollowVO deleteFollowing) throws DataAccessException;
	int deleteFeed(FeedVO deleteFeeding) throws DataAccessException;
	List<FeedVO> selectFeedById(int user_Id) throws DataAccessException;
	UserVO selectFolloweeId(HashMap memberMap) throws DataAccessException;
	List<FollowVO> selectFollowerId(int followeeId) throws DataAccessException;
	UserVO selectFolloweeId(FollowVO followVO) throws DataAccessException;

}
