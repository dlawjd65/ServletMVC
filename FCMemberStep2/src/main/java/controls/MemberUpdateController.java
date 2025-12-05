package controls;

import java.util.Map;

import dao.MemberDao;
import dto.Member;

public class MemberUpdateController implements Controller {

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		
		if(model.get("member") == null ) {
			int mno = (Integer)model.get("mno");
			model.put("member", memberDao.selectOne(mno));
			return "/member/MemberUpdate.jsp";
		} else {
			Member member = (Member)model.get("member");
			memberDao.update(member);
			
			return "redirect:../member/list.do";
		}
	}

}
