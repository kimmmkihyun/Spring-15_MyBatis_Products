package mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import product.model.Product;

@Controller
public class CartAddController {  //ProductDetailView.jsp에서 주문 클릭 시

	final String command = "add.mall";
	final String gotoPage = "redirect:/list.mall";
	
	
	@RequestMapping(command)
	public String doAction(Product product,HttpSession session) {
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/detail.prd?num="+product.getNum());
			return "redirect:/loginForm.me";
		}
		else {
			//장바구니 하나에 계속 누적 시키기 위해서 session 사용
			MyCartList mycart = (MyCartList)session.getAttribute("mycart");
			
			if(mycart == null) {
				mycart = new MyCartList();  //장바구니 만들었다
			}
			
			mycart.addOrder(product.getNum(),product.getOrderqty());
			session.setAttribute("mycart", mycart);
			return gotoPage;
		}
		
	}
	
}
