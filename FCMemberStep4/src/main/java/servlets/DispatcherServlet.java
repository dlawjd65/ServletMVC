package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bind.DataBinding;
import bind.ServletRequestDataBinder;
import context.ApplicationContext;
import controls.Controller;
import listeners.ContextLoaderListener;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		String servletPath = request.getServletPath();
		System.out.println("servletPath : "+servletPath);
		try {
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			
			ServletContext sc = this.getServletContext();
			model.put("session", request.getSession());
			
			Controller pageController = (Controller)ctx.getBean(servletPath);
			
			if(pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			String viewUrl = pageController.execute(model);
			System.out.println("viewUrl : "+viewUrl);
			
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
	
	private void prepareRequestData(HttpServletRequest request, 
					HashMap<String, Object> model, DataBinding dataBinding) throws Exception {
	    
		Object[] dataBinders = dataBinding.getDataBinders();
	    
		String dataName = null;
	    Class<?> dataType = null;
	    Object dataObj = null;
	    
	    for (int i = 0; i < dataBinders.length; i+=2) {
	      dataName = (String)dataBinders[i];
	      dataType = (Class<?>) dataBinders[i+1];
	      dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
	      model.put(dataName, dataObj);
	    }
	}

}
