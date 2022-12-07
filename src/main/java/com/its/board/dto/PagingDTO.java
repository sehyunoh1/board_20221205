package com.its.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingDTO {
    private int page; // 현재 페이지
    private int maxPage; // 필요한 페이지 개수
    private int startPage; // 시작 페이지
    private int endPage; // 끝 페이지
}
