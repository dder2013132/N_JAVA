/**
 * ajax.js 
 */

let obj;
const xhtp = new XMLHttpRequest();
xhtp.open('get', 'data/MOCK_DATA.json'); //경로지정.
xhtp.send();
xhtp.onload = function() { // load이벤트 발생.

    obj = JSON.parse(xhtp.responseText);
    console.log(obj);
    
    //화면출력.
    obj.forEach(function(item, idx, ary) {
        let tr = makeRow(item);
        document.querySelector('tbody#target').appendChild(tr);
    });
}

// console.log('Update!!!@@!!!');

// 한건 데이터를 매개값으로 tr을 생성하는 함수.
function makeRow(emp = { id, first_name, last_name, email, gender, salary }) {
    const fields = ['id', 'first_name', 'last_name', 'gender'];
    let tr = document.createElement('tr'); //<tr></tr>

    // checkbox 생성.
    let tdd = document.createElement('td')
    let chk = document.createElement('input')
    chk.setAttribute('type', 'checkbox');

    // 부모-자식
    tdd.appendChild(chk);
    tr.appendChild(tdd);

    // td 생성.
    for (let i = 0; i < fields.length; i++) {
        let td = document.createElement('td'); // <td></td>
        td.innerHTML = emp[fields[i]]; // <td>1</td>
        tr.appendChild(td); //<tr><td>1</td><td>Matthieu</td></tr>
    }
    // button생성.
    let td = document.createElement('td');
    let btn = document.createElement('button');
    btn.setAttribute('class', 'btn btn-danger');
    btn.innerText = '삭제';
    btn.addEventListener('click', deleteRow)
    // 부모 - 자식
    td.appendChild(btn);
    tr.appendChild(td);
    return tr;
}

// btn의 이벤트핸들러.
function deleteRow(e) {
    // console.log(e.target);
    e.target.parentElement.parentElement.remove();
}

// 체크박스 이벤트
document.querySelector('#headCheck').addEventListener('change', (e) => {
    let checkboxes = document.querySelectorAll('tbody#target input[type="checkbox"]');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = e.target.checked;
    });
});

document.querySelector("#searchGender").addEventListener('change', (e) => {
    let gender = document.querySelector("#searchGender").value;
    document.querySelector('tbody#target').innerHTML = "";
    obj.forEach(function(item, idx, ary) {
        if (gender == 'all' || item['gender'] == gender) {
            let tr = makeRow(item);
            document.querySelector('tbody#target').appendChild(tr);
        }
    })
});
