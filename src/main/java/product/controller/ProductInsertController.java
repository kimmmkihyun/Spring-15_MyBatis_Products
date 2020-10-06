package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import product.model.Product;
import product.model.ProductDao;

@Controller
public class ProductInsertController {
	final String command = "/insert.prd";
	final String getPage = "ProductInsertForm";
	final String gotoPage = "redirect:/list.prd";
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	ServletContext servletContext;   //�̹� ������� �ִ� ��ü�� servletContext�� �����ϱ� ���ؼ�
	
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String doAction(HttpSession session) {
		
		System.out.println("loginInfo : " + session.getAttribute("loginInfo")); // null
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/insert.prd");
			return "redirect:/loginForm.me";
		}
		else {
			return getPage;
		}
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView doAction(@Valid Product product,BindingResult result ) {
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			System.out.println("���� �߻�");
			mav.setViewName(getPage);
			return mav;
			//return new ModelAndView(getPage);
		}
		
		MultipartFile multi = product.getUpload();  //interface �̱� ������ ��ü�� �������� ���Ѵ�.
		
		// request.getContextPath();  ex/ 
		System.out.println("servletContext.getRealPath('/') : " + servletContext.getRealPath("/"));
		//C:\SpringWorkspace_kkh\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\15_MyBatis_Products\
	
		String uploadPath = servletContext.getRealPath("/resources");
		System.out.println("uploadPath : "+uploadPath);
		//C:\SpringWorkspace_kkh\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\15_MyBatis_Products\resources\a.jpg
		
		System.out.println("������ ȭ�ϸ� : " +product.getImage() );
		File file = new File(uploadPath+ File.separator +product.getImage()); //resources �ȿ� ������.
		
		try {
			multi.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		productDao.insertProduct(product);
		
		mav.setViewName(gotoPage);
		return mav;
	}
	
	
	
	
	
	
	
}

