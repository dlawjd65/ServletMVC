package controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dto.Member;

public class LoginController implements Controller {

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		if(model.get("member") == null ) {
			// 로그인 성공
			return "/auth/LoginForm.jsp";
		} else {
			// 로그인 실패
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			Member member = (Member)model.get("member");
			Member loginMember = memberDao.login(member);
			
			if(loginMember != null) {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("loginMember", loginMember);
				return "redirect:../member/list.do";
			} else {
				return "/auth/LoginFail.jsp";
			}
			
		}

	}

}
