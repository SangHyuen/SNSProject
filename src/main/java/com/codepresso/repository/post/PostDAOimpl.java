package com.codepresso.repository.post;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.codepresso.domain.PostVO;

@Repository
public class PostDAOimpl implements PostDAO{
	
	@Autowired(required = true)
	private SqlSession sqlSession ;
	

	@Override
	public List<PostVO> selectAllPost() throws DataAccessException {
		List<PostVO> postList = sqlSession.selectList("com.codepresso.mapper.postMapper.selectAllPost");
		return postList;
	}
	
	@Override
	public int insertPost(PostVO postVO) throws DataAccessException {
		int result = sqlSession.insert("com.codepresso.mapper.postMapper.insertPost", postVO);
		return result;
	}
	@Override
	public List<PostVO> selectPostById(Integer user_Id) throws DataAccessException{
		List<PostVO> vo = sqlSession.selectList("com.codepresso.mapper.postMapper.selectPostById",user_Id);
		return vo;
	}
	
	@Override
	public PostVO selectOnePostById(Integer user_Id) throws DataAccessException{
		PostVO vo = sqlSession.selectOne("com.codepresso.mapper.postMapper.selectOnePostById",user_Id);
		return vo;
	}
	@Override
	public PostVO selectByPostId(Integer postId) throws DataAccessException{
		PostVO Pvo = sqlSession.selectOne("com.codepresso.mapper.postMapper.selectByPostId",postId);
		return Pvo;
	}
	@Override
	public List<PostVO> selectPostByPostId(Integer postId) throws DataAccessException{
		List<PostVO> postVO = sqlSession.selectList("com.codepresso.mapper.postMapper.SelectPostByPostId",postId);
		return postVO;
	}
	@Override
	public int deletePost(Integer postId) throws DataAccessException {
		int result = sqlSession.delete("com.codepresso.mapper.postMapper.deletePost", postId);
		return result;
	}
	@Override
	public int updatePost(PostVO postVO) throws DataAccessException {
		int result = sqlSession.update("com.codepresso.mapper.postMapper.updatePost", postVO);
		return result;
	}

}
