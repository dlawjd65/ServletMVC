package listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("ContextLoaderListenr init()......");
    	
    	try {
    		
    		InitialContext ic = new InitialContext();
    		DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/web");
    		
    		MemberDao memberDao = new MemberDao();
    		memberDao.setDataSource(ds);
			
			ServletContext sc = sce.getServletContext();
			sc.setAttribute("memberDao", memberDao);
		} catch (Exception e) {
			e.printStackTrace();
		} 
   	
   }

    public void contextDestroyed(ServletContextEvent sce)  { 

    }
	
}
