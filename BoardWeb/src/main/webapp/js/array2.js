const numArr = [10, 17, 23, 26, 49];
let filterArr = numArr.filter(function (item, idx, arr) {
  return item % 2 == 0;
});
console.log(filterArr);

const jsonStr = `[{"id":1,"first_name":"Genevieve","last_name":"David","email":"gdavid0@phoca.cz","gender":"Female","salary":3541},
{"id":2,"first_name":"George","last_name":"Storks","email":"gstorks1@upenn.edu","gender":"Female","salary":5772},
{"id":3,"first_name":"Kim","last_name":"Waples","email":"kwaples2@comcast.net","gender":"Male","salary":2943},
{"id":4,"first_name":"Raynard","last_name":"Baccus","email":"rbaccus3@pinterest.com","gender":"Male","salary":7078},
{"id":5,"first_name":"Jaimie","last_name":"Congreve","email":"jcongreve4@smugmug.com","gender":"Male","salary":8044},
{"id":6,"first_name":"Jeffie","last_name":"Mulryan","email":"jmulryan5@virginia.edu","gender":"Male","salary":8242},
{"id":7,"first_name":"Alasteir","last_name":"Mourant","email":"amourant6@nytimes.com","gender":"Male","salary":5561},
{"id":8,"first_name":"Natalina","last_name":"Trevett","email":"ntrevett7@bravesites.com","gender":"Female","salary":7895},
{"id":9,"first_name":"Kym","last_name":"Capenor","email":"kcapenor8@imageshack.us","gender":"Female","salary":5604},
{"id":10,"first_name":"Nelia","last_name":"Fellows","email":"nfellows9@techcrunch.com","gender":"Bigender","salary":9068},
{"id":11,"first_name":"Ethe","last_name":"Sanderson","email":"esandersona@ustream.tv","gender":"Male","salary":1563},
{"id":12,"first_name":"Daloris","last_name":"Batiste","email":"dbatisteb@infoseek.co.jp","gender":"Female","salary":6115},
{"id":13,"first_name":"Deloris","last_name":"Spearett","email":"dspearettc@hc360.com","gender":"Female","salary":7417},
{"id":14,"first_name":"Drew","last_name":"Robroe","email":"drobroed@instagram.com","gender":"Male","salary":6973},
{"id":15,"first_name":"Maximilian","last_name":"Stocken","email":"mstockene@ca.gov","gender":"Male","salary":2397}]`;
const jsonArr = JSON.parse(jsonStr);

filterArr = jsonArr.filter(item => item.salary >= 5000 && item.gender == 'Female');
console.log(filterArr);

console.clear();
let arr = [10, 23, 34, 48, 51].reduce(function (acc, item, idx, ary) {
  console.log(acc, item);
  if (item > 30) {
    acc.push(item);
  }
  return acc;
}, []);
console.log(arr);
console.clear();

let list = document.querySelector('#list');
[10, 23, 34, 48, 51].reduce((acc, item) => {
  let li = document.createElement('li');
  li.innerHTML = item;
  list.appendChild(li);
  // return acc;
}, list);

const objArr = [
  {
    name: "홍길동",
    phone: "010-1111-2222"
  },
  {
    name: "최길동",
    phone: "010-1111-3333"
  },
  {
    name: "박길동",
    phone: "010-1111-4444"
  },
  {
    name: "김길동",
    phone: "010-1111-5555"
  }
]

list.innerHTML = "";
objArr.reduce((acc, item) => {
  let li = document.createElement('li');
  li.innerHTML = `${item.name} | ${item.phone}`;
  list.appendChild(li);
}, list);