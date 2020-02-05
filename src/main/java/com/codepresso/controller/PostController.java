package com.codepresso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.repository.MemberVO;
import com.codepresso.repository.ReturnVO;
import com.codepresso.repository.UserVO;
import com.codepresso.repository.post.PostVO;
import com.codepresso.service.MemberServiceimpl;
import com.codepresso.service.PostService;

@RestController
@RequestMapping(value = "/*")
public class PostController {

	static Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	MemberServiceimpl memberService;

	@Autowired
	PostService postService;

	@Autowired
	MemberVO memberVO;

	@Autowired
	PostVO postVO;
	@Autowired
	UserVO userVO;

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<List<ReturnVO>> postText(@RequestBody PostVO reqPostVO,
			@CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity resEntity;
		try {
			memberVO = memberService.SelectIdByToken(cookieToken);
			reqPostVO.setUser_Id(memberVO.getUser_Id());
			int postResult = postService.insertPost(reqPostVO);
			userVO = memberService.selectByUserID(reqPostVO.getUser_Id());
			reqPostVO.setUser(userVO);
			logger.info("try OK");
			reVO.setCode(HttpStatus.OK);
			reVO.setMessage("200");
			reVO.setData(reqPostVO);
			resEntity = new ResponseEntity(reVO, HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseEntity<List<PostVO>> postList() {
		ReturnVO reVO = new ReturnVO();
		List<PostVO> selectlist = null;
		selectlist = postService.selectAllPost();
		ResponseEntity resEntity;
		PostVO outpv = new PostVO();
		List<MemberVO> userlist = new ArrayList<MemberVO>();
		try {
			for (PostVO pv : selectlist) {
				UserVO vo = memberService.selectByUserID(pv.getUser_Id());
				pv.setUser(vo);
			}
			reVO.setCode(HttpStatus.OK);
			reVO.setMessage("200");
			reVO.setData(selectlist);
			resEntity = new ResponseEntity(reVO, HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post/my", method = RequestMethod.GET)
	public ResponseEntity<List<PostVO>> myPostList(@CookieValue(value = "accesstoken") String cookieToken) {
		memberVO = memberService.SelectIdByToken(cookieToken);
		List<PostVO> myVO = postService.SelectPostById(memberVO.getUser_Id());
		ReturnVO reVO = new ReturnVO();
		ResponseEntity resEntity;
		for (PostVO pv : myVO) {
			UserVO userVO = memberService.selectByUserID(pv.getUser_Id());
			pv.setUser(userVO);
		}
		try {
			reVO.setCode(HttpStatus.OK);
			reVO.setMessage("200");
			reVO.setData(myVO);
			resEntity = new ResponseEntity(reVO, HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public ResponseEntity<List<PostVO>> postDetail(@PathVariable("postId") int postID) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity resEntity;
		try {
			PostVO myVO = postService.SelectByPostId(postID);
			UserVO detailUser = memberService.selectByUserID(myVO.getUser_Id());
			reVO.setCode(HttpStatus.OK);
			reVO.setMessage("200");
			myVO.setUser(detailUser);
			reVO.setData(myVO);
			resEntity = new ResponseEntity(reVO, HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
	public ResponseEntity<List<PostVO>> postDelete(@PathVariable("postId") int postID, @CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity resEntity;
		postVO = postService.SelectByPostId(postID);
		memberVO = memberService.SelectIdByToken(cookieToken);
		if (postVO.getUser_Id() == memberVO.getUser_Id()) {
			int delResult = postService.deletePost(postVO.getId());
			reVO.setCode(HttpStatus.OK);
			reVO.setMessage("200");

			try {

				resEntity = new ResponseEntity(reVO, HttpStatus.OK);
			} catch (Exception e) {
				resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			reVO.setCode(HttpStatus.BAD_REQUEST);
			reVO.setMessage("User does not match");
			resEntity = new ResponseEntity(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post", method = RequestMethod.PUT)
	public ResponseEntity<List<PostVO>> postPut(@RequestBody PostVO updatePost,
			@CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity resEntity;
		postVO = postService.SelectByPostId(updatePost.getId());
		memberVO = memberService.SelectIdByToken(cookieToken);
		if (postVO.getUser_Id() == memberVO.getUser_Id()) {
			postVO.setTitle(updatePost.getTitle());
			postVO.setContent(updatePost.getContent());
			int delResult = postService.updatePost(postVO);
			reVO.setCode(HttpStatus.OK);
			reVO.setMessage("200");
			reVO.setData(postVO);

			try {

				resEntity = new ResponseEntity(reVO, HttpStatus.OK);
			} catch (Exception e) {
				resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			reVO.setCode(HttpStatus.BAD_REQUEST);
			reVO.setMessage("User does not match");
			resEntity = new ResponseEntity(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

}
