<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common.jsp" %>
ShopList.jsp<br>
<a href="logout.jsp">로그아웃</a>
<h2>주문 내역</h2>

<table border="1" align="center">
	<tr>
		<td colspan="3" align="center">주문자 정보 : ${ sessionScope.loginInfo.name }(${ sessionScope.loginInfo.id })
	</tr>
	<tr>
		<th>주문 번호</th>
		<th>주문 일자</th>
		<th>상세 보기</th>
	</tr>
	<c:forEach items="${lists }" var="order">
		<tr>
			<td>${order.oid }</td>
			<td>${order.orderdate }</td>
			<td><a href="detailView.mall?oid=${order.oid }">상세 보기</a></td>		
		</tr>
	</c:forEach>

</table>
