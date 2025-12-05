package servlet;

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
import controls.Controller;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		String servletPath = request.getServletPath();
		System.out.println("servletPath : "+servletPath);
		try {
			ServletContext sc = this.getServletContext();
			model.put("session", request.getSession());
			
			Controller pageController = (Controller)sc.getAttribute(servletPath);
			
			if(pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
				// instanceof는 연산자 pageController는 DataBinding의 자식이냐?
				// 그럼 Object는 pageController의 조상? = ㅇㅇ 
				// DataBinding이 인터페이스 구현 했냐->그럼prepareRequestData ㄱㄱ
				// 안 했냐->execute로 감
			}
			
			String ViewUrl = pageController.execute(model);
			System.out.println("ViewUrl : "+ViewUrl);
			
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if(ViewUrl.startsWith("redirect:")) {
				response.sendRedirect(ViewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(ViewUrl);
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
