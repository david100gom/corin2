<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- include summernote(텍스트에디터) css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>

<!-- include hashtags(해쉬태그) css/js -->
<link rel="stylesheet" href="resources/css/board/tagsinput.css">
<script src="resources/js/board/tagsinput.js"></script>
<link rel="stylesheet" href="resources/css/board/troubleshooting.css">
<script type="text/javascript">			    
	//페이지 생성완료 후에 섬머노트를 loading, 태그란에 마우스포인터 위치
	$(document).ready(function() {
		  $('#summernote').summernote({
			  onblur : function(e) {
		            $('#summercontent').html($('#summernote').code());
		        },
		    height : 100, // set editor height
		    width : 900 // set editor width
		  });
		  $('#summernote2').summernote({
			  onblur : function(e) {
		            $('#summercontent').html($('#summernote').code());
		        },
		    height : 150, // set editor height
		    width : 900 // set editor width
		  });
			
		  //최초커서위치를 hashtag로 이동. 안됨 ㅠㅠ 다시알아볼것
		  //document.insfrm.hashtag.focus();
	});
	
</script>

<div class="troublebackdiv">
	<h2 id='boardTitle'>트러블슈팅 등록</h2>
		<div class="container-ts" style="margin-top: 20px;">
		<form action="insert?pNum=${sessionScope.sessionProjectNum}"
			method="post" name="insfrm">
			<b>태그등록 :</b> <input type="text" name="hashtag" data-role="tagsinput"
				placeholder="Add tags" /> <br />
			<h3>발생한 문제</h3>
			<textarea id="summernote" name="problem"></textarea>
			<h3>해결/조치 방법</h3>
			<textarea id="summernote2" name="solution"></textarea>
			<div> 
				<input type="submit" class="btn btn-primary" value="작성" style="margin-right: 15px"> 
				<input type="button" class="btn btn-danger" value="취소" OnClick="history.back()">
			</div>
			<input type="hidden" name="projectNum"
				value="${sessionScope.sessionProjectNum}" />
			<input type="hidden" name="userId"
				value="${pageContext.request.userPrincipal.name}" />
		</form>
	
		</div>
</div>
