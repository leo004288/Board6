package com.green.paging.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.PagingResponse;
import com.green.paging.dto.SearchDto;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardPagingMapper boardPagingMapper;

	// /BoardPaging/List?menu_id=MENU01&nowpage=1
	@RequestMapping("/List")
	public ModelAndView list(BoardDto boarddto, int nowpage) {
		
		// 메뉴목록 : menus.jsp
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 게시물 목록 조회 (페이징해서)
		// 해당 메뉴의 자료갯수 : 
		int totalcount = boardPagingMapper.count(boarddto);  // menu_id
		System.out.println("totcount:" + totalcount);
		
		PagingResponse<BoardDto> response = null;
		if(totalcount < 1) {  // 현재 menu_id로 조회한 자료가 없다면
			response = new PagingResponse<>(
					Collections.emptyList(), null);
					//Collections.emptyList() : 자료가 없는 빈 리스트를 채운다
		}
		
		// 페이징을 위한 초기설정
		SearchDto searchdto = new SearchDto();
		searchdto.setPageNo(nowpage); // 현재페이지 정보
		searchdto.setNumOfRows(10);   // 한페이지의 자료수
		searchdto.setPageSize(10);    // paging.jsp 에 한줄에 출력될 페이지 번호 수
		
		//  pagination 설정
		Pagination pagination = new Pagination(totalcount, searchdto);
		searchdto.setPagination(pagination);
		
		// 검색 조건 추가
		// 추가된 검색 조건
		String menu_id = boarddto.getMenu_id();
		String title   = boarddto.getTitle();
		String writer  = boarddto.getWriter();
		String content = boarddto.getContent();
		
		int offset    = searchdto.getOffset();
		int numOfRows = searchdto.getNumOfRows();
		
		// 검색조건추가
		List<BoardDto> list = boardPagingMapper.getBoardPagingList(
				menu_id, title, writer, content, offset, numOfRows);
		response = new PagingResponse<>(list, pagination);
		
		System.out.println(response);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("menuList", menuList);

		mv.addObject("nowpage", nowpage);
		mv.addObject("menu_id", menu_id);        // 현재 메뉴정보
		
		mv.addObject("boardList", list);
		mv.addObject("searchdto", searchdto);
		
		return mv;
	} 
	
	// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1
	
}
