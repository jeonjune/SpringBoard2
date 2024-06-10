<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

	<form role="form" method="post" action="/board/regist">
		<div class="box-body">
			<div class="form-group">
			<label for="exampleInputEmail1">제목</label>
			<input type="text" class="form-control" id="exampleInputEmail1" placeholder="제목을 입력하걸아" name="title">
			</div>
			
			<div class="form-group">
			<label for="exampleInputEmail1">작성자</label>
			<input type="text" class="form-control" id="exampleInputEmail1" placeholder="작성자를 입력하걸아" name="writer">
			</div>
			
			<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="3" placeholder="Enter ..." name="content"></textarea>
			</div>
		</div>
	
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">글쓰기</button>
		</div>
	</form>
</div>

<%@ include file="../include/footer.jsp" %>