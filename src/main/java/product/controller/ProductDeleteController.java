package product.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductDeleteController {

	final String command = "delete.prd";
	final String gotoPage = "redirect:/list.prd";
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(command)
	public String doAction(@RequestParam(value="num",required=true) int num) {
		
		Product product = productDao.getOneProduct(num);
		System.out.println(product.getImage());
		
		String uploadPath = servletContext.getRealPath("/resources");
		System.out.println("uploadPath : "+uploadPath);
		//C:\SpringWorkspace_kkh\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\15_MyBatis_Products\resources\a.jpg

		File deFile = new File(uploadPath+ File.separator +product.getImage());
		deFile.delete();
		
		productDao.deleteProduct(num);		
		
		return gotoPage;
	}
	
}
