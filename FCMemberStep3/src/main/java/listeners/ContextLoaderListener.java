package listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import controls.LoginController;
import controls.LogoutController;
import controls.MemberAddController;
import controls.MemberDeleteController;
import controls.MemberListController;
import controls.MemberUpdateController;
import dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    
    public void contextInitialized(ServletContextEvent sce)  { 
    	//ServletContext sc = sce.getServletContext();
    	System.out.println("ContextLoaderListenr init()......");
    	
    	try {
    		
    		InitialContext ic = new InitialContext();
    		DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/web");
    		
    		MemberDao memberDao = new MemberDao();
    		memberDao.setDataSource(ds);
			
			ServletContext sc = sce.getServletContext();
			// sc.setAttribute("memberDao", memberDao);
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
			sc.setAttribute("/auth/login.do", new LoginController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogoutController());

		} catch (Exception e) {
			e.printStackTrace();
		} 
   	
   }

    public void contextDestroyed(ServletContextEvent sce)  { 

    }
	
}
