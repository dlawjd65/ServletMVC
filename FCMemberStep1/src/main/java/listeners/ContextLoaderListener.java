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
//	Connection conn;

    public ContextLoaderListener() {
        // 생성자, 리턴타입 없음, 클래스가 객체가 될 때 초기화
    	// 역할 추가할 거 아니면 지워도 ㄱㅊ
    }
    
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("ContextLoaderListenr init()......");
    	
    	try {
//			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "mysql");
    		
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
