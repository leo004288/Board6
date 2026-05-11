package com.green.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
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
		int totcount = boardPagingMapper.count(boarddto);  // menu_id
		System.out.println("totcount:" + totcount);
		
		String       menu_id = boarddto.getMenu_id();
		ModelAndView mv      = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("menuList", menuList);
		mv.addObject("nowpage", nowpage);

		mv.addObject("menu_id", menu_id);  // 현재 메뉴정보
		return mv;
	} 
	
	// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1
	
}
