package servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;


@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    
       

   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
//   		Connection conn = null;
   		
   		try {
   			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");
//   		MemberDao memberDao = new MemberDao();
//   		memberDao.setConn(conn);
   			
    		MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
   			request.setAttribute("members", memberDao.selectList());
   			
   			// 뷰 위임하기
//   			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
//   			rd.include(request, response);
   			request.setAttribute("ViewUrl", "/member/MemberList.jsp");
   			
   		} catch (Exception e) {
   			e.printStackTrace();
   			throw new ServletException();
   		} 
   	}
}
