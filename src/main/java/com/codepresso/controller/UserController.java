package com.codepresso.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import org.json.simple.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.repository.MemberDAOimpl;
import com.codepresso.repository.MemberVO;
import com.codepresso.repository.ReturnVO;
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
		List<MemberVO> selectlist = null;
		selectlist = memberService.selectAllMemberList();
		ResponseEntity resEntity;
		try {

			for (MemberVO mem : selectlist) {
				System.out.println("mem" + mem);
			}

			resEntity = new ResponseEntity(selectlist, HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<ReturnVO> addMember(@RequestBody MemberVO memberVO) {
		ReturnVO reVO = new ReturnVO();
		// HashMap userMap = new HashMap();
		// HashMap snsMap = new HashMap();
		ResponseEntity reEntity = null;
		MemberVO checkVO = null;
		checkVO = memberService.selectByUserName(memberVO.getUsername());
		System.out.println(checkVO);
		try {
			if (checkVO == null) {
				memberVO.setCreatedAt();
				System.out.println(memberVO.toString());
				int result = memberService.insertMember(memberVO);
				reVO.setData(memberVO);
				reVO.setCode(HttpStatus.OK);
				reVO.setMessage("200");
				System.out.println("suc reVO = " + reVO.getData());
				reEntity = new ResponseEntity(reVO, HttpStatus.OK);
			} else {
				reEntity = new ResponseEntity("error", HttpStatus.BAD_REQUEST);
			}
			// resEntity = new ResponseEntity(snsMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// reVO.setCode(HttpStatus.BAD_REQUEST);
		}
		return reEntity;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<ReturnVO> loginmember(@RequestBody MemberVO memberVO) {
		MemberVO loginMember;
		ResponseEntity reEntity = null;
		ReturnVO reVO = new ReturnVO();
		try {
			loginMember = memberService.selectByUserName(memberVO.getUsername());
			if (loginMember.getUsername().equals(memberVO.getUsername())) {
				if (loginMember.getPassword().equals(memberVO.getPassword())) {
					MemberVO tokenMember = null;
					tokenMember = memberService.selectByToken(loginMember.getId());
					if (tokenMember == null) {
						loginMember.setToken();
						loginMember.setCreatedAt();
						logger.info("userlogin OK");
						int result = memberService.loginmember(loginMember);
						reVO.setCode(HttpStatus.OK);
						reVO.setMessage("200");
						reVO.setData(loginMember);
						System.out.println("token s = "+reVO.getData());
						reEntity = new ResponseEntity(reVO, HttpStatus.OK);
					} else {
						loginMember.setToken(tokenMember.getToken());
						reVO.setCode(HttpStatus.OK);
						reVO.setMessage("200");
						reVO.setData(loginMember);
						System.out.println("token f = "+reVO.getData());
						reEntity = new ResponseEntity(reVO, HttpStatus.OK);
					}

				} else {
					System.out.println("password 오류");
					reEntity = new ResponseEntity("error", HttpStatus.BAD_REQUEST);
				}
			} else {
				System.out.println("UserName 오류");
				reEntity = new ResponseEntity("error", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reVO.setCode(HttpStatus.BAD_REQUEST);
			reEntity = new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
		return reEntity;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<ReturnVO> selNumMember(HttpServletRequest request) {
		ResponseEntity reEntity = null;
		String requestId = request.getParameter("id");
		int requestIntId = Integer.parseInt(requestId);
		MemberVO test = new MemberVO();
		ReturnVO reVO = new ReturnVO();
		try {
			logger.info("adduser OK");
			test = memberService.selectByID(requestIntId);

			reVO.setCode(HttpStatus.OK);
			reVO.setMessage("200");
			reVO.setData(test);
			reEntity = new ResponseEntity(reVO,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			reEntity = new ResponseEntity(e,HttpStatus.BAD_REQUEST);
		}
		return reEntity;
	}

}
