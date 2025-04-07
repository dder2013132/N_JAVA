/**
 * 
 *

fetch('replyList.do?bno=' + bno)
    .then(result => result.json())
    .then(result => result.forEach(item => makerow2(item)))
    .catch(err => console.error(err));
  */  
    
let successCallback = function successCallback(result) {
    result.forEach(result);
}

let errorCallback = function errorCallback(err) {
    console.error(err);
}
 
svc.replyList(bno, successCallback, errorCallback);
   
function makerow2(item) {
    let html = `<li>
                  <span class="col-sm-2">${item.replyNo}</span>
                  <span class="col-sm-2">${item.reply}</span>
                  <span class="col-sm-2">${item.replyer}</span>
                  <span class="col-sm-2"><button type='button'>삭제</button></span>
                </li>`;
    let templ = document.querySelector('div.content>ul>li');
    templ.insertAdjacentHTML('beforeend', html);
}

/*
setTimeout(function() {
    console.log(1);
    setTimeout(function() {
        console.log(2);
        setTimeout(function() {
            console.log(3);
        }, 1000);
    }, 1000);
}, 1000);

setTimeout(function() {
    console.log(2);
}, 1000);

setTimeout(function() {
    console.log(3);
}, 1000);
*/