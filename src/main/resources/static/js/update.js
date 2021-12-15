// (1) 회원정보 수정
	
function update(userId){
	
	let data = $("#profileUpdate").serialize();
	console.log(data);
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json"
	}).done(res=>{
		console.log("성공");
		location.href=`/user/${userId}`
	}).fail(error=>{
		console.log("실패");
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
	
