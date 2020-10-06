package member.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.model.Member;
import member.model.MemberDao;

@Controller
public class MemberLoginController {

	final String command = "/loginForm.me";
	final String getPage = "MemberLoginForm";
	
	@Autowired
	MemberDao memberDao;
	
	// ProductInsertController에서 넘어옴
	@RequestMapping(value=command,method = RequestMethod.GET)
	public String doAction() {
		System.out.println(getClass()+" GET방식"); //MemberLoginController get방식
		return getPage;
	}
	
	//MemberLoginForm.jsp에서 post 방식으러  넘어옴
	@RequestMapping(value=command,method = RequestMethod.POST)
	public ModelAndView doAction(Member member, 
							HttpServletResponse response,
							HttpSession session) throws IOException {
		System.out.println(getClass()+" POST방식"); //MemberLoginController Post방식
		
		System.out.println("입력한 id : "+member.getId()); 
		System.out.println("입력한 password : "+member.getPassword());
		
		
		Member dbMember = memberDao.checkData(member.getId());
		PrintWriter pw = response.getWriter();	//웹브라우저에 출력
		response.setContentType("text/html; charset=UTF-8");
		//ModelAndView mav = new ModelAndView();
		if(dbMember == null) {
			System.out.println("존재하지 않는 회원");
			//pw.println("존재하지 않는 회원");
			
			pw.println("<script type='text/javascript'>");
			pw.println("alert('해당 아이디가 존재하지 않습니다.')");	
			pw.println("</script>");
			pw.flush();
			return new ModelAndView(getPage);
			
		}
		else {
			System.out.println("존재하는 회원");
			
			/*
			pw.println("<script type='text/javascript'>");
			pw.println("alert('존재하는 회원입니다.')");	
			pw.println("</script>");
			pw.flush();
			*/
			
			if(member.getPassword().equals(dbMember.getPassword())) {
				session.setAttribute("loginInfo", dbMember);
				
				//1.return (String)session.getAttribute("destination");
				//2.mav.setViewName((String)session.getAttribute("destination"));
				//return mav;
				return new ModelAndView((String)session.getAttribute("destination"));
			}
			else {
				System.out.println("아이디는 일치, 비번 불일치");
				pw.println("<script type='text/javascript'>");
				pw.println("alert('비번이 일치하지 않습니다.')");	
				pw.println("</script>");
				pw.flush();
				
				//return getPage	
				return new ModelAndView(getPage);
			}
			
		}
			
		
	}


}

