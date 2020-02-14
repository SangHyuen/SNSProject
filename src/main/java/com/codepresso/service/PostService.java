package com.codepresso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.domain.FeedVO;
import com.codepresso.domain.FollowVO;
import com.codepresso.domain.MemberVO;
import com.codepresso.domain.PostVO;
import com.codepresso.domain.ReturnVO;
import com.codepresso.repository.post.PostDAOimpl;

@Repository
public class PostService {
	
	@Autowired
	private PostDAOimpl postDAO;
	@Autowired
	private MemberServiceimpl memberService;

	public List<PostVO> selectAllPost() throws DataAccessException {
		return postDAO.selectAllPost();
	}

	public int insertPost(PostVO postVO) throws DataAccessException {
		return postDAO.insertPost(postVO);
	}
	
	public List<PostVO> selectPostById(Integer user_Id) throws DataAccessException{
		return postDAO.selectPostById(user_Id);
	}
	public PostVO selectByPostId(Integer postId) throws DataAccessException{
		return postDAO.selectByPostId(postId);
	}
	public int deletePost(Integer postId) throws DataAccessException {
		return postDAO.deletePost(postId);
	}
	public int updatePost(PostVO postVO) throws DataAccessException {
		return postDAO.updatePost(postVO);
	}
	
	public List<PostVO> selectPostByPostId(Integer postId) throws DataAccessException{		
		return postDAO.selectPostByPostId(postId);
	}
	
	public List<PostVO> feedPost(List<FeedVO> feedList) throws DataAccessException {
		PostVO feedVO;

		List<PostVO> resultVO = new ArrayList<PostVO>();
		
			for(FeedVO indexVO : feedList) {
				feedVO = selectByPostId(indexVO.getPostid());
				if(feedVO ==null) {
					continue;
				}
				else {
				resultVO.add(feedVO);
				}
			}
		return resultVO;
	}
	
	public ReturnVO insertPostWithFeed(PostVO postVO,MemberVO memberVO) throws DataAccessException {

		ReturnVO resultVO = new ReturnVO();
		List<FollowVO> followList = new ArrayList<FollowVO>();
		Integer postResult = insertPost(postVO);
		PostVO selpost = postDAO.selectOnePostById(memberVO.getUser_Id());//follower들에게 넣어줄 글 번호
		followList = memberService.selectFollowerId(memberVO.getUser_Id());//내 ID가 followee인 ID들 조회
	
		for(FollowVO indexVO : followList) {
			FeedVO insertFeedVO = new FeedVO();
			insertFeedVO.setUserid(indexVO.getFollowerId());
			insertFeedVO.setFolloweeid(memberVO.getUser_Id());
			insertFeedVO.setPostid(selpost.getId());
			int insertFeed = memberService.insertFeed(insertFeedVO);
		}
		
		resultVO.setCode("200");
		resultVO.setMessage("Success");
		resultVO.setData(postVO);
			
		return resultVO;
	}
	

}
