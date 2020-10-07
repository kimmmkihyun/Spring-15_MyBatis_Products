package mall.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import member.model.Member;
import member.model.MemberDao;
import order.model.OrderDao;
import orderdetail.model.OrderDetail;
import orderdetail.model.OrderDetailDao;
import product.model.ProductDao;

@Controller 
public class CartCalculateController { //MallList 에서 결제하기 클릭했을 때

	final String command ="calculate.mall";
	final String gotoPage ="redirect:/list.prd";
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired 
	private OrderDao orderDao; 
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	
	@RequestMapping(value=command)
	public String doAction(HttpSession session) {  //장바구니 내용 가져와야 한다
		
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		Map<Integer,Integer> maplists = mycart.getAllOrderLists();
		Set<Integer> keylist = maplists.keySet();
		System.out.println("keylist : "+keylist);
		
		Member member = (Member)session.getAttribute("loginInfo");
		orderDao.insertData(member.getId());
		
		int maxOid = orderDao.getMaxOid();
		for(Integer pnum : keylist) {
			Integer qty = maplists.get(pnum);
			
			productDao.updateStock(pnum,qty);
			
			OrderDetail odBean = new OrderDetail();
			odBean.setOid(maxOid);
			odBean.setPnum(pnum);
			odBean.setQty(qty);
			
			orderDetailDao.insertData(odBean);
		
		} 
		
		memberDao.updatePoint(member.getId(),100);
		
		session.removeAttribute("mycart");
		return gotoPage;
	}
}
