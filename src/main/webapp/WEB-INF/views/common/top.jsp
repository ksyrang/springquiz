<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
<!--

a:link{color:black;font-family:sans-serif;text-decoration:none;}
a:visited{color:black;font-family:sans-serif;text-decoration:none;}
a:hover{color:#cc3300; font-weight:bold; }
a:active{color:#ff00cc; text-decoration:underline; }
-->
</style>

<c:url var="root" value="/"/><!-- a태그는 무조건 상대경로로 찾아가기 때문에 /를 사용하면 contextroot의 최상의 경로의 절대 경로를 지정하기 때문에 다른 프로젝트에서도 추가적인 수정 없이 사용 가능 -->
<table width=800>
	<tr><td align="center" colspan=5><h1>CARE Lab</h1></td></tr>
	<tr align="right">
		<td width=500></td>
		<td><a href="${root }index?formpath=home">홈</a></td>
		<c:choose>
			<c:when test="${empty sessionScope.id }">
				<td><a href="${root }index?formpath=login">로그인</a></td>
				<td><a href="${root }index?formpath=member">회원가입</a></td>
			</c:when>	
			<c:otherwise>
				<td><a href="${root }logout">로그 아웃</a></td><!-- index가 있는 homecontroller가 아닌 logincontroller로 가기위함 -->
				<td><a href="${root }memberListProc">회원목록</a></td><!-- href="${root }memberListProc"  / href="${root }memberlist" / href="${root }index?formpath=memberlist"  -->
			</c:otherwise>
		</c:choose>
		
		<td><a href="${root }index?formpath=board">게시판</a></td>
	</tr>
	<tr><td align="center" colspan=5><hr/></td></tr>
</table>

