package com.green.paging.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingResponse<T> {
	
	// 페이징된 실제 자료들 모임 : numOfRows 10개
	private List<T> list = new ArrayList<>();
	
	// 페이지와 관련 변수들 모임
	private Pagination pagination;

	public PagingResponse(List<T> list, Pagination pagination) {
		this.list = list;
		this.pagination = pagination;
	}
	
}
