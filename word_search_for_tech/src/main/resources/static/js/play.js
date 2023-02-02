				const canvasElm = document.getElementsByClassName( "board-canvas" )[0];
				//const canvasTop = canvasElm.offsetTop;
				//const canvasLeft = canvasElm.offsetLeft;
				//const canvasTop = window.pageYOffset;
				//const canvasLeft = window.pageXOffset;
				
				const canvasTop = canvasElm.getBoundingClientRect().top; 
				const canvasLeft = canvasElm.getBoundingClientRect().left; 
				
				
				console.log("canvasTop: " + canvasTop);
				console.log("canvasLeft: " + canvasLeft);

				var drawFromY;
				var drawFromX;
				var drawToY;
				var drawToX;

				//'use strict';
				

				function startDraw(e) {
					
					e.preventDefault();
					
					console.log("started");

					var originX = e.touches[0].pageX;
					var originY = e.touches[0].pageY;
					
					var elmFrom = document.elementFromPoint(originX, originY);
					
					var left = elmFrom.offsetLeft;
					var top = elmFrom.offsetTop;
					var width = elmFrom.clientWidth;
					var height = elmFrom.clientHeight;

					drawFromX = drawToX = offsetCenterLeft = left + (width / 2) - canvasLeft;
					drawFromY = drawToY = offsetCenterTop = top + (height / 2) - canvasTop;
					
					console.log(offsetCenterLeft + " " + offsetCenterTop);
				}

				function draw(e) {
					
					e.preventDefault();
					
					
					
					console.log("drawing");
					
					var destX = e.changedTouches[0].pageX;
					var destY = e.changedTouches[0].pageY;
					
					var elmFrom = document.elementFromPoint(destX, destY);
					
					var left = elmFrom.clientX;
					var top = elmFrom.clientY;
					var width = elmFrom.clientWidth;
					var height = elmFrom.clientHeight;

					drawToX = left + (width / 2) - canvasLeft ;
					drawToY = top + (height / 2) - canvasTop;
					
					console.log(drawFromX + " " + drawFromY);
					console.log(drawToX + " " + drawToY);
					console.log(width + " : width");
					console.log(height + " : height");
					
					var element = document.getElementsByClassName( "board-canvas" )[0];
					console.log(element);
					var context = element.getContext( "2d" ) ;
					
					console.log(context);
					
					
					context.fillStyle = '#aaa';
					context.strokeStyle = '#aaa';
					context.beginPath() ;
					
					context.moveTo( drawFromX, drawFromY );
					context.lineTo( drawToX, drawToY );
					
					context.strokeStyle = "red" ;
					context.lineWidth = 10 ;
					context.strokeStyle = 'rgb(255,0,255)';
					context.stroke() ;
					
					
				}

				function endDraw(e) {
					console.log("ended");


				}

				var letters = document.getElementsByClassName('letter');

				for (var i = 0; i < letters.length; i++) {
					console.log("i = " + i);
					letters[i].ontouchstart = startDraw;
					letters[i].ontouchmove = draw;
					letters[i].ontouchend = endDraw;
				}

				/**
					var from;
					var to;


					$('.letter').on('touchstart', function() {

						console.log("touchstart");

						from = to = $(this).data('id');

						var context = $('canvas')[0].getContext('2d');
						//ここに具体的な描画内容を指定する
						//新しいパスを開始する
						context.beginPath();
						//パスの開始座標を指定する

						var left = $(this).offset().left;
						var top = $(this).offset().top;
						var width = $(this).width();
						var height = $(this).height();

						offsetCenterLeft = left + (width / 2);
						offsetCenterTop = top + (height / 2);

						context.moveTo(offsetCenterLeft, offsetCenterTop);

						console.log(from);
					});



					$('.letter').on('touchmove', function() {
						console.log("touchmove");

						to = $(this).data('id');
						console.log(to);

						var context = $('canvas')[0].getContext('2d');
						//ここに具体的な描画内容を指定する
						//新しいパスを開始する

						var left = $(this).offset().left;
						var top = $(this).offset().top;
						var width = $(this).width();
						var height = $(this).height();

						offsetCenterLeft = left + (width / 2);
						offsetCenterTop = top + (height / 2);

						context.fillStyle = '#ccc';
						context.strokeStyle = 'red';

						context.lineTo(offsetCenterLeft, offsetCenterTop);
						context.stroke();
					});

					$('.letter').on('touchend', function() {
				/*
						console.log("touchend");
				 */

				/**
				 var context = $('canvas')[0].getContext('2d');
				 //ここに具体的な描画内容を指定する
				 //新しいパスを開始する

				 var left = $(this).offset().left;
				 var top = $(this).offset().top;
				 var width = $(this).width();
				 var height = $(this).height();

				 offsetCenterLeft = left + (width / 2);
				 offsetCenterTop = top + (height / 2);

				 context.fillStyle = '#ccc';
				 context.strokeStyle = 'red';
				 /**
				 console.log(offsetCenterLeft + " " + offsetCenterTop);
				 **/

				/**
				 context.lineTo(offsetCenterLeft, offsetCenterTop);
				 context.stroke();
				 /**
				 console.log("from : " + from.attr('data-id') + " to : " + to.attr('data-id'));
				 **/
				/*	});*/