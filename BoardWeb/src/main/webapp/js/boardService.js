/**
 * 
 */

const svc = {
    name: "홍길동",
    replyList: function(search = { bno, page }, successCallback, errorCallback) {
        fetch('replyList.do?bno=' + search.bno + '&page=' + search.page)
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
    },
    pagingList(bno, successCallback, errorCallback) {
        fetch('replyCount.do?bno=' + bno)
            .then(result => result.json())
            .then(successCallback)
            .catch(errorCallback);
    }
}