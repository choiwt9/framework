package com.kh.spring.board.controller;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		   
		   model.addAttribute("list", bList); //게시글 목록 데이터
		   model.addAttribute("pi", pi);      //페이징 목록 데이터
		   
		
		//WEB-INF/views/board/boardList.jsp
		return "board/boardList";
		
	}
	
}
