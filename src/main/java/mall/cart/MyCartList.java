package mall.cart;

import java.util.HashMap;
import java.util.Map;

public class MyCartList {
	
	//key=상품번호  value=주문수량
	private Map<Integer,Integer> orderlists = null;
	
	public MyCartList() {
		orderlists = new HashMap<Integer, Integer>();
	}

	public void addOrder(int pnum, int oqty) {
	
		if(orderlists.containsKey(pnum) == false) {  //새상품이면
			
			orderlists.put(pnum, oqty);
			
		}
		else { //이미 있는 상품이면
			
			int oldoqty = orderlists.get(pnum); //기존에 담겨있던 수량에 새로 입력햇 넘어오는 수량을 더한다
			orderlists.put(pnum, oldoqty+oqty);
			
		}
		
	}

	public Map<Integer,Integer> getAllOrderLists(){
		return orderlists;
	}

	
}
