package mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mall.cart.ShoppingInfo;
import product.model.CompositeDao;

@Controller
public class DetailViewController {
	
	final String command="detailView.mall";
	final String getPage ="ShopResult";
	
	@Autowired
	private CompositeDao compositeDao;
	
	@RequestMapping(command)
	public String doAction(@RequestParam( value="oid" ) int oid,Model model) {
		
		//순번 상품명 수문수량 단가 금액  shoppinginfo
		List<ShoppingInfo> lists = compositeDao.showDetail(oid);
		model.addAttribute("oid", oid); //넘기기
		model.addAttribute("lists", lists); //넘기기
		return getPage;
		
	}
}
