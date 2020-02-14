package com.codepresso.repository.post;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.codepresso.domain.PostVO;

public interface PostDAO {

	List selectAllPost() throws DataAccessException;

	int insertPost(PostVO postVO) throws DataAccessException;

	List<PostVO> selectPostById(Integer userId) throws DataAccessException;

	PostVO selectByPostId(Integer post_Id) throws DataAccessException;

	int deletePost(Integer postId) throws DataAccessException;

	int updatePost(PostVO postVO) throws DataAccessException;

	List<PostVO> selectPostByPostId(Integer postId) throws DataAccessException;
	public PostVO selectOnePostById(Integer user_Id) throws DataAccessException;




}
