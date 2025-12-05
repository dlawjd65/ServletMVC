package controls;

import java.util.Map;

import annotation.Component;
import bind.DataBinding;
import dao.MemberDao;
import dto.Member;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"mno", Integer.class, "member", dto.Member.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
		if(member.getEmail() == null) {
			int mno = (Integer)model.get("mno");
			model.put("member", memberDao.selectOne(mno));
			return "/member/MemberUpdate.jsp";
		} else {
			//Member member = (Member)model.get("member");
			memberDao.update(member);
			
			return "redirect:list.do";
		}
	}
}
