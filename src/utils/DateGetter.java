package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGetter {
	
	public static String getDate(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
		String dateNowStr = sdf.format(d);  
        System.out.println("格式化后的日期：" + dateNowStr); 
        return dateNowStr;
	}
	
	public static String getTimeSample() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
        String tsStr = "";  
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
        try {  
            //方法一  
            tsStr = sdf.format(ts);  
            System.out.println(tsStr);  
//            //方法二  
//            tsStr = ts.toString();  
//            System.out.println(tsStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return tsStr;
	}
	
	public static String parsePubdate(String pubDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String standardDate = format.format(Date.parse(pubDate));
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss",Locale.US);
//		Date parse = sdf.parse("Wed, 30 Mar 2016 17:12:28 +0800");
		
		
		System.out.println(standardDate);
//		System.out.println(parse.toString());
		return standardDate;
	}
	
	public static String getIntegrateTime() {
		String str = new java.sql.Timestamp(System.currentTimeMillis()).toString();
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(parsePubdate("Wed, 30 Mar 2016 14:52:00 +0800"));
	}

}
