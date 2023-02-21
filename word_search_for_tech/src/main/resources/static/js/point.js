window.onload = function () {

	var now = new Date();

	var Year = now.getFullYear();
	var Month = now.getMonth() + 1;
	var date = now.getDate();
	var Hour = now.getHours();
	var Min = now.getMinutes();

	console.log("onload");
	let cookies = document.cookie;
	let array = cookies.split(';');
	let point = 0;
	let access = 1;

	let hasCookie = false;

	array.forEach(function (value) {

		let content = value.split('=');

		if (content[0].trim() == "point") {

			console.log(content[1]); // valueを取得
			point = content[1];
			hasCookie = true;
		}

		if (content[0].trim() == "access") {

			console.log(content[1]); // valueを取得
			if ((Year == 2023 && Month == 2 && date == 22 && Hour == 10 && Min >= 15 && Min <= 29)) {
				access = Number(content[1]) + 1;

			} else {
				access = Number(content[1]);
			}
			hasCookie = true;
		}

	})

	if (!hasCookie) {
		document.cookie = "point=" + 0 + "; path=/;";
		document.cookie = "warning=please-do-not-cheat."
		document.cookie = "access=" + access + "; path=/;";
	}

	document.getElementById("point").innerHTML = point;
	document.getElementById("access").innerHTML = access;
}