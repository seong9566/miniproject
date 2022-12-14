<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ include file="../layout/header.jsp" %>


		<div id="main">
			<br /> <input id="resume_title" type="text" class="form-control" placeholder="이력서 제목을 입력해주세요.">
			<div class="tool">
				<div class="left">
					<br />

					<h3>개인정보 입력</h3>
					<div class="left_input">
						<div id="userId">sopu5555</div>
						<br />
						<div id="userName">박동훈</div>
						<br />
						<div id="phonenumber">01024102957</div>
						<br />
						<div id="email">sop55544@gmail.com</div>
						<input type="hidden" id="resumesId" value="1">
					</div>
				</div>
				<div class="right">
					<img id="previewImg" />
				</div>
			</div>

			<input type="file" Id="fileUpload" accept='image/*' /> <br /> <br />
			<div class="form">
				<h2>자기소개서 작성</h2>
				<div id="summernote"></div>
			</div>
			<div class="btn-update">
				<c:choose>
					<c:when test="${ empty principal }">
						<button type="button" class="btn btn-primary">로그인</button>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${ empty personalLike}">
								<button id="btnlike" type="button" class="btn btn-primary">좋아요</button>
							</c:when>
							<c:otherwise>
								<button id="btnlike" type="button" class="btn btn-primary">좋아요취소</button>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<button id="btnapply" type="button" class="btn btn-primary">연락하기</button>
			</div>


		</div>
		
		<%@ include file="../layout/footer.jsp" %>