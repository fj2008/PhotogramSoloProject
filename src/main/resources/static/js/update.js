// (1) 회원정보 수정
	
function update(userId,event){
	event.preventDefault();//폼태크 액션방지
	let data = $("#profileUpdate").serialize();
	console.log(data);
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json"
	}).done(res=>{//HttpStatue 상태코드 200번대
		console.log("성공",res);
		location.href=`/user/${userId}`
	}).fail(error=>{//HTTPStatus 상태코드 200번대가 아닐때
		console.log("실패",error);
		if(error.data == null){
			alert(error.responseJSON.message);
		}else{
			alert(JSON.stringify(error.responseJSON.data.name));
		}
		
	});
	}

//	console.log(userId)
//	event.preventDefault();
//	let data = $("#profileUpdate").serialize();
//	console.log(data);
//	let response = await fetch("/api/user/"+userId,{
//		method:"put",
//		body:data,
//		headers:{}
//	});
//	
//	let parseResponse = await response.text();
//	console.log(parseResponse);
//	
//	if(parseResponse ===1){
//		alert("회원정보 수정이 완료 됐습니다.")
//		location.href="/user/"+userId;
//	}
	
