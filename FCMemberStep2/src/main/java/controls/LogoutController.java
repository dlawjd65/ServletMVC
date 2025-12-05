package controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = (HttpSession)model.get("session");
		session.invalidate();
		return "redirect:login.do";
	}

}
