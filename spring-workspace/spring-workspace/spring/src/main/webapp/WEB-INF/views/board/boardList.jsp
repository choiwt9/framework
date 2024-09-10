<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%-- header.jsp 포함 => 포함된 파일에 선언된 변수를 공유하고자 할 때(include 지시어)--%>
	<%@ include file="../common/header.jsp"%>

	<div class="outer">
        <br><br>
        <div class="innerOuter" style="padding: 5% 10%">
            <h2>게시판</h2>
            <br>

            <%-- 로그인 시에만 글쓰기 버튼 표시 --%>
            <a href="" class="btn btn-secondary" style="float:right">글쓰기</a>
            <br>
            
            <br>
            <table id="boardList" class="table table-hover" align="center">
                <thead>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>첨부파일</th>
                </thead>

                <tbody>
                    <tr>
                        <td>5</td>
                        <td>마지막 공지사항 제목</td>
                        <td>admin</td>
                        <td>10</td>
                        <td>2024-04-01</td>
                        <td>■</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>공지사항 제목 4</td>
                        <td>admin</td>
                        <td>10</td>
                        <td>2024-03-21</td>
                        <td>■</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>공지사항 제목 3</td>
                        <td>admin</td>
                        <td>30</td>
                        <td>2024-03-07</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>공지사항 제목 2</td>
                        <td>admin</td>
                        <td>50</td>
                        <td>2024-02-22</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>공지사항 제목 1</td>
                        <td>admin</td>
                        <td>130</td>
                        <td>2024-01-07</td>
                        <td></td>
                    </tr>                                        
                </tbody>
            </table>
            <br>

            <div id="pagingArea">
                <ul class="pagination">
                    <li class="page-item"><a href="" class="page-link">Prev</a></li>
                    <li class="page-item"><a href="" class="page-link">1</a></li>
                    <li class="page-item"><a href="" class="page-link">2</a></li>
                    <li class="page-item"><a href="" class="page-link">3</a></li>
                    <li class="page-item"><a href="" class="page-link">4</a></li>
                    <li class="page-item"><a href="" class="page-link">5</a></li>
                    <li class="page-item"><a href="" class="page-link">Next</a></li>
                </ul>
            </div>

            <br clear="both">

            <form action="" id="searchForm">
                <div class="select">
                    <select name="condition" id="" class="custom-select form-select">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword">
                </div>
                <button class="searchBtn btn btn-secondary">검색</button>
            </form>
            <br><br>
        </div>
        <br><br>
    </div>


	<%-- footer.jsp 포함 => 해당 페이지를 포함만 시키고자 할 때(표준액션태그)--%>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>