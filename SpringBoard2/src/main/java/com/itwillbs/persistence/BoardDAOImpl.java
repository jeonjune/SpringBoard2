package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

// 하나의 빈의 객체로 등록돼서 외부에서 사용할 수 있도록 해준다
@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// 디비 연결정보 -> 객체 주입을 통해서 사용(root-context안에 들어있는)
	@Inject
	private SqlSession SqlSession;
	
	@Override
	public void create(BoardVO vo) throws Exception {
		
	}

	
}
