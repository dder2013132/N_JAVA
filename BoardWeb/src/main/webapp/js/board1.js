/**
 * 
 *

fetch('replyList.do?bno=' + bno)
    .then(result => result.json())
    .then(result => result.forEach(item => makerow2(item)))
    .catch(err => console.error(err));
  */
let page = 1;
let startPage, endPage;
let prev, next;
let totalCnt;
let realEnd;

let successCallback = function successCallback(result) {
    document.querySelectorAll('div.reply div.content>ul>li:not(.not-erase-reply)').forEach(e => {e.remove();});
    result.forEach(item => {
        makerow2(item);
    })
}

let errorCallback = function errorCallback(err) {
    console.error(err);
}

let pagingCallback = function(result) {
    totalCnt = result.totalCnt;
    
    endPage = Math.ceil(page / 10) * 10;
    startPage = endPage - 9;
    realEnd = Math.ceil(totalCnt / 5);
    endPage = (endPage > realEnd) ? realEnd : endPage;
    prev = startPage == 1 ? false : true;
    next = endPage < realEnd ? true : false;
    
    let target = document.querySelector('nav>ul.pagination');
    target.innerHTML = "";
    let prevL = prev ? '"><a class="page-link" id="page-reply-prev" href="#">Previous</a>' : ' disabled"><span class="page-link">Previous</span>';
    target.insertAdjacentHTML('beforeend', `<li class="page-item${prevL}</li>`);
    for (let p = startPage; p <= endPage; p++) {
        let html = "";
        if (page == p) {
            html = `<li class="page-item active" id="page-reply_${p}"><span class="page-link">${p}</span></li>`;
        }
        else {
            html = `<li class="page-item" id="page-reply_${p}"><a class="page-link" href="#">${p}</a></li>`;
        }
        target.insertAdjacentHTML('beforeend', html);
    }
    let nextL = next ? '"><a class="page-link" id="page-reply-next" href="#">Next</a>' : ' disabled"><span class="page-link">Next</span>';
    target.insertAdjacentHTML('beforeend', `<li class="page-item${nextL}</li>`);
    pageLink();
}

// 이벤트
document.querySelector('button.addReply').addEventListener('click', function(e) {
    if (replyer == "") {
        alert('댓글을 달려면 로그인을 해야해!! （＞人＜；）');
        return;
    }
    let reply = document.querySelector("#reply").value;
    if (reply == "") {
        alert('댓글을 입력해줘!! ~(>_<。)＼');
        return;
    }

    svc.addReply(
        { bno, reply, replyer },
        function(result) {
            console.log(result);
            if (result.retCode == 'OK') {
                alert("등록했어!! \(￣︶￣*\)) ");
                let item = result.retVal;
                // makerow2(item);
                console.log(item + '\n 등록');
                document.querySelector("#reply").value= "";
                page = 1;
                svc.replyList({ bno, page }, successCallback, errorCallback);
                svc.pagingList(bno, pagingCallback, errorCallback);
            }
            else {
                alert("등록 실패했어..〒▽〒");
            }
        },
        errorCallback
    );
});

function deleteFnc(rno) {
    let deleteOK = confirm("진짜 삭제할꺼야? (⩌⩊⩌)");
    if (!deleteOK) {
        return;
    }
    svc.removeReply(rno,
        function(result) {
            console.log(result);
            if (result.retCode == 'OK') {
                alert("삭제했어!! \(￣︶￣*\)) ");
                svc.replyList({ bno, page }, successCallback, errorCallback);
                svc.pagingList(bno, pagingCallback, errorCallback);
                // document.querySelector('#rno_' + rno).remove();
            }
            else {
                alert("삭제 실패했어..〒▽〒");
            }
        },
        errorCallback);
}


function makerow2(item) {
    let html = `<li data-rno='${item.replyNo}' id='rno_${item.replyNo}'>
                  <span class="col-sm-2">${item.replyNo}</span>
                  <span class="col-sm-2">${item.reply}</span>
                  <span class="col-sm-2">${item.replyer}</span>
                  <span class="col-sm-2"><button type='button' onclick='deleteFnc(${item.replyNo})'>삭제</button></span>
                </li>`;
    let templ = document.querySelector('div.content>ul');
    templ.insertAdjacentHTML('beforeend', html);
}

// 페이지 링크에 이벤트 등록
function pageLink() {
    document.querySelectorAll('div.reply ul a').forEach(function(atag) {
        atag.addEventListener('click', function(e) {
            e.preventDefault(); // 기본 이벤트 차단
            
            if (atag.innerHTML == 'Next') {
                page = startPage + 10;
                svc.pagingList(bno, pagingCallback, errorCallback);
            }
            else if (atag.innerHTML == 'Previous') {
                page = startPage - 10;
                svc.pagingList(bno, pagingCallback, errorCallback);
            }
            else {
                page = atag.innerHTML;
                svc.pagingList(bno, pagingCallback, errorCallback);
            }
            
            svc.replyList({ bno, page }, successCallback, errorCallback);
        })
    });
}

// 댓글 목록 보여주기 용
svc.replyList({ bno, page }, successCallback, errorCallback);

// 페이징 목록
svc.pagingList(bno, pagingCallback, errorCallback);

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