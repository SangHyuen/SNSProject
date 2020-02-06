package com.codepresso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.domain.MemberVO;
import com.codepresso.domain.PostVO;
import com.codepresso.domain.UserVO;
import com.codepresso.repository.member.MemberDAOimpl;

@Repository
public class MemberServiceimpl {
	
	@Autowired
	private MemberDAOimpl memberDAO; 
	
	public List selectAllMemberList() {
		return memberDAO.selectAllMemberList();
	}
	public MemberVO selectByUserCheck(MemberVO memberVO) throws DataAccessException {
		return memberDAO.selectByUserCheck(memberVO);
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
	public int insertToken(MemberVO memberVO) throws DataAccessException {
		return memberDAO.insertToken(memberVO);
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
	
	public List<PostVO> InsertUser(List<PostVO> selList) {
		for (PostVO pv : selList) {
			UserVO vo = selectByUserID(pv.getUser_Id());
			pv.setUser(vo);
		}
		return selList;
	}
	public MemberVO TokenCheck(MemberVO loginMemberVO,MemberVO tokenMemberVO) {
		if(tokenMemberVO == null) {
			loginMemberVO.setToken();
			loginMemberVO.setCreatedAt();
			int result = insertToken(loginMemberVO);
		}
		else {
			loginMemberVO.setToken(tokenMemberVO.getToken());
		}
		return loginMemberVO;
		
	}

}
