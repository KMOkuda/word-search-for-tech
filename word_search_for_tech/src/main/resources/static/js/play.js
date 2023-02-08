var CvsPoint = class {

	constructor(x, y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param {var} x
	 */
	set setX(x) {
		this.x = x;
	}

	/**
	 * @param {var} y
	 */
	set setY(y) {
		this.y = y;
	}

	get getX() {
		/* get構文 */
		return this.x
	}
	get getY() {
		/* get構文 */
		return this.y
	}
}

/**
 * @param {var} index
 */

function eraseLetter(index){
	var delElm = document.querySelector('[data-index="' + index + '"]');
	delElm.classList.add("deleted");
}

/**
 *
 * @param {list} list
 */
function refleshFont(list) {
	list.forEach((elm) => {
		elm.classList.remove("selected");
	})
}


/**
 * @param {var} id
 */
function calcDrawPoint(id) {
	var elm = document.querySelectorAll(`[data-id="${id}"]`)[0];

	var left = elm.offsetLeft;
	var top = elm.offsetTop;
	var width = elm.getBoundingClientRect().width;
	var height = elm.getBoundingClientRect().height;

	var drawX = left + (width / 2);
	var drawY = top + (height / 2);

	return new CvsPoint(drawX, drawY);
}

/**
 * @param {var} context
 * @param {var} fromId
 * @param {var} toId
 * @param {boolean} overWrite
 */
function draw(context, fromId, toId, overWrite) {
	var fromCvsPoint = calcDrawPoint(fromId);
	var toCvsPoint = calcDrawPoint(toId);

	var fromHeight = calcHeight(fromId);
	var fromWidth = calcWidth(fromId);
	var toHeight = calcHeight(toId);
	var toWidth = calcWidth(toId);

	var currentHeight = fromHeight;
	var currentWidth = fromWidth;
	var currentId = fromId;

	var dx = (fromWidth == toWidth) ? 0 : (fromWidth < toWidth) ? 1 : -1;
	var dy = (fromHeight == toHeight) ? 0 : (fromHeight < toHeight) ? 1 : -1;

	if (overWrite) {
		refleshFont(tmpSelectList);
	}

	while(true) {
		var target = document.querySelector('[data-id="' + currentId + '"]');
		target.classList.add("selected");

		if(!overWrite){
			target.classList.add("protected");
		}

		if (overWrite && !target.classList.contains("protected")) {
			tmpSelectList.push(target);
		}
		
		if(currentId == toId){
			break;
		}

		currentWidth += dx;
		currentHeight += dy;

		currentId = calcId(currentHeight, currentWidth);
	} 

	if (overWrite == true) {
		console.log("AAA");
		context.clearRect(0, 0, topCanvasElm.width, topCanvasElm.height);
	}

	context.fillStyle = '#aaa';
	context.strokeStyle = '#aaa';
	context.beginPath();
	context.lineCap = "round";

	context.moveTo(fromCvsPoint.getX, fromCvsPoint.getY);
	context.lineTo(toCvsPoint.getX, toCvsPoint.getY);


	context.strokeStyle = "red";
	context.lineWidth = 30;
	context.strokeStyle = 'rgb(255,0,255)';
	context.stroke();
}

/**
 *
 * @param {var} height
 * @param {var} width
 */
function calcId(height, width) {
	return height * boardWidth + width;
}


/**
 *
 * @param {var} id
 */
function calcHeight(id) {
	return Math.floor(id / boardWidth);
}

/**
 *
 * @param {var} id
 */
function calcWidth(id) {
	return id % boardWidth;
}

function isDraggable(fromId, toId) {
	if (fromId == null || toId == null) {
		return false;
	}
	
	var elmFrom = document.querySelector('[data-id="' + fromId + '"]');
	var elmTo = document.querySelector('[data-id="' + toId + '"]');

	if(elmFrom.classList.contains("protected") || elmTo.classList.contains("protected")){
		return false;
	}


	var fromHeight = calcHeight(fromId);
	var fromWidth = calcWidth(fromId);
	var toHeight = calcHeight(toId);
	var toWidth = calcWidth(toId);

	if (fromHeight == toHeight || fromWidth == toWidth
		|| Math.abs(fromHeight - toHeight) / Math.abs(fromWidth - toWidth) == 1) {
		return true;
	} else {
		return false;
	}

}


const topCanvasElm = document.getElementsByClassName("top-canvas")[0];
topCanvasElm.width = topCanvasElm.offsetWidth;
topCanvasElm.height = topCanvasElm.offsetHeight;
const topContext = topCanvasElm.getContext("2d");

const bottomCanvasElm = document.getElementsByClassName("bottom-canvas")[0];
bottomCanvasElm.width = bottomCanvasElm.offsetWidth;
bottomCanvasElm.height = bottomCanvasElm.offsetHeight;
const bottomContext = bottomCanvasElm.getContext("2d");

var fromId = null;
var toId = null;
var tmpSelectList = [];

var xhr = new window.XMLHttpRequest();

function startDraw(e) {

	e.preventDefault();

	console.log("started");

	var destX = e.touches[0].pageX;
	var destY = e.touches[0].pageY;

	var elmFrom = document.elementFromPoint(destX, destY);

	if (elmFrom.dataset.id != null) {
		toId = fromId = elmFrom.dataset.id;
	}

	if (elmFrom.classList.contains('letter') && isDraggable(fromId, toId)) {
		draw(topContext, fromId, toId, true);
	}

}

function drawing(e) {

	e.preventDefault();

	console.log("drawing");

	var destX = e.changedTouches[0].pageX;
	var destY = e.changedTouches[0].pageY;

	var elmTo = document.elementsFromPoint(destX, destY)[0];

	if (elmTo.dataset.id != null) {
		toId = elmTo.dataset.id;
	}
	

	if (elmTo.classList.contains('letter') && isDraggable(fromId, toId)) {
		draw(topContext, fromId, toId, true);
	}
}

function endDraw(e) {
	console.log("ended");

	refleshFont(tmpSelectList);
	topContext.clearRect(0, 0, topCanvasElm.width, topCanvasElm.height);

	if (!isDraggable(fromId, toId)) {
		return;
	}


	let header = $("meta[name='_csrf_header']").attr("content");
	let token = $("meta[name='_csrf']").attr("content");

	var data = [
		{
			"playId": playId,
			"fromId": fromId,
			"toId": toId
		}
	];

	var dataJSON = JSON.stringify(data);

	xhr.open('POST', '/ws-answer', true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader('Content-Type', 'application/json');

	xhr.send(dataJSON);

	fromId = toId = null;
}

var letters = document.getElementsByClassName('letter');

for (var i = 0; i < letters.length; i++) {
	letters[i].ontouchstart = startDraw;
	letters[i].ontouchmove = drawing;
	letters[i].ontouchend = endDraw;
}

answerStatus.forEach((elm) => {
	if (elm.hasAnswer == true) {
		draw(bottomContext, elm.fromId, elm.toId, false);
		eraseLetter(elm.index);
	}

});

xhr.onload = function(){        //レスポンスを受け取った時の処理（非同期）
    var res = xhr.responseText;
	var parse_data = JSON.parse(res);
	var answerStatus = parse_data.responseAnswerStatus;

    console.log(parse_data);
	if(answerStatus.hasAnswer == true){
		draw(bottomContext, answerStatus.fromId, answerStatus.toId, false);
		tmpSelectList = [];
		eraseLetter(answerStatus.index);
	}

	if(parse_data.hasCleared == true){
		screenLock();
		var elm = document.getElementById("clear");
		elm.style.display = "block";
	}
};

xhr.onerror = function(){       //エラーが起きた時の処理（非同期）
    alert("error!");
}

function screenLock(){
	var element = document.createElement('div');
	element.id = "screenLock";

	element.style.height = '100%';
	element.style.left = '0px';
	element.style.position = 'absolute';
	element.style.top = '0px';
	element.style.width = '100%';
	element.style.zIndex = '10';
	element.style.opacity = '0.9';
	element.style.backgroundColor = "white";

	var objBody = document.getElementById("m-inner");
	objBody.appendChild(element);
  }

  /**
   * ScreenUnLook
   */
  function screenUnLock(){
	var screenLock = document.getElementById("screenLock");
	screenLock.parentNode.removeChild(screenLock);
  }