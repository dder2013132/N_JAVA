/**
 * 
 *

fetch('replyList.do?bno=' + bno)
    .then(result => result.json())
    .then(result => result.forEach(item => makerow2(item)))
    .catch(err => console.error(err));
  */

let successCallback = function successCallback(result) {
    result.forEach(item => {
        makerow2(item);
    })
}

let errorCallback = function errorCallback(err) {
    console.error(err);
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
                makerow2(item);
                document.querySelector("#reply").value= "";
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
                document.querySelector('#rno_' + rno).remove();
            }
            else {
                alert("삭제 실패했어..〒▽〒");
            }
        },
        errorCallback);
}

// 댓글 목록 보여주기 용
svc.replyList(bno, successCallback, errorCallback);

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