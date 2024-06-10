package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	private Integer bno; // int보다 Integer 형태를 더 많이 사용
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private int viewcnt;
}
