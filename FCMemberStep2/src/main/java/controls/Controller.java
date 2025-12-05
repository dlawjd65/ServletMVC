package controls;

import java.util.Map;

public interface Controller {
	String excute(Map<String, Object> model) throws Exception;
}
