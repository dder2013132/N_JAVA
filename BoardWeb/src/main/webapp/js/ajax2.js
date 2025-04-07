/**
 * 
 */

const xhtp = new XMLHttpRequest();
xhtp.open('get', 'data/MOCK_DATA.json'); //경로지정.
xhtp.send();
xhtp.onload = function(){ // load이벤트 발생.
    let obj = JSON.parse(xhtp.responseText);
    console.log(obj);
    //화면출력.
    obj.forEach(function(item, idx, ary){
        let tr= makeRow(item);
        document.querySelector('tbody#target').appendChild(tr);
    })
}
