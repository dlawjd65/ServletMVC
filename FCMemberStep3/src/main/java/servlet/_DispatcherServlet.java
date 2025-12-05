package servlet;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controls.Controller;
import dto.Member;
//@WebServlet("*.do")
public class _DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HashMap<String, Object> model = new HashMap<String, Object>();
		
		// 어디서 온 요청?
		String servletPath = request.getServletPath();
		System.out.println("servletPath ==> " + servletPath);
		try {
			ServletContext sc = request.getServletContext();
//			model.put("memberDao", sc.getAttribute("memberDao"));
			model.put("session", request.getSession());
			
			
			Controller pageController = (Controller)sc.getAttribute(servletPath);
			
			/* if ("/member/list.do".equals(servletPath)) {
//				pageController = new MemberListController();
			}
//=================================추가=================================
			else*/ if ("/member/add.do".equals(servletPath)) {
//				pageController = new MemberAddController();
				if (request.getParameter("email") != null) {
					Member member = new Member();
					member.setEmail(request.getParameter("email"));
					member.setMname(request.getParameter("mname"));
					member.setPwd(request.getParameter("pwd"));
					
					model.put("member", member);
				}
			}
//==================================수정==================================
			else if ("/member/update.do".equals(servletPath)) {
//				pageController = new MemberUpdateController();				
				if (request.getParameter("email") == null) {
					model.put("mno", Integer.parseInt(request.getParameter("mno")));
				} else {
					Member member = new Member();
					member.setMno(Integer.parseInt(request.getParameter("mno")));
					member.setEmail(request.getParameter("email"));
					member.setMname(request.getParameter("mname"));
					
					model.put("member", member);
				}
			}
//==================================삭제==================================
			else if ("/member/delete.do".equals(servletPath)) {
//				pageController = new MemberDeleteController();
				model.put("mno", Integer.parseInt(request.getParameter("mno")));
			}
//==================================로그인==================================
			else if ("/auth/login.do".equals(servletPath)) {
//				pageController = new LoginController();
				if(request.getParameter("email") != null) {
					Member member = new Member();
					member.setEmail(request.getParameter("email"));
					member.setPwd(request.getParameter("pwd"));
					
					model.put("member", member);				
				}
			}
//==================================로그아웃==================================
			/* else if ("/auth/logout.do".equals(servletPath)) {
				pageController = new LogoutController();
			}*/
			
			// view 위임
			String viewUrl = pageController.execute(model);
			System.out.println("viewUrl ==>" + viewUrl);
			
			//Controller의 작업 결과물을 view에 전달하기 위해 request에 보관함
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
				System.out.println("key==> "+ key);
			}
			
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}