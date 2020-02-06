package com.codepresso.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.domain.MemberVO;
import com.codepresso.domain.PostVO;
import com.codepresso.domain.ReturnVO;
import com.codepresso.domain.UserVO;
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
	public ResponseEntity<ReturnVO> postText(@RequestBody PostVO reqPostVO,
			@CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity;
		try {
			memberVO = memberService.SelectIdByToken(cookieToken);
			reqPostVO.setUser_Id(memberVO.getUser_Id());
			int postResult = postService.insertPost(reqPostVO);
			userVO = memberService.selectByUserID(reqPostVO.getUser_Id());
			reqPostVO.setUser(userVO);
			logger.info("try OK");
			reVO.setCode("200");
			reVO.setMessage("Success");
			reVO.setData(reqPostVO);
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		} catch (Exception e) {
			e.getStackTrace();
			reVO.setCode("500");
			reVO.setMessage("error");
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> postList() {
		ReturnVO reVO = new ReturnVO();
		List<PostVO> selectlist = null;
		selectlist = postService.selectAllPost();
		ResponseEntity<ReturnVO> resEntity;
		PostVO outpv = new PostVO();
		List<MemberVO> userlist = new ArrayList<MemberVO>();
		try {
			memberService.InsertUser(selectlist);
			reVO.setCode("200");
			reVO.setMessage("Success");
			reVO.setData(selectlist);
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		} catch (Exception e) {
			e.getStackTrace();
			reVO.setCode("500");
			reVO.setMessage("error");
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post/my", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> myPostList(@CookieValue(value = "accesstoken") String cookieToken) {
		memberVO = memberService.SelectIdByToken(cookieToken);
		List<PostVO> myVO = postService.SelectPostById(memberVO.getUser_Id());
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity;
		memberService.InsertUser(myVO);
		try {
			reVO.setCode("200");
			reVO.setMessage("Success");
			reVO.setData(myVO);
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		} catch (Exception e) {
			e.getStackTrace();
			reVO.setCode("500");
			reVO.setMessage("error");
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> postDetail(@PathVariable("postId") int postID) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity;
		try {
			PostVO myVO = postService.SelectByPostId(postID);
			UserVO detailUser = memberService.selectByUserID(myVO.getUser_Id());
			reVO.setCode("200");
			reVO.setMessage("Success");
			myVO.setUser(detailUser);
			reVO.setData(myVO);
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		} catch (Exception e) {
			e.getStackTrace();
			reVO.setCode("500");
			reVO.setMessage("error");
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
	public ResponseEntity<ReturnVO> postDelete(@PathVariable("postId") int postID, @CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity;
		postVO = postService.SelectByPostId(postID);
		memberVO = memberService.SelectIdByToken(cookieToken);
		if (postVO.getUser_Id() == memberVO.getUser_Id()) {
			int delResult = postService.deletePost(postVO.getId());
			reVO.setCode("200");
			reVO.setMessage("Success");
			try {
				resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
			} catch (Exception e) {
				e.getStackTrace();
				reVO.setCode("400");
				reVO.setMessage("error");
				resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
			}
		} else {
			reVO.setMessage("User does not match");
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/post", method = RequestMethod.PUT)
	public ResponseEntity<ReturnVO> postPut(@RequestBody PostVO updatePost,
			@CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO>  resEntity;
		postVO = postService.SelectByPostId(updatePost.getId());
		memberVO = memberService.SelectIdByToken(cookieToken);
		if (postVO.getUser_Id() == memberVO.getUser_Id()) {
			postVO.setTitle(updatePost.getTitle());
			postVO.setContent(updatePost.getContent());
			int delResult = postService.updatePost(postVO);
			reVO.setCode("200");
			reVO.setMessage("success");
			reVO.setData(postVO);
			try {
				resEntity = new ResponseEntity<ReturnVO> (reVO, HttpStatus.OK);
			} catch (Exception e) {
				e.getStackTrace();
				reVO.setCode("500");
				reVO.setMessage("Error");
				resEntity = new ResponseEntity<ReturnVO> (reVO, HttpStatus.BAD_REQUEST);
			}
		} else {
			reVO.setMessage("User does not match");
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

}
