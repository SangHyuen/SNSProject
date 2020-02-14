package com.codepresso.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.domain.FeedVO;
import com.codepresso.domain.FollowVO;
import com.codepresso.domain.MemberVO;
import com.codepresso.domain.PostVO;
import com.codepresso.domain.ReturnVO;
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

	public MemberVO selectIdByToken(String memberToken) throws DataAccessException {
		return memberDAO.selectIdByToken(memberToken);
	}

	public UserVO selectByUserID(int id) throws DataAccessException {
		return memberDAO.selectByUserID(id);
	}

	public FollowVO selectByFollowId(FollowVO selectFollowVO) throws DataAccessException {
		return memberDAO.selectByFollowId(selectFollowVO);
	}

	public UserVO selectFolloweeId(FollowVO selectFollow) throws DataAccessException {
		return memberDAO.selectFolloweeId(selectFollow);
	}

	public List<FollowVO> selectFollowerId(int followeeId) throws DataAccessException {
		return memberDAO.selectFollowerId(followeeId);
	}
	
	public int insertFollow(FollowVO insertFollow) throws DataAccessException {
		return memberDAO.insertFollow(insertFollow);
	}
	
	public int deleteFollow(FollowVO deleteFollowing) throws DataAccessException {
		return memberDAO.deleteFollow(deleteFollowing);
	}

	public int insertFeed(FeedVO insertFeedVO) throws DataAccessException {
		return memberDAO.insertFeed(insertFeedVO);
	}

	public int deleteFeed(FeedVO deleteFeeding) throws DataAccessException {
		return memberDAO.deleteFeed(deleteFeeding);
	}
	
	public List<FeedVO> selectFeedById(int user_Id) throws DataAccessException {
		return memberDAO.selectFeedById(user_Id);
	}

	
	public ReturnVO insertALLPostUserInfo(List<PostVO> selList, MemberVO memberVO) {
		ReturnVO resultVO = new ReturnVO();
		UserVO vo2 = new UserVO();
		FollowVO selectFollow = new FollowVO();

		for (PostVO pv : selList) {
			UserVO vo = selectByUserID(pv.getUser_Id());// 글쓴이 정보 갖고옴
			if (memberVO != null) {
				selectFollow.setFolloweeId(vo.getId());
				selectFollow.setFollowerId(memberVO.getUser_Id());
				vo2 = selectFolloweeId(selectFollow);// 조회

				if (vo2 == null) {
					vo2 = new UserVO();
					vo2.setisFollow(false);
				} else {
					vo2.setisFollow(true);
				}
				vo.setisFollow(vo2.getisFollow());
			}
			pv.setUser(vo);
		}
		resultVO.setCode("200");
		resultVO.setMessage("Success");
		resultVO.setData(selList);

		return resultVO;
	}

	public ReturnVO insertPostUserInfo(List<PostVO> selList, MemberVO memberVO) {
		ReturnVO resultVO = new ReturnVO();
		UserVO vo2 = new UserVO();
		FollowVO selectFollow = new FollowVO();

		for (PostVO pv : selList) {
			if (pv != null) {
				UserVO vo = selectByUserID(pv.getUser_Id());// 글쓴이 정보 갖고옴
				selectFollow.setFolloweeId(vo.getId());
				selectFollow.setFollowerId(memberVO.getUser_Id());
				vo2 = selectFolloweeId(selectFollow);// 조회

				if (vo2 == null) {
					vo2 = new UserVO();
					vo2.setisFollow(false);
				} else {
					vo2.setisFollow(true);
				}
				vo.setisFollow(vo2.getisFollow());
				pv.setUser(vo);
			} else
				continue;
		}

		resultVO.setCode("200");
		resultVO.setMessage("Success");
		resultVO.setData(selList);

		return resultVO;
	}

	public ReturnVO signupUser(MemberVO memberVO) {

		ReturnVO returnVO = new ReturnVO();
		FollowVO followMe = new FollowVO();

		Integer memberResult;
		int followResult;
		memberResult = insertMember(memberVO);
		MemberVO followMember = selectByUserCheck(memberVO);

		followMe.setFolloweeId(followMember.getId());
		followMe.setFollowerId(followMember.getId());
		followResult = insertFollow(followMe);
		returnVO.setData(memberVO);
		returnVO.setCode("200");
		returnVO.setMessage("Succcess");

		return returnVO;
	}

	public ReturnVO insertCheckFollow(FollowVO checkFollowVO) throws DataAccessException {
		ReturnVO resultVO = new ReturnVO();

		FollowVO selectFollowVO = memberDAO.selectByFollowId(checkFollowVO);

		if (selectFollowVO == null) {
			int followResult = memberDAO.insertFollow(checkFollowVO);
			resultVO.setCode("200");
			resultVO.setMessage("OK");
			resultVO.setData("success");
		} else {
			resultVO.setCode("500");
			resultVO.setMessage("Fail");
			resultVO.setData("Already Follow");
		}
		return resultVO;

	}

	public ReturnVO deleteCheckFollow(FollowVO checkFollowVO) throws DataAccessException {
		ReturnVO resultVO = new ReturnVO();
		int delFollowResult;
		int delFeed;
		FeedVO delFeedVO = new FeedVO();
		delFeedVO.setUserid(checkFollowVO.getFollowerId());
		delFeedVO.setFolloweeid(checkFollowVO.getFolloweeId());

		if (checkFollowVO != null) {
			delFollowResult = memberDAO.deleteFollow(checkFollowVO);
			delFeed = memberDAO.deleteFeed(delFeedVO);
			resultVO.setCode("200");
			resultVO.setMessage("OK");
			resultVO.setData("success");
		}
		return resultVO;
	}

	public ReturnVO TokenCheck(MemberVO loginMemberVO, MemberVO tokenMemberVO) {
		ReturnVO reVO = new ReturnVO();
		if (tokenMemberVO == null) {
			loginMemberVO.setToken();
			loginMemberVO.setCreatedAt();
			int result = insertToken(loginMemberVO);
			reVO.setData(loginMemberVO);
			reVO.setCode("200");
			reVO.setMessage("success");
		} else {
			loginMemberVO.setToken(tokenMemberVO.getToken());
			reVO.setData(loginMemberVO);
			reVO.setCode("200");
			reVO.setMessage("success");
		}
		return reVO;

	}
	
	
	public ReturnVO selectByID(MemberVO memberVO, int index) throws DataAccessException {
		ReturnVO resultVO = new ReturnVO();
		memberVO = selectByID(index);
		resultVO.setCode("200");
		resultVO.setMessage("Success");
		resultVO.setData(memberVO);
		return resultVO;
	}


}
