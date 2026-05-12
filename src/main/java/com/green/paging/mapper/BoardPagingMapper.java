package com.green.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;

@Mapper
public interface BoardPagingMapper {

	int count(BoardDto boarddto);

	List<BoardDto> getBoardPagingList(String menu_id, String title, String writer, int offset,
			int numOfRows);

}
