package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductDetailController {

	final String command ="detail.prd";
	final String getPage = "ProductDetailView";
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(command)
	public ModelAndView doAction( @RequestParam(value="num") int num ) {
		
		Product bean = productDao.getOneProduct(num);
		
		ModelAndView mav = new  ModelAndView();
		mav.addObject("bean", bean);
		mav.setViewName(getPage);
		return mav;
	}
}
