var Point = class {

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

const Directions = {
	NONE: 0,
	RIGHT: 1,
	DOWN: 2,
	LEFT: 3,
	UP: 4,
	TOPRIGHT: 5,
	DOWNRIGHT: 6,
	DOWNLEFT: 7,
	TOPLEFT: 8
};


/**
 *
 * @param {var} id
 */
function calcHeight(id){
    return Math.floor(id / boardWidth);
}

/**
 *
 * @param {var} id
 */
function calcWidth(id){
    return id % boardWidth;
}

/**
 *
 * @param {var} fromId
 * @param {var} toId
 */
function calcShortDict(fromId, toId) {
	console.log("fromId, toId" + fromId, toId);



	if (fromId + 1 == toId) {
		return Directions.RIGHT;
	}

	if (fromId - 1 == toId) {
		return Directions.LEFT;
	}

	if (fromId + boardWidth == toId) {
		return Directions.DOWN;
	}

	if (fromId - boardWidth == toId) {
		return Directions.UP;
	}

	if (fromId + boardWidth + 1 == toId) {
		return Directions.DOWNRIGHT;
	}

	if (fromId + boardWidth - 1 == toId) {
		return Directions.DOWNLEFT;
	}

	if (fromId - boardWidth + 1 == toId) {
		return Directions.TOPRIGHT;
	}

	if (fromId - boardWidth - 1 == toId) {
		return Directions.TOPLEFT;
	}

	return Directions.NONE;
}

function isDraggable(fromId, toId) {
    if(fromId == null || toId == null){
        return false;
    }

    var fromHeight = calcHeight(fromId);
    var fromWidth = calcWidth(fromId);
    var toHeight = calcHeight(toId);
    var toWidth = calcWidth(toId);

    if(fromHeight == toHeight || fromWidth == toWidth
        || Math.abs(fromHeight - toHeight) / Math.abs(fromWidth - toWidth) == 1){
            return true;
    } else{
        return false;
    }

}


const canvasElm = document.getElementsByClassName("board-canvas")[0];
canvasElm.width = document.getElementsByClassName("board-content")[0].offsetWidth;
canvasElm.height = document.getElementsByClassName("board-content")[0].offsetHeight;

var context = canvasElm.getContext("2d");

const canvasTop = canvasElm.getBoundingClientRect().top;
const canvasLeft = canvasElm.getBoundingClientRect().left;

var fromId = null;
var toId = null;
var drawFrom = new Point(null, null);
var drawTo = new Point(null, null);
var dict = Directions.NONE;
var isFixed = false;

var xhr = new window.XMLHttpRequest();

function startDraw(e) {

	e.preventDefault();

	console.log("started");

	var destX = e.touches[0].pageX;
	var destY = e.touches[0].pageY;

	var elmFrom = document.elementFromPoint(destX, destY);

    if(elmFrom.dataset.id != null){
	    fromId = elmFrom.dataset.id;
    }

	var left = elmFrom.offsetLeft;
	var top = elmFrom.offsetTop;
	var width = elmFrom.getBoundingClientRect().width;
	var height = elmFrom.getBoundingClientRect().height;



	var drawFromX = offsetCenterLeft = left + (width / 2);
	var drawFromY = offsetCenterTop = top + (height / 2);

	drawFrom.setX = drawFromX;
	drawFrom.setY = drawFromY;
	drawTo.setX = drawFromX;
	drawTo.setY = drawFromY;

	console.log(left + " " + top);
}

function draw(e) {


	e.preventDefault();

	console.log("drawing");


	var destX = e.changedTouches[0].pageX;
	var destY = e.changedTouches[0].pageY;

	var elmTo = document.elementsFromPoint(destX, destY)[0];

    if(elmTo.dataset.id != null){
	    toId = elmTo.dataset.id;
    }


	if (elmTo.className == 'letter' && isDraggable(fromId, toId)) {
		console.log("fromId:" + fromId + " toId" + toId);
		var left = elmTo.offsetLeft;
		var top = elmTo.offsetTop;
		var width = elmTo.getBoundingClientRect().width;
		var height = elmTo.getBoundingClientRect().height;

		var drawToX = left + (width / 2);
		var drawToY = top + (height / 2);

		drawTo.setX = drawToX;
		drawTo.setY = drawToY;


        context.clearRect(0, 0, canvasElm.width, canvasElm.height);
		context.fillStyle = '#aaa';
		context.strokeStyle = '#aaa';
		context.beginPath();
		context.lineCap = "round";

		context.moveTo(drawFrom.getX, drawFrom.getY);
		context.lineTo(drawTo.getX, drawTo.getY);

		context.strokeStyle = "red";
		context.lineWidth = 30;
		context.strokeStyle = 'rgb(255,0,255)';
		context.stroke();

	}
}

function endDraw(e) {
	console.log("ended");

    context.clearRect(0, 0, canvasElm.width, canvasElm.height);

    if(playId == null || fromId == null){
        return;
    }


    let header = $("meta[name='_csrf_header']").attr("content");
    let token = $("meta[name='_csrf']").attr("content");

    var data = [
        { "playId" : playId,
          "fromId" : fromId,
          "toId" : toId
        }
      ];

    var dataJSON = JSON.stringify(data);

    xhr.open('POST', '/ws-answer', true);
    xhr.setRequestHeader(header, token);
    xhr.setRequestHeader('Content-Type', 'application/json');

    console.log(header);
    console.log(token);

    xhr.send(dataJSON);

    fromId = toId = null;
}

var letters = document.getElementsByClassName('letter');

for (var i = 0; i < letters.length; i++) {
	console.log("i = " + i);
	letters[i].ontouchstart = startDraw;
	letters[i].ontouchmove = draw;
	letters[i].ontouchend = endDraw;
}

xhr.onload = function(){        //レスポンスを受け取った時の処理（非同期）
    var res = xhr.responseText;
    console.log(res.length);
};
xhr.onerror = function(){       //エラーが起きた時の処理（非同期）
    alert("error!");
}

