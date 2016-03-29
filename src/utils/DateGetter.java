package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGetter {
	
	public static String getDate(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String dateNowStr = sdf.format(d);  
        System.out.println("格式化后的日期：" + dateNowStr); 
        return dateNowStr;
	}
	
	public static void main(String[] args) {
		getDate();
	}

}
