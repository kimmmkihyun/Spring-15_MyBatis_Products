package member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.model.Member;
import member.model.MemberDao;


@Controller
public class MemberRegisterController {
	final String command = "registerForm.me";
	final String getPage = "MemberRegisterForm";
	final String gotoPage = "redirect:/list.me";
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping(value=command,method = RequestMethod.GET) // MemberLoginForm.jsp에서 넘어옴
	public String doAction() {
		
		return getPage;
	}
	
	@RequestMapping(value=command,method = RequestMethod.POST) // MemberLoginForm.jsp에서 넘어옴
	public ModelAndView doAction(@Valid Member member, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			System.out.println("유효성 검사 오류 발생");
			//return getPage;
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = memberDao.insertMember(member);
		System.out.println("Register insert cnt  :  " +cnt);
		mav.setViewName(gotoPage);// "redirect:/list.me"
		return mav;
	
	}
	
	
	
}
