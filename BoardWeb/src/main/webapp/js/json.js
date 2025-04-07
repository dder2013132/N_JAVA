/**
 * json.js
 */

const jsonStr = `[{"id":1,"first_name":"Genevieve","last_name":"David","email":"gdavid0@phoca.cz","gender":"Female","ip_address":3541},
{"id":2,"first_name":"George","last_name":"Storks","email":"gstorks1@upenn.edu","gender":"Female","ip_address":5772},
{"id":3,"first_name":"Kim","last_name":"Waples","email":"kwaples2@comcast.net","gender":"Male","ip_address":2943},
{"id":4,"first_name":"Raynard","last_name":"Baccus","email":"rbaccus3@pinterest.com","gender":"Male","ip_address":7078},
{"id":5,"first_name":"Jaimie","last_name":"Congreve","email":"jcongreve4@smugmug.com","gender":"Male","ip_address":8044},
{"id":6,"first_name":"Jeffie","last_name":"Mulryan","email":"jmulryan5@virginia.edu","gender":"Male","ip_address":8242},
{"id":7,"first_name":"Alasteir","last_name":"Mourant","email":"amourant6@nytimes.com","gender":"Male","ip_address":5561},
{"id":8,"first_name":"Natalina","last_name":"Trevett","email":"ntrevett7@bravesites.com","gender":"Female","ip_address":7895},
{"id":9,"first_name":"Kym","last_name":"Capenor","email":"kcapenor8@imageshack.us","gender":"Female","ip_address":5604},
{"id":10,"first_name":"Nelia","last_name":"Fellows","email":"nfellows9@techcrunch.com","gender":"Bigender","ip_address":9068},
{"id":11,"first_name":"Ethe","last_name":"Sanderson","email":"esandersona@ustream.tv","gender":"Male","ip_address":1563},
{"id":12,"first_name":"Daloris","last_name":"Batiste","email":"dbatisteb@infoseek.co.jp","gender":"Female","ip_address":6115},
{"id":13,"first_name":"Deloris","last_name":"Spearett","email":"dspearettc@hc360.com","gender":"Female","ip_address":7417},
{"id":14,"first_name":"Drew","last_name":"Robroe","email":"drobroed@instagram.com","gender":"Male","ip_address":6973},
{"id":15,"first_name":"Maximilian","last_name":"Stocken","email":"mstockene@ca.gov","gender":"Male","ip_address":2397}]`;

let obj = JSON.parse(jsonStr); // json문자열 -> object 변경.
let str = JSON.stringify(obj); // object -> json문자열 변경.
console.log(obj[2].last_name);

// 한건 데이터를 매개값으로 tr을 생성하는 함수.
function makeRow(emp = {id, first_name, last_name, email, gender, salary}) {
	const fields = ['id', 'first_name', 'last_name', 'email'];
	let tr = document.createElement('tr'); //<tr></tr>
	for (let i= 0; i< fields.length; i++) {
		let td = document.createElement('td'); // <td></td>
		td.innerHTML = emp[fields[i]]; // <td>1</td>
		tr.appendChild(td); //<tr><td>1</td><td>Matthieu</td></tr>
	}
	return tr;
}

//화면출력.
obj.forEach(function(item, idx, ary){
	let tr= makeRow(item);
	document.querySelector('tbody#target').appendChild(tr);
})
