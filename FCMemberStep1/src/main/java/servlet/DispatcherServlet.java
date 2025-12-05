package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dto.Member;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 어디서 오는 요청인지 
		String servletPath = request.getServletPath();
		System.out.println("servletPath ===> " + servletPath);
		
		try {
			String pageController = null;
			
			if("/member/list.do".equals(servletPath)) {
				pageController = "/member/list";
			} else if("/member/add.do".equals(servletPath)) {
				pageController = "/member/add";
				if(request.getParameter("email") != null) {
					Member member = new Member();
					member.setEmail(request.getParameter("email"));
					member.setMname(request.getParameter("mname"));
					member.setPwd(request.getParameter("pwd"));
					
					request.setAttribute("member", member);
				} 
			} else if("/member/update.do".equals(servletPath)) {
				pageController = "/member/update";
				if(request.getParameter("email") == null) {
					request.setAttribute("mno", Integer.parseInt(request.getParameter("mno")));
				} else {
					Member member = new Member();
					member.setMno(Integer.parseInt(request.getParameter("mno")));
					member.setEmail(request.getParameter("email"));
					member.setMname(request.getParameter("mname"));
					
					request.setAttribute("member", member);
				}
			} else if("/member/delete.do".equals(servletPath)) {
				pageController = "/member/delete";
				request.setAttribute("mno", Integer.parseInt(request.getParameter("mno")));
				
			} else if("/auth/login.do".equals(servletPath)) {
				pageController = "/auth/login";
				if(request.getParameter("email") != null) {
					Member member = new Member();
					member.setEmail(request.getParameter("email"));
					member.setPwd(request.getParameter("pwd"));
					
					request.setAttribute("member", member);
				}
			} else if("/auth/logout.do".equals(servletPath)) {
				pageController = "/auth/logout";
			}
			
//			요청 처리할 해당 서블릿 위임
			RequestDispatcher rd = request.getRequestDispatcher(pageController);
			rd.include(request, response);
			
//			view 위임
			String ViewUrl = (String)request.getAttribute("ViewUrl");
			System.out.println("ViewUrl ===> " + ViewUrl);
			
			if(ViewUrl.startsWith("redirect:")) {
				response.sendRedirect(ViewUrl.substring(9));
			} else {
				rd = request.getRequestDispatcher(ViewUrl);
				rd.include(request, response);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
