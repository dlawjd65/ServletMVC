package servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MemberDao;
import dto.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberAdd.jsp");
//		rd.include(request, response);
		request.setAttribute("ViewUrl", "/member/MemberAdd.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String mname = request.getParameter("mname");
//		String email = request.getParameter("email");
//		String pwd = request.getParameter("pwd");
		
//		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConn(conn);
			
    		MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			Member member = (Member)request.getAttribute("member");
//			Member member = new Member();
//			member.setMname(mname);
//			member.setEmail(email);
//			member.setPwd(pwd);
			
			if(memberDao.insert(member) > 0) {
				// 성공 시 목록 페이지로 리다이렉트
//				response.sendRedirect("list");
				request.setAttribute("ViewUrl", "redirect:list.do");
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
}
