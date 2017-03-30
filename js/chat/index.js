var productAnswerType = "products";
var greetingAnswerType = "greeting";
var emptyAnswer = "No Products Available";
var url = 'http://54.208.118.138:10024/api/v1/getHistory/?userId=user1&timeStamp=2017-03-24T07:36:23.529Z';
var question_url = 'http://54.208.118.138:10024/api/v1/ask/';

/*------------------------------------- Loaders -------------------------------------*/

loadCSS = function(href) {
	var cssLink = $("<link rel='stylesheet' type='text/css' href='"+href+"'>");
	$("head").append(cssLink); 
};

loadJS = function(src) {
	var jsLink = $("<script type='text/javascript' src='"+src+"'>");
	$("head").append(jsLink); 
};
/*------------------------------------- Screen Rendering -------------------------------------*/

piqChat();


/*------------------------------------- High level UI creation -------------------------------------*/

function piqChat(){
	loadCSS("./main.css");
	loadCSS("//cdn.jsdelivr.net/jquery.slick/1.6.0/slick.css");
	loadJS("https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js");
	loadJS("//cdn.jsdelivr.net/jquery.slick/1.6.0/slick.min.js");


	setupChatArea();
	setupTypingArea();
	fetchHistory();

};

/*------------------------------------- UI helpers -------------------------------------*/

function setupQuestion(questionData){
	if(null != questionData &&
		null != questionData.question){
		addQuestion(questionData.question);
}
};

function setupAnswer(answerData){
	if(null != answerData && null != answerData.type){
		if(answerData.type === productAnswerType && 
			null != answerData.data){
			if(answerData.data.length > 0){
				addAnswerData(answerData.data);
			}else{
				addEmptyAnswer(emptyAnswer);
			}
		}else if(answerData.type === greetingAnswerType && 
			null != answerData.message){
			addAnswerMessage(answerData.message);
		}

	}
};

function sendMessage(){
	var user_question = $("#typed_message").val();
	if(user_question.length > 0){
		addQuestion(user_question);
	}
	fetchAnswerFromServer(user_question);
};


/*------------------------------------- Data helpers -------------------------------------*/

function getPostJsonForQuestion(question){
	var json = {};
	json["question"] = question;
	json["vendor"] = "abof";
	json["token"] = "66fe96f8dd7011e6bf82542696d17e7d";
	json["gender"] = "women";
	json["userId"] = "user1";
	json["timestamp"] = "2017-03-24T07:36:23.529Z";
	json["page"] = "0";
	return json;
}



/*------------------------------------- Network Calls -------------------------------------*/
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

function fetchAnswerFromServer(user_question){
	var jsonData = getPostJsonForQuestion(user_question);
	$.ajax({
		type: 'post',
		url: question_url,
		data: JSON.stringify(jsonData),
		contentType: "application/json; charset=utf-8",
		traditional: true,
		success:  function( jsonResData) {
			var successFlag = jsonResData.status;
			if(successFlag === "success") {
				alert( "Answer loaded." );
				setupAnswer(jsonResData.response);
			} else{
				alert( "No Answer" );
			}
		} 
	});
};

/*------------------------------------- UI rendering helpers -------------------------------------*/


function setupChatArea(){
	$("#chatbot").append("<div class='jumbotron'>In conversation with Fashion Bot</div>");
	$("#chatbot").append("<div class='ques_answ_area' id='ques_answ_area'></div>");
	$("#chatbot").append("<div class='typing_area' id='typing_area'></div>");
};
function setupTypingArea(){
	$("#typing_area").append("<input type='text' id='typed_message'/>");
	$("#typing_area").append("<img id='send_button' onClick='sendMessage()' src='./assets/send.png' height='30px' width='30px'/>")
};

function addQuestion(data){
	$("#ques_answ_area").append("<div class='question_message'>" + data + "</div>");

};
function addAnswerData(data){
	$('.ques_answ_area').slick({
		slidesToShow: 3,
		slidesToScroll: 3
	});
	answerData.data.forEach(function(item){

		$("#ques_answ_area").slick('slickAdd','<div><h3>' + item.title + '</h3></div>');
	});
};

function addEmptyAnswer(data){
	$("#ques_answ_area").append("<div class='answer_empty'>" + data + "</div>");

};
function addAnswerMessage(data){
	$("#ques_answ_area").append("<div class='answer_greeting'>" + data + "</div>");
};
