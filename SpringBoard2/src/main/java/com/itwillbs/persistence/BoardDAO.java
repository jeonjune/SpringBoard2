package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {

	// 게시판 글작성
	public void create(BoardVO vo) throws Exception;
	
	// 게시판 리스트(ALL)
	public List<BoardVO> listALL() throws Exception;

	public void updateReadCnt(int bno) throws Exception;

	public BoardVO getBoard(int bno) throws Exception;
	
	public void updateBoard(BoardVO vo) throws Exception;
	
	public void deleteBoard(int bno) throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception;
	
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	
	public int getTotalCount() throws Exception;
}
