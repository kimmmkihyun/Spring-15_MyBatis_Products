package mall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import mall.cart.ShoppingInfo;
import product.model.Product;
import product.model.ProductDao;

@Controller
public class CartListController {  //CartAddController에서 넘어옴

	final String command = "list.mall";
	final String getPage = "MallList";
	
	@Autowired
	private ProductDao productDao;
	
	
	@RequestMapping(value=command)
	public String doAction(HttpSession session) {
		
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		Map<Integer,Integer> maplists = mycart.getAllOrderLists(); 
		System.out.println("장바구니 상품 수 :" +maplists.size());
		
		Set<Integer> keylist = maplists.keySet();
		System.out.println("keylists : "+keylist);
		
		List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>();
		int totalAmount = 0;
		for( Integer pnum : keylist) {
			Integer oqty = maplists.get(pnum); //장바구니에 있는 번호의 수량을 담는작업
			System.out.println(pnum + ":" + oqty);
			
			Product bean = productDao.getOneProduct(pnum);
			
			ShoppingInfo shopInfo = new ShoppingInfo();			
			shopInfo.setPnum(pnum);
			shopInfo.setPname(bean.getName());
			shopInfo.setQty(oqty);
			shopInfo.setPrice(bean.getPrice());
			shopInfo.setAmount(bean.getPrice() * oqty);
			
			totalAmount += bean.getPrice() * oqty;
			shoplists.add(shopInfo);
		}
		
		session.setAttribute("totalAmount", totalAmount);
		session.setAttribute("shoplists",shoplists );
		return getPage; //MallList.jsp에서  필요한 5가지 : 상품번호,상품명,주문수량,단가,금액
		
	}
	
}
