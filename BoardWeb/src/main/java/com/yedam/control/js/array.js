/**
 * array.js 
 * pysh, unshift 추가.
 * pop, shift 제거.
 * splice 추가,수정,삭제
 * forEach()
 */
const ary = [];
// pushm unshift
ary.push('최길동');
ary.push('홍길동'); //마지막위치
ary.push('김길동');
ary.unshift('최길동');

//ary.pop(); //삭제
//ary.shift(); //삭제.
 
ary.splice(0, 1); // 첫번째, 크기, 대체값.

ary.forEach(function(item, idx, ary) {
	console.log(`item=>${item}, index=>${idx} array=> ${ary}`);
});

// 함수.
function addElement(elem = "noElem") {
	ary.push(elem);
}


// 화면요소 제거.
function deleteElement(e) {
	//alert('삭제버튼클릭됨.');
	console.log(e.target.parentElement);
	e.target.parentElement.remove();
}


// 추가버튼에 클릭이벤트.
document.querySelector('button#addBtn')//
	.addEventListener('click',function(){
	// input의 값.
		let val = document.querySelector('input#userInput').value;
		addElement(val); // ary배열에 추가.
		// 화면에 출력.
		let html = '';
		ary.forEach(function(item, idx, ary){
			html += '<li>' + item + '<button onclick="deleteElement(event)">삭제</button></li>';
		});
		// ul태그의 영역.
		document.querySelector('ul#list').innerHTML = html;
	});
