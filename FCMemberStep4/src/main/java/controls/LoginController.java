package controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import annotation.Component;
import bind.DataBinding;
import dao.MemberDao;
import dto.Member;

@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public LoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"member", dto.Member.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
		if(member.getEmail() == null) {
			return "/auth/LoginForm.jsp";
		} else {
			//MemberDao memberDao = (MemberDao)model.get("memberDao");
			//Member member = (Member)model.get("member");
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
