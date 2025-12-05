package controls;

import java.util.Map;

import bind.DataBinding;
import dao.MemberDao;
import dto.Member;

public class MemberAddController implements Controller, DataBinding {
MemberDao memberDao;
	
	public MemberAddController setMemberDao(MemberDao memberDao) {
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
		if(member.getEmail() == null ) {
			return "/member/MemberAdd.jsp";
		} else {
			// MemberDao memberDao = (MemberDao)model.get("memberDao");
			//Member member = (Member)model.get("member");
			memberDao.insert(member);
			
			return "redirect:list.do";
		}
	}

}
