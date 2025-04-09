/**
 *  board2.js
 */
new DataTable('#replytable', {
    ajax: 'replyListDatatable.do?bno=' + bno,
    columns: [
        { data: 'REPLY_NO' },
        { data: 'REPLY' },
        { data: 'REPLYER' },
        { data: 'REPLY_DATE' }
    ],
    lengthMenu: [
        [5, 10, 25, 50, -1],
        [5, 10, 25, 50, 'All']
    ],
    order: [[3, 'desc']]
});

function addNewRow() {
    let replyT = document.querySelector('#replytext').value;
    fetch('addReply.do?bno=' + bno + '&replyer=' + replyer + '&reply=' + replyT)
        .then(result => result.json())
        .then(result => {
            table.row.add({
                REPLY_NO: result.retVal.replyNo,
                REPLY: result.retVal.reply,
                REPLYER: result.retVal.replyer,
                REPLY_DATE: result.retVal.replyDate
            }).draw(false);
        })
        .catch(err => console.error(err));
    document.querySelector('#replytext').value = "";


    counter++;
}

const table = new DataTable('#replytable');
let counter = 1;

document.querySelector('#addRow').addEventListener('click', addNewRow);

table.on('click', 'tbody tr', (e) => {
    console.log(e);
    let classList = e.currentTarget.classList;

    if (classList.contains('selected')) {
        classList.remove('selected');
    }
    else {
        table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
        classList.add('selected');
    }
});


document.querySelector('#removereply').addEventListener('click', function() {
    console.log(document.querySelector('.selected .dt-type-numeric').innerHTML);
    let rno = document.querySelector('.selected .dt-type-numeric').innerHTML;
    fetch('removeReply.do?rno=' + rno)
        .then(function(result) {
            console.log(result);
            if (result.retCode == 'OK') {
                alert("삭제했어!! \(￣︶￣*\)) ");
            }
        })
        .catch(err => console.error(err));
    table.row('.selected').remove().draw(false);
});

// Automatically add a first row of data
// addNewRow();