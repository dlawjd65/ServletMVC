package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dto.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 로그인 컨트롤러 페이지 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginForm.jsp");
//		rd.include(request, response);
		request.setAttribute("ViewUrl", "/auth/LoginForm.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Member member = new Member();
//		member.setEmail(request.getParameter("email"));
//		member.setPwd(request.getParameter("pwd"));
		
//		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConn(conn);
			
    		MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
    		Member member = (Member)request.getAttribute("member");
			
			Member loginMember = memberDao.login(member);
			
			if(loginMember != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember);
//				response.sendRedirect("../member/list");
				request.setAttribute("ViewUrl", "redirect:../member/list.do");
			} else {
//				RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginFail.jsp");
//				rd.include(request, response);
				request.setAttribute("ViewUrl", "/auth/LoginFail.jsp");

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

}
