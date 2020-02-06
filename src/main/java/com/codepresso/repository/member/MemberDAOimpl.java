package com.codepresso.repository.member;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.domain.MemberVO;
import com.codepresso.domain.UserVO;
import com.codepresso.repository.*;

@Repository
public class MemberDAOimpl implements MemberDAO{
	
	@Autowired(required = true)
	private SqlSession sqlSession ;

	@Override
	public List selectAllMemberList() throws DataAccessException {

		List<MemberVO> membersList = sqlSession.selectList("com.codepresso.mapper.memberMapper.selectAllMemberList");

		return membersList;
	}
	@Override
	public MemberVO selectByUserCheck(MemberVO memberVO) throws DataAccessException {
		MemberVO testmember = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectByUserCheck",memberVO);
		return testmember;
	}
	@Override
	public MemberVO selectByUserName(String userName) throws DataAccessException {
		MemberVO testmember = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectByUserName",userName);
		return testmember;
	}
	@Override
	public MemberVO selectByPassWord(String passWord) throws DataAccessException {
		MemberVO testmember = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectByPassWord",passWord);
		return testmember;
	}
	@Override
	public MemberVO selectByID(int id) throws DataAccessException {
		MemberVO testmember = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectByID",id);
		return testmember;
	}
	@Override
	public UserVO selectByUserID(int id) throws DataAccessException {
		UserVO testmember = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectByUserID",id);
		return testmember;
	}
	@Override
	public MemberVO selectByToken(int id) throws DataAccessException {
		MemberVO testmember = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectByToken",id);
		return testmember;
	}
	@Override
	public List selectByID() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {

		int result = sqlSession.insert("com.codepresso.mapper.memberMapper.insertMember", memberVO);
		return result;
	}
	@Override
	public int insertToken(MemberVO memberVO) throws DataAccessException {

		int result = sqlSession.insert("com.codepresso.mapper.memberMapper.inserttoken", memberVO);
		return result;
	}
	@Override
	public MemberVO SelectIdByToken(String memberToken) throws DataAccessException{
		MemberVO selectId = sqlSession.selectOne("com.codepresso.mapper.memberMapper.SelectIdByToken",memberToken);
		return selectId;
	}


	/*
	public int deleteMember(Integer Id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", Id);
		return result;
	}
	*/
	public MemberVO loginById(Integer userId) throws DataAccessException{
		  MemberVO vo = sqlSession.selectOne("mapper.member.loginById",userId);

		return vo;
	}


	@Override
	public int deleteMember(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public MemberVO loginById(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
/*
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}
	*/

}
