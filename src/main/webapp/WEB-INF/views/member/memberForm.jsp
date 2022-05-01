<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	var req;
	function sendId(){
		req = new XMLHttpRequest();
		req.onreadystatechange = textChang;
		req.open('post','idcheck');
		var id = document.getElementById('id').value;
		req.send(id);//데이터를 보낸 것 문자열로 예시로 송부
	}
	
	function textChang(){
		if(req.readyState == 4 && req.status == 200) {
			var idcheck_result = document.getElementById('result_display');
			idcheck_result.innerHTML = req.responseText;
		}
	}

</script>
<c:if test="${not empty msg }">
	<script>alert("${msg}");</script>
</c:if>
<center>
<h3 id="result_display" style="color : red; "></h3>
<form action="insert" method="post" id="f">
	<table>
		<tr>
			<td align='right' height=40>아이디</td>
			<td>
				<input type=text id='id' name='id' placeholder='id 입력'/> 
			</td>
			<td colspan="2"><input type="button" value="중복 확인" onclick="sendId();"></td>
		</tr>
		<tr>
			<td align='right' height=40>패스워드</td>
			<td>
				<input type=text name='pw' placeholder='pw 입력'/> 
			</td>
			<td align='right'>패스워드 확인</td>
			<td>
				<input type=text name='pwOk' placeholder='pw 입력'/> 
			</td>
		</tr>
		<tr>
			<td align='right' height=40>E-Mail</td>
			<td>
				<input type=text name='email' placeholder='E-Mail 입력'/> 
			</td>
			<td colspan="2"><input type="button" value="인증번호 전송"></td>
		</tr>
		<tr>
			<td align='right'>인증번호</td>
			<td>
				<input type=text name='authNum' placeholder='인증번호 입력'/> 
			</td>
			<td colspan="2"><input type="button" value="인증번호 확인"></td>
		</tr>
		<tr>
			<td align='right'>우편번호</td>
			<td>
				<input type=text name='zipcode' readonly="readonly"/> 
			</td>
			<td colspan="2"><input type="button" value="우편번호 검색"></td>
		</tr>
		<tr>
			<td align='right'>주소</td>
			<td colspan="3">
				<input type=text name='addr1' readonly="readonly" style="width: 475px; "/> 
			</td>
		</tr>
		<tr>
			<td align='right'>상세주소</td>
			<td colspan="3">
				<input type=text name='addr2' style="width: 475px; "/> 
			</td>
		</tr>
		<tr>
			<td align='right' width=120>성 별</td>
			<td colspan="3">
				<input type=radio name='gender' value='n' checked="checked"/>선택 안함
				<input type=radio name='gender' value='m' />남자
				<input type=radio name='gender' value='w' />여자 
			</td>
		</tr>
		
		<tr>
			<td align='center' height=40 colspan=4>
				<input type="submit" value='회원 가입' style="width: 120px; "/>
				<input type="reset" value='취소' style="width: 120px; "/>	 
			</td>
		</tr>
	</table>
</form>
</center>