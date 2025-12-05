package servlet;
import java.io.IOException;
//import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MemberDao;
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
//			int mno = (Integer)request.getAttribute("mno");
			
			if(memberDao.delete(Integer.parseInt(request.getParameter("mno")))>0) {
				request.setAttribute("ViewUrl", "redirect:list.do");
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
	}
}