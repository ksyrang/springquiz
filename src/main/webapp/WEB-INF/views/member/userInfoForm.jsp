<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.id }">
<script>
	location.href= 'index?formpath=login';
</script>
</c:if> 
<c:url var="root" value="/"/>

		
<center>
<table style="width: 650px; ">
	<tr>
		<td style="width: 300px; height:40px;" align="center"><h2>회원 정보</h2></td>
	</tr>
	<tr>
		<td colspan=2><hr/></td>
	</tr>
	<tr>
		<td style="width: 100px; height:40px;" align="center" valign="bottom">아이디</td>
		<td style="width: 300px; height:40px;" align="center" valign="bottom">${user.id }</td>
	</tr>
	<tr>
		<td style="width: 100px; height:40px;" align="center" valign="bottom">이메일</td>
		<td style="width: 300px; height:40px;" align="center" valign="bottom">${user.email }</td>
	</tr>
	<tr>
		<td style="width: 100px; height:40px;" align="center" valign="bottom">성별</td>
		<c:choose>
			<c:when test="${user.gender } == 'm'">
				<td style="width: 300px; height:40px;" align="center" valign="bottom">남</td>
			</c:when>
			<c:when test="${user.gender } == 'w'">
				<td style="width: 300px; height:40px;" align="center" valign="bottom">여</td>
			</c:when>
			<c:otherwise>
				<td style="width: 300px; height:40px;" align="center" valign="bottom">선택X</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td style="width: 100px; height:40px;" align="center" valign="bottom">주소</td>
		<td style="width: 300px; height:40px;" align="center" valign="bottom">
			${user.addr1 }&nbsp;&nbsp;${user.addr2 }
		</td>
	</tr>
	<tr>
		<td colspan=2><hr/></td>
	</tr>
	<tr>
		<td colspan=2 align="right">
			<input type=button style="width: 60px; " value='수정'
			onclick="location.href='${root }index?formpath=modifyCheck&modifyId=${user.id }'"/>
			<input type=button style="width: 60px; " value='삭제'
			onclick="location.href='${root }index?formpath=memberDelete&modifyId=${user.id }'"/>
			<input type=button style="width: 60px; " value='목록'
			onclick="location.href='memberListProc'"/>
		</td>
	</tr>
</table>
</center>