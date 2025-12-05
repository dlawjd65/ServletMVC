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

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int mno =  Integer.parseInt(request.getParameter("mno"));
		
//		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");
//			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConn(conn);
			

			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			int mno = (Integer)request.getAttribute("mno");
   			request.setAttribute("member", memberDao.selectOne(mno));
   			request.setAttribute("ViewUrl", "/member/MemberUpdate.jsp");
   			
   			//view 위임
//   			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdate.jsp");
//            rd.include(request, response);
   			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		String mname = request.getParameter("mname");
		String email = request.getParameter("email");
		
//		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConn(conn);
			
    		MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			Member member = new Member();
			member.setMno(mno);
			member.setMname(mname);
			member.setEmail(email);
			
			if(memberDao.update(member) > 0) {
				// 성공 시 목록 페이지로 리다이렉트
//				response.sendRedirect("list");
				request.setAttribute("ViewUrl", "redirect:../member/list.do");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException();
		} 
	}
}