package fastjson;

import com.alibaba.fastjson.JSON;

public class FastJsonTools {
	
	public static String createJsonString(Object object) {
		String jsonString = "";
		jsonString = JSON.toJSONString(object);
		return jsonString;
	}
	
	

}
