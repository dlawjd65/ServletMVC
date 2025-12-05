package controls;

import java.util.Map;

import bind.DataBinding;
import dao.MemberDao;

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
			// MemberDao memberDao = (MemberDao)model.get("memberDao");
			int mno = (Integer) model.get("mno");
			memberDao.delete(mno);
			return "redirect:list.do";
		
	}

}
