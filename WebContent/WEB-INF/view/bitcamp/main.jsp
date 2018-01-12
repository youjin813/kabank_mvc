<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bitcamp.css" />
<body>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/nav.jsp"%>
<div id="wrapper">
<section>
<article>
<h1>비트캠프</h1>
</article>
<article>
<form name="change_pass" id="change_pass" action="${ctx}/user.do">
	<table id="mypage_table">
		<tr>
			<td rowspan="4"><img src="${ctx}/resources/img/profile.png" /></td>
			<td class="column">ID</td>
			<td>${sessionScope.user.id}</td>
			<td class="column">PW</td>
			<td>${sessionScope.user.pass}</td>
		</tr>
		<tr>
			<td class="column">NAME</td>
			<td>${sessionScope.user.name}</td>
			<td class="column">GENDER</td>
			<td>${sessionScope.user.ssn}</td>
		</tr>
		<tr>
			<td class="column">BIRTHDAY</td>
			<td>${sessionScope.user.ssn}</td>
			<td class="column">PHONE</td>
			<td>${sessionScope.user.phone}</td>
		</tr>
		<tr>
			<td class="column">EMAIL</td>
			<td>${sessionScope.user.email}</td>
			<td class="column">ADDRESS</td>
			<td>${sessionScope.user.addr}</td>
		</tr>
	</table>
</form>	
	</article>
<table id="attend_table">
	<tr>
		<th>요일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
	</tr>
	<tr>
		<td>상태</td>
		<td>
		<select name="result" id="">
		<option>출석</option>
		<option>지각</option>
		<option>조퇴</option>
		<option>결석</option>
		</select>
		</td>
		<td>
		<select name="result" id="">
		<option>출석</option>
		<option>지각</option>
		<option>조퇴</option>
		<option>결석</option>
		</select>
		</td>
		<td>
		<select name="result" id="">
		<option>출석</option>
		<option>지각</option>
		<option>조퇴</option>
		<option>결석</option>
		</select>
		</td>
		<td>
		<select name="result" id="">
		<option>출석</option>
		<option>지각</option>
		<option>조퇴</option>
		<option>결석</option>
		</select>
		</td>
		<td>
		<select name="result" id="">
		<option>출석</option>
		<option>지각</option>
		<option>조퇴</option>
		<option>결석</option>
		</select>
		</td>

	</tr>
	<tr>
		<td>결과</td>
		<td><input type="text" name="result"/></td>
		<td><input type="text" name="result"/></td>
		<td><input type="text" name="result"/></td>
		<td><input type="text" name="result"/></td>
		<td><input type="text" name="result"/></td>
	</tr>
	<tr>
	<td colspan="6">
	<input type="hidden" name="id" value="g" />
	<button id="attend_result" style="width: 200px; margin: 0 auto;">확인</button>
	<button id="change_pass_btn" name ="change_pass_btn" style="width: 200px">비밀번호 변경</button>
	</td>
	</tr>
</table>
</section>
</div>
<%@ include file="../common/footer.jsp"%>
<script>
	document.querySelector('#change_pass_btn').addEventListener('click',
			function() {
				alert('비밀번호를 변경하시겠습니까?')
				document.querySelector('#change_pass').submit;
			},false)
</script>
</body>
</html>

