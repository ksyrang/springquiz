<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<center>
<h3 id="msg" style="color : red; ">${msg }</h3>
<form action="loginProc" method="post" id="f">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type=text id="id" name='id' placeholder='ID 입력'/></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type=password id="pw" name='pw' placeholder='PW 입력'/></td>
		</tr>
		<tr>
			<td colspan=2 align='center'>
				<input type=submit value='로그인' style="width: 86px; "/>
				<input type=reset value='취소' style="width: 86px; "/> 
			</td>
		</tr>
	</table>
</form>
</center>
