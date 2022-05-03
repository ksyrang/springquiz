<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	var req;
	function isExistId(){
		req = new XMLHttpRequest();
		req.onreadystatechange = printMsg;
		req.open('post','isExistId');
		var id = document.getElementById('id').value;
		req.send(id);//데이터를 보낸 것 문자열로 예시로 송부
	}
	
	function sendAuth(){
		req = new XMLHttpRequest();
		req.onreadystatechange = printMsg;
		req.open('post','sendAuth');
		var email = document.getElementById('email').value;
		req.send(email);
	}
	function checkAuth(){
		req = new XMLHttpRequest();
		req.onreadystatechange = printMsg;
		req.open('post','checkAuth');
		var authNum = document.getElementById('authNum').value;
		req.send(authNum);
	}
	
	function printMsg(){
		if(req.readyState == 4 && req.status == 200) {
			var msg = document.getElementById('msg');
			msg.innerHTML = req.responseText;
		}
	}

</script>
<center>
<h3 id="msg" style="color : red; "></h3>
<form action="insert" method="post" id="f">
	<table>
		<tr>
			<td align='right' height=40>아이디</td>
			<td>
				<input type=text id='id' name='id' placeholder='id 입력'/> 
			</td>
			<td colspan="2"><input type="button" value="중복 확인" onclick="isExistId();"></td>
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
				<input type=text id='email' name='email' placeholder='E-Mail 입력'/> 
			</td>
			<td colspan="2"><input type="button" value="인증번호 전송" onclick="sendAuth();"></td>
		</tr>
		<tr>
			<td align='right'>인증번호</td>
			<td>
				<input type=text id='authNum' name='authNum' placeholder='인증번호 입력'/> 
			</td>
			<td colspan="2"><input type="button" value="인증번호 확인" onclick="checkAuth();"></td>
		</tr>
		<tr>
			<td align='right'>우편번호</td>
			<td>
				<input type=text id='zipcode' name='zipcode' readonly="readonly"/> 
			</td>
			<td colspan="2"><input type="button" value="우편번호 검색" onclick="daumPost();"></td>
		</tr>
		<tr>
			<td align='right'>주소</td>
			<td colspan="3">
				<input type=text id='addr1' name='addr1' readonly="readonly" style="width: 475px; "/> 
			</td>
		</tr>
		<tr>
			<td align='right'>상세주소</td>
			<td colspan="3">
				<input type=text id='addr2' name='addr2' style="width: 475px; "/> 
			</td>
		</tr>
		<tr>
			<td align='right' width=120>성 별</td>
			<td colspan="3">
				<input type=radio id='gender' name='gender' value='n' checked="checked"/>선택 안함
				<input type=radio id='gender' name='gender' value='m' />남자
				<input type=radio id='gender' name='gender' value='w' />여자 
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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function daumPost(){
		 new daum.Postcode({
		        oncomplete: function(data) {//이벤트 함수
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            var addr ="";
		       		if(data.userSelectedType == "R"){//회신한 주소의 형식이 도로명 주소인가?
		       			addr = data.roadAddress;
		       		}else{//도로명이 아니면 지번 주소를 회신
		       			addr= jibunAddress;
		       		}
		       		//회신받은 주소 정보를 분류하여 저장
		       		document.getElementById('zipcode').value = data.zonecode;
		       		document.getElementById('addr1').value = addr;
		       		document.getElementById('addr2').focus();
		       		
		        }
		    }).open();
	}
</script>