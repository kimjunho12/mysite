<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script type="text/javascript">
	var messageBox = function(title, message, callback) {
		$("#dialog p").text(message);
		$("#dialog").attr('title', title).dialog({
			width : 350,
			modal : true,
			buttons : {
				"확인" : function() {
					$(this).dialog("close");
				}
			},
			close : callback
		});
	}

	$(function() {
		$("#join-form").submit(function(event) {
			event.preventDefault();

			// 이름 유효성 체크
			if ($("#name").val() === '') {
				//alert("이름 넣어라");
				console.log(this);
				messageBox("회원가입", "이름은 필수 입력입니다.", function() {
					$("#name").focus();
				});
				return;
			}

			// 이메일 유효성 체크 (empty)
			if ($("#email").val() === '') {
				//alert("이메일 넣어라");
				messageBox("회원가입", "이메일은 필수 입력입니다.", function() {
					$("#email").focus();
				});
				return;
			}

			// 중복체크 유무
			if ($("#btn-checkemail").css("display") === "inline-block") {
			// if ($("#btn-checkemail").is(":visible")) {
				//alert("이메일 중복 확인 해라");
				messageBox("회원가입", "중복 확인은 필수입니다.", function() {
					$("#btn-checkemail").focus();
				});
				return;
			}

			// 비밀번호 유효성 체크
			if ($("#password").val() === '') {
				// alert("비밀번호 넣어라");
				messageBox("회원가입", "비밀번호는 필수 입력입니다.", function() {
					$("#password").focus();
				});
				return;
			}

			// 유효성 ok
			console.log("ok");
			$("#join-form")[0].submit();

		});

		$("#email").change(function() {
			$("#img-checkemail").hide();
			$("#btn-checkemail").show();
		});

		$("#btn-checkemail")
				.click(
						function() {
							var email = $("#email").val();
							if (email == '') {
								return;
							}
							$
									.ajax({
										url : "${pageContext.request.contextPath }/user/api/checkemail?email="
												+ email,
										type : "get",
										dataType : "json",
										success : function(response) {
											if (response.result !== 'success') {
												console.error(response.message);
												return;
											}
											if (response.data) {
												messageBox(
														"회원가입",
														"중복 되었습니다. 다른 이메일을 사용해 주세요.",
														function() {
															$("#email").val('')
																	.focus();
														});
												return;
											}

											$("#img-checkemail").show();
											$("#btn-checkemail").hide();

										},
										error : function(xhr, status, e) {
											console.error(status, e);
										}
									});
						});
	});
</script>
</head>

<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form modelAttribute="userVo" id="join-form" name="joinForm"
					method="post"
					action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="text-align: left; padding-left: 0px; color: #f00;">
						<form:errors path="name" />
					</p>

					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<input type="button" id="btn-checkemail" value="id 중복체크">
					<img id="img-checkemail" alt="중복확인"
						src="${pageContext.request.contextPath }/assets/images/check.png"
						style="width: 16px; display: none" />
					<p style="text-align: left; padding-left: 0px; color: #f00;">
						<form:errors path="email" />
					</p>

					<label class="block-label">패스워드</label>
					<form:password path="password" />
					<p style="text-align: left; padding-left: 0px; color: #f00;">
						<form:errors path="password" />
					</p>

					<fieldset>
						<legend>성별</legend>
						<form:radiobutton path="gender" value="female" label="여" checked=" ${userVo.gender == 'female' }" />
						<form:radiobutton path="gender" value="male" label="남" checked="${userVo.gender == 'male' }" />
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<div id="dialog" title="" style="display: none">
			<p></p>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>

</html>