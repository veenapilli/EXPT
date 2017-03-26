piqChat();
function piqChat(){
	console.log("in index.js");
	$("#chatbot").append("<div class='jumbotron'><h3>In conversation with Fashion Bot</h3></div>")
	.css({"text-align":"center", "margin":"2% 20% 2% 20%", "border":"2px"});
	
	var url = 'http://54.208.118.138:10024/api/v1/getHistory/?userId=user1&timeStamp=2017-03-24T07:36:23.529Z';

	$.getJSON( url, function( jsonResData ) {
		var successFlag = jsonResData.status;
		if(successFlag === "success") {
			alert( "History was loaded." );
		} 
		if(null != jsonResData.response && 
			null != jsonResData.response.data &&
			jsonResData.response.data.length > 0) {
			alert( "History Available" );	
		jsonResData.response.data.forEach(function(item) {
			if(null != item.questionComponent){
				$("#chatbot").append(JSON.stringify(item.questionComponent)); 
			}
			if(null != item.answerComponent){
				$("#chatbot").append(JSON.stringify(item.answerComponent)); 

			}
		});
	}else{
		alert( "No History available" );
	}
});
}