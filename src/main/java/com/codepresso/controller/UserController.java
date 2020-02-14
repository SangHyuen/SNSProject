package com.codepresso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.codepresso.domain.FollowVO;
import com.codepresso.domain.MemberVO;
import com.codepresso.domain.PostVO;
import com.codepresso.domain.ReturnVO;
import com.codepresso.service.MemberServiceimpl;
import com.codepresso.service.PostService;

@RestController
@RequestMapping(value = "/*")
public class UserController {
	private static final int ReturnVO = 0;

	static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	MemberServiceimpl memberService;

	@Autowired
	MemberVO memberVO;
	@Autowired
	PostService postService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> listArticles(@RequestBody MemberVO memberVO) {

		List<MemberVO> selectlist = memberService.selectAllMemberList();
		ResponseEntity<List<MemberVO>> resEntity;
		resEntity = new ResponseEntity<List<MemberVO>>(selectlist, HttpStatus.OK);

		return resEntity;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<ReturnVO> addMember(@RequestBody MemberVO memberVO) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> reEntity = null;
		MemberVO checkVO = null;
		checkVO = memberService.selectByUserName(memberVO.getUsername());

		if (checkVO != null) {
			reVO.setCode("400");
			reVO.setMessage("Already User");
			reVO.setData("Error");
			reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		memberVO.setCreatedAt();
		reVO = memberService.signupUser(memberVO);
		reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);

		return reEntity;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<ReturnVO> loginmember(@RequestBody MemberVO memberVO) {
		MemberVO loginMember;
		ResponseEntity<ReturnVO> reEntity = null;
		ReturnVO reVO = new ReturnVO();

		loginMember = memberService.selectByUserCheck(memberVO);
		MemberVO tokenMember = null;

		tokenMember = memberService.selectByToken(loginMember.getId());
		reVO = memberService.TokenCheck(loginMember, tokenMember);

		logger.info("userlogin OK");
		reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);

		return reEntity;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> selNumMember(HttpServletRequest request) {
		ResponseEntity<ReturnVO> reEntity = null;
		String requestId = request.getParameter("id");
		int requestIntId = Integer.parseInt(requestId);
		MemberVO test = new MemberVO();
		ReturnVO reVO = new ReturnVO();
		logger.info("adduser OK");
		reVO = memberService.selectByID(test, requestIntId);

		reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);

		return reEntity;
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public ResponseEntity<ReturnVO> followUser(@RequestBody FollowVO followVO,
			@CookieValue(value = "accesstoken") String cookieToken) {

		ResponseEntity<ReturnVO> reEntity = null;
		memberVO = memberService.selectIdByToken(cookieToken);

		followVO.setFollowerId(memberVO.getUser_Id());
		ReturnVO reVO = memberService.insertCheckFollow(followVO);

		reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		return reEntity;
	}

	@RequestMapping(value = "/follow", method = RequestMethod.DELETE)
	public ResponseEntity<ReturnVO> followDelete(@RequestBody FollowVO followVO,
			@CookieValue(value = "accesstoken") String cookieToken) {

		ResponseEntity<ReturnVO> resEntity = null;
		memberVO = memberService.selectIdByToken(cookieToken);
		followVO.setFollowerId(memberVO.getUser_Id());
		
		ReturnVO reVO = memberService.deleteCheckFollow(followVO);
		resEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		return resEntity;
	}

}
