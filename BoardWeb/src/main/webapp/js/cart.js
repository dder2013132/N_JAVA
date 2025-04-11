/**
 * 
 */

let basket = {
  // changePNum: function(n, id) {
  //   let inputbox = document.querySelector("#p_num" + id);
  //   inputbox.value = parseInt(inputbox.value) + n;
  //   let basketCnt = parseInt(inputbox.value);

  //   let price = document.querySelector("#p_price" + id);
  //   let sumPrice = parseInt(price.value) * basketCnt;

  //   let sum = price.parentElement.parentElement.querySelector('.sum');
  //   sum.innerHTML = `${sumPrice.toLocaleString()}원`;
    
  //   this.updateAllBasketCount();
  //   // console.log(typeof basketCnt);
  // },
  changePNum: function(n, e) {
    let parent = e.parentElement.parentElement.parentElement;
    let inputbox = parent.querySelector(".p_num");
    inputbox.value = parseInt(inputbox.value) + n;
    let basketCnt = parseInt(inputbox.value);

    let price = parent.querySelector(".p_price");
    let sumPrice = parseInt(price.value) * basketCnt;

    let sum = price.parentElement.parentElement.querySelector('.sum');
    sum.innerHTML = `${sumPrice.toLocaleString()}원`;
    
    this.updateAllBasketCount();
    // console.log(typeof basketCnt);
  },
  updateAllBasketCount: function() {
    let basketList = document.querySelectorAll(".row.data");
    let total = [ 0, 0 ];
    basketList.forEach((item, idx) => {
      let isChecked = item.querySelector(".check input").checked;
      console.log(isChecked);
      if (isChecked) {
        let basketCnt = parseInt(item.querySelector(".p_num").value);
        let basketPrice = parseInt(item.querySelector(".p_price").value) * basketCnt;
        // console.log(basketPrice);
        total = [basketCnt + total[0], basketPrice + total[1]];
      }
    });

    // console.log(total);

    document.querySelector("#sum_p_num").innerHTML = `상품갯수: <span>${total[0].toLocaleString()}</span>개`;
    document.querySelector("#sum_p_price").innerHTML = `합계금액: <span>${total[1].toLocaleString()}</span>원`;
  },
  delAllItem: function() {
    document.querySelectorAll(".row.data").forEach((item) => {
      item.remove();
    });
    basket.updateAllBasketCount();
  },
  delCheckedItem: function() {
    document.querySelectorAll(".row.data").forEach((item, idx) => {
      let isChecked = item.querySelector(".check input").checked;
      if (isChecked) {
        item.remove();
      }
    });
    basket.updateAllBasketCount();
  },
  delItem: function(e) {
    // console.log(e.target);
    e.target.parentElement.parentElement.parentElement.remove();
    basket.updateAllBasketCount();
  },
  buyItem: function() {
    alert("주문 완료 (⩌⩊⩌)");
  }
}

document.querySelectorAll(".row.data").forEach((item, idx) => {
  item.querySelector(".check input").addEventListener('change', function() {
    basket.updateAllBasketCount();
  });
});

basket.updateAllBasketCount();

