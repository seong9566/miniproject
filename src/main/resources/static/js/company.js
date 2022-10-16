
 function setThumbnail(event) {
        let reader = new FileReader();
        
        reader.onload = function(event) {
            if(document.getElementById("newImg")){
                document.getElementById("newImg").remove();
            }
            let img = document.createElement("img");
            img.setAttribute("src", event.target.result);
            img.setAttribute("id", "newImg");
            document.querySelector("#imageContainer").appendChild(img);
        };
        reader.readAsDataURL(event.target.files[0]);
    }


//체크박스 
$('#etc').click(function() {
	var checked = $('#etc').is(':checked');
	if (checked)
		$('input:checkbox').prop('checked', true);
});


//주소불러오기
function findAddr() {
	new daum.Postcode(
		{
			oncomplete: function(data) {
				console.log(data);
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var roadAddr = data.roadAddress; // 도로명 주소 변수
				var jibunAddr = data.jibunAddress; // 지번 주소 변수
				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('post').value = data.zonecode;
				if (roadAddr !== '') {
					document.getElementById("addr").value = roadAddr;
				} else if (jibunAddr !== '') {
					document.getElementById("addr").value = jibunAddr;
				}
			}
		}).open();
}


// 회사 가입 버튼 클릭 시
function join() {
	let formData = new FormData();
	
	let data = {
		loginId: $("#userId").val(),
		loginPassword: $("#password").val(),
		companyName: $("#username").val(),
		companyEmail: $("#email").val(),
		companyPhoneNumber: $("#phonenumber").val(),
		companyAddress: $("#post").val() + "," + $("#addr").val() + "," + $("#detail_address").val(),
	};
	
	console.log(data);
	
	formData.append('fileUpload',$("#fileUpload")[0].files[0]);
	formData.append('joinDto',new Blob([JSON.stringify(data)],{type: "application/json"}));
	
	$.ajax("/join/company", {
		type: "POST",
		data: formData,
		processData: false,
		contentType: false,
		enctype : 'multipart/form-data'
	}).done((res) => {
		if(res.code == 1){
			alert(res.message);
			location.href ="/main";
		}
	});
}




