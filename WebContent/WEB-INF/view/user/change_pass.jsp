<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
<heaber>
<h1>비밀번호변경</h1>
</heaber>
<form name="pass_change" action="${ctx}/user.do">
	<table>
		<tr>
			<td>현재 비밀번호</td>
			<td>
				<input name="present" type="text" />
			</td>
		</tr>
		<tr>
			<td>수정 비밀번호</td>
			<td>
				<input name="change" type="text" />
			</td>
		</tr>
		<tr>
			<td>수정 비밀번호 확인</td>
			<td>
			<input name="change_check" type="text" />
			</td>
		</tr>
		<tr>
			<td>
				<button>확인</button>
			</td>
		</tr>
	</table>
</form>
</body>
<script>

</script>
</html>