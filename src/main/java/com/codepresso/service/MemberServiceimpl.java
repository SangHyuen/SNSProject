package com.codepresso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.repository.MemberDAOimpl;
import com.codepresso.repository.MemberVO;
import com.codepresso.repository.UserVO;

@Repository
public class MemberServiceimpl {
	
	@Autowired
	private MemberDAOimpl memberDAO; 
	
	public List selectAllMemberList() {
		return memberDAO.selectAllMemberList();
	}
	public MemberVO selectByUserName(String userName) throws DataAccessException {
		return memberDAO.selectByUserName(userName);
	}
	public MemberVO selectByID(int id) throws DataAccessException {
		return memberDAO.selectByID(id);
	}
	public MemberVO selectByPassWord(String passWord) throws DataAccessException {
		return memberDAO.selectByPassWord(passWord);
	}
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.insertMember(memberVO);
	}
	public int loginmember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.loginmember(memberVO);
	}
	public MemberVO selectByToken(int id) throws DataAccessException {
		return memberDAO.selectByToken(id);
	}

	public MemberVO SelectIdByToken(String memberToken) throws DataAccessException{
		return memberDAO.SelectIdByToken(memberToken);
	}
	public UserVO selectByUserID(int id) throws DataAccessException {
		return memberDAO.selectByUserID(id);
	}

}
