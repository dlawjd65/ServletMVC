package controls;

import java.util.Map;

import dao.MemberDao;

public class MemberDeleteController implements Controller {

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			int mno = (Integer) model.get("mno");
			
			memberDao.delete(mno);
			
			return "redirect:../member/list.do";
		
	}

}
