package mall.cart;

import java.util.HashMap;
import java.util.Map;

public class MyCartList {
	
	//key=��ǰ��ȣ  value=�ֹ�����
	private Map<Integer,Integer> orderlists = null;
	
	public MyCartList() {
		orderlists = new HashMap<Integer, Integer>();
	}

	public void addOrder(int pnum, int oqty) {
	
		if(orderlists.containsKey(pnum) == false) {  //����ǰ�̸�
			
			orderlists.put(pnum, oqty);
			
		}
		else { //�̹� �ִ� ��ǰ�̸�
			
			int oldoqty = orderlists.get(pnum); //������ ����ִ� ������ ���� �Է��� �Ѿ���� ������ ���Ѵ�
			orderlists.put(pnum, oldoqty+oqty);
			
		}
		
	}

	public Map<Integer,Integer> getAllOrderLists(){
		return orderlists;
	}

	
}
