/**
 * 
 */

const svc = {
    name: "홍길동",
    replyList: function(bno, successCallback, errorCallback) {
        fetch('replyList.do?bno=' + bno)
            .then(result => result.json())
            .then(successCallback)
            .catch(errorCallback);
    },
    removeReply(rno, successCallback, errorCallback) {
        fetch('removeReply.do?rno=' + rno)
            .then(result => result.json())
            .then(successCallback)
            .catch(errorCallback);
    },
    addReply(rvo = { bno, reply, replyer }, successCallback, errorCallback) {
        fetch('addReply.do?bno=' + rvo.bno + '&reply=' + rvo.reply + '&replyer=' + rvo.replyer)
            .then(result => result.json())
            .then(successCallback)
            .catch(errorCallback);
    }
}