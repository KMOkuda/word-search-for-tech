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

function drawLineThrough(index) {
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
 * 
 * 
 * ユーザーが線を引く時の他、途中でゲームがロードされた時にも呼び出す。
 * 引かれた線が正解だった時や途中ロードの時など、
 * 消されない線を描きたい場合は
 * 最後の引数をfalseにして呼び出す事。
 * 
 * @param {var} context
 * @param {var} fromId
 * @param {var} toId
 * @param {boolean} erasable
 * 
 * 
 */
function draw(context, fromId, toId, erasable) {
	let fromCvsPoint = calcDrawPoint(fromId);
	let toCvsPoint = calcDrawPoint(toId);

	//移動方向の計算に使用する変数
	let fromX = calcX(fromId);
	let fromY = calcY(fromId);
	let toX = calcX(toId);
	let toY = calcY(toId);

	let tmpId = fromId;

	//縦横の移動方向
	let dx = (fromX == toX) ? 0 : (fromX < toX) ? 1 : -1;
	let dy = (fromY == toY) ? 0 : (fromY < toY) ? 1 : -1;

	const colors = ["#ffc107", "#0d6efd", "#d63384", "#6610f2", "#fd7e14", "#198754", "#0dcaf0"];

	//白くなっている文字を一旦消す
	if (erasable) {
		refleshFont(tmpSelectList);
	}

	while (true) {
		let target = document.querySelector('[data-id="' + tmpId + '"]');

		//文字を白くする
		target.classList.add("selected");

		if (!erasable) {
			target.classList.add("protected");
		}

		if (erasable && !target.classList.contains("protected")) {
			tmpSelectList.push(target);
		}

		if (tmpId == toId) {
			break;
		} else {
			tmpId = calcId(calcX(tmpId) + dx, calcY(tmpId) + dy);
		}
	}

	//今書いているものを一旦消す
	if (erasable == true) {
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
 * @param {var} x
 * @param {var} y
 */
function calcId(x, y) {
	return y * boardWidth + x;
}


/**
 *
 * @param {var} id
 */
function calcY(id) {
	return Math.floor(id / boardWidth);
}

/**
 *
 * @param {var} id
 */
function calcX(id) {
	return id % boardWidth;
}

function isDraggable(fromId, toId) {

	if (fromId == null || toId == null) {
		return false;
	}

	let fromY = calcY(fromId);
	let fromX = calcX(fromId);
	let toY = calcY(toId);
	let toX = calcX(toId);

	let dx = (toX - fromX) == 0 ? 0 : fromX < toX ? 1 : -1;
	let dy = (toY - fromY) == 0 ? 0 : fromY < toY ? 1 : -1;

	//横か縦か45度の斜めの場合は、選択が確定している要素を突き抜けなければ引ける
	if (fromY == toY || fromX == toX
		|| Math.abs(fromY - toY) / Math.abs(fromX - toX) == 1) {

		let tmpId = fromId;

		while (true) {

			let target = document.querySelector('[data-id="' + tmpId + '"]');

			if (target.classList.contains("protected")) {
				return false;
			}

			if (tmpId == toId) {
				return true;
			} else {
				tmpId = calcId(calcX(calcX(tmpId)) + dx, calcY(tmpId) + dy);
			}
		}

		return true;

	} else {//それ以外の斜めなどは引けない
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

	if (elmTo.classList.contains('letter') && isDraggable(fromId, newToId)) {
		toId = newToId;
		draw(topContext, fromId, toId, true);
	}
}

function endDraw(e) {

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

xhr.onload = function () {
	
	let cookies = document.cookie;
	let array = cookies.split(';');
	console.log(cookies);

	var now = new Date();
	console.log(now);

	var Year = now.getFullYear();
	var Month = now.getMonth() + 1;
	var date = now.getDate();
	var Hour = now.getHours();
	var Min = now.getMinutes();


	//レスポンスを受け取った時の処理（非同期）
	let res = xhr.responseText;
	let parse_data = JSON.parse(res);
	let answerStatus = parse_data.responseAnswerStatus;

	console.log(parse_data);



	if (Year == 2023 && Month == 2 && date == 22 && Hour == 10 && Min >= 15 && Min <= 29) {


		console.log("access    ");
		array.forEach(function (value) {

			let content = value.split('=');

			if (content[0].trim() == "access") {
				console.log("access  trim  ");
				access = content[1];
				access++;
				document.cookie = "access=" + access + "; path=/";

				document.getElementById("access").innerHTML = access;
			}

		})
	}



	if (answerStatus.answerFlg == true) {


		if (Year == 2023 && Month == 2 && date == 22 && Hour == 10 && Min >= 15 && Min <= 29) {

			array.forEach(function (value) {

				let content = value.split('=');

				if (content[0].trim() == "point") {
					console.log("aaa")
					point = content[1];
					point++;
					if (parse_data.hasCleared == true) {
						point += 4;
					}
					document.cookie = "point=" + point + "; path=/";

					document.getElementById("point").innerHTML = point;
				}

			})
		}

		//固定レイヤーで描く（最後の引数）
		draw(bottomContext, answerStatus.fromId, answerStatus.toId, false);
		tmpSelectList = [];

		//消し込み線を書く
		drawLineThrough(answerStatus.orderIndex);
		protectedCount++;
	} else {
		//今選択している状態を外す
		refleshFont(tmpSelectList);
		topContext.clearRect(0, 0, topCanvasElm.width, topCanvasElm.height);

	}

	if (parse_data.hasCleared == true) {
		screenLock();
		let elm = document.getElementById("clear");
		elm.style.display = "block";
	}
};

xhr.onerror = function () {       //エラーが起きた時の処理（非同期）
	alert("通信エラーが発生しました。");
}

function screenLock() {　//クリア画面表示時に使用
	let element = document.createElement('div');
	element.id = "screen-lock";

	let objBody = document.getElementById("m-inner");
	objBody.appendChild(element);
}

/**
 * まだ使ってない。
 */
function screenUnLock() {
	let screenLock = document.getElementById("screenLock");
	screenLock.parentNode.removeChild(screenLock);
}