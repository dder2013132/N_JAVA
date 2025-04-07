/**
 * 
 */

const svc = {
    name: "홍길동",
    replyList: function(bno, successCallback, errorCallback) {
        fetch('replyList.do?bno=' + bno)
            .then(result => result.json())
            .then(result => result.forEach(item => makerow2(item)))
            .catch(err => console.error(err));
    }
}