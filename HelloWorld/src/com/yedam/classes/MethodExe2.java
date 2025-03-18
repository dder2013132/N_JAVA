package com.yedam.classes;

import java.util.ArrayList;
import java.util.List;

public class MethodExe2 {

	private List<Product> store; // 필드.

	// 생성자.
	MethodExe2() {
		store = new ArrayList<Product>();
		store.add(new Product("A001", "지우개", 500));
		store.add(new Product("B001", "샤프1000", 1000));
		store.add(new Product("C001", "연필500", 800));
		store.add(new Product("D001", "지우개", 1000));
	}

	// 메소드.
	boolean add(Product prd) {
		boolean result = store.add(prd);
		return result;
	} // end of add(Product prd)

	// 상품이름, ALL
	List<Product> productList(Product prd) {
		List<Product> list = new ArrayList<Product>();
		int idx = 0;
		for (int i = 0; i < store.size(); i++) {
			if (prd.getProductName().equals("ALL") //
						|| store.get(i).getProductName()
							.equals(prd.getProductName())){
					// 상품가격이 조건으로 추가됨.
					if (store.get(i).getPrice() >= prd.getPrice()) {
						list.add(store.get(i));
					}
				}
			}
		
		return list;
	} // end of productList.

	// 삭제 => boolean remove(String code)
	boolean remove(String code) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getProductCode().equals(code)) {
				return true;
			}
		}
		return false;
	} // end of remove.

	// 수정 => boolean modify(Product prod)
	boolean modify(Product prod) {
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getProductCode().equals(prod.getProductCode())) {
				// 상품명수정.
				if (prod.getProductName() != null) {
					store.get(i).setProductName(prod.getProductName());
				}
				// 상품가격수정.
				if (prod.getPrice() != 0) {
					store.get(i).setPrice(prod.getPrice());
				}
				return true;
			}
		}
		return false;
	} // end of modify.
}
