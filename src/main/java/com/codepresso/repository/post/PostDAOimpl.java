package com.codepresso.repository.post;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.codepresso.repository.MemberVO;
import com.codepresso.repository.post.*;

@Repository
public class PostDAOimpl implements PostDAO{
	
	@Autowired(required = true)
	private SqlSession sqlSession ;
	

	@Override
	public List<PostVO> selectAllPost() throws DataAccessException {
		List<PostVO> postList = sqlSession.selectList("com.codepresso.mapper.memberMapper.selectAllPost");
		return postList;
	}
	
	@Override
	public int insertPost(PostVO postVO) throws DataAccessException {
		int result = sqlSession.insert("com.codepresso.mapper.memberMapper.insertPost", postVO);
		return result;
	}
	@Override
	public List<PostVO> SelectPostById(Integer user_Id) throws DataAccessException{
		List<PostVO> vo = sqlSession.selectList("com.codepresso.mapper.memberMapper.SelectPostById",user_Id);
		return vo;
	}
	@Override
	public PostVO SelectByPostId(Integer postId) throws DataAccessException{
		PostVO Pvo = sqlSession.selectOne("com.codepresso.mapper.memberMapper.SelectByPostId",postId);
		return Pvo;
	}
	@Override
	public int deletePost(Integer postId) throws DataAccessException {
		int result = sqlSession.delete("com.codepresso.mapper.memberMapper.deletePost", postId);
		return result;
	}
	@Override
	public int updatePost(PostVO postVO) throws DataAccessException {
		int result = sqlSession.update("com.codepresso.mapper.memberMapper.updatePost", postVO);
		return result;
	}

}
