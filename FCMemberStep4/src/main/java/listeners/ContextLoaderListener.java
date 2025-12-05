package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import context.ApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  static ApplicationContext applicationContext;
  
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
   
  @Override
  public void contextInitialized(ServletContextEvent event) {
	  try {
	      ServletContext sc = event.getServletContext();
	      
	      String propertiesPath = sc.getRealPath(
	          sc.getInitParameter("contextConfigLocation"));
	      applicationContext = new ApplicationContext(propertiesPath);
	      // propertiesPath을 getInitParameter로 받아오는 코드
	      // contextConfigLocation=context가 잇는 위치정보
	      
	    } catch(Throwable e) {
	      e.printStackTrace();
	    }
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent event) {}
}
