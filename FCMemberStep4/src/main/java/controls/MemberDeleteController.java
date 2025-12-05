package controls;

import java.util.Map;

import annotation.Component;
import bind.DataBinding;
import dao.MemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {"mno", Integer.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		int mno = (Integer)model.get("mno");
		memberDao.delete(mno);
		return "redirect:list.do";
	}

}
