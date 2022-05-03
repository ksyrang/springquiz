<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.id }">
	<script>
		location.href= 'index?formpath=login';
	</script>
</c:if> 



<center>

<table style="width: 650px; ">
	<thead>
	<tr>
		<th style="width: 180px; height:20px;" align="center">아이디</th>
		<th style="width: 400px; height:20px;" align="center">이메일</th>
		<th style="width: 70px; height:20px;" align="center">성별</th>
	</tr>
	</thead>
	<tr>
		<td style="width: 180px; height:20px;" align="center"><hr/></td>
		<td style="width: 400px; height:20px;" align="center"><hr/></td>
		<td style="width: 70px; height:20px;" align="center"><hr/></td>
	</tr>
	<!-- for 반복문 사용해야 할곳 -->
	<c:forEach var="member" items="${list }">
	<tr>
		<td style="width: 180px; height:40px;" align="center">
			<a href="userInfoProc?id=${member.getId() }">${member.getId() }</a>
		</td>
		<td style="width: 400px; height:40px;" align="center">${member.getEmail() }</td>
		<td style="width: 70px; height:40px;" align="center">${member.getGender() }</td>
	</tr>
	</c:forEach>
	
	<tr><td colspan=5><hr/></td></tr>
</table>
이전 1 2 3 4 다음
<table>
<tr>
<td>
	<select id="searchTitle" name="searchTitle" size="1">
		<option>아이디</option>
		<option>이메일</option>
	</select>
	<input type=text name='searchData'/>
	<input type=button id="searchBtn" name='searchBtn' value='검색' onclick="search();" style="width: 80px; "/>
</td>
</tr>
</table>
</center>