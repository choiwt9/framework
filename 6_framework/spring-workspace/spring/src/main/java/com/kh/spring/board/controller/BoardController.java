package com.kh.spring.board.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.template.Pagenation;
import com.kh.spring.member.model.vo.PageInfo;
import com.kh.spring.member.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	//BoardService 객체를 주입
	private final BoardService bService;

	public BoardController(BoardService bService) {
		this.bService = bService;
	}
	/*
	 * /board/list 요청이 들어오는 경우
	 * =>메뉴바에서 자유게시판 메뉴 클릭 /board/list 요청 (기본적으로 첫페이지 표시)
	 * =>페이징바 클릭 /board/list?cpage= 요청할 페이지번호
	 */

	@RequestMapping("/list")
	public String boardList(@RequestParam(value="cpage", defaultValue="1")int currentPage, Model model) {
		System.out.println("현재 페이지 : " + currentPage);
		
		//전체 게시글 수 조회
		   int listCount = bService.selectListCount();
		   
		   PageInfo pi = Pagenation.getPageInfo(listCount, currentPage, 5, 5);
		   
		   //게시글 목록 조회
		   ArrayList<Board> bList = bService.selectList(pi);
		   
		   System.out.println(bList);
		   
		   model.addAttribute("list", bList); //게시글 목록 데이터
		   model.addAttribute("pi", pi);      //페이징 목록 데이터
		   
		
		//WEB-INF/views/board/boardList.jsp
		return "board/boardList";
		
	}
	
	@RequestMapping("/enrollForm")
	
	public String boardEnrollForm() {
		return "board/boardEnrollForm";
		
	}
	
	@RequestMapping("/insert")
	public String insertBoard(Board b, MultipartFile upfile, 
			                     HttpSession session, Model model) {
		
		System.out.println(b);
		//System.out.println(upfile);
		
		//첨부파일이 있는 경우 -> 전달된 파일을 서버에 저장 + Board 객체에 파일정보 저장
		
		if(!upfile.getOriginalFilename().isEmpty()) {
			// 파일명을 변경하여 저장
			// 변경 파일명 => yyyyMMddHHmss + xxxxx(랜덤값) + .확장자
			
			//* 현재 날짜 시간 관련 정보
			String currTime = new SimpleDateFormat("yyyyMMddHHmss").format(new Date());
			 //*5자리 랜덤값 (10000~99999)
			
			int random = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;
			
			// *확장자 (.txt, .java, .png, ...)
			String orgName = upfile.getOriginalFilename();
			 String ext = orgName.substring(orgName.lastIndexOf("."));
			
			 String chgName = currTime + random + ext;
			 
			 // 업로드할 파일을 저장할 위치의 물리적인 경로 조회
	       	   String path = session.getServletContext().getRealPath("/resources/uploadFiles");
	
		     try {
				upfile.transferTo(new File(path + chgName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     b.setOriginName(orgName);
		     b.setChangeName("resources/uploadFiles/" + chgName);
		}
		
		//DB에 게시글 정보 저장(첨부파일 유/무 상관 없이 처리)
		int result = bService.insertBoard(b);
		
		if(result > 0) { // 게시글 등록 성공
			session.setAttribute("alertMsg", "게시글 등록 성공");
			
			return "redirect:/board/list";
			
		}else {// 게시글 등록 실패
			model.addAttribute("errorMsg", "게시글 등록 실패");
			return "common/errorPage";
		}
		
		
		
	}
	
}
