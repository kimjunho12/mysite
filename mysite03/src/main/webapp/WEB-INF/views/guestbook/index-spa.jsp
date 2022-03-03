<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script type="text/javascript">
	var render = function(vo) {
		console.log("render call!");
		var html = "<li data-no='" + vo.no + "'>" + "<strong>" + vo.name
				+ "</strong>" + "<p>" + vo.message + "</p>"
				+ "<a href='' data-no='" + vo.no + "'>삭제</a>" + "</li>"
		return html;
	}

	var startNo = -1;
	var fetch = function() {
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook?sn=" + startNo,	// + ?sn=
			type : "get",
			dataType : "json",
			contentType : 'application/json',
			success : function(response) {
				if (response.result !== 'success') {
					console.error(response.message);
					return;
				}

				for (var i = 0; i < response.data.length; i++) {
					var vo = response.data[i];
					$("#list-guestbook").append(render(vo));
					startNo = vo.no;
				}

			}
		});
	}
	
	var add = function(event) {
		event.preventDefault();

		var vo = {}; //serlizer
		vo.name = $("#input-name").val();
		vo.password = $("#input-password").val();
		vo.message = $("#tx-content").val();
		
		if (vo.name === '' || vo.password === '' || vo.message === ''){
			return ;
		}

		console.log(vo);

		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook",
			type : "post",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify(vo),
			success : function(response) {
				if (response.result !== 'success') {
					console.error(response.message);
					return;
				}

				var html = render(response.data);
				$("#list-guestbook").prepend(html);
				
				$("#input-name").val("");
				$("#input-password").val("");
				$("#tx-content").val("");

				console.log(response);
			}
		})

	}

	$(function() {
		// 삭제 다이얼로그 객체 만들기
		var dialogDelete = $("#dialog-delete-form").dialog({
			autoOpen : false,
			modal: true,
			buttons: {
				"삭제" : function() {
					var no = $("#hidden-no").val();
					var password = $("#password-delete").val();
					var url = "${pageContext.request.contextPath }/api/guestbook/" + no;
					
					$.ajax({
						url: url,
						type: 'delete',
						dataType: 'json',
						data: "password=" + password,
						success: function(response) {
							if(response.result !== "success"){
								console.error(response.message);		
								return ;
							}
							
							console.log(response.data);
							
							// 삭제 실패 경우(비밀번호 불일치)
							if (response.data === -1) {
								$(".validateTips.error").show();
								$("#password-delete").val("").focus();
								return ;
							}
							
							// 삭제가 된 경우
							$("#list-guestbook li[data-no='" + response.data + "']").remove();
							dialogDelete.dialog('close');							
						}
					});
				},
				"취소" : function() {
					$(this).dialog("close");
				}
			},
			close: function() {
				$(".validateTips.error").hide();
				
				$("#password-delete").val("");
				$("#hidden-no").val("");
			}
			
		});
		
		// 글 삭제 버튼 클릭 이벤트 (Live Event 처리)
		$(document).on("click", "#list-guestbook li a", function(event) {
			event.preventDefault();
			
			var no = $(this).attr("data-no");	// == $(this).data("no")
			$("#hidden-no").val(no);
			
			dialogDelete.dialog("open");
		});
		
		// 글 작성 버튼 클릭 이벤트
		$("#add-form").submit(add);
				
		fetch();
		// 스크롤
        $(window).scroll(function() {
            var $window = $(this);
            var $document = $(document);

            var windowHeight = $window.height();
            var documentHeight = $document.height();
            var scrollTop = $window.scrollTop();

            if (scrollTop + windowHeight === documentHeight) {
				// 최초 리스트 가져오기
				fetch();
            }
        });
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form"
					action=""
					method="post">
					<input type="text" id="input-name" placeholder="이름" name="name">
					<input type="password" id="input-password" placeholder="비밀번호"
						name="password">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요." name="message"></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook"></ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" id="password-delete" value=""
						class="text ui-widget-content ui-corner-all"> <input
						type="hidden" id="hidden-no" value=""> <input
						type="submit" tabindex="-1"
						style="position: absolute; top: -1000px">
				</form>
			</div>
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-spa" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>