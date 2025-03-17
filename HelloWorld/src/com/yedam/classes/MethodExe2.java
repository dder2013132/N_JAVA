package com.yedam.classes;

public class MethodExe2 {

	private Product[] store; // 필드.

	// 생성자.
	MethodExe2() {
		store = new Product[10];
		store[0] = new Product("A001", "지우개", 500);
		store[1] = new Product("B001", "샤프1000", 1000);
		store[2] = new Product("C001", "연필500", 800);
		store[3] = new Product("D001", "지우개", 1000);
	}

	// 메소드.
	boolean add(Product prd) {
		for (int i = 0; i < store.length; i++) {
			if (store[i] == null) {
				store[i] = prd;
				return true;
			}
		}
		return false;
	} // end of add(Product prd)

	// 상품이름, ALL
	Product[] productList(Product prd) {
		Product[] list = new Product[10];
		int idx = 0;
		for (int i = 0; i < store.length; i++) {
			if (store[i] != null) {
				if (prd.getProductName().equals("ALL") //
						|| store[i].getProductName()//
								.equals(prd.getProductName())) {
					// 상품가격이 조건으로 추가됨.
					if (store[i].getPrice() >= prd.getPrice()) {
						list[idx++] = store[i];
					}
				}
			}
		}
		return list;
	} // end of productList.

	// 삭제 => boolean remove(String code)
	boolean remove(String code) {
		for (int i = 0; i < store.length; i++) {
			if (store[i] != null && store[i].getProductCode().equals(code)) {
				store[i] = null;
				return true;
			}
		}
		return false;
	} // end of remove.

	// 수정 => boolean modify(Product prod)
	boolean modify(Product prod) {
		for (int i = 0; i < store.length; i++) {
			if (store[i] != null //
					&& store[i].getProductCode().equals(prod.getProductCode())) {
				// 상품명수정.
				if (prod.getProductName() != null) {
					store[i].setProductName(prod.getProductName());
				}
				// 상품가격수정.
				if (prod.getPrice() != 0) {
					store[i].setPrice(prod.getPrice());
				}
				return true;
			}
		}
		return false;
	} // end of modify.
}
