/**
 * 
 */
console.log(bno);
const xhtp = new XMLHttpRequest();
xhtp.open('get', 'replyList.do?bno=' + bno);
xhtp.send();
xhtp.onload = function() {
    console.log(xhtp.responseText);
    let data = JSON.parse(xhtp.responseText);
    console.log(data);
    data.forEach((item) => {
        makerow2(item);
    });
}

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

function makerow1(item) {
    let templ = document.querySelector('div.content>ul>li').cloneNode(true);
    // console.log(templ);
    templ.querySelector('span:nth-of-type(1)').innerHTML = item["replyNo"];
    templ.querySelector('span:nth-of-type(2)').innerHTML = item["reply"];
    templ.querySelector('span:nth-of-type(3)').innerHTML = item["replyer"];
    templ.querySelector('span:nth-of-type(4)').innerHTML = "<button type='button'>삭제</button>";

    document.querySelector("div.content>ul").appendChild(templ);
}