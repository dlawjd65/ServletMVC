package controls;

import java.util.Map;

import dao.MemberDao;
import dto.Member;

public class MemberAddController implements Controller {

	@Override
	public String excute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		if(model.get("member") == null ) {
			return "/member/MemberAdd.jsp";
		} else {
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			Member member = (Member)model.get("member");
			memberDao.insert(member);
			
			return "redirect:list.do";
		}
	}

}
