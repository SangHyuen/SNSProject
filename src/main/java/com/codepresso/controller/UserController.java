package com.codepresso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.domain.MemberVO;
import com.codepresso.domain.ReturnVO;
import com.codepresso.service.MemberServiceimpl;

@RestController
@RequestMapping(value = "/*")
public class UserController {
	static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	MemberServiceimpl memberService;

	@Autowired
	MemberVO memberVO;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> listArticles(@RequestBody MemberVO memberVO) {

		List<MemberVO> selectlist = memberService.selectAllMemberList();
		ResponseEntity<List<MemberVO>> resEntity;
		try {
			resEntity = new ResponseEntity<List<MemberVO>>(selectlist, HttpStatus.OK);
		} catch (Exception e) {
			e.getStackTrace();
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<ReturnVO> addMember(@RequestBody MemberVO memberVO) {
		ReturnVO reVO = new ReturnVO();
		ResponseEntity<ReturnVO> reEntity = null;
		MemberVO checkVO = null;
		System.out.println("name = "+memberVO.getUsername());
		checkVO = memberService.selectByUserName(memberVO.getUsername());
		System.out.println("checkVO = "+checkVO);
		if (checkVO == null) {
			try {
				memberVO.setCreatedAt();
				System.out.println(memberVO.toString());
				int result = memberService.insertMember(memberVO);
				reVO.setData(memberVO);
				reVO.setCode("200");
				reVO.setMessage("Succcess");
				reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				reVO.setCode("500");
				reVO.setMessage("error");
				reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
			}
		}else {
			reVO.setMessage("User already exists");
			reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return reEntity;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<ReturnVO> loginmember(@RequestBody MemberVO memberVO) {
		MemberVO loginMember;
		ResponseEntity<ReturnVO> reEntity = null;
		ReturnVO reVO = new ReturnVO();
		try {
			loginMember = memberService.selectByUserCheck(memberVO);
			MemberVO tokenMember = null;
			tokenMember = memberService.selectByToken(loginMember.getId());
			loginMember = memberService.TokenCheck(loginMember, tokenMember);
			logger.info("userlogin OK");
			reVO.setCode("200");
			reVO.setMessage("Success");
			reVO.setData(loginMember);
			reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			reVO.setCode("500");
			reVO.setMessage("error");
			reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return reEntity;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> selNumMember(HttpServletRequest request) {
		ResponseEntity<ReturnVO> reEntity = null;
		String requestId = request.getParameter("id");
		int requestIntId = Integer.parseInt(requestId);
		MemberVO test = new MemberVO();
		ReturnVO reVO = new ReturnVO();
		try {
			logger.info("adduser OK");
			test = memberService.selectByID(requestIntId);
			reVO.setCode("200");
			reVO.setMessage("Success");
			reVO.setData(test);
			reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			reVO.setCode("500");
			reVO.setMessage("error");
			reEntity = new ResponseEntity<ReturnVO>(reVO, HttpStatus.BAD_REQUEST);
		}
		return reEntity;
	}

}
