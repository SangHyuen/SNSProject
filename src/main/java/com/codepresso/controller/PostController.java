package com.codepresso.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.filefilter.FalseFileFilter;
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

import com.codepresso.domain.FeedVO;
import com.codepresso.domain.FollowVO;
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
		memberVO = memberService.selectIdByToken(cookieToken);
		reqPostVO.setUser_Id(memberVO.getUser_Id());
		reVO = postService.insertPostWithFeed(reqPostVO,memberVO);
		userVO = memberService.selectByUserID(reqPostVO.getUser_Id());
		userVO.setisFollow(true);
		reqPostVO.setUser(userVO);
		logger.info("try OK");
		
		resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);

		return resEntity;
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> postList(@CookieValue(value = "accesstoken",required = false) String cookieToken) {
		ReturnVO reVO = new ReturnVO();

		List<PostVO> selectlist = postService.selectAllPost();
		ResponseEntity<ReturnVO> resEntity;
		memberVO = memberService.selectIdByToken(cookieToken);
		reVO = memberService.insertALLPostUserInfo(selectlist,memberVO);
		resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);

		return resEntity;
	}

	@RequestMapping(value = "/post/feed", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> myPostList(@CookieValue(value = "accesstoken") String cookieToken) {

		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity;
		
		memberVO = memberService.selectIdByToken(cookieToken);
		System.out.println("post/feed = "+memberVO.toString());
		List<FeedVO> selectFeed = memberService.selectFeedById(memberVO.getUser_Id());
		List<PostVO> feedVO = postService.feedPost(selectFeed);
		
		reVO = memberService.insertPostUserInfo(feedVO,memberVO);
		resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);

		return resEntity;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> postDetail(@PathVariable("postId") int postID) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity;
		PostVO myVO = postService.selectByPostId(postID);
		UserVO detailUser = memberService.selectByUserID(myVO.getUser_Id());
		reVO.setCode("200");
		reVO.setMessage("Success");
		myVO.setUser(detailUser);
		reVO.setData(myVO);
		resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}

	@RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
	public ResponseEntity<ReturnVO> postDelete(@PathVariable("postId") int postID,
			@CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity;
		postVO = postService.selectByPostId(postID);
		memberVO = memberService.selectIdByToken(cookieToken);
		if (postVO.getUser_Id() == memberVO.getUser_Id()) {
			int delResult = postService.deletePost(postVO.getId());
			reVO.setCode("200");
			reVO.setMessage("Success");
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		} else {
			reVO.setCode("400");
			reVO.setMessage("error, User does not match");
			resEntity = new ResponseEntity(reVO, HttpStatus.BAD_REQUEST);
		}
		System.out.println("eeee"+resEntity);
		return resEntity;
	}

	@RequestMapping(value = "/post", method = RequestMethod.PUT)
	public ResponseEntity<ReturnVO> postPut(@RequestBody PostVO updatePost,
			@CookieValue(value = "accesstoken") String cookieToken) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> resEntity = null;
		postVO = postService.selectByPostId(updatePost.getId());
		memberVO = memberService.selectIdByToken(cookieToken);
		if (postVO.getUser_Id() == memberVO.getUser_Id()) {
			postVO.setTitle(updatePost.getTitle());
			postVO.setContent(updatePost.getContent());
			int delResult = postService.updatePost(postVO);
			reVO.setCode("200");
			reVO.setMessage("success");
			reVO.setData(postVO);
			resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		}
		return resEntity;
	}

}