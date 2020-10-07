<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>    
MallList.jsp<br>

<h2>주문 내역</h2>

<table border="1">

	<tr>
		<td colspan="5" align="center">주문자 정보 : ${sessionScope.loginInfo.name }(${sessionScope.loginInfo.id })</td>
	</tr>
	<tr>
		<th>상품 번호</th>
		<th>상품명</th>
		<th>주문 수량</th>
		<th>단가</th>
		<th>금액</th>
	</tr>
	
	<c:forEach var="lists" items="${ sessionScope.shoplists }">
		<tr>
			<td align="center">${lists.pnum }</td>
			<td align="center">${lists.pname }</td>
			<td align="center">${lists.qty }</td>
			<td align="center">${lists.price }</td>
			<td align="center">${lists.amount }</td>
		</tr>
	</c:forEach>
	
	<tr>
		<td colspan="3" align="center">
			<a href="calculate.mall">결제하기</a> &nbsp;&nbsp; 
			<a href="list.prd">추가주문</a> 
		</td>
		<td colspan="2" align="center">총 금액 :${ sessionScope.totalAmount }</td>
	</tr>


</table>