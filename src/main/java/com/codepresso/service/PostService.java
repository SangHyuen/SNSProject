package com.codepresso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.domain.PostVO;
import com.codepresso.repository.post.PostDAOimpl;

@Repository
public class PostService {
	
	@Autowired
	private PostDAOimpl postDAO;

	public List<PostVO> selectAllPost() throws DataAccessException {
		return postDAO.selectAllPost();
	}
	

	public int insertPost(PostVO postVO) throws DataAccessException {
		return postDAO.insertPost(postVO);
	}

	public List<PostVO> SelectPostById(Integer user_Id) throws DataAccessException{
		return postDAO.SelectPostById(user_Id);
	}
	public PostVO SelectByPostId(Integer postId) throws DataAccessException{
		return postDAO.SelectByPostId(postId);
	}
	public int deletePost(Integer postId) throws DataAccessException {
		return postDAO.deletePost(postId);
	}
	public int updatePost(PostVO postVO) throws DataAccessException {
		return postDAO.updatePost(postVO);
	}

}
