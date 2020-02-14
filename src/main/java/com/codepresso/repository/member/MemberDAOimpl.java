package com.codepresso.repository.member;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.domain.FeedVO;
import com.codepresso.domain.FollowVO;
import com.codepresso.domain.MemberVO;
import com.codepresso.domain.UserVO;
import com.codepresso.repository.*;

@Repository
public class MemberDAOimpl implements MemberDAO{
	
	@Autowired(required = true)
	private SqlSession sqlSession ;

	@Override
	public List<MemberVO> selectAllMemberList() throws DataAccessException {

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
	public MemberVO selectIdByToken(String memberToken) throws DataAccessException{
		MemberVO selectId = sqlSession.selectOne("com.codepresso.mapper.memberMapper.SelectIdByToken",memberToken);
		return selectId;
	}
	
	@Override
	public int insertFollow(FollowVO insertFollowVO) throws DataAccessException{
		int followResult = sqlSession.insert("com.codepresso.mapper.memberMapper.insertFollow",insertFollowVO);
		return followResult;
	}
	@Override
	public FollowVO selectByFollowId(FollowVO selectFollowVO) throws DataAccessException {
		FollowVO followVO = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectByFollowId",selectFollowVO);
		return followVO;
	}
	@Override
	public List<FollowVO> selectFollowerId(int followeeId) throws DataAccessException {
		List<FollowVO> followVO = sqlSession.selectList("com.codepresso.mapper.memberMapper.selectFollowerId",followeeId);
		return followVO;
	}
	@Override
	public int insertFeed(FeedVO insertFeedVO) throws DataAccessException{
		int followResult = sqlSession.insert("com.codepresso.mapper.memberMapper.insertFeed",insertFeedVO);
		return followResult;
	}
	
	@Override
	public int deleteFollow(FollowVO deleteFollowing) throws DataAccessException {
		int result = sqlSession.delete("com.codepresso.mapper.memberMapper.deleteFollow", deleteFollowing);
		return result;
	}
	@Override
	public int deleteFeed(FeedVO deleteFeeding) throws DataAccessException {
		int result = sqlSession.delete("com.codepresso.mapper.memberMapper.deleteFeed", deleteFeeding);
		return result;
	}
	@Override
	public List<FeedVO> selectFeedById(int user_Id) throws DataAccessException {
		List<FeedVO> resultFeed = sqlSession.selectList("com.codepresso.mapper.memberMapper.selectFeedById",user_Id);
		return resultFeed;
	}

	@Override
	public UserVO selectFolloweeId(FollowVO followVO)throws DataAccessException{
		UserVO resultFollowee = sqlSession.selectOne("com.codepresso.mapper.memberMapper.selectFolloweeId",followVO);
		return resultFollowee;
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
	@Override
	public List selectByID() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserVO selectFolloweeId(HashMap memberMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


}
