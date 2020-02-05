package com.codepresso.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface MemberDAO {
	
	 public List selectAllMemberList() throws DataAccessException;
	 public int insertMember(MemberVO memberVO) throws DataAccessException ;
	 public int deleteMember(String id) throws DataAccessException;
	 public MemberVO loginById(String id) throws DataAccessException;
	int loginmember(MemberVO memberVO) throws DataAccessException;
	List selectByID() throws DataAccessException;
	MemberVO selectByID(int id) throws DataAccessException;
	MemberVO selectByUserName(String userName) throws DataAccessException;
	MemberVO selectByPassWord(String passWord) throws DataAccessException;
	MemberVO selectByToken(int id) throws DataAccessException;
	MemberVO SelectIdByToken(String memberToken) throws DataAccessException;
	UserVO selectByUserID(int id) throws DataAccessException;

}
