var productAnswerType = "products";
var greetingAnswerType = "greeting";
var emptyAnswer = "No Products Available";
var url = 'http://54.208.118.138:10024/api/v1/getHistory/?userId=user1&timeStamp=2017-03-24T07:36:23.529Z';

 loadCSS = function(href) {
     var cssLink = $("<link rel='stylesheet' type='text/css' href='"+href+"'>");
     $("head").append(cssLink); 
 };
piqChat();
function piqChat(){
	loadCSS("./main.css");
	console.log("in index.js");

	$("#chatbot").append("<div class='jumbotron'>In conversation with Fashion Bot</div>");
	$("#chatbot").append("<div class='ques_answ_area' id='ques_answ_area'></div>");
	$("#chatbot").append("<div class='typing_area' id='typing_area'></div>");
	setupTypingArea();
	fetchHistory();

};

function setupTypingArea(){
	$("#typing_area").append("<input type='text' id='typed_message'/>");
	$("#typing_area").append("<img id='send_button' src='./assets/send.png' height='30px' width='30px'/>")
};
function fetchHistory(){
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
		$("#ques_answ_area").append("<div class='question_message'>" + questionData.question + "</div>");
}
};

function setupAnswer(answerData){
	if(null != answerData && null != answerData.type){
		if(answerData.type === productAnswerType && 
			null != answerData.data){
			if(answerData.data.length > 0){
				answerData.data.forEach(function(item){
					$("#ques_answ_area").append("<div class='answer_data'>" + item.title + "; " + "</div>");
				});
			}else{
				$("#ques_answ_area").append("<div class='answer_empty'>" + emptyAnswer + "</div>");
			}
		}else if(answerData.type === greetingAnswerType && 
			null != answerData.message){
			$("#ques_answ_area").append("<div class='answer_greeting'>" + answerData.message + "</div>");
		}

	}
};
