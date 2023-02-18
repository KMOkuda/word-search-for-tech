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

function drawLineThrough(index){
	let delElm = document.querySelector('[data-index="' + index + '"]');
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
	let elm = document.querySelectorAll(`[data-id="${id}"]`)[0];

	let left = elm.offsetLeft;
	let top = elm.offsetTop;
	let width = elm.getBoundingClientRect().width;
	let height = elm.getBoundingClientRect().height;

	let drawX = left + (width / 2);
	let drawY = top + (height / 2);

	return new CvsPoint(drawX, drawY);
}

/**
 * @param {var} context
 * @param {var} fromId
 * @param {var} toId
 * @param {boolean} overWrite
 * 
 * ユーザーが線を引く時の他、途中でゲームがロードされた時にも呼び出す。
 * 引かれた線が正解だった時や途中ロードの時など、
 * 消されない線を描きたい場合は
 * 最後の引数をfalseにして呼び出す事。
 * 
 */
function draw(context, fromId, toId, overWrite) {
	let fromCvsPoint = calcDrawPoint(fromId);
	let toCvsPoint = calcDrawPoint(toId);

	let fromHeight = calcHeight(fromId);
	let fromWidth = calcWidth(fromId);
	let toHeight = calcHeight(toId);
	let toWidth = calcWidth(toId);

	let currentHeight = fromHeight;
	let currentWidth = fromWidth;
	let currentId = fromId;


	//縦横の移動距離
	let dx = (fromWidth == toWidth) ? 0 : (fromWidth < toWidth) ? 1 : -1;
	let dy = (fromHeight == toHeight) ? 0 : (fromHeight < toHeight) ? 1 : -1;
	
	const colors = ["green", "purple", "blue"]

	//白くなっている文字を一旦消す
	if (overWrite) {
		refleshFont(tmpSelectList);
	}

	while(true) {
		let target = document.querySelector('[data-id="' + currentId + '"]');

		//文字を白くする
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

		//次のマスのIDと縦横を計算
		currentWidth += dx;
		currentHeight += dy;
		currentId = calcId(currentHeight, currentWidth);
	}

	//今書いているものを一旦消す
	if (overWrite == true) {
		context.clearRect(0, 0, topCanvasElm.width, topCanvasElm.height);
	}

	context.fillStyle = '#aaa';
	context.strokeStyle = '#aaa';
	context.beginPath();
	context.lineCap = "round";

	context.moveTo(fromCvsPoint.getX, fromCvsPoint.getY);
	context.lineTo(toCvsPoint.getX, toCvsPoint.getY);


	context.strokeStyle = colors[protectedCount % colors.length];
	context.lineWidth = 30;
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

	let elmFrom = document.querySelector('[data-id="' + fromId + '"]');
	let elmTo = document.querySelector('[data-id="' + toId + '"]');

	let fromHeight = calcHeight(fromId);
	let fromWidth = calcWidth(fromId);
	let toHeight = calcHeight(toId);
	let toWidth = calcWidth(toId);

	if (fromId == null || toId == null) {
		return false;
	}

	if(elmFrom.classList.contains("protected") || elmTo.classList.contains("protected")){
		return false;
	}

	if (fromHeight == toHeight || fromWidth == toWidth
		|| Math.abs(fromHeight - toHeight) / Math.abs(fromWidth - toWidth) == 1) {
		return true;
	} else {
		return false;
	}

}



//canvasのheightとwidthは自動的に設定されない模様。

//固定レイヤー。選択を確定させる時はこちらを使う。
const topCanvasElm = document.getElementsByClassName("top-canvas")[0];
const topContext = topCanvasElm.getContext("2d");

topCanvasElm.width = topCanvasElm.offsetWidth;
topCanvasElm.height = topCanvasElm.offsetHeight;


//一時レイヤー。ユーザーが線を引いている間はこちらを使う。
const bottomCanvasElm = document.getElementsByClassName("bottom-canvas")[0];
const bottomContext = bottomCanvasElm.getContext("2d");

bottomCanvasElm.width = bottomCanvasElm.offsetWidth;
bottomCanvasElm.height = bottomCanvasElm.offsetHeight;


var fromId = null;
var toId = null;

//ユーザーが線を引いている時、文字を白くするために使う。
var tmpSelectList = [];

var xhr = new window.XMLHttpRequest();

function startDraw(e) {

	e.preventDefault();

	console.log("started");

	let destX = e.touches[0].pageX;
	let destY = e.touches[0].pageY;

	//文字が選択出来ていなければnullになる
	let elmFrom = document.elementFromPoint(destX, destY);

	//各文字にはdata-idが振り分けられている
	toId = fromId = elmFrom.dataset.id;
	

	//idを取得出来ても各文字部分のクラスを取得出来ていない場合があったので回避
	if (elmFrom.classList.contains('letter') && isDraggable(fromId, toId)) {
		draw(topContext, fromId, toId, true);
	}

}

function drawing(e) {

	e.preventDefault();

	console.log("drawing");

	let destX = e.changedTouches[0].pageX;
	let destY = e.changedTouches[0].pageY;

	let elmTo = document.elementsFromPoint(destX, destY)[0];

	let newToId = elmTo.dataset.id;

	if (elmTo.classList.contains('letter') && elmTo.dataset.id != null && isDraggable(fromId, newToId)) {
		toId = newToId;
		draw(topContext, fromId, toId, true);
	}
}

function endDraw(e) {
	console.log("ended");

	if (!isDraggable(fromId, toId)) {
		return;
	}


	let header = $("meta[name='_csrf_header']").attr("content");
	let token = $("meta[name='_csrf']").attr("content");

	let data = [
		{
			"playId": playId,
			"fromId": fromId,
			"toId": toId
		}
	];
	
	console.log(data);

	let dataJSON = JSON.stringify(data);

	xhr.open('POST', '/ws-answer', true);
	xhr.setRequestHeader(header, token);
	xhr.setRequestHeader('Content-Type', 'application/json');

	xhr.send(dataJSON);

	fromId = toId = null;
}

//一つ一つのアルファベット文字要素
var letters = document.getElementsByClassName('letter');

//カラーパレット用に選択済みの数を格納
var protectedCount = 0;

for (var i = 0; i < letters.length; i++) {
	letters[i].ontouchstart = startDraw;
	letters[i].ontouchmove = drawing;
	letters[i].ontouchend = endDraw;
}

answerStatus.forEach((elm) => {
	if (elm.answerFlg == true) {

		//固定レイヤーで描く（最後の引数）
		draw(bottomContext, elm.fromId, elm.toId, false);
		drawLineThrough(elm.orderIndex);
		protectedCount++;
	}

});

xhr.onload = function(){        //レスポンスを受け取った時の処理（非同期）
    let res = xhr.responseText;
	let parse_data = JSON.parse(res);
	let answerStatus = parse_data.responseAnswerStatus;

    console.log(parse_data);
	if(answerStatus.answerFlg == true){
		//固定レイヤーで描く（最後の引数）
		draw(bottomContext, answerStatus.fromId, answerStatus.toId, false);
		tmpSelectList = [];

		//消し込み線を書く
		drawLineThrough(answerStatus.orderIndex);
		protectedCount++;
	}else{
		//今選択している状態を外す
		refleshFont(tmpSelectList);
		topContext.clearRect(0, 0, topCanvasElm.width, topCanvasElm.height);

	}

	if(parse_data.hasCleared == true){
		screenLock();
		let elm = document.getElementById("clear");
		elm.style.display = "block";
	}
};

xhr.onerror = function(){       //エラーが起きた時の処理（非同期）
    alert("error!");
}

function screenLock(){　//クリア画面表示時に使用
	let element = document.createElement('div');
	element.id = "screen-lock";

	let objBody = document.getElementById("m-inner");
	objBody.appendChild(element);
  }

  /**
   * まだ使ってない。
   */
  function screenUnLock(){
	let screenLock = document.getElementById("screenLock");
	screenLock.parentNode.removeChild(screenLock);
  }