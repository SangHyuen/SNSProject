package com.codepresso.repository.post;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.codepresso.domain.PostVO;

public interface PostDAO {

	List selectAllPost() throws DataAccessException;

	int insertPost(PostVO postVO) throws DataAccessException;

	List<PostVO> SelectPostById(Integer userId) throws DataAccessException;

	PostVO SelectByPostId(Integer post_Id) throws DataAccessException;

	int deletePost(Integer postId) throws DataAccessException;

	int updatePost(PostVO postVO) throws DataAccessException;


}
