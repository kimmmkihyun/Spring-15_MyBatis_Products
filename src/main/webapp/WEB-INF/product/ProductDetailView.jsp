<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common.jsp" %>   
 
ProductDetailView.jsp<br>
<h2>상품 상세 화면</h2>
<table border="1">
<tr>
	<td>
		<img src="<%=request.getContextPath() %>/resources/${bean.image}" width="100px" height="100px"/>
	</td>

	<td>
		<table border="1">
			<tr>
				<td width="150">상품명</td>
				<td width="300">${ bean.name }</td>
			</tr>
			
			<tr>
				<td width="150">가격</td>
				<td width="300">${ bean.price }</td>
			</tr>
			
			<tr>
				<td width="150">재고 수량</td>
				<td width="300">${ bean.stock }</td>
			</tr>
			
			<tr>
				<td width="150">설명</td>
				<td width="300">${ bean.contents }</td>
			</tr>
			
			<tr>
				<td width="150">주문 수량</td>
				<td width="300">
				<!-- add.mall 요청 -> cartaddcontroller로 -->
					<form action="add.mall" method="post">
						<input type="hidden" name="num" value="${bean.num }">
						<input type="text" name="orderqty" value="1">
						<input type="submit" value="주문">
					</form>				
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<a href="list.prd">상품 리스트</a>
				</td>	
			</tr>
		</table>
	</td>
</tr>	
	
</table>
