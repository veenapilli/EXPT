var productAnswerType = "products";
var greetingAnswerType = "greeting";

piqChat();
function piqChat(){
			setupViewCss();
	
	console.log("in index.js");
	$("#chatbot").append("<div class='jumbotron'><h3>In conversation with Fashion Bot</h3></div>");
	

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
			setupQuestion(item.questionComponent);
			setupAnswer(item.answerComponent.answer);
		});
	}else{
		alert( "No History available" );
	}
});
};

function setupQuestion(questionData){
	if(null != questionData &&
		null != questionData.question){
		$("#chatbot").append("<div class='question_message'>" + questionData.question + "</div>")
}
};

function setupAnswer(answerData){
	if(null != answerData && null != answerData.type){
		if(answerData.type === productAnswerType && 
			null != answerData.data){
			if(answerData.data.length > 0){
				answerData.data.forEach(function(item){
					$("#chatbot").append("<div class='answer_data'>" + item.title + "; " + "</div>")
				});
			}else{
				$("#chatbot").append("<div class='answer_empty'>" + answerData.message + "</div>")
			}
		}else if(answerData.type === greetingAnswerType && 
			null != answerData.message){
			$("#chatbot").append("<div class='answer_greeting'>" + answerData.message + "</div>")
		}

	}
}

function setupViewCss(){
	$("#jumbotron").css({"text-align":"center", "margin":"2% 20% 2% 20%", "padding":"20px", "border":"2px"});
	$("#question_message").css({"float":"right"}); 
	$("#answer_data").css({"float":"left"});  
	$("#answer_empty").css({"float":"left"});  
	$("#answer_greeting").css({"float":"left"});  

}