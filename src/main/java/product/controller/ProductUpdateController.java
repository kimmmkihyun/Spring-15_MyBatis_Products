package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductUpdateController {

	final String command = "update.prd";
	final String getPage = "ProductUpdateForm";
	final String gotoPage = "redirect:/list.prd";

	@Autowired
	private ProductDao productDao;

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String doAction(@RequestParam(value="num",required = true) int num,
							Model model,HttpSession session) {
		
		System.out.println("session.getAttribute('loginInfo') : " + session.getAttribute("loginInfo"));
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/update.prd?num="+num);
			return "redirect:/loginForm.me";
			
		}
			
		Product product = productDao.getOneProduct(num);
		model.addAttribute("product", product);
		
		return getPage;
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView doAction(@Valid Product product,BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
				
		
		if(result.hasErrors()) {
			System.out.println("update 오류 발생");
			mav.setViewName(getPage);
			return mav;
		}
		String uploadPath = servletContext.getRealPath("/resources");
		System.out.println("uploadPath : "+uploadPath);
		//C:\SpringWorkspace_kkh\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\15_MyBatis_Products\resources\a.jpg
		System.out.println("새로 선택한 화일명 : "+product.getImage());
		File file = new File(uploadPath+ File.separator +product.getImage());
		
		File deFile = new File(uploadPath+ File.separator +product.getUploadOld());
		deFile.delete();
		
		MultipartFile multi = product.getUpload();
		try {
			multi.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cnt = productDao.updateProduct(product);
		
		if(cnt>0) {
		mav.setViewName(gotoPage);
		}
		else {
		mav.setViewName(getPage);	
		}
		
		return mav;
	}
	

}
